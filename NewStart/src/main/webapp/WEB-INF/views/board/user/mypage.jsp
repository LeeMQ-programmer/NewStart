<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${newstart.user_name}님의 마이페이지</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>
<script type="text/javascript">
// 	$(function(){
// 		$("#modify").on("click",function(){
// 			var a = $("#user_nickname").val();
// 			$.ajax({
// 				url :"updateNn.do",
// 				method : "post",
// 				dataType: "a",
// 				success : function(){
// 					alert("수정 성공");
// 				},
// 				error : function(){
// 					alert("수정 실패");
// 				}
// 			});
// 		});
		
// 	});
	
	
	function modifyMyPage(){
		var user_nickname = $("#user_nickname").val();
		var user_phone = $("#user_phone").val();
		
// 		alert(user_nickname+":   "+user_phone);
		$.ajax({
			url :"updateMyPage.do",
			method : "post",
			dataType: {"user_nickname":user_nickname,"user_phone":user_phone},
			success : function(dto){
				alert("수정 성공");
				alert(dto);
			},
			error : function(){
				alert("수정 실패");
			}
		});
	}
	function requestTeacher(user_seq){
		location.href="./tReq.do"
	}
	
	
	
</script>
<h1>${newstart.user_name}님의mypage</h1>
<div class="container">
${newstart }
  <form action="./updateMyPage.do" method="post">
  <input type="text" name="user_seq" id="user_seq" value="${newstart.user_seq }">
    <div class="form-group">
      <label for="usr">이름 :</label>
      <input type="text" class="form-control" id="usr" value="${newstart.user_name }">
    </div>
    <div class="form-group">
      <label for="user_nickname">닉네임 :</label>
      <input type="text" class="form-control" name="user_nickname" id="user_nickname" value="${newstart.user_nickname }">
    </div>
    <div class="form-group">
      <label for="user_phone">전화번호 :</label>
      <input type="text" class="form-control" name="user_phone" id="user_phone" value="${newstart.user_phone }">
    </div>
    <div class="form-group">
     	광고성 수신 여부 :  <input type="checkbox"  name="user_adchk" id="user_adchk" value="${newstart.user_adchk }">
    </div>
    <div class="form-group">
      <label for="user_tchk">강사 인증 :</label>
      <input type="button" name="user_tchk" id="user_tchk" value="${newstart.user_tchk }" onclick="requestTeacher(${newstart.user_tchk })">
    </div>
    
    <input type="submit"value="수정하기">
    
  </form>
</div>
</body>
</html>