package com.start.pro.ctrl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.start.pro.dto.DTO_Filter;
import com.start.pro.dto.DTO_Mounui;
import com.start.pro.email.AsyncTask_SendEmail;
import com.start.pro.models.email.IService_Email;
import com.start.pro.models.mounui.IService_Mounui;

@RestController
public class RestController_Mounui {

	@Autowired
	private IService_Mounui service;
	
	@Autowired
	private IService_Email mservice;

	@Autowired
	private AsyncTask_SendEmail emailSender;
	
	//문의 게시판 답장 메일 보내기
	@RequestMapping(value = "/MReplySend.do", method = RequestMethod.POST)
	public boolean MReplySend(DTO_Mounui dto, String user_email) throws IOException{

		if(emailSender.sendReplyMail(user_email, dto.getTitle(), dto.getContent())) {
			dto.setDelchk("Y");
			mservice.SendReply(dto);
			service.replyMounui(dto.getMounui_seq());
			return true;
			
		}else {
			return false;
		}
	}
	
	//관리자 문의게시판 필터
	@RequestMapping(value = "/AdminMBoard.do", method = RequestMethod.POST)
	public List<DTO_Mounui> AdminMBoard(DTO_Filter dto){
		
			System.out.println("문의 게시판 필터!!"+dto.toString());
			List<DTO_Mounui> dtos = service.adminBoard(dto);
			System.out.println(dtos.toString());
		
		return dtos;
	}
	
}
