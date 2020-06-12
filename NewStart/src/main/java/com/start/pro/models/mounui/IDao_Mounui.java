package com.start.pro.models.mounui;

import java.util.List;
import java.util.Map;

import com.start.pro.dto.DTO_Mounui;

public interface IDao_Mounui {

	/**
	 * 문의글을 등록할 시 사용한다.
	 * 필요한 데이터는 다음과 같다.<br>
	 * user_seq, category, title, content, filechk
	 * @param DTO_Mounui
	 * @return boolean
	 */
	public boolean insertBoard(DTO_Mounui dto);
	
	/**
	 * 유저의 회원번호를 통해 본인이 작성한 문의글들을 가져올 때 사용한다.
	 * @param user_seq
	 * @return List<DTO_Mounui>
	 */
	public List<DTO_Mounui> userBoard(String seq);
	
	/**
	 * 문의글 seq를 통해 글 상세조회할 때 사용한다.
	 * 가져오는 데이터는 다음과 같다<br>
	 * MOUNUI_SEQ, TITLE, CONTENT, REGDATE, REPLYCHK, FILECHK
	 * @param mounui_seq
	 * @return DTO_Mounui
	 */
	public DTO_Mounui userBoardDetail(String seq);
	
	/**
	 * 글 seq를 통해 수정할 때 사용한다.
	 * 필요한 데이터는 다음과 같다 <br>
	 * category, title, content, filechk, mounui_seq
	 * @param DTO_Mounui
	 * @return boolean
	 */
	public boolean updateBoard(DTO_Mounui dto);
	
	/**
	 * 유저가 삭제할 때 사용한다.
	 * 필요한 데이터는 다음과 같다.<br>
	 * key : String seq<br>
	 * value : String[] 
	 * @param Map<String, String[]> map
	 * @return boolean
	 */
	public boolean delBoard(Map<String, String[]> map);
	
	/**
	 * 관리자가 삭제한 글까지 포함한 모든 글을 가져올 때 사용한다.
	 * 가져오는 데이터는 다음과 같다. <br>
	 * MOUNUI_SEQ, USER_SEQ, CATEGORY, TITLE,REGDATE, REPLYCHK, FILECHK, DELCHK  
	 * @return List<DTO_Mounui>
	 */
	public List<DTO_Mounui> adminBoard();
	 
	/**
	 * 관리자가 글을 상세조회할 때 사용한다.
	 * 가져오는 데이터는 다음과 같다.<br>
	 * MOUNUI_SEQ, USER_SEQ, CATEGORY, TITLE, CONTENT, REGDATE, REPLYCHK, FILECHK, DELCHK 
	 * @param mounui_seq
	 * @return DTO_Mounui
	 */
	public DTO_Mounui adminBoardDetail(String seq);
	
	/**
	 * 답변 이메일을 작성했을 시 답변처리여부를 Y로 바꿔준다.
	 * 
	 * @param mounui_seq
	 * @return boolean
	 */
	public boolean replyMounui(String seq);
	
	/**
	 * 관리자가 글을 직접 삭제할 때 사용한다.
	 * 필요한 키 값은 다음과 같다.<br>
	 * key : String seq<br>
	 * value : String[]
	 * 
	 * @param Map<String, String[]>
	 * @return boolean
	 */
	public boolean adminDelBoard(Map<String, String[]> map);
	
	
}
