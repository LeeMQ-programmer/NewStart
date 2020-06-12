package com.start.pro.models.profile;

import java.util.Date;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_Career;
import com.start.pro.dto.DTO_Profile;
import com.start.pro.dto.DTO_User;
import com.start.pro.security.Sc_User;

@Repository
public class Dao_ProfileImpl implements IDao_Profile {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.start.pro.Profile.";
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public DTO_Profile searchProfile(int user_seq) {
		log.info("DAO@@@@@프로필 조회,{}",user_seq);
		return session.selectOne(NS+"searchProfile",user_seq);
	}

	@Override
	public boolean insertProfile(DTO_Profile pDto) {
		log.info("DAO@@@@@프로필 등록,{}",pDto);
		int isc = session.insert(NS+"insertProfile", pDto);
		return isc>0?true:false;
	}

	@Override
	public boolean insertCareer(DTO_Career cDto) {
		log.info("DAO@@@@@경력 등록,{}",cDto);
		int isc = session.insert(NS+"insertCareer", cDto);

		return isc>0?true:false;
	}

	@Override
	public boolean updateProfile(DTO_Profile pDto) {
		log.info("DAO@@@@@프로필 수정,{}",pDto);
		int isc = session.insert(NS+"updateProfile", pDto);

		return isc>0?true:false;
	}

	@Override
	public boolean updateCareer(DTO_Career cDto) {
		log.info("DAO@@@@@경력 수정,{}",cDto);
		int isc = session.insert(NS+"updateCareer", cDto);

		return isc>0?true:false;
	}

	@Override
	public Double avgStar(int user_seq) {
		log.info("DAO@@@@@경력 수정,{}",user_seq);
		return session.selectOne(NS+"avgStar",user_seq);
	}

}
