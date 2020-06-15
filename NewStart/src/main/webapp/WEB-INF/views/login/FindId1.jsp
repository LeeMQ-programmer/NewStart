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
<form action="./FindId.do">
휴대폰 번호 : 
<input type="text" name="phone">
<input type="submit">
</form>
</body>
</html>