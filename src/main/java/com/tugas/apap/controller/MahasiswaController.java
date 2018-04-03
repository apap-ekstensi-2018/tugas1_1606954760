package com.tugas.apap.controller;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tugas.apap.model.MahasiswaModel;
import com.tugas.apap.service.MahasiswaService;
import com.tugas.apap.service.ProgramStudiService;
import com.tugas.apap.utilities.DateUtil;
import com.tugas.apap.utilities.MahasiswaUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MahasiswaController {
	
	@Autowired
    MahasiswaService mahasiswaDAO;
	
	@Autowired
	ProgramStudiService psDAO;
	
	@RequestMapping("/")
    public String index (){
        return "index";
    }
	
	@RequestMapping("/mahasiswa")
    public String view (
    		Model model,
            @RequestParam(value = "npm", required = false) String npm){
		MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa(npm);
		
		//Convert date
		mahasiswa.setTanggal_lahir(DateUtil.convertDate(mahasiswa.getTanggal_lahir()));
		
        model.addAttribute ("mahasiswa", mahasiswa);
        return "view";
    }
	
	@RequestMapping("/mahasiswa/tambah")
    public String add (Model model){
		model.addAttribute ("prodis", psDAO.selectAllProgramStudis());
        return "form-add-mahasiswa";
    }
	
	@RequestMapping(value = "/mahasiswa/tambah", method = RequestMethod.POST)
    public String addSubmit (
    		 @RequestParam(value = "nama", required = false) String nama,
         @RequestParam(value = "tempat_lahir", required = false) String tempat_lahir,
         @RequestParam(value = "tanggal_lahir", required = false) String tanggal_lahir,
         @RequestParam(value = "jenis_kelamin", required = false) String jenis_kelamin,
         @RequestParam(value = "agama", required = false) String agama,
         @RequestParam(value = "golongan_darah", required = false) String golongan_darah,
         @RequestParam(value = "status", required = false) String status,
         @RequestParam(value = "tahun_masuk", required = false) String tahun_masuk,
         @RequestParam(value = "jalur_masuk", required = false) String jalur_masuk,
         @RequestParam(value = "id_prodi", required = false) String id_prodi
        )
    {
    		MahasiswaModel mahasiswaValid = new MahasiswaModel(
    				"1111111", 
    				nama, 
    				tempat_lahir, 
    				tanggal_lahir, 
    				Integer.valueOf(jenis_kelamin), 
    				agama, 
    				golongan_darah, 
    				status, 
    				tahun_masuk, 
    				jalur_masuk, 
    				Integer.valueOf(id_prodi));
    		
    		//mahasiswaValid.setNpm(MahasiswaUtil.generateNPM(mahasiswaValid));
    		
    		mahasiswaDAO.addMahasiswa(mahasiswaValid);
	    return "success-add";
    		
    }
	
	@RequestMapping("/mahasiswa/ubah/{npm}")
    public String edit (Model model, @PathVariable(value = "npm") String npm
    		)
    {
		MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa(npm);
		model.addAttribute ("prodis", psDAO.selectAllProgramStudis());
        model.addAttribute("mahasiswa", mahasiswa);
        model.addAttribute("kode_jalur_masuk", 
        		MahasiswaUtil.getJalurMasuk(mahasiswa.getJalur_masuk()));
		return "form-update-mahasiswa";
    }
	
	
	@RequestMapping(value = "/mahasiswa/ubah", method = RequestMethod.POST)
    public String updateSubmit (@ModelAttribute("mahasiswa") MahasiswaModel mahasiswa, ModelMap model)
    {
    		MahasiswaModel mahasiswaValid = mahasiswaDAO.selectMahasiswa(mahasiswa.getNpm());
    		mahasiswaDAO.updateMahasiswa(mahasiswaValid);
	    return "success-update";
    }
	
	@RequestMapping("/mahasiswa/kelulusan")
    public String graduate (Model model){
        return "form-graduated";
    }
	
	@RequestMapping(value = "/mahasiswa/kelulusan", method = RequestMethod.GET)
    public String graduateCalculate (
    		Model model,
    		 @RequestParam(value = "tahun", required = false) String tahun,
         @RequestParam(value = "prodi", required = false) String id_prodi
        ) {
		
		double totalMahasiswaLulus = mahasiswaDAO.
				selecTotalMahasiswaByProgStudiIdAndStatus(Integer.valueOf(id_prodi), tahun, "Lulus");
		double totalMahasiswa = mahasiswaDAO.
				selecTotalMahasiswaByProgStudiId(Integer.valueOf(id_prodi), tahun);
		
		double resultPercent = (totalMahasiswaLulus/totalMahasiswa)*100;
		DecimalFormat formatResult = new DecimalFormat("##.00");
		
		model.addAttribute ("prodis", psDAO.selectAllProgramStudis());
		model.addAttribute("result", formatResult.format(resultPercent));
		model.addAttribute("tahun", tahun);
		model.addAttribute("prodi", id_prodi);
		model.addAttribute("fakultas", 
				psDAO.selectFakultasById(
							psDAO.selectProgramStudiById(Integer.valueOf(id_prodi)).getId_fakultas()
						)
				);
		
		return "form-graduated";
	}
	
	
}
