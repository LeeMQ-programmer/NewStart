<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">


function ccchk(){
	
	var key = $('input[name=key]').val();
	var chk = $('input[name=chk]').val();

	if(typeof key == 'undefined'){
		$('form').submit();
	}else{
		//alert('캡챠 확인합니다');
		getkey(key, chk);
	}
}

function getkey(key, chk){
	
	$.ajax({
		url : "./valchk.do",
		type : "post",
		data : {"key":key,"chk":chk},
		dataType : "json",
		success: function(data){
			//alert(data.result);
			if(data.result == true){
				//alert('성공');
				$('form').submit();
			}else{
				alert('캡챠를 확인해주세요');
				return;
			}
		},
		error: function(){
			alert('캡차 오류');
			return false;
		}
	});
}





</script>
</head>
<body>
${msg}
${error}
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


<a href="./singUpform1.do">회원가입</a>
<a href="./goFId.do">아이디찾기</a>
<a href="./goFPW.do">비밀번호 찾기</a>





<%-- 
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <font color="red">
        Your login attempt was not successful due to <br/>
            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
        <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
    </font>
</c:if> --%>


</body>
</html>