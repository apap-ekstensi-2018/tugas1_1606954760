package com.tugas.apap.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tugas.apap.dao.FakultasMapper;
import com.tugas.apap.model.FakultasModel;

public class FakultasServiceDatabase implements FakultasService{
	@Autowired
    private FakultasMapper fakultasMapper;
	
	@Override
	public FakultasModel selectFakultasById(int id) {
		return fakultasMapper.selectFakultasById(id);
	}

}
