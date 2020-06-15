package com.start.pro.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.start.pro.dto.DTO_File;
import com.start.pro.models.file.IService_File;

@Controller
public class Controller_File {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IService_File service;
	
	@RequestMapping(value = "/moveInsert.do", method = RequestMethod.GET)
	public String moveInsert() {
	
		return "board/file/writeform";
	}
	
	// 게시판 글 작성
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public String write(DTO_File dto_file, MultipartHttpServletRequest mpRequest) throws Exception{
		log.info("write");
		service.write(dto_file, mpRequest);
		
		return "redirect:/board/list";
	}
}
