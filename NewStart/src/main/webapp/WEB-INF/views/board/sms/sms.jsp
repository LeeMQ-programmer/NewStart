<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sms</title>
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="./css/bootstrap-theme.css">
<link type="text/css" rel="stylesheet" href="./css/sweetalert.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/sweetalert.js"></script>
<body>
<div id="container" style="width:500px; padding:20px; margin: 10px auto; text-align: center; border:2px solid skyblue;"> 
	<h1>SMS인증 할 사람~</h1>
	<!-- 문자보내는 폼 -->
	<form id="smsForm" style="padding: 5px; margin: 5px;">
		<input type="text" name="phone" id="phone" placeholder="전화번호 입력 ( - 포함 )" />  <!-- 전화번호 입력시 반드시 - 포함 -->
		<input type="button" class="btn btn-primary" onclick="sendSms();" id="send" value="전송"/> <br>
		
	</form>
</div> 
  <script>
    function sendSms() { // 인증번호 전송 ajax 처리
    	$.ajax({ 
    		url: "./sendSms.do",
    		data: "phone="+$("#phone").val(), 
    		type: "post", 
    		success: function(msg) { 
    			if (msg == "true") { 
    				console.log(msg);
    				swal("SMS인증","인증번호 전송 성공"); 
    				$("#send").val("재전송");
    			} else { 
    				swal("SMS인증 오류","인증번호 전송 실패","error"); 
    			} 
    		},
    		error: function(){
    			swal("오류","잘못된 요청입니다.","warning");
    		}
    	}); 
    } 
    function numberCheck(){ // 인증번호 체크 ajax 처리
    	$.ajax({ 
    		url: "./smsCheck.do", 
    		type: "post", 
    		data: "sms="+$("#sms").val(),
    		success: function(msg) { 
    			if (msg == "ok") { 
    				swal("SMS인증","번호 인증 성공"); 
//     				location.href="./sign.do"; // 인증성공 후 이동할 페이지
    			} else { 
    				swal("SMS인증 오류","번호 인증 실패","warning"); 
    			} 
    		},
    		error: function(){
    			swal("잘못된 요청입니다.");
    		}
    	}); 
    }
  </script>
</body>
</html>