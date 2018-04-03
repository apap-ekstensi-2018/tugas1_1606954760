package com.tugas.apap.utilities;

import java.util.HashMap;
import java.util.Map;

import com.tugas.apap.controller.MahasiswaController;
import com.tugas.apap.model.MahasiswaModel;
import com.tugas.apap.service.MahasiswaService;
import com.tugas.apap.service.MahasiswaServiceDatabase;
import com.tugas.apap.service.ProgramStudiService;
import com.tugas.apap.service.ProgramStudiServiceDatabase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MahasiswaUtil {
	public static final Map<String, String> JALUR_MASUK = new HashMap<String, String>(){{{
		put("53", "Undangan Olimpiade");
		put("54", "Undangan Reguler/SNMPTN");
		put("55", "Undangan Paralel/PPKB");
		put("57", "Ujian Tulis Bersama/SBMPTN");
		put("62", "Ujian Tulis Mandiri");
	}}};
	/**
	 * NPM terdiri dari 12 digit.
	 * 2 digit awal merupakan 2 digit akhir dari tahun masuk mahasiswa.
	 * 3 digit setelahnya merupakan kode Universitas, dan 
	 * 2 digit setelahnya merupakan kode Program Studi.
	 * 2 digit setelahnya merupakan jalur masuk mahasiswa.
	 * 3 digit terakhir adalah nomor berdasarkan urutan input data
	 * @param mhs
	 * @return
	 */
	public static String generateNPM(MahasiswaModel mhs) {
		String npmResult = "";
		
		//2 digit awal merupakan 2 digit akhir dari tahun masuk mahasiswa.
		String twoDigitLastYear = 
				mhs.getTahun_masuk().length() > 2 ? mhs.getTahun_masuk().substring(mhs.getTahun_masuk().length() - 2) : mhs.getTahun_masuk();
		
				
				
		//2 digit setelahnya merupakan jalur masuk mahasiswa.
		MahasiswaService mhsDb = new MahasiswaServiceDatabase();
		ProgramStudiService pdDB = new ProgramStudiServiceDatabase();
		
		log.info("BEWE getTahun_masuk : " + mhs.getTahun_masuk());
		log.info("BEWE getId_prodi : " + mhs.getId_prodi());
		log.info("BEWE getIdgetJalur_masuk_prodi : " + mhs.getJalur_masuk());
		
		
		int orderEntered = mhsDb.selectMahasiswaUrutan(
				mhs.getTahun_masuk(), 
				mhs.getId_prodi(),
				mhs.getJalur_masuk());
		
		log.info("BEWE orderEntered : " + orderEntered);
		
		npmResult = twoDigitLastYear ;
		
		return npmResult;
		
	}
	
	public static String getJalurMasuk(String jalurMasuk) {
		String kodeJalurMasuk = "";
		for(String kode : JALUR_MASUK.keySet()) {
			if(JALUR_MASUK.get(kode).toLowerCase().equals(jalurMasuk.trim().toLowerCase())) {
				kodeJalurMasuk = kode;
				break;
			}
		}
		
		return kodeJalurMasuk;
		
	}

}
