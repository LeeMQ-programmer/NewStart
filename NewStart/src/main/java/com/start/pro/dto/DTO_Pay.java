package com.start.pro.dto;

import java.util.Date;

public class DTO_Pay {

	private String pay_seq;
	private String pay_token;
	private String pay_type;
	private int pay_orderno;
	private String pay_amount;
	private Date pay_date;
	private int user_seq;
	private int admin_seq;
	
	public DTO_Pay() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "DTO_Pay [pay_seq=" + pay_seq + ", pay_token=" + pay_token + ", pay_type=" + pay_type + ", pay_orderno="
				+ pay_orderno + ", pay_amount=" + pay_amount + ", pay_date=" + pay_date + ", user_seq=" + user_seq
				+ ", admin_seq=" + admin_seq + "]";
	}

	public String getPay_seq() {
		return pay_seq;
	}
	public void setPay_seq(String pay_seq) {
		this.pay_seq = pay_seq;
	}
	public String getPay_token() {
		return pay_token;
	}
	public void setPay_token(String pay_token) {
		this.pay_token = pay_token;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(String pay_amount) {
		this.pay_amount = pay_amount;
	}
	public Date getPay_date() {
		return pay_date;
	}
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	public int getAdmin_seq() {
		return admin_seq;
	}
	public void setAdmin_seq(int admin_seq) {
		this.admin_seq = admin_seq;
	}

	public int getPay_orderno() {
		return pay_orderno;
	}

	public void setPay_orderno(int pay_orderno) {
		this.pay_orderno = pay_orderno;
	}

	public DTO_Pay(String pay_seq, String pay_token, int pay_orderno, String pay_amount, Date pay_date,
			int user_seq) {
		super();
		this.pay_seq = pay_seq;
		this.pay_token = pay_token;
		this.pay_orderno = pay_orderno;
		this.pay_amount = pay_amount;
		this.pay_date = pay_date;
		this.user_seq = user_seq;
	}
	

	
}
