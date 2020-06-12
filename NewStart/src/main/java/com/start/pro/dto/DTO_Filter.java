package com.start.pro.dto;

import java.util.List;

public class DTO_Filter {

	private String user_grade;
	private String replychk;
	private String delchk;
	private String filter;
	private String firstDate;
	private String lastDate;
	private List<String> successchk;
	
	public DTO_Filter() {}

	public DTO_Filter(String user_grade, String replychk, String delchk, String filter, String firstDate,
			String lastDate, List<String> successchk) {
		super();
		this.user_grade = user_grade;
		this.replychk = replychk;
		this.delchk = delchk;
		this.filter = filter;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
		this.successchk = successchk;
	}

	public String getUser_grade() {
		return user_grade;
	}

	public void setUser_grade(String user_grade) {
		this.user_grade = user_grade;
	}

	public String getReplychk() {
		return replychk;
	}

	public void setReplychk(String replychk) {
		this.replychk = replychk;
	}

	public String getDelchk() {
		return delchk;
	}

	public void setDelchk(String delchk) {
		this.delchk = delchk;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public List<String> getSuccesschk() {
		return successchk;
	}

	public void setSuccesschk(List<String> successchk) {
		this.successchk = successchk;
	}

	@Override
	public String toString() {
		return "DTO_Filter [user_grade=" + user_grade + ", replychk=" + replychk + ", delchk=" + delchk + ", filter="
				+ filter + ", firstDate=" + firstDate + ", lastDate=" + lastDate + ", successchk=" + successchk + "]";
	}
	
	
	
}
