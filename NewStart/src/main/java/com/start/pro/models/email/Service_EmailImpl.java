package com.start.pro.models.email;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_Email;
import com.start.pro.dto.DTO_Filter;
@Service
public class Service_EmailImpl implements IService_Email{

	@Autowired
	private IDao_Email dao;
	
	@Override
	public boolean sendLJ(Map<String, String> map) {
		return dao.sendLJ(map);
	}

	@Override
	public String LJKey(Map<String, String> map) {
		return dao.LJKey(map);
	}

	@Override
	public boolean DelLJ() {
		return dao.DelLJ();
	}

	@Override
	public boolean SetAutoEmail(DTO_Email dto) {
		return dao.SetAutoEmail(dto);
	}

	@Override
	public boolean UpdateAuto(DTO_Email dto) {
		return dao.UpdateAuto(dto);
	}

	@Override
	public List<DTO_Email> SelAuto() {
		return dao.SelAuto();
	}

	@Override
	public DTO_Email SelDetailAuto(String code) {
		return dao.SelDetailAuto(code);
	}

	@Override
	public DTO_Email SendEmail(DTO_Email dto) {
		return dao.SendEmail(dto);
	}

	@Override
	public boolean MailSuccess(Map<String, String> map) {
		return dao.MailSuccess(map);
	}

	@Override
	public DTO_Email mailresend(String seq) {
		return dao.mailresend(seq);
	}

	@Override
	public boolean DelMail() {
		return dao.DelMail();
	}

	@Override
	public List<DTO_Email> SelAllMail() {
		return dao.SelAllMail();
	}

	@Override
	public DTO_Email SelMailDetail(String seq) {
		return dao.SelMailDetail(seq);
	}

	@Override
	public List<DTO_Email> SelMailFilter(DTO_Filter dto) {
		return dao.SelMailFilter(dto);
	}

	@Override
	public List<String> SelUserFiter(Map<String, String[]> map) {
		return dao.SelUserFiter(map);
	}

}
