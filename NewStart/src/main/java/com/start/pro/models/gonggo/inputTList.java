package com.start.pro.models.gonggo;

import java.util.List;

import com.start.pro.dto.DTO_Gonggo;
import com.start.pro.dto.DTO_User;

public class inputTList {
	private List<DTO_Gonggo> lists;
	private DTO_User T_user;

	public void setLists(List<DTO_Gonggo> lists) {
		this.lists = lists;
	}

	public void setT_user(DTO_User T_user) {
		this.T_user = T_user;
	}

	// 날짜
	private String dateFormat(String date) {
		return date.substring(0, date.indexOf(" "));
	}

	// 이미지
	private String titleFormat() {
		StringBuffer buf = new StringBuffer();
		buf.append("<img src='./img/reply.jpg'/>");
		return buf.toString();
	}

	// 출력 리스트 폼
	private String listForm(DTO_Gonggo dto) {
		StringBuffer buf = new StringBuffer();
		// colspan의 기본 user의 값
		int n = 7;
		buf.append(
				"<tr>                                                                                                    				  ");
		buf.append("<td><input type='checkbox' name='chkVal' value='" + dto.getGonggo_seq()
				+ "'></td>                                               				  ");
		buf.append("<td>" + dto.getGonggo_seq()
				+ "</td>                                                                                             			  ");
		buf.append(
				"<td>                                                                                                    			 	 ");
		buf.append(
				"	 <div class='panel-heading'>                                                                          				");
		buf.append("		<a data-toggle='collapse' data-parent='#accordion' href='#collapse" + dto.getGonggo_seq()
				+ "' onclick='collapse(\"" + dto.getGonggo_seq() + "\")'>" + titleFormat() + dto.getGonggo_title()
				+ "</a>	");
		buf.append(
				"	 </div>                                                                                     						   ");
		buf.append(
				"</td>                                                                                        				 		     ");
		buf.append("<td>" + dto.getUser_seq()
				+ "</td>                                                                                				   			 ");
		buf.append("<td>"
				+ "</td>                                                                            							     ");
		if (T_user.getUser_grade().equalsIgnoreCase("A")) {
			n = 7;
			buf.append("<td>" + dto.getFileox() + "</td>");

		}
		buf.append("<td>" + dateFormat(dto.getGonggo_time())
				+ "</td>                                                                                  ");
		buf.append(
				"</tr>                                                                                              ");
		buf.append(
				"<tr>                                                                                               ");
		buf.append("	<td colspan='" + n
				+ "'>                                                                               ");
		buf.append("		<div id='collapse'" + dto.getGonggo_seq()
				+ " class='panel-collapse'>                                    ");
		buf.append(
				"			<div class='form-group'>                                                               ");
		buf.append("				<label>내용</label>                                                                ");
		buf.append("				<textarea rows='7' class='form-control' readonly='readonly'>" + dto.getGonggo_content()
				+ "</textarea>");
		buf.append(
				"			</div>                                                                                 ");
		buf.append("<div>");
		buf.append("	<div class ='form-group'>");
		if (T_user.getUser_seq().equalsIgnoreCase(dto.getUser_seq())) {
			buf.append("<input class='btn btn-primary btn-center' type='button' value='글 수정' onclick='modify(\""
					+ dto.getGonggo_seq() + "\")'>");
		}
		if (T_user.getUser_seq().equalsIgnoreCase(dto.getUser_seq()) || T_user.getUser_grade().equalsIgnoreCase("A")) {
			buf.append("<input class='btn btn-primary btn-center' type='button' value='글 삭제' onclick='del(\""
					+ dto.getGonggo_seq() + "\")'>");
		}
		if (T_user.getUser_grade().equalsIgnoreCase("U")) {
			buf.append("<input class='btn btn-primary btn-center' type='button' value='답글' onclick='reply(\""
					+ dto.getGonggo_seq() + "\")'>");
		}
		buf.append("	</div>");
		buf.append("</div>");
		buf.append(
				"		</div>                                                                                     ");
		buf.append("</td>");
		buf.append("</tr>");

		return buf.toString();
	}

	// 리스트 가져가기
	public String getListForm() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < lists.size(); i++) {
			buf.append(listForm(lists.get(i)));
		}
		return buf.toString();
	}

}
