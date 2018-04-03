package com.tugas.apap.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tugas.apap.model.FakultasModel;


@Mapper
public interface FakultasMapper {
	@Select("select * from fakultas where id = #{id} ")
    FakultasModel selectFakultasById (@Param("id") int id);
}
