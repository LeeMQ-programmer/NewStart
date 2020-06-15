<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<script type="text/javascript" src="./js/login.js"></script>
</head>
<body>
<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>

<form action="./logingo.do" method="post" id='login'>

아이디 : 
<input type="text" name="username" value="${id}">
비밀번호
<input type="text" name="password" value="${password}">
<input id="remember_me" name ="remember_me" type = "checkbox"/>Remember me
 <c:if test="${not empty key}"> 
		<br>
	<img  src="https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=${key}">
		<br>
	<input type="hidden" name="key" value="${key}">
	입력 : 
	<input type="text" name="chk">
</c:if> 

<button type="button" onclick="return ccchk()">로그인</button>
</form>
<div style="color:red;">${error}</div>

<a href="./singUpform1.do">회원가입</a>
<a href="./goFId.do">아이디찾기</a>
<a href="./goFPW.do">비밀번호 찾기</a>


</body>
</html>