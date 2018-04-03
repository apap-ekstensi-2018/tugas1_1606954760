package com.tugas.apap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugas.apap.dao.MahasiswaMapper;
import com.tugas.apap.model.MahasiswaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MahasiswaServiceDatabase implements MahasiswaService
{
    @Autowired
    private MahasiswaMapper mahasiswaMapper;
    
    public MahasiswaServiceDatabase(){
    }
    
    public MahasiswaServiceDatabase(MahasiswaMapper mahasiswaMapper){
    		this.mahasiswaMapper = mahasiswaMapper;
    }


    @Override
    public MahasiswaModel selectMahasiswa (String npm)
    {
        log.info ("select student with npm {}", npm);
        return mahasiswaMapper.selectMahasiswa (npm);
    }


    @Override
    public List<MahasiswaModel> selectAllMahasiswas()
    {
        log.info ("select all students");
        return mahasiswaMapper.selectAllMahasiswas ();
    }
    
    @Override
    public boolean addMahasiswa (MahasiswaModel mahasiswa)
    {
    		log.info ("Mahasiswa " + mahasiswa.getNpm() + " added");
        return mahasiswaMapper.addMahasiswa(mahasiswa);
    }
    
    @Override
    public boolean updateMahasiswa (MahasiswaModel mahasiswa)
    {
    		log.info ("Mahasiswa " + mahasiswa.getNpm() + " added");
        return mahasiswaMapper.addMahasiswa(mahasiswa);
    }

	@Override
	public int selectMahasiswaUrutan(String tahun_masuk, int id_prodi, String jalur_masuk) {
		return mahasiswaMapper.selectMahasiswaUrutan(tahun_masuk, id_prodi, jalur_masuk);
	}

	@Override
	public int selecTotalMahasiswaByProgStudiId(int id_prodi, String tahun_masuk) {
		return mahasiswaMapper.selecTotalMahasiswaByProgStudiId(id_prodi, tahun_masuk);
	}

	@Override
	public int selecTotalMahasiswaByProgStudiIdAndStatus(int id_prodi, String tahun_masuk, String status) {
		return mahasiswaMapper.selecTotalMahasiswaByProgStudiIdAndStatus(id_prodi, tahun_masuk, status);
	}



}
