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
이메일 인증 받으세요!!
<a href="./EmailChk.do?seq=61">휴면 인증</a>
<a href="./UnLock.do?seq=61">회원 인증</a>
</body>
</html>