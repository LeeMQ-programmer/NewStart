package com.start.pro.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.start.pro.dto.DTO_Email;
import com.start.pro.dto.DTO_Filter;
import com.start.pro.dto.DTO_User;
import com.start.pro.email.AsyncTask_SendEmail;
import com.start.pro.models.email.IService_Email;
import com.start.pro.models.login.IService_Login;
import com.start.pro.util.Util_JSON;

@Controller
public class Controller_Email {

	@Autowired
	private IService_Email service;
	
	@Autowired
	private IService_Login loginservice;
	
	@Autowired
	private AsyncTask_SendEmail emailSend;
	
	@Autowired
	private Util_JSON jsonUtil;
	
	//메일 보내기
	@RequestMapping(value = "/testmail.do", method = RequestMethod.GET)
	public String test() {
		
//		emailSend.ddd();
		return "email/ManyMail";
	}
	
	
	//메일 보내기
	@RequestMapping(value = "/ljmail.do", method = RequestMethod.GET)
	public String LJ_Email(String code, String email, HttpServletResponse resp) {
		System.out.println("숑");
		
		DTO_Email dto = service.SelDetailAuto(code);
		
		String content = dto.getEmail_content();
		content = content.replace("#{email}", email);
		content =content.replace("#{key}", "1234");
		dto.setEmail_content(content);
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_email", dto.getUser_email());
		map.put("lj_key","1234");
		map.put("lj_code",code);
		service.sendLJ(map);
//		
		emailSend.sendOneMail(dto, resp);
		
		return "login/SignUp3";
	}
	
	
	//메일 인증
	@RequestMapping(value = "/ljmailchk.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String ljmailchk(String email, String key, Model model) {
		System.out.println(email);
		System.out.println(key);
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_email",email);
		map.put("lj_key", key);
		String code = service.LJKey(map);
		if(code != null) {
			System.out.println("인증되었습니다.");
			switch (code) {
			case "0": loginservice.changeNomal(email); return "login/SignUpResult";
			case "1": model.addAttribute("email", email); return "login/FindPW1";
			case "2":  loginservice.changeSleep(email); return "login/Human";
			default : return "login/LJError";
			}
		}else {
			System.out.println("인증실패");
			return "login/LJError";
		}
	}
	
	
	//자동 이메일 보기
	@RequestMapping(value = "/AutomailB.do", method = RequestMethod.GET)
	public String AutomailB(Model model) {
		
		List<DTO_Email> dtos = service.SelAuto();
		model.addAttribute("dtos",dtos);
		
		return "email/AutoEmailBoard";
	}
	
	//자동 이메일 상세보기
	@RequestMapping(value = "/AutomailBD.do", method = RequestMethod.GET)
	public String AutomailBDetail(String seq, Model model) {
		
		DTO_Email dto = service.SelDetailAuto(seq);
		model.addAttribute("dto",dto);
		
		return "email/AutoEmailBoardD";
	}
	
	//자동 이메일 상세보기
	@RequestMapping(value = "/AutomailUp.do", method = RequestMethod.POST)
	public String AutomailUp(DTO_Email dto) {
		
		System.out.println("??"+dto.toString());
		service.UpdateAuto(dto);
		
		return "redirect:/AutomailB.do";
	}

	
	//대량 메일 보내러 가기
	@RequestMapping(value = "/manymailForm.do", method = RequestMethod.GET)
	public String manymailForm() {
		
		return "email/ManyMail";
	}

	//대량 메일 정보 받기
	@RequestMapping(value = "/manymailget.do", method = RequestMethod.POST)
	public String manymailget(DTO_Email dto) {
		
		System.out.println(dto.toString());
		
		
		return "email/ManyMail";
	}
	
	//ManyMailSend.do
	@RequestMapping(value = "/ManyMailSend.do", method = RequestMethod.POST)
	public String ManyMailSend(DTO_Email dto) {
		
		System.out.println("받아옴??"+dto.toString());
//		dto.setSuccesschk("S");
//		service.SendEmail(dto);
//		System.out.println(dto.toString());
		emailSend.sendManyMail(dto);
		
		return "email/ManyMail";
	}
	
	//관리자페이지 메일기록 확인
	@RequestMapping(value = "/checkMailSave.do", method = RequestMethod.GET)
	public String checkMailSave(Model model) {
		
		List<DTO_Email> dtos = service.SelAllMail();
		
		model.addAttribute("dtos",dtos);
		
		
		return "email/MailSaveBoard";
	}

	//이메일 기록 상세 보기
	//SelMailDetail.do
	@RequestMapping(value = "/SelMailDetail.do", method = RequestMethod.GET)
	public String SelMailDetail(Model model, String seq) {
		
		DTO_Email dto = service.SelMailDetail(seq);
		List<String> emailList = jsonUtil.jsonToList(dto.getUser_email(), "user_email");
		String emails = "";
		for (int i = 0; i < emailList.size(); i++) {
			if(i == emailList.size()-1) {
				emails += emailList.get(i);
			}else {
				emails += emailList.get(i)+"  /  ";
			}
		}
		dto.setUser_email(emails);
		
		model.addAttribute("dto",dto);
		
		return "email/SelMailDetail";
	}
	
	//resend.do
	//재전송
	@RequestMapping(value = "/resend.do", method = RequestMethod.GET)
	public String resend(Model model, String seq) {

		DTO_Email dto = service.mailresend(seq);
		System.out.println("머가 들었길래 안나와"+dto.toString());
		Map<String, String> map = new HashMap<String, String>();
		map.put("email_seq", dto.getEmail_seq());
		map.put("successchk","S");
		service.MailSuccess(map);
		
		emailSend.resend(dto);
		
		return "redirect:/checkMailSave.do";
	}
	
	
	//유저 이메일들 받기
	@ResponseBody
	@RequestMapping(value = "/getuserEmails.do", method = RequestMethod.POST)
	public String getuserEmails(String[] user_grade, String[] user_type) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		if(user_grade != null) {
			map.put("user_grade", user_grade);
		}else if(user_type != null) {
			map.put("user_type", user_type);
		}
		List<String> emailList = service.SelUserFiter(map);
		System.out.println(emailList.toString());
		
		return jsonUtil.listToJson(emailList, "user_email");
	}
	
	//검색 필터
	//SelMailFilter
	@ResponseBody
	@RequestMapping(value = "/SelMailFilter.do", method = RequestMethod.POST)
	public List<DTO_Email> SelMailFilter(DTO_Filter dto) {
	
		System.out.println(dto.toString());
//		if(dto.getSuccesschk().size() == 0 ) {
//			dto.setSuccesschk(null);
//		}
		List<DTO_Email> edto = service.SelMailFilter(dto);
		System.out.println(edto.toString());
		
		
		return edto;
	}
	
	
	
	
//	@SuppressWarnings("unchecked")
//	private String paresJson(List<String> emailList) {
//		
//		JSONArray jArray = new JSONArray();
//		try {
//			for (int i = 0; i < emailList.size(); i++) {
//				JSONObject sObj = new JSONObject();
//				sObj.put("user_email", emailList.get(i));
//				jArray.add(sObj);
//			}
//			System.out.println(jArray.toJSONString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return jArray.toJSONString();
//	}
	
	
	
	
	
	
}
