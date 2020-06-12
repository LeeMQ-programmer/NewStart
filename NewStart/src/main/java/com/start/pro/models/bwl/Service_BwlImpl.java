package com.start.pro.models.bwl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_BWL;

@Service
public class Service_BwlImpl implements IService_Bwl {


	@Autowired
	IDao_Bwl dao;
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public List<DTO_BWL> bwl_show() {
		log.info("Service impl bwl_show : \t{}");
		return dao.bwl_show();
	}

	@Override
	public DTO_BWL bwl_detail(String seq) {
		log.info("Service impl bwl_detail : \t{}");
		return dao.bwl_detail(seq);
	}

	@Override
	public boolean bwl_winner(DTO_BWL dto) {
		log.info("Service impl bwl_winner : \t{}");
		return dao.bwl_winner(dto);
	}

}
