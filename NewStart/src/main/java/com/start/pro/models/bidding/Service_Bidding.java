package com.start.pro.models.bidding;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_Bidding;

@Service
public class Service_Bidding implements IService_Bidding {



	@Autowired
	IDao_Bidding dao;
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean bidding_insert(DTO_Bidding dto) {
		log.info("Service impl bidding_insert : \t{}");
		if(dao.bidding_insert(dto)) {
			if(dao.bidding_update(dto)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public List<DTO_Bidding> bidding_list(String seq) {
		log.info("Service impl bidding_list : \t{}");
		return dao.bidding_list(seq);
	}

}
