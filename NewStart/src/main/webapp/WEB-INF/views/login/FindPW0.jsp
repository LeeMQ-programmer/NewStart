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
비밀번호 찾으러 가자
<form action="./goFPW1.do" method="post">
아이디 : 
<input type="text" name="email">
<input type="submit" value="제출">
</form>
</body>
</html>