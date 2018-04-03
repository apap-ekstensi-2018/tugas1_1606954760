package com.tugas.apap.service;

import java.util.List;

import com.tugas.apap.model.MahasiswaModel;

public interface MahasiswaService {
	MahasiswaModel selectMahasiswa (String npm);
	
	List<MahasiswaModel> selectAllMahasiswas ();
	
	boolean addMahasiswa (MahasiswaModel student);
	
	boolean updateMahasiswa (MahasiswaModel mahasiswa);
	
	int selectMahasiswaUrutan(String tahun_masuk, int id_prodi, String jalur_masuk);

	int selecTotalMahasiswaByProgStudiId(int id_prodi, String tahun_masuk);
	
	int selecTotalMahasiswaByProgStudiIdAndStatus(int id_prodi, String tahun_masuk, String status);

	
}
