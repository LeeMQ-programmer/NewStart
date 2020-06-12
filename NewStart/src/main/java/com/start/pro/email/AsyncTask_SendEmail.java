package com.start.pro.email;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_Email;
import com.start.pro.models.email.IService_Email;
import com.start.pro.util.Util_JSON;



@Service
public class AsyncTask_SendEmail {

	
	private final String setFrom = "ckadl0118@gmail.com";
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private IService_Email service;
	
	@Autowired
	private Util_JSON jsonUtil;
	
	// 단일 메세지 전송
		@Async("myex")
		public void sendOneMail(DTO_Email dto, HttpServletResponse resp) {
			System.out.println("비동기 들어왔어");
			MimeMessage message = mailSender.createMimeMessage();
			try {
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setFrom(setFrom);
				System.out.println("보낼사람이메일");
				messageHelper.setTo(dto.getUser_email());
				System.out.println("받는사람이메일");
				messageHelper.setSubject(dto.getEmail_title());
				messageHelper.setText(dto.getEmail_content(),true);

				System.out.println("메세지 보내?");
				mailSender.send(message);
				System.out.println("메세지 보냈어");
			} catch (MessagingException e) {
				System.out.println("메일발송 오류??");
				PrintWriter out;
				try {
					out = resp.getWriter();
					out.println("<script>alert('메일 발송을 실패했습니다.');</script>");
					out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		}
		
		
		
		
		@Async("myex")
		public void LJMail(String code, String email,HttpServletResponse resp) {
			System.out.println("비동기 들어왔어");
			
			DTO_Email dto = service.SelDetailAuto(code);
			System.out.println("잘가져왔어?"+dto.toString());
			
			String key = randomKey();
			
			String content = dto.getEmail_content();
			content = content.replace("#{email}", email);
			content =content.replace("#{key}", key);
			dto.setEmail_content(content);
			Map<String, String> map = new HashMap<String, String>();
			map.put("user_email", email);
			map.put("lj_key",key);
			map.put("lj_code",code);
			service.sendLJ(map);
			
			
			MimeMessage message = mailSender.createMimeMessage();
			try {
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setFrom(setFrom);
				System.out.println("보낼사람이메일");
				messageHelper.setTo(email);
				System.out.println("받는사람이메일");
				messageHelper.setSubject(dto.getEmail_title());
				messageHelper.setText(dto.getEmail_content(),true);

				System.out.println("메세지 보내?");
				mailSender.send(message);
				System.out.println("메세지 보냈어");
			} catch (MessagingException e) {
				System.out.println("메일발송 오류??");
				PrintWriter out;
				try {
					out = resp.getWriter();
					out.println("<script>alert('메일 발송을 실패했습니다.');</script>");
					out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		}
		
		
		@Async("myex")
		public void sendManyMail(DTO_Email mailList) {
		
			System.out.println("들어왓숑!");
			mailList.setSuccesschk("S");
			mailList.setCategory_code("1");;
			service.SendEmail(mailList);
			System.out.println("이런게 왔당"+mailList.toString());
			
			List<String> mails = jsonUtil.jsonToList(mailList.getUser_email(),"user_email");

			MimeMessagePreparator[] preparators = new MimeMessagePreparator[mails.size()];
			int i = 0;
			for (String mail : mails) {
				
			preparators[i++] = new MimeMessagePreparator() {
								@Override
								public void prepare(MimeMessage mimeMessage) throws Exception {
									final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
									helper.setFrom(setFrom);
									helper.setTo(mail);
									helper.setSubject(mailList.getEmail_title());
									helper.setText(mailList.getEmail_content(), true);
								}
							};
						}

			mailSender.send(preparators);
			System.out.println("다 보내졌어");
			
			try {
				Thread.sleep(10000);
				chkFailMail(mailList, mails);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		//재전송
		@Async("myex")
		public void resend(DTO_Email mailList) {
		
			System.out.println("들어왓숑!");
			
			
			List<String> mails = jsonUtil.jsonToList(mailList.getUser_email(),"user_email");

			MimeMessagePreparator[] preparators = new MimeMessagePreparator[mails.size()];
			int i = 0;
			for (String mail : mails) {
				
			preparators[i++] = new MimeMessagePreparator() {
								@Override
								public void prepare(MimeMessage mimeMessage) throws Exception {
									final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
									helper.setFrom(setFrom);
									helper.setTo(mail);
									helper.setSubject(mailList.getEmail_title());
									helper.setText(mailList.getEmail_content(), true);
								}
							};
						}

			mailSender.send(preparators);
			System.out.println("다 보내졌어");
			
			try {
				Thread.sleep(10000);
				chkFailMail(mailList, mails);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		//반송 이메일 확인하기
		private void chkFailMail(DTO_Email mailList, List<String> mails) {


			String host = "imap.gmail.com"; //imap 호스트 주소. ex) imap.gmail.com
			String userEmail = "ckadl0118@gmail.com"; //유저 이메일 주소
			String password = "3wndeo12!@"; //유저 암호

			URLName url = new URLName("imaps", host, 993, "INBOX", userEmail, password);

			Session session= null;
			Store store = null;
			Folder folder = null;	

			if (session == null) {
				Properties props = null;
				try {
					props = System.getProperties();
				} catch (SecurityException e) {
					props = new Properties();
				}
				session = Session.getInstance(props, null);
			}
			try {
				store = session.getStore(url);
				store.connect();
				folder = store.getFolder("inbox"); //inbox는 받은 메일함을 의미
				
				if(folder==null || !folder.exists()) {
					return;
				}
				
				folder.open(Folder.READ_WRITE); //읽고 쓰기 전용


				Message[] msgArray = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

				if(msgArray.length == 0) {
					System.out.println("안읽은 메일이 없음");
					Map<String, String> map = new HashMap<String, String>();
					map.put("email_seq", mailList.getEmail_seq());
					map.put("successchk", "Y");
					service.MailSuccess(map);
					return;
				}
				
				//실패한 메일 모음
				List<String> failMails = new ArrayList<String>();
				
				for (int i = 0; i < msgArray.length; i++) {

					Message msg = msgArray[i];
					try {
						if(msg.getSubject().equalsIgnoreCase("Delivery Status Notification (Failure)")) {
						String result = saveParts(msg.getContent());
						System.out.println("이거 리턴돼?"+result);
						if(result!=null && failMails.indexOf(result) == -1) {
							failMails.add(result);
						}
						
						folder.setFlags(new Message[] {msg}, new Flags(Flags.Flag.SEEN), true);
							

						}
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
				folder.close(true);
				store.close();
				folder = null;
				store = null;
				session = null;
				
				
				// 성공에서 실패한거 빼고 수정..
				// 실패한 메일들 삭제
				System.out.println("삭제 전"+mails.toString());
				System.out.println("삭제 할 이메일"+failMails.toString());
				
				if(mails.size() != failMails.size()) {
					
				
				for (String failMail : failMails) {
					mails.remove(failMail);
				}
				System.out.println("삭제 전"+mails.toString());
				String sucmail = jsonUtil.listToJson(mails,"user_email");
				// 실패 제외한 나머지 이메일들
				System.out.println("자 확인해보자"+mailList.getEmail_seq()+":"+sucmail+":"+mailList.getSuccesschk());
				Map<String, String> map = new HashMap<String, String>();
				map.put("email_seq", mailList.getEmail_seq());
				map.put("user_email", sucmail);
				map.put("successchk", "Y");
				service.MailSuccess(map);

				//실패 insert
				mailList.setUser_email(jsonUtil.listToJson(failMails,"user_email"));
				mailList.setSuccesschk("F");
				
				service.SendEmail(mailList);
				}else {
					System.out.println("다 실패 ㅡㅡ"+mailList.toString());
					Map<String, String> map = new HashMap<String, String>();
					map.put("successchk", "F");
					map.put("email_seq", mailList.getEmail_seq());
					service.MailSuccess(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private String saveParts(Object content) throws Exception { 
			
			String failEmail = null;
			
			if (content instanceof Multipart) {
				
				Multipart multi = ((Multipart) content);

				for (int j = 0; j < multi.getCount(); j++) {
					
					MimeBodyPart part = (MimeBodyPart) multi.getBodyPart(j);
					
					if (part.getContent() instanceof Multipart) {
						String result = saveParts(part.getContent());
						if(result != null) {
							failEmail = result;
						}
					} else if(part.isMimeType("text/plain")){
						System.out.println("내용 ㅜㅜ"+part.getContent()); 
						String str = (String) part.getContent();
						str = (str.substring(str.indexOf("to")+2, str.indexOf("because"))).trim();
						System.out.println("실패한 메일 주소"+str);
						if(str != null) {
							failEmail = str;
						}
					} 
				} 
			}
			System.out.println("마지막 확인해보자"+failEmail);
			return failEmail;
		} 

	
		
		private String randomKey() {
			
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			uuid = uuid.substring(0, 10);
			
			return uuid;
			
		}
		
		// String -> jsonArray -> list<String>
		private List<String> getEmails(String emails) {
		
			JSONParser parser = new JSONParser();
			List<String> emailList = new ArrayList<String>();
			Object obj = null;
			try {
				obj = parser.parse(emails);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			JSONArray jArr = (JSONArray) obj;
			for (int i = 0; i < jArr.size(); i++) {
				JSONObject jObj = (JSONObject) jArr.get(i);
				emailList.add((String) jObj.get("user_email"));
				System.out.println("몇번째??"+emailList.get(i));
			}
//			System.out.println("1"+jArr.toJSONString());
//			JSONObject jOb = new JSONObject();
//			jOb.put("user_email", "ckadl0118@naver.com");
//			jArr.remove(jOb);
//			System.out.println("2"+jArr.toJSONString());
//			
			return emailList;
		}
		
		
		//List<String> -> jsonArray -> String
		@SuppressWarnings("unchecked")
		private String parseString(List<String> failMails) {
			
			JSONArray jarr = new JSONArray(); 
			for (String failMail : failMails) {
				JSONObject obj = new JSONObject();
				obj.put("user_mail", failMail);
				jarr.add(obj);
			}
			
			System.out.println(jarr.toJSONString());
			return jarr.toJSONString();
		}
		
}
