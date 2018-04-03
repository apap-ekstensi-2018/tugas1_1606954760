package com.tugas.apap.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tugas.apap.model.MahasiswaModel;

@Mapper
public interface MahasiswaMapper {
	@Select("select * from mahasiswa where npm = #{npm} ")
    MahasiswaModel selectMahasiswa (@Param("npm") String npm);
	
	@Select("select * from mahasiswa" )
	List<MahasiswaModel> selectAllMahasiswas ();
	
	@Insert("INSERT INTO mahasiswa ("
			
			+ "npm, nama, tempat_lahir, tanggal_lahir,"
			+ "jenis_kelamin, agama, golongan_darah, "
			+ "status, tahun_masuk, "
			+ "jalur_masuk, id_prodi) "
			
			+ "VALUES (#{npm}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, "
			+ "#{jenis_kelamin}, #{agama}, #{golongan_darah}, "
			+ "#{status}, #{tahun_masuk}, #{jalur_masuk}, #{id_prodi})")
    boolean addMahasiswa (MahasiswaModel mahasiswa);
	
	@Select("SELECT count(*) FROM mahasiswa m, program_studi ps, fakultas f, universitas u ")
	int selectMahasiswaUrutan(String tahun_masuk, int kode_prodi, String jalur_masuk);
	
	@Update("UPDATE mahasiswa "
			+ "SET "
			
			+ "nama = #{nama},"
			+ "tempat_lahir = #{tempat_lahir},"
			+ "tanggal_lahir = #{tanggal_lahir},"
			+ "jenis_kelamin = #{jenis_kelamin},"
			+ "agama = #{agama},"
			+ "golongan_darah = #{golongan_darah},"
			+ "status = #{status},"
			+ "tahun_masuk = #{tahun_masuk},"
			+ "jalur_masuk = #{jalur_masuk},"
			+ "id_prodi = #{id_prodi},"
			
			+ "npm, nama, tempat_lahir, tanggal_lahir,"
			+ "jenis_kelamin, agama, golongan_darah, "
			+ "status, tahun_masuk, "
			+ "jalur_masuk, id_prodi) "
			
			+ "WHERE npm = #{npm}")
    boolean updateMahasiswa (MahasiswaModel mahasiswa);
	
	
	@Select("SELECT count(*) FROM mahasiswa m, program_studi ps "
			+ "where m.id_prodi = ps.id "
			+ "and ps.id = #{id_prodi} "
			+ "and m.tahun_masuk = #{tahun_masuk} ")
	int selecTotalMahasiswaByProgStudiId(@Param("id_prodi") int id_prodi, @Param("tahun_masuk") String tahun_masuk);
	
	@Select("SELECT count(*) FROM mahasiswa m, program_studi ps "
			+ "where m.id_prodi = ps.id "
			+ "and m.status = #{status} "
			+ "and m.id_prodi = #{id_prodi} "
			+ "and m.tahun_masuk = #{tahun_masuk} ")
	int selecTotalMahasiswaByProgStudiIdAndStatus(@Param("id_prodi") int id_prodi, @Param("tahun_masuk") String tahun_masuk,@Param("status") String status);
	

}
