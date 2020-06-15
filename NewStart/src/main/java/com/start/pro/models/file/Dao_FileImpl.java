package com.start.pro.models.file;

<<<<<<< HEAD
import java.util.List;
=======
import java.util.Map;

import javax.annotation.Resource;
>>>>>>> branch 'master' of https://github.com/LeeMQ-programmer/NewStart.git

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.start.pro.dto.DTO_File;

@Repository
public class Dao_FileImpl implements IDao_File {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.start.pro.File.";
	@Autowired
	private SqlSessionTemplate session;
	
	@Resource
	private FileUtils fileUtils;
	
	@Override
	public boolean insertFile(DTO_File dto) {
		log.info("DAO@@@@@@@@@@@@@@@@@insertFile,{}",dto);
		int isc = session.insert(NS+"insertFile",dto);
		return isc>0?true:false;
	}

	@Override
	public List<DTO_File> searchFile(DTO_File dto) {
		log.info("DAO@@@@@@@@@@@@@@@@@searchFile,{}",dto);
		
		return session.selectList(NS+"searchFile",dto);
	}

	@Override
	public boolean delFile(DTO_File dto) {
		log.info("DAO@@@@@@@@@@@@@@@@@delFile,{}",dto);
		int isc = session.update(NS+"delFile",dto);
		return isc>0?true:false;
	}

<<<<<<< HEAD
	@Override
	public DTO_File getDown(String seq) {
		return session.selectOne(NS+"getDown", seq);
	}
=======
	
>>>>>>> branch 'master' of https://github.com/LeeMQ-programmer/NewStart.git


	
}
