package com.start.pro.models.file;

<<<<<<< HEAD
import java.util.List;
=======
import javax.annotation.Resource;
>>>>>>> branch 'master' of https://github.com/LeeMQ-programmer/NewStart.git

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_File;

@Service
public class Service_FileImpl implements IService_File {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IDao_File dao;
	@Resource
	private FileUtils fileUtils;

	@Override
	public boolean insertFile(DTO_File dto) {
		log.info("Service@@@@@@@@@@@insertFile,{}", dto);
		
		return dao.insertFile(dto);
	}

	@Override
<<<<<<< HEAD
	public List<DTO_File> searchFile(DTO_File dto) {
		log.info("Service@@@@@@@@@@@searchFile,{}",dto);
=======
	public DTO_File searchFile(DTO_File dto) {
		log.info("Service@@@@@@@@@@@searchFile,{}", dto);
>>>>>>> branch 'master' of https://github.com/LeeMQ-programmer/NewStart.git
		return dao.searchFile(dto);
	}

	@Override
	public boolean delFile(DTO_File dto) {
		log.info("Service@@@@@@@@@@@delFile,{}", dto);
		return dao.delFile(dto);
	}

<<<<<<< HEAD
	@Override
	public DTO_File getDown(String seq) {
		return dao.getDown(seq);
	}


=======
	
	
>>>>>>> branch 'master' of https://github.com/LeeMQ-programmer/NewStart.git

}
