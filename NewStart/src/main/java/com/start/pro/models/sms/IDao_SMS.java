package com.start.pro.models.sms;

import java.util.List;
import java.util.Map;

import com.start.pro.dto.DTO_SMS;
import com.start.pro.dto.DTO_User;
import com.start.pro.security.Sc_User;

public interface IDao_SMS {

	
	/**
	 * sms 발송 전체 조회
	 * @return DTO_SMS
	 */
	public List<DTO_SMS> searchAll();
	
	/**
	 * sms 발송 상세 조회
	 * @param user_seq(유저 번호)
	 * @return DTO_SMS
	 */
	public DTO_SMS searchDetail(int user_seq);
	
	/**
	 * 문자 보내기
	 * @param dto 유저 번호, 문자 타입, 제목, 내용, 유저번호, 
	 * @return 보내기 성공 실패 여부
	 */
	public boolean insertSend(DTO_SMS dto);
	
	/**
	 * 발송 결과값 
	 * @param dto 유저 번호, 결과 코드
	 * @return TRUE, FALSE
	 */
	public boolean insertResult(DTO_SMS dto);
	
	
		
}
