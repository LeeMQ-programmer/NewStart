package com.start.pro.ctrl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.start.pro.dto.DTO_User;
import com.start.pro.models.user.IService_User;

@Controller
public class Controller_User {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IService_User service;
	
	//===================이동 컨트롤러=================
	@RequestMapping(value = "/userMain.do", method =  {RequestMethod.GET,RequestMethod.POST})
	public String userMain(Model model) {
		log.info("UserMain으로 이동합니다.!!@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		List<DTO_User> lists = service.searchAll();
		
		log.info("@@@@@@@@@@@@@@@@2외어ㅏㄶ왿@@@@@@@@@@@@@@@@@,{}",lists);
		model.addAttribute("lists",lists);
		return "board/user/userMain";
	}
	
	@RequestMapping(value = "/moveDetail.do", method = RequestMethod.GET)
	public String userDetail(HttpServletRequest req, Model model) {
		String user_seq = req.getParameter("user_seq");
		
		DTO_User dto = service.searchDetail(user_seq);
		model.addAttribute("dto",dto);
		return "board/user/userDetail";
	}
	@RequestMapping(value = "/searchTReq.do" ,method = {RequestMethod.GET,RequestMethod.POST})
	public String searchTReq(Model model) {
		log.info("UserMain으로 이동합니다.!!@@@@@@@@@@@@@@@@@@@@@@@@@@");

		List<DTO_User> lists = service.searchTReqAll();
		log.info("@@@@@@@@@@@@@@@@2외어ㅏㄶ왿@@@@@@@@@@@@@@@@@,{}", lists);
		model.addAttribute("lists", lists);
		return "board/user/userTReq";
	}
	@RequestMapping(value = "/searchTReqDetail.do" ,method = {RequestMethod.GET,RequestMethod.POST})
	public String searchTReqDetail(HttpServletRequest req, Model model) {
		String user_seq = req.getParameter("user_seq");
		DTO_User dto = service.searchTReqDetail(user_seq);
		model.addAttribute("dto",dto);
		return "board/user/userTReqDetail";
	}

	
	//--------------------마이페이지--------------------------
	//마이페이지 닉네임, 전화번호 수정
	@RequestMapping(value = "/updateMyPage.do",method = RequestMethod.POST)
	public String updateMyPage(HttpServletRequest req) {
		log.info("마이페이지 업데이트! {}",new Date());
		String user_seq =req.getParameter("user_seq");
		String user_nickname = req.getParameter("user_nickname");
		String user_phone = req.getParameter("user_phone");
		String user_adchk = req.getParameter("user_adchk");
		log.info(user_seq+ "    :      "+user_nickname+"   :    "+user_phone+" : "+user_adchk);
		DTO_User dto = new DTO_User(user_seq,user_nickname, user_phone,user_adchk);
		service.updateMyPage(dto);
		System.out.println("넘어간다!");
		return "redirect:/reviewMain.do";
	}
	
	
	
	//----------------강사 인증--------------
	
	@RequestMapping(value = "/teacherReq.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String teacherReq(HttpSession session) {
		DTO_User dto = (DTO_User) session.getAttribute("newstart");
		service.teacherReq(dto.getUser_seq());
		return null;
	}
	
	
}






