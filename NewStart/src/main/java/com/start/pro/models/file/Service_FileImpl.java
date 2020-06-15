package com.start.pro.models.file;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.start.pro.dto.DTO_File;

@Service
public class Service_FileImpl implements IService_File {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IDao_File dao;
	@Resource
	private FileUtils fileUtils;

	@Override
	public boolean insertFile(DTO_File dto, MultipartHttpServletRequest mpreq) {
		log.info("Service@@@@@@@@@@@insertFile,{}", dto);
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(boardVO, mpRequest);
		int size = list.size();
		for (int i = 0; i < size; i++) {
			dao.insertFile(list.get(i));
		}
		return dao.insertFile(dto, mpreq);
	}

	@Override
	public DTO_File searchFile(DTO_File dto) {
		log.info("Service@@@@@@@@@@@searchFile,{}", dto);
		return dao.searchFile(dto);
	}

	@Override
	public boolean delFile(DTO_File dto) {
		log.info("Service@@@@@@@@@@@delFile,{}", dto);
		return dao.delFile(dto);
	}

	@Override
	public void write(DTO_File dto_file, MultipartHttpServletRequest mpreq) throws Exception {
		dao.write(dto_file);

		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(dto_file, mpreq);
		int size = list.size();
		for (int i = 0; i < size; i++) {
			dao.insert(list.get(i));
		}

	}

}
