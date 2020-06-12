package com.start.pro.models.mounui;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.start.pro.dto.DTO_Mounui;

public class Service_MounuiImpl implements IService_Mounui{

	@Autowired
	private IDao_Mounui dao;
	
	
	@Override
	public boolean insertBoard(DTO_Mounui dto) {
		return dao.insertBoard(dto);
	}

	@Override
	public List<DTO_Mounui> userBoard(String seq) {
		return dao.userBoard(seq);
	}

	@Override
	public DTO_Mounui userBoardDetail(String seq) {
		return dao.userBoardDetail(seq);
	}

	@Override
	public boolean updateBoard(DTO_Mounui dto) {
		return dao.updateBoard(dto);
	}

	@Override
	public boolean delBoard(Map<String, String[]> map) {
		return dao.delBoard(map);
	}

	@Override
	public List<DTO_Mounui> adminBoard() {
		return dao.adminBoard();
	}

	@Override
	public DTO_Mounui adminBoardDetail(String seq) {
		return dao.adminBoardDetail(seq);
	}

	@Override
	public boolean replyMounui(String seq) {
		return dao.replyMounui(seq);
	}

	@Override
	public boolean adminDelBoard(Map<String, String[]> map) {
		return dao.adminDelBoard(map);
	}

}
