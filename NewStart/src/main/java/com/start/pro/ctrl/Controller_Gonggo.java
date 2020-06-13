package com.start.pro.ctrl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.start.pro.dto.DTO_BWL;
import com.start.pro.dto.DTO_Gonggo;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.bidding.IService_Bidding;
import com.start.pro.models.bwl.IService_Bwl;
import com.start.pro.models.gonggo.IService_Gonggo;
import com.start.pro.models.user.IService_User;

@Controller
public class Controller_Gonggo {

	@Autowired
	IService_Gonggo gonggo;
	
	@Autowired
	IService_User user;
	
	@Autowired
	IService_Bwl BWL_service;
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	
	// /gonggo.do
	@RequestMapping(value="/gonggo.do", method = RequestMethod.GET)
	public String Gonggo(HttpSession session) {
		log.info("gonggo.do 접속 완료");
		DTO_User this_user =  user.searchDetail(Integer.parseInt("1"));
		session.setAttribute("newstart", this_user);
		return "board/gonggo/GonggoDiv";
	}
	
//////////////////////////////////////////////// 멘티 관련 페이지    ///////////////////////////////////////////////////////////	
	
	
	@RequestMapping(value="/m_main.do", method = RequestMethod.GET)
	public String boardList_M(HttpSession session, Model model) {
		log.info("t_main.do 접속 완료");

		DTO_User userD = (DTO_User) session.getAttribute("newstart");
		System.out.println("uDto의 값은? : " + userD);
		
		// 페이징 처리된 리스트
		List<DTO_Gonggo> glists = null;
		
		System.out.println("강사페이지  페이지 정리 끝");
		
		// 1) 1)의 객체를 사용자 List로 가져오는 service!
		glists = gonggo.gonggo_View();
		
		
		System.out.println("강사페이지  공고글 전체 불러와 리스트에 담기" + glists);
		
		// 멘티 게시판이므로 강사의 공고는 지우고 출력되게 하기
		for(int i = 0; i< glists.size(); i++){
				String gangsa = glists.get(i).getUser_seq();
				System.out.println("gangsa seq : " + gangsa);
				DTO_User gangsa_dto = user.searchDetail(Integer.parseInt(gangsa));
				String grade = gangsa_dto.getUser_grade();
				System.out.println("도출된 강사의 grade는 ?" + grade);
			if(grade.equalsIgnoreCase("T")) {
				// 1) 페이징에 관련된 정보 DTO
				System.out.println("삭제 될 glists의 종류 : " + glists.get(i));
				glists.remove(i);
				--i;
			}
		}
		

		// BWL에서 이미 매칭이 된 공고는 지우고 출력되게 만들기
		List<DTO_BWL> BWL_lists = BWL_service.bwl_show();
		for (int i = 0; i < BWL_lists.size(); i++) {
			if(BWL_lists.get(i).getSuccess_person() != null) {
				String BWL_Seq = BWL_lists.get(i).getGonggo_seq();
				for (int j = 0; j < glists.size(); j++) {
					String gong_seq = glists.get(j).getGonggo_seq();
					if(BWL_Seq.equalsIgnoreCase(gong_seq)) {
						glists.remove(j);
						--j;
					}
				}
			}
		}
		
		
		model.addAttribute("lists", glists);
		model.addAttribute("users", userD);
		System.out.println("row의 값은?" + model.getAttribute("row"));
		System.out.println("lists의 값은? " + model.getAttribute("lists"));
		System.out.println("users의 값은? " + model.getAttribute("users"));
		
		return "board/gonggo/boardList_M"; // board/gonggo/A_Main
	}
	
	
	@RequestMapping(value="/m_detail.do", method = RequestMethod.GET)
	public String M_Detail() {
		log.info("m_detail.do 접속 완료");
		return "board/gonggo/M_Detail";
	}
	
	@RequestMapping(value="/m_insert.do", method = RequestMethod.GET)
	public String M_Insert() {
		log.info("m_insert.do 접속 완료");
		return "board/gonggo/M_Insert";
	}
	
	@RequestMapping(value="/m_modify.do", method = RequestMethod.GET)
	public String M_Modify() {
		log.info("m_modify.do 접속 완료");
		return "board/gonggo/M_Modify";
	}
	
	
////////////////////////////////////////////////////// 강사 관련 페이지 ///////////////////////////////////////////////////////	
	
	@RequestMapping(value="/t_main.do", method = RequestMethod.GET)
	public String boardList_T(HttpSession session, Model model) {
		log.info("t_main.do 접속 완료");
		
		DTO_User userD = (DTO_User) session.getAttribute("newstart");
		System.out.println("uDto의 값은? : " + userD);
		// 페이징 처리된 리스트
		List<DTO_Gonggo> glists = null;
		
		
		
		
		// 1) 1)의 객체를 사용자 List로 가져오는 service!
		glists = gonggo.gonggo_View();
		
		
		System.out.println("강사페이지  공고글 전체 불러와 리스트에 담기" + glists);
		

		// 강사 게시판이므로 멘티의 공고는 지우고 출력되게 하기
		for(int i = 0; i< glists.size(); i++){
			String gangsa = glists.get(i).getUser_seq();
			System.out.println("gangsa seq : " + gangsa);
			DTO_User gangsa_dto = user.searchDetail(Integer.parseInt(gangsa));
			String grade = gangsa_dto.getUser_grade();
			System.out.println("도출된 강사의 grade는 ?" + grade);
				
			// 만약 회원 grade가 M이라면 제거하기(여긴 강사게시판이니까 당연히 제거 해줘야지)
			if(grade.equalsIgnoreCase("M")) {
				// 1) 페이징에 관련된 정보 DTO
				System.out.println("삭제 될 glists의 종류 : " + glists.get(i));
				glists.remove(i);
				--i;
			}
		}
		
		
		// BWL에서 이미 매칭이 된 공고는 지우고 출력되게 만들기
		List<DTO_BWL> BWL_lists = BWL_service.bwl_show();
		for (int i = 0; i < BWL_lists.size(); i++) {
			if(BWL_lists.get(i).getSuccess_person() != null) {
				String BWL_Seq = BWL_lists.get(i).getGonggo_seq();
				for (int j = 0; j < glists.size(); j++) {
					String gong_seq = glists.get(j).getGonggo_seq();
					if(BWL_Seq.equalsIgnoreCase(gong_seq)) {
						glists.remove(j);
					}
				}
			}
		}
		
		model.addAttribute("lists", glists);
		model.addAttribute("users", userD);
		System.out.println("row의 값은?" + model.getAttribute("row"));
		System.out.println("lists의 값은? " + model.getAttribute("lists"));
		System.out.println("users의 값은? " + model.getAttribute("users"));
		
		return "board/gonggo/boardList_T"; // board/gonggo/A_Main
	}
	
	
	
	@RequestMapping(value="/t_detail.do", method = RequestMethod.GET)
	public String T_Detail() {
		log.info("t_detail.do 접속 완료");
		return "board/gonggo/T_Detail";
	}
	@RequestMapping(value="/t_insert.do", method = RequestMethod.GET)
	public String T_Insert() {
		log.info("t_insert.do 접속 완료");
		return "board/gonggo/T_Insert";
	}
	@RequestMapping(value="/t_modify.do", method = RequestMethod.GET)
	public String T_Modify() {
		log.info("t_modify.do 접속 완료");
		return "board/gonggo/T_Modify";
	}
	
	
	
	
//////////////////////////////////////////////////////// 관리자 관련 페이지  //////////////////////////////////////////////////////	
	
	
	
	@RequestMapping(value="/a_main.do", method = RequestMethod.GET)
	
	public String boardList_A(HttpSession session, Model model) {
		log.info("boardList_A.do 접속 완료");
		
		DTO_User userD = (DTO_User) session.getAttribute("newstart");
		System.out.println("uDto의 값은? : " + userD);

		List<DTO_Gonggo> glists = null;
		

		if(userD.getUser_grade().equalsIgnoreCase("A")) { //U는 일반유저, A는 관리자
//			lists = service.userBoardList();
			// 1) 1)의 객체를 사용자 List로 가져오는 service!
			glists = gonggo.gonggo_View();
			
		} else {
//			lists = service.userBoardList();
			// 1) 1)의 객체를 사용자 List로 가져오는 service!
//			glists = gonggo.gonggo_View();
			// 2) 사용자 글 전체를 가져오는 service --> RowDto에 total값을 넣어주기
//			rowDto.setTotal(glists.size());
		}
		
		// 2) 글들
		model.addAttribute("lists", glists);
		model.addAttribute("users", userD);
		System.out.println("row의 값은?" + model.getAttribute("row"));
		System.out.println("lists의 값은? " + model.getAttribute("lists"));
		System.out.println("users의 값은? " + model.getAttribute("users"));
		
		return "board/gonggo/boardList_A"; // board/gonggo/A_Main
	}
	
	
	
	
///////////////////////////////////////////////////  글 삭제  /////////////////////////////////////////////////////
	@RequestMapping(value="/delGonggo.do", method = RequestMethod.GET)
	public String del(HttpSession session, String seq) {
		log.info("Welcome del.do : \t{}", seq);
		DTO_Gonggo dto = gonggo.gonggo_detail(seq);
		System.out.println("▥▥▥▥▥▥▥▥값 찾자 dto : " + dto + "▥▥▥▥▥▥▥▥");
		
		DTO_User mDto = (DTO_User) session.getAttribute("newstart");
		System.out.println("▥▥▥▥▥▥▥▥값 찾자 mDto : " + mDto + "▥▥▥▥▥▥▥▥");
			
		boolean isc = false;
		if(mDto.getUser_grade().equalsIgnoreCase("A") || dto.getUser_seq().equalsIgnoreCase(mDto.getUser_seq())) {
			isc = gonggo.gonggo_delete(seq);
		} else {
			System.out.println("값처리가 안됐어");
		}
		
		
		// TODO
		// 나중엔 홈페이지의 메인페이지로 돌아가게 만들면 되겠다!
		return isc ? "redirect:/gonggo.do" : "redirect:/logout.do";			
	}
	
	
	
	
	
//////////////////////////////////////////////////////글 수정하기 //////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modifyGo.do", method = RequestMethod.POST, produces = "application/text; charset=UTF-8;")
	@ResponseBody
	public String modifyForm(String gonggo_seq) {
		log.info("Welcome modifyForm : \t{}", gonggo_seq);
		JSONObject json = new JSONObject();

		DTO_Gonggo dto = gonggo.gonggo_detail(gonggo_seq);
		json.put("Gonggo_seq", dto.getGonggo_seq());
		json.put("Gonggo_title", dto.getGonggo_title());
		json.put("Gonggo_content", dto.getGonggo_content());
		json.put("User_seq", dto.getUser_seq());
		json.put("Gonggo_time", dto.getGonggo_time());
		json.put("Fileox", dto.getFileox());
		json.put("Gonggo_cost", dto.getgongo_cost());
		
		log.info("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ json의 결과 " + json.toString() + " ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		return json.toString();
	}
	
	
	@RequestMapping(value="/modifyFormGonggo.do", method = RequestMethod.GET)
	public String modifyGo(String gonggo_seq, Model model) {
		DTO_Gonggo dto = gonggo.gonggo_detail(gonggo_seq);
		String title = dto.getGonggo_title();
		String content = dto.getGonggo_content();
		String Gonggo_seq = dto.getGonggo_seq();
		model.addAttribute("title", title);
		model.addAttribute("content", content);
		model.addAttribute("Gonggo_seq", Gonggo_seq);
		System.out.println("모델 title의 값 : "+model.getAttribute("title"));
		System.out.println("모델 content의 값 : "+model.getAttribute("content"));
		System.out.println("모델 Gonggo_seq의 값 : "+model.getAttribute("Gonggo_seq"));
		return "board/gonggo/T_Modify";			
	}
	
	
	@RequestMapping(value="/modifyGonggo.do", method = RequestMethod.GET)
	public String modify(DTO_Gonggo dto,String title, String content,  String Gonggo_seq) {
		log.info("Welcome modify.do : \t{}", dto);
		dto = new DTO_Gonggo(Gonggo_seq, title, content);
		boolean isc = gonggo.gonggo_modify(dto);
		return "redirect:/gonggo.do";			
	}
	
	
	
	////////////////////////////////////////// 게시글에 파일 업로드 시키기~ ///////////////////////////////////////////
	
	// /upload.do
	@Autowired
	private ServletFileUpload upload;
	@RequestMapping(value="/upload.do",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject upload(HttpServletRequest request, HttpServletResponse resp) {
		String url = request.getContextPath()+"/img/";
//		String rootPath = request.getRealPath("/img");  => 이거 이제 사라졌다.
		String rootPath = request.getSession().getServletContext().getRealPath("/img");

		File file = new File(rootPath);
		if(!file.exists()) { file.mkdir();	}
		String filename = "";
		int uploaded = 0;
		try {
			List<FileItem> items = upload.parseRequest((HttpServletRequest)request);
			for (FileItem fileItem : items) {
				if(fileItem.isFormField()) {
				}else {
					filename = fileItem.getName();
					File upFile = new File(rootPath+File.separator+filename);
					try {
						fileItem.write(upFile);
						uploaded++;
					} catch (Exception e) {	e.printStackTrace();	}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();	}
			JSONObject obj = new JSONObject();
			obj.put("uploaded", uploaded);
			obj.put("url", url+filename);
			obj.put("fileName", filename);
		return obj;	
	}
	
	////////////////////////////////////////////////// 글 작성하기 ///////////////////////////////////////////////////////////
	
	// /boardInsertT.do
	@RequestMapping(value="/boardInsertT.do", method = RequestMethod.GET)
	public String boardInsertT() {
		log.info("Welcome boardInsertT.do \t{}");
		return "board/gonggo/T_Insert";
	}
	
	// /boardInsertM.do
	@RequestMapping(value="/boardInsertM.do", method = RequestMethod.GET)
	public String boardInsertM() {
		return "board/gonggo/M_Insert";
	}
	
	// /boardInsert.do
	@RequestMapping(value="/boardInsert.do", method = RequestMethod.POST)
	public String boardInsert(DTO_Gonggo dto) {
		log.info("Welcome boardInsert 공고글 제작 시작 \t{}",dto.toString());
		gonggo.gonggo_insert(dto);
		return "redirect:/gonggo.do";
	}
	
}