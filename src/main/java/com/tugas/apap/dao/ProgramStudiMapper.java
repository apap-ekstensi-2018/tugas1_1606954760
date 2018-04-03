package com.tugas.apap.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tugas.apap.model.MahasiswaModel;
import com.tugas.apap.model.ProgramStudiModel;

@Mapper
public interface ProgramStudiMapper {
	@Select("select * from program_studi" )
	List<ProgramStudiModel> selectAllProgramStudis();
	
	@Select("select * from program_studi "
			+ "where id = #{id}" )
	ProgramStudiModel selectProgramStudiById(@Param("id") int id);
}
