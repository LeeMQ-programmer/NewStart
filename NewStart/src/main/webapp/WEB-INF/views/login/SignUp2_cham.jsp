<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
function idchk(val){
	
	var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

	if(regExp.test(val.value)){
		Multichk(val.name,val.value);
	}else{
		alert('올바른 이메일 형식이 아닙니다.');
	}
	
}

function nickchk(val){
	Multichk(val.name,val.value);
}


var Multichk = function(url,val){
	
	$.ajax({
		url : "./"+url+"/MultiChk.do",
		type : "post",
		data : {"val":val},
		dataType : "text",
		success: function(data){
			if(data == 'false'){
				alert("사용 가능");
				$('input[name='+url+']').attr("id",val);
			}else{
				alert("사용 불가");
			}
		},
		error: function(){
			alert('오류');
		}
	});
}

 function efChk(){
	
	 $('input').each(function(i){
		 
		 //입력하지 않았을때
		if($.trim($(this).val()) == ''){
			alert('모두 입력하셔야 회원가입이 가능합니다.');
			$(this).focus();
			return false;
		}
		// 중복검사하지 않았을 때
		if (i == 0 || i == 1) {
			if ($.trim($(this).attr('id')) != $.trim($(this).val())) {
				alert('중복검사를 완료해주세요');
				$(this).focus();
				return false;
				}
		}else{
			if($(this).attr('id') != 'true'){
				alert('올바른 값을 입력해주세요');
				$(this).focus();
				return false;
			}
		}
		if(i == 7){
			$('.signUp').submit();
		}

	 });
	 
}

 function pwchk(val){
	 var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/; //  8 ~ 10자 영문, 숫자 조합
	 
	if(regExp.test(val)){
		$('#pw').css('color','blue');
		$('#pw').html('사용 가능한 비밀번호 입니다!');
		$('input[name=user_pw]').attr('id','true');
	}else{
		$('#pw').css('color','red');
		$('#pw').html('8 ~ 10자 영문, 숫자 조합이여야 합니다.');
		$('input[name=user_pw]').attr('id','false');
	}

 }


function phonechk(val){
	var regExp = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;
		
	if(!regExp.test(val)){
		$('#phone').css('color','red');
		$('#phone').html('올바른 핸드폰 번호가 아닙니다');
		$('input[name=user_phone]').attr('id','false');
	}else{
		$('#phone').html('');
		$('input[name=user_phone]').attr('id','true');
	}


}

function pwchk2(val){
	if($('[name=user_pw]').val() != val){
		$('#pw2').css('color','red');
		$('#pw2').html('비밀번호가 일치하지 않습니다.');
		$('input[name=user_pw2]').attr('id','false');
	}else{
		$('#pw2').html('');
		$('input[name=user_pw2]').attr('id','true');
	}
}
 
</script>
</head>
<body>
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
핸드폰 번호 : <input type="text" name="user_phone" onchange="phonechk(this.value)"><br/>
<div id='phone'></div>
<input type="hidden" id='true' name="user_adchk" value="${user_adchk}"><br/>
<input type="button" id='true' value="회원가입" onclick="efChk()">
</form>
</body>
</html>