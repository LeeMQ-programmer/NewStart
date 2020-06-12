package com.start.pro.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.start.pro.dto.DTO_Email;
import com.start.pro.dto.DTO_FAQ;
import com.start.pro.dto.DTO_Mounui;
import com.start.pro.dto.DTO_User;
import com.start.pro.email.AsyncTask_SendEmail;
import com.start.pro.models.email.IService_Email;
import com.start.pro.models.mounui.IService_Mounui;

@Controller
public class Controller_Mounui {

	@Autowired
	private IService_Mounui service;
	
	
	
	@RequestMapping(value = "/mounuiboard.do", method = RequestMethod.GET)
	public String mounuiboard(Model model){

		List<DTO_FAQ> Fdto = service.getCategory();
		model.addAttribute("Fdto",Fdto);
		
		return "board/mounui/InsertBoard";
	}
	
	//insertBoard
	@RequestMapping(value = "/insertBoard.do", method = RequestMethod.POST)
	public void insertBoard(DTO_Mounui dto, HttpSession session,Model model,
			HttpServletResponse resp) throws IOException{
		
		
		
		DTO_User user = (DTO_User) session.getAttribute("newstart");
		dto.setUser_seq(user.getUser_seq());
		System.out.println(dto.toString());
//		System.out.println(user.toString());
		service.insertBoard(dto);

		
		String userSeq = ((DTO_User) session.getAttribute("newstart")).getUser_seq();
		List<DTO_Mounui> dtos = service.userBoard(userSeq);
		
		model.addAttribute("dtos", dtos);
		resp.setCharacterEncoding("utf-8");
	    resp.setContentType("text/html; charset=UTF-8");


		
		
	    PrintWriter	out = resp.getWriter();
		out.println("<script>alert('문의글이 등록되었습니다.');  location.href='./UserMBoard.do'; </script>");
		out.flush();

//		return "board/mounui/UserMBoard";
	}

	//유저 문이 게시판
	@RequestMapping(value = "/UserMBoard.do", method = RequestMethod.GET)
	public String UserMBoard(Model model, HttpSession session){
		
		String userSeq = ((DTO_User) session.getAttribute("newstart")).getUser_seq();
		List<DTO_Mounui> dtos = service.userBoard(userSeq);
		
		model.addAttribute("dtos", dtos);
		return "board/mounui/UserMBoard";
	}

	// 유저 문의 상세보기
	@RequestMapping(value = "/UserMBoardDetail.do", method = RequestMethod.GET)
	public String UserMBoardDetail(String seq, Model model){
		
		DTO_Mounui dto = service.userBoardDetail(seq);
		
		System.out.println("왜안돼"+dto.toString());
		
		model.addAttribute("dto", dto);
		return "board/mounui/UserMBoardD";
	}

	
	@RequestMapping(value = "/UserMBoardDel.do", method = {RequestMethod.GET,RequestMethod.POST})
	public void UserMBoardDel(String[] seq, HttpServletResponse resp) throws IOException {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("seq", seq);
		service.delBoard(map);
		
//		System.out.println(seq[0]);
		
		PrintWriter	out = resp.getWriter();
		out.println("<script>alert('삭제되었습니다.');location.href='./UserMBoard.do'; </script>");
		out.flush();
		
	}

	// 관리자 문의 게시판
	@RequestMapping(value = "/AdminMBoard.do", method = RequestMethod.GET)
	public String AdminMBoard(Model model){
		
			List<DTO_Mounui> dtos = service.adminBoard(null);
			System.out.println(dtos.toString());
			model.addAttribute("dtos", dtos);
		
		
		return "board/mounui/AdminMainBoard";
	}


	
	// 관리자 문의 상세보기
	@RequestMapping(value = "/AdminMBoardDetail.do", method = RequestMethod.GET)
	public String AdminMBoardDetail(String seq, Model model){
		
		DTO_Mounui dto = service.adminBoardDetail(seq);
		
		System.out.println("왜안돼"+dto.toString());
		
		if(dto.getReplychk().equalsIgnoreCase("Y")) {
			DTO_Email edto = service.getReply(seq);
			model.addAttribute("edto", edto);
		}
		
		model.addAttribute("dto", dto);
		return "board/mounui/AdminMBoardD";
	}
	
	
	//문의 게시판 답장하기
	@RequestMapping(value = "/MBoardReply.do", method = RequestMethod.GET)
	public String MBoardReply(String seq, String email, Model model){
		
		model.addAttribute("seq", seq);
		model.addAttribute("email", email);

		return "board/mounui/EmailReply";
	}


	
	
//	./AdminMBoardDel.do
	//문의 게시판 삭제하기
	@RequestMapping(value = "/AdminMBoardDel.do", method = RequestMethod.GET)
	public String AdminMBoardDel(String[] seq, Model model){
		
		Map<String, String[]> map = new HashMap<String,String[]>();
		map.put("seq", seq);
		service.adminDelBoard(map);

		return "redirect:/AdminMBoard.do";
	}
	
}
