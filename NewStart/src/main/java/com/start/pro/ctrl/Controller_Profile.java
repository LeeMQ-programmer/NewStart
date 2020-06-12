package com.start.pro.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.start.pro.dto.DTO_Career;
import com.start.pro.dto.DTO_Profile;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.profile.IService_Profile;


@Controller
public class Controller_Profile {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IService_Profile service;
	
	@RequestMapping(value = "/writeProfile.do",method={RequestMethod.GET,RequestMethod.POST})
	public String writeProfile(HttpServletRequest req,Model model) {
		log.info("writeProfile.do 실행");
		
		int user_seq = Integer.parseInt(req.getParameter("user_seq"));
		String pro_school = req.getParameter("pro_school");
		String pro_major = req.getParameter("pro_major");
		String pro_tech = req.getParameter("pro_tech");
		String pro_info = req.getParameter("pro_info");
		DTO_Profile profile = new DTO_Profile(user_seq, pro_school, pro_major, pro_tech, pro_info);
		log.info("profile 확인., {}", profile.toString());
		service.insertProfile(profile);
		
		return "redirect:/moveCareer.do";
	}
	
	@RequestMapping(value = "/moveCareer.do",method= {RequestMethod.GET,RequestMethod.POST})
	public String moveCareer(HttpSession session,Model model,HttpServletRequest req) {
		log.info("경력 입력으로 이동합니다.");
		DTO_User newstart = (DTO_User) session.getAttribute("newstart");

		model.addAttribute("newstart",newstart);
		return "board/profile/writeCareer";
	}
	
	@RequestMapping(value = "/writeCareer.do",method={RequestMethod.GET,RequestMethod.POST})
	public String writeCareer(HttpServletRequest req,HttpSession session,Model model) {
		log.info("writeCareer.do 실행");
		
		
		int user_seq = Integer.parseInt(req.getParameter("user_seq"));
		String career_company = req.getParameter("career_company");
		String career_dept = req.getParameter("career_dept");
		String career_job = req.getParameter("career_job");
		String career_term = req.getParameter("career_term");
		DTO_Career career = new DTO_Career(user_seq, career_company, career_dept, career_job, career_term);
		service.insertCareer(career);

		return "redirect:/proFile.do";
	}
}
