package com.start.pro.ctrl;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.start.pro.comm.Coolsms;
import com.start.pro.models.sms.IService_SMS;

@Controller
public class Controller_Sms {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private IService_SMS service;
	
	/**
	 * 임의의 인증번호 6자리 숫자를 랜덤으로 생성해 <br> 
	 * String으로 반환하는 메소드
	 * @return String 인증번호 6자리
	 */
	private String makeCode() {
		int rand = (int) (Math.random() * 899999) + 100000;
		String key = Integer.toString(rand);
		return key;
	}
	
	// 생성된 인증번호를 담을 String
	private String code = null;
	
	/**
	 * 첫번째 화면 이동
	 */
	@RequestMapping(value="/sms.do", method = RequestMethod.GET)
	public String smsPage(HttpSession session ) {
		log.info("Welcome sms Page : \t {}",new Date());
		return "sms";
	}
	
	/**
	 * Ajax 처리로 입력한 번호로 인증번호 6자리를 전송하는 메소드
	 * @param String 입력받은 핸드폰 번호
	 * @return String 문자 전송 결과 : 성공-true/실패-false
	 */
	@ResponseBody
	@RequestMapping(value="/sendSms.do", method = RequestMethod.POST)
	public String smsSend(String phone) {
		log.info("Welcome sendSms.do : \t {} / {}",phone);
		
		String apiKey = "NCSAFEI3ZSHRXEO7";
		String apiSecret = "O4PUVHV0FFEREVKFPTVOUMESRADAOQ6I";
		
		Coolsms cool = new Coolsms(apiKey, apiSecret);
		
		this.code = makeCode();
		
		// 전송할 문자에 대한 정보를 HashMap으로 담음
		HashMap<String, String> set = new HashMap<String, String>();
		// 수신번호.  - 없이 붙여서 입력 ( ex> 01012340000 ) 본인이 발신번호로 등록한 번호여야한다.
        set.put("from", "010-9544-6466"); 
        // 발신번호, jsp에서 전송한 발신번호를 받아 map에 저장한다.
        set.put("to", phone); 
        // 문자내용, jsp에서 전송한 문자내용을 받아 map에 저장한다.
        set.put("text", "본인 확인 번호 ["+code+"]입니다. 정확히 입력해 주세요."); 
        // 문자 타입
        set.put("type", "SMS"); 
		
        System.out.println("문자 정보 확인해볼까?"+set); 
        
        // 메세지를 전송하고 전송 결과를 JSONObject로 반환하는 send 메소드
        JSONObject result = cool.send(set);
		
        if ((boolean)result.get("status") == true) {
            // 메시지 보내기 성공 및 전송결과 출력
            System.out.println("성공");
            System.out.println(result.get("group_id")); // 그룹아이디
            System.out.println(result.get("result_code")); // 결과코드
            System.out.println(result.get("result_message")); // 결과 메시지
            System.out.println(result.get("success_count")); // 메시지아이디
            System.out.println(result.get("error_count")); // 여러개 보낼시 오류난 메시지 수 
			return "true"; 
          } else {
            // 메시지 보내기 실패
            System.out.println("실패");
            System.out.println(result.get("code")); // REST API 에러코드
            System.out.println(result.get("message")); // 에러메시지
            return "false";
          }
	}
	
	/**
	 * Ajax처리로 입력받은 인증번호를 비교하여 결과를 반환하는 메소드
	 * @param String 입력받은 인증번호
	 * @return String 인증 성공 여부 : 성공-ok/실패-false
	 */
	@ResponseBody
	@RequestMapping(value="/smsCheck.do", method = RequestMethod.POST)
	public String smsCheck(String sms) {
		String chkCode = this.code;
		log.info("Welcome smsCheck.do \n");
		if(sms.equals(chkCode)) { 
			log.info("Welcome smsCheck.do 인증성공 - \t 인증번호 : {} / 입력값 : {}",chkCode,sms);
			return "ok"; 
		}else { 
			log.info("Welcome smsCheck.do 인증실패 - \t 인증번호 : {} / 입력값 : {}",chkCode,sms);
			return "no"; 
		}
	}

//	public String sendSMS() {
//		
//		return 
//	}
	
}
