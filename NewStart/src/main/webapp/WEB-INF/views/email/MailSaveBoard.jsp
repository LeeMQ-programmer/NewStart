<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">


function checkAll(bool,name){
	//alert(name);
	var filter = document.getElementsByName('filter')[0];
	var f = '';
	var chkVals = document.getElementsByName(name);
	for (var i = 0; i < chkVals.length; i++) {
		chkVals[i].checked = bool;
		}
	
	 if(bool){
		if(name=='user_grade'){
			filter.value = 'MT/'+filter.value.split('/')[1];
		}else{
			filter.value = filter.value.split('/')[0]+'/NHL';
		}
	}else{
		if(name=='user_grade'){
			filter.value = 'X/'+filter.value.split('/')[1];
		}else{
			filter.value = filter.value.split('/')[0]+'/X';
		}
	} 
}

function chk(name){
	var chkbool = document.getElementsByName(name);
	var filter = document.getElementsByName('filter')[0];
	var f = "";
	var cnt = 0;
	for (var i = 1; i < chkbool.length; i++) {
		if(chkbool[i].checked){
			if(name == 'user_grade'){
				f = f+chkbool[i].value;
				cnt++;
			}else{
				f = f+chkbool[i].value;
			}
		}else{
			chkbool[0].checked = false;
		}
	}
		if(f == ""){
			f = 'X';
		}
		if(name == 'user_grade'){
			filter.value = f+'/'+filter.value.split('/')[1];
		}else{
			filter.value = filter.value.split('/')[0]+'/'+f;
		}
		
		if(cnt == chkbool.length-1){
			chkbool[0].checked = true;
		}
	
}


function subchk(bool,name){
	var chkbool = document.getElementsByName(name);
	if(!bool){
		chkbool[0].checked = false;
	}else{
		var cnt = 0;
		for (var i = 1; i < chkbool.length; i++) {
			if(chkbool[i].checked){
				cnt++;
			}
		}
		if(cnt == chkbool.length-1){
			chkbool[0].checked = true;
		}
	}
}

function searchFilter(){
	
	var filter = document.getElementsByName('filter')[0].value;
	var successchk = document.getElementsByName('successchk');
	var succhkidx = [];
	var firstDate = document.getElementsByName('firstDate')[0].value;
	var lastDate = document.getElementsByName('lastDate')[0].value;
	for (var i = 1; i < successchk.length; i++) {
		if(successchk[i].checked){
			succhkidx.push(successchk[i].value);			
		}
	}
	
	
	$.ajax({
		url : "./SelMailFilter.do",
		type : "post",
		traditional : true,
		data : {"filter":filter, "successchk" : succhkidx,"firstDate":firstDate,"lastDate":lastDate},
		dataType : "json",
		success: function(data){
			//alert(data[0].email_seq);
			//alert(data.length);

			var html = "";
			var div = document.getElementsByClassName('container')[0];
		html+=	"<h2>Basic Table</h2>";
		html+=	"<p>The .table class adds basic styling (light padding and only horizontal dividers) to a table:</p>";   
		html+=	"<table class='table'>";
		html+=	"<thead>";
		html+=	"<tr>";
		html+=	"<th>#</th>";
		html+=	"<th>카테고리</th>";
		html+=	"<th>제목</th>";
		html+=	"<th>상태</th>";
		html+=	"<th>수신자</th>";
		html+=	" <th>상태발송일</th>";
		html+=	"</tr>";
		html+=	"</thead>";
		html+=	"<tbody>";
		
		
		if(data.length > 0){
		
		
		 for (var i = 0; i < data.length; i++) {
				html+=	"<tr>";
				html+=	"<td>"+(data.length-i)+"</td>";
				html+=	"<td>";
				if(data[i].category_code == '1'){
				html+=	"광고성";
				}
				html+=	"</td>";
				html+=	"<td><a href=\"./SelMailDetail.do?seq="+data[i].email_seq+"\">"+data[i].email_title+"</a></td>";
				html+=	"<td>";
				switch(data[i].successchk){
				case 'S' : html+="대기 중"; break;
				case 'F' : html+="실패"; break;
				case 'Y' : html+="성공"; break;
				}
				html+=	"</td>";
				html+=	"<td>";
				
				var e = JSON.parse(data[i].user_email);
				if(e.length > 1){
					html += e[0].user_email + '외    '+(e.length-1);
				}else{
					html += e[0].user_email;
				}
				html+=	"</td>";
				html+=	"<td>"+data[i].regdate+"</td>";
				html+=	"</tr>";
		} 
		
		}else{
			html+=	"<tr>";
			html+=	"<td>글이 존재하지 않습니다...</td>";
			html+=	"</tr>";
		}
		html+=	"</tbody>";
		html+=	"</table>";
			
			div.innerHTML = html;
			
		},
		error: function(){
			alert('오류');
		}
	}); 
}



</script>
<body>
	<jsp:useBean id="emailparser" class="com.start.pro.util.Util_JSON"
		scope="page" />
	<jsp:setProperty property="key" name="emailparser" value="user_email" />


	유저 타입 :
	<div id="searchfilter">
		회원 등급 : <input type="checkbox" name="user_grade" value="A"
			onclick="checkAll(this.checked, this.name)"> 전체 <input
			type="checkbox" name="user_grade" value="M" onclick="chk(this.name)">
		멘티 <input type="checkbox" name="user_grade" value="T"
			onclick="chk(this.name)"> 강사 <br> 회원 상태 : <input
			type="checkbox" name="user_type" value="A"
			onclick="checkAll(this.checked, this.name)"> 전체 <input
			type="checkbox" name="user_type" value="N" onclick="chk(this.name)">
		일반 <input type="checkbox" name="user_type" value="H"
			onclick="chk(this.name)"> 휴면 <input type="checkbox"
			name="user_type" value="L" onclick="chk(this.name)"> 잠김 <br>
		상태 여부 : <input type="checkbox" name="successchk" value="A"
			onclick="checkAll(this.checked, this.name)"> 전체 <input
			type="checkbox" name="successchk" value="Y"
			onclick="subchk(this.checked,this.name)"> 성공 <input
			type="checkbox" name="successchk" value="S"
			onclick="subchk(this.checked,this.name)"> 대기 <input
			type="checkbox" name="successchk" value="F"
			onclick="subchk(this.checked,this.name)"> 실패 <br> 날짜 검색
		: <input type="date" name="firstDate"> ~ <input type="date"
			name="lastDate"> <input type="hidden" name="filter"
			value="X/X"> <input type="hidden" name="successchk"
			value="X/X">
		<button onclick="searchFilter()">검색</button>
	</div>






	<div class="container">
		<h2>Basic Table</h2>
		<p>The .table class adds basic styling (light padding and only
			horizontal dividers) to a table:</p>
		<table class="table">
			<thead>
				<tr>
					<th>#</th>
					<th>카테고리</th>
					<th>제목</th>
					<th>상태</th>
					<th>수신자</th>
					<th>상태발송일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${dtos}" varStatus="idx">
					<tr>
						<td>${fn:length(dtos)-idx.index}</td>
						<td><c:choose>
								<c:when test="${dto.category_code eq '1'}">광고성</c:when>
							</c:choose></td>
						<td><a href="./SelMailDetail.do?seq=${dto.email_seq}">${dto.email_title}</a></td>
						<td><c:choose>
								<c:when test="${dto.successchk eq 'S'}">대기 중</c:when>
								<c:when test="${dto.successchk eq 'F'}">실패</c:when>
								<c:when test="${dto.successchk eq 'Y'}">성공</c:when>
							</c:choose></td>
						<td><jsp:setProperty property="str" name="emailparser"
								value="${dto.user_email}" /> <jsp:getProperty property="str"
								name="emailparser" /></td>
						<td>${dto.regdate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>