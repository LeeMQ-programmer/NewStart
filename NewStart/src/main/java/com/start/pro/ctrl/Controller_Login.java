package com.start.pro.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.start.pro.captcha.ICaptchaKey;
import com.start.pro.dto.DTO_Email;
import com.start.pro.dto.DTO_User;
import com.start.pro.email.AsyncTask_SendEmail;
import com.start.pro.models.email.IService_Email;
import com.start.pro.models.login.IService_Login;

@Controller
public class Controller_Login {

	@Autowired
	private IService_Login service;
	
	@Autowired
	private IService_Email email_service;

	@Autowired
	private AsyncTask_SendEmail emailSend;

	//키를 받아오는 클래스
	@Resource(name = "getKey")
	private ICaptchaKey getKey;
	
	// 사용자 입력값 판단하는 클래스
	@Resource(name = "valChk")
	private ICaptchaKey valchk;
	
	//캡챠가 구현되는 페이지
		@ResponseBody
		@RequestMapping(value = "/getKey.do", method = RequestMethod.POST)
		public String main() {
			
			String key = getKey.get("0");
			System.out.println(key+"키 받아왔나요?");
			
			// json으로 key 값 뽑아오기
	        JSONParser parser  = new JSONParser();
	        Object obj = null;
			try {
				obj = parser.parse(key);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        JSONObject jsonobj = (JSONObject) obj;
	        
	        key = (String) jsonobj.get("key");
	        System.out.println(key);
	        	//model.addAttribute("key",key);
	        return key;
	         
		}
	
		
		//결과값을 표출하는 페이지
		@RequestMapping(value = "/valchk.do", method = RequestMethod.POST)
		@ResponseBody
		public String chk(String chk, String key) {
			
			System.out.println("아작스 실행??"+key+":"+chk);
			
			String attach = "1&key="+key+"&value="+chk;
			String result = valchk.get(attach);
			System.out.println(result);
			
			return result;
		}
		
		
	
	//로그인창 
	@RequestMapping(value = "/loginForm.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String loginForm(@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "error", required = false) String error,
			Model model, HttpServletRequest req) {

		//		model.addAttribute("dto",session.getAttribute("user"));

		if(logout != null) {
			System.out.println("로그아웃거칩니다");
			model.addAttribute("msg", "로그아웃 성공!");
		}

		if (error != null) {
			model.addAttribute("error", req.getAttribute("error"));
		}


		return "login/LoginForm_cham";
	}

	//회원가입 완료시
	@RequestMapping(value = "/singUpSc.do", method = RequestMethod.POST)
	public String singUpSc(DTO_User dto, HttpServletResponse resp) {
		System.out.println(dto.toString());
		service.signUp(dto);
		
		emailSend.LJMail("0", dto.getUser_email(), resp);
		
		return "login/SignUp3";
	}

	//로그인 완료시
	@RequestMapping(value = "/loginResult.do", method = RequestMethod.GET)
	public String loginResult(HttpSession session, Authentication auth, Model model) {
//		System.out.println(session.getAttribute("user").toString());
//		System.out.println(auth.toString());
		DTO_User newstart = (DTO_User) session.getAttribute("user");
		model.addAttribute("dto",newstart);
		
		return "board/review/reviewMain";
	}

	//회원가입쪽으로
	@RequestMapping(value = "/singUpform.do", method = RequestMethod.GET)
	public String singUpForm() {
		System.out.println("dd");
	
		return "login/SignUp2_cham";
	}

	//아이디찾자
	@RequestMapping(value = "/goFId.do", method = RequestMethod.GET)
	public String goFId() {
		return "login/FindId1";
	}
	
	///FindId.do
	@RequestMapping(value = "/FindId.do", method = RequestMethod.GET)
	public String FindId(Model model,String phone) {
		System.out.println("phone");
		String id = service.findId(phone);
		model.addAttribute("id", id);
		return "login/FindId2";
	}

	
	//비밀번호 찾자
	@RequestMapping(value = "/goFPW.do", method = RequestMethod.GET)
	public String goFPW() {
		return "login/FindPW0";
	}

	@RequestMapping(value = "/goFPW1.do", method = RequestMethod.POST)
	public String goFPW1(String email,Model model, HttpServletResponse resp) {
		
		model.addAttribute("email", email);
		
		emailSend.LJMail("1", email, resp);
		
		return "login/PWLJchk";
	}



	
	@RequestMapping(value = "/ChangePW.do", method = RequestMethod.POST)
	public String ChangePW(String email, String newPW, Model model) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_email", email);
		map.put("user_pw", newPW);

		service.updatePW(map);
		
		return "login/FindPW2";
	}
	
	
	// 중복검사
	@RequestMapping(value = "/idChk.do", method = RequestMethod.GET)
	public String idChk() {
		return "login/MultiChkId";
	}
	
	//./MultiChkId.do

	@ResponseBody
	@RequestMapping(value = "/{pathval}/MultiChk.do", method = RequestMethod.POST)
	public boolean MultiChk(@PathVariable String pathval, String val) {
		System.out.println(pathval+":"+val);
		Map<String, String> map = new HashMap<String, String>();
		map.put(pathval, val);
		boolean isc = service.MultipleChk(map);
		return isc;
	}
	
	// 휴면회원을 일반회원으로 등급변경(이메일 인증시)
	@RequestMapping(value = "/changeN.do", method = RequestMethod.GET)
	public String changeN(String seq) {
		service.changeSleep(seq);
		return "login/LoginForm_cham";
	}
	
	// 휴면이나 잠금계정 보내기 EmailChk.do
	@RequestMapping(value = "/EmailChk.do", method = RequestMethod.GET)
	public String EmailChk(String email, HttpServletResponse resp) {
		
		emailSend.LJMail("2", email, resp);
		
		return "login/EmailChk";
	}
	@RequestMapping(value = "/LEmailChk.do", method = RequestMethod.GET)
	public String LEmailChk() {
		
		
		return "login/EmailChk";
	}
	
	// 잠금계정 풀기(이메일 인증시)
	@RequestMapping(value = "/UnLock.do", method = RequestMethod.GET)
	public String UnLock(String seq) {
		service.changeNomal(seq);
		return "login/LoginForm_cham";
	}
	
	
}
