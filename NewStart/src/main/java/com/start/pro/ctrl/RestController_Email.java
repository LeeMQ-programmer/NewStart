package com.start.pro.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.start.pro.dto.DTO_Email;
import com.start.pro.dto.DTO_Filter;
import com.start.pro.models.email.IService_Email;
import com.start.pro.util.Util_JSON;

@RestController
public class RestController_Email {

	@Autowired
	private IService_Email service;
	
	@Autowired
	private Util_JSON jsonUtil;
	
	// 검색 필터
	// SelMailFilter
	@RequestMapping(value = "/SelMailFilter.do", method = RequestMethod.POST)
	public List<DTO_Email> SelMailFilter(DTO_Filter dto) {

		System.out.println(dto.toString());
		List<DTO_Email> edto = service.SelMailFilter(dto);
		System.out.println(edto.toString());

		return edto;
	}
	
	// 유저 이메일들 받기
	@RequestMapping(value = "/getuserEmails.do", method = RequestMethod.POST)
	public String getuserEmails(String user_grade) {

		System.out.println("???" + user_grade);
		// 'M',''T
		  Map<String, String> map = new HashMap<String, String>();

		 if(user_grade !=null) {
			  map.put("user_grade", user_grade); 
		 }
		  
		 List<String> emailList  = service.SelUserFiter(map); 
		 String emails = jsonUtil.listToJson(emailList, "user_email");
		  
		 return emails;
	}
	
}
