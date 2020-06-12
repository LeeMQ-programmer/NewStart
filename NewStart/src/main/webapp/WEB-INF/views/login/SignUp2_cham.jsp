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
	//alert(val.name);
	//alert(val.value);
	Multichk(val.name,val.value);
	
}

function nickchk(val){
	//alert(val.name);
	//alert(val.value);
	Multichk(val.name,val.value);
}


var Multichk = function(url,val){
	//alert(url+":"+val);
	
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
		 
		if($.trim($(this).val()) == ''){
			alert('모두 입력하셔야 회원가입이 가능합니다.');
			$(this).focus();
			return false;
		}

		if (i == 0 || i == 1) {
			if ($.trim($(this).attr('id')) != $.trim($(this).val())) {
				alert('중복검사를 완료해주세요');
				$(this).focus();
				return false;
				}
		}else if(i == 6){
			alert('회원가입이 성공되었습니다');
			$('.signUp').submit();
		}

	 });
	 
	}
</script>
</head>
<body>
<form class="signUp" action="./singUpSc.do" method="post">
아이디 : <input type="text" name="user_email">
<button type="button" onclick="idchk(user_email)">중복확인</button><br/>
닉네임 : <input type="text" name="user_nickname">
<button type="button" onclick="nickchk(user_nickname)">중복확인</button><br/>
이름 : <input type="text" name="user_name"><br/>
비밀번호 :<input type="text" name="user_pw"><br/>
핸드폰 번호 : <input type="text" name="user_phone"><br/>
광고알림 : <input type="text" name="user_adchk" value="Y"><br/>
<input type="button" value="회원가입" onclick="efChk()">
</form>
</body>
</html>