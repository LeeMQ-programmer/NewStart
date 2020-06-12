<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>
	<a href="./t_main.do">강사들 공고 메인페이지 접속</a>
	<a href="./m_detail.do">상세글 보기 페이지 접속</a>
	<a href="./m_insert.do">게시글 작성페이지 접속</a>
	<a href="./m_modify.do">게시글 수정 페이지 접속</a>
	
	<div>
	${lists}
	</div>
</body>
</html>