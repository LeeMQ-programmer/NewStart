<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="./js/login.js"></script>
<body>

<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>

<form class="signUp" action="./singUpSc.do" method="post">
아이디 : <input type="text" name="user_email">
<button type="button" onclick="idchk(user_email)">중복확인</button><br/>
닉네임 : <input type="text" name="user_nickname">
<button type="button" onclick="nickchk(user_nickname)">중복확인</button><br/>
이름 : <input type="text" id='true' name="user_name"><br/>
비밀번호 :<input type="password" name="user_pw" onchange="pwchk(this.value)"><br/>
<div id="pw">8 ~ 10자 영문, 숫자 조합이여야 합니다.</div><br/>
비밀번호 확인 :<input type="password" name="user_pw2" onchange="pwchk2(this.value)"><br/>
<div id="pw2"></div><br/>
핸드폰 번호 : <input type="tel" name="user_phone" onchange="phonechk(this.value)"><br/>
<div id='phone'>-는 제외한 번호만 입력해주세요</div>
<input type="hidden" id='true' name="user_adchk" value="${user_adchk}"><br/>
<input type="button" id='true' value="회원가입" onclick="efChk()">
</form>
</body>
</html>