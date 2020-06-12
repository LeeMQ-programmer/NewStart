package com.start.pro.models.sms;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_SMS;
import com.start.pro.dto.DTO_User;
import com.start.pro.security.Sc_User;

@Repository
public class Dao_SMSImpl implements IDao_SMS {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.start.pro.SMS.";
	@Autowired
	private SqlSessionTemplate session;
	@Override
	public List<DTO_SMS> searchAll() {
		log.info("DAO@@@@@후기 게시판 전체 조회,{}",new Date());
		
		return session.selectList(NS+"searchAll");
	}

	@Override
	public DTO_SMS searchDetail(int user_seq) {
		log.info("DAO@@@@@후기 게시판 전체 조회,{}",new Date());
		return session.selectOne(NS+"searchDetail",user_seq);
	}

	@Override
	public boolean insertSend(DTO_SMS dto) {
		log.info("DAO@@@@@후기 게시판 전체 조회,{}",dto);
		int isc = session.insert(NS+"insertSend",dto);
		return isc>0?true:false;
	}

	@Override
	public boolean insertResult(DTO_SMS dto) {
		log.info("DAO@@@@@후기 게시판 전체 조회,{}",new Date());
		int isc = session.insert(NS+"insertResult",dto);
		return isc>0?true:false;
	}


	
}
