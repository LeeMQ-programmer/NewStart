package com.start.pro.models.sms;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.start.pro.dto.DTO_SMS;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.review.IDao_Review;
import com.start.pro.security.Sc_User;

@Service
public class Service_SMSImpl implements IService_SMS {

	private Logger log = LoggerFactory.getLogger(this.getClass());
 
	@Autowired
	private IDao_SMS dao;

	@Override
	public List<DTO_SMS> searchAll() {
		log.info("Service@@@@@SMS 전체 조회,{}",new Date());

		return dao.searchAll();
	}

	@Override
	public DTO_SMS searchDetail(int user_seq) {
		log.info("Service@@@@@SMS 상세 조회, {}",user_seq);
		return dao.searchDetail(user_seq);
	}

	@Override
	public boolean insertSend(DTO_SMS dto) {
		log.info("Service@@@@@SMS 문자 보내기,{}",dto);
		return dao.insertSend(dto);
	}

	@Override
	public boolean insertResult(DTO_SMS dto) {
		log.info("Service@@@@@SMS 문자 결과 받기,{}",dto);
		return dao.insertResult(dto);
	}

}
