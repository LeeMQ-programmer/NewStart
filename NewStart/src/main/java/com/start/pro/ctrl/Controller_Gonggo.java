package com.start.pro.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.start.pro.dto.DTO_Gonggo;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.gonggo.IService_Gonggo;

@Controller
public class Controller_Gonggo {

	@Autowired
	IService_Gonggo gonggo;
	
	
	// /gonggo.do
	@RequestMapping(value="/gonggo.do", method = RequestMethod.GET)
	public String Gonggo(HttpSession session) {
		
//		session.setAttribute("newstart", "21");
		return "board/gonggo/GonggoDiv";
	}
	
///////////////////// 멘티 관련 페이지//////////////////////////	
	
	@RequestMapping(value="/m_main.do", method = RequestMethod.GET)
	public String M_Main(HttpSession session, Model model) {
		String seq = (String) session.getAttribute("newstart");
		List<DTO_Gonggo> lists = new ArrayList<DTO_Gonggo>();
		lists = gonggo.Gonggo_Show(seq);
		model.addAttribute(lists);
		return "board/gonggo/M_Main";
	}
	@RequestMapping(value="/m_detail.do", method = RequestMethod.GET)
	public String M_Detail() {
		return "board/gonggo/M_Detail";
	}
	@RequestMapping(value="/m_insert.do", method = RequestMethod.GET)
	public String M_Insert() {
		return "board/gonggo/M_Insert";
	}
	@RequestMapping(value="/m_modify.do", method = RequestMethod.GET)
	public String M_Modify() {
		return "board/gonggo/M_Modify";
	}
	
	
///////////////////// 강사 관련 페이지//////////////////////////	
	
	@RequestMapping(value="/t_main.do", method = RequestMethod.GET)
	public String T_Main() {
		return "board/gonggo/T_Main";
	}
	@RequestMapping(value="/t_detail.do", method = RequestMethod.GET)
	public String T_Detail() {
		return "board/gonggo/T_Detail";
	}
	@RequestMapping(value="/t_insert.do", method = RequestMethod.GET)
	public String T_Insert() {
		return "board/gonggo/T_Insert";
	}
	@RequestMapping(value="/t_modify.do", method = RequestMethod.GET)
	public String T_Modify() {
		return "board/gonggo/T_Modify";
	}
	
	
///////////////////// 강사 관련 페이지//////////////////////////	
	
	@RequestMapping(value="/a_main.do", method = RequestMethod.GET)
	public String A_Main(HttpSession session, Model model) {
		String seq = "A";
		List<DTO_Gonggo> lists = new ArrayList<DTO_Gonggo>();
		lists = gonggo.Gonggo_Show(seq);
		model.addAttribute(lists);
		System.out.println(model.toString());
		return "board/gonggo/A_Main";
	}
	
}
