package com.start.pro.models.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_File;

@Service
public class Service_FileImpl implements IService_File{

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IDao_File dao;
	
	@Override
	public boolean insertFile(DTO_File dto) {
		log.info("Service@@@@@@@@@@@insertFile,{}",dto);
		return dao.insertFile(dto);
	}

	@Override
	public DTO_File searchFile(DTO_File dto) {
		log.info("Service@@@@@@@@@@@searchFile,{}",dto);
		return dao.searchFile(dto);
	}

	@Override
	public boolean delFile(DTO_File dto) {
		log.info("Service@@@@@@@@@@@delFile,{}",dto);
		return dao.delFile(dto);
	}



}
