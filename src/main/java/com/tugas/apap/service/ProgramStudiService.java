package com.tugas.apap.service;

import java.util.List;

import com.tugas.apap.model.FakultasModel;
import com.tugas.apap.model.ProgramStudiModel;


public interface ProgramStudiService {
	List<ProgramStudiModel> selectAllProgramStudis();
	ProgramStudiModel selectProgramStudiById(int id);
	FakultasModel selectFakultasById(int id);
}
