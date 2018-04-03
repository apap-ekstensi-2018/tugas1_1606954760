package com.tugas.apap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugas.apap.dao.FakultasMapper;
import com.tugas.apap.dao.ProgramStudiMapper;
import com.tugas.apap.model.FakultasModel;
import com.tugas.apap.model.ProgramStudiModel;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ProgramStudiServiceDatabase implements ProgramStudiService, FakultasService{
	@Autowired
    private ProgramStudiMapper programStudiMapper;
	
	@Autowired
    private FakultasMapper fakultasMapper;
    
    public ProgramStudiServiceDatabase(){
    }
    
    public ProgramStudiServiceDatabase(ProgramStudiMapper programStudiMapper){
    		this.programStudiMapper = programStudiMapper;
    }

	@Override
	public List<ProgramStudiModel> selectAllProgramStudis() {
		return programStudiMapper.selectAllProgramStudis();
	}

	@Override
	public ProgramStudiModel selectProgramStudiById(int id) {
		return programStudiMapper.selectProgramStudiById(id);
	}
	
	@Override
	public FakultasModel selectFakultasById(int id) {
		return fakultasMapper.selectFakultasById(id);
	}

}
