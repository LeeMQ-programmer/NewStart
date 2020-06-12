//---------------------------------------- 페이징 관련--------------------------------------------------
function pageList(){
//	alert("문제가 있어");
	var index = document.getElementById("index");		// 페이지 번호 : 1번페이지 2번페이지 등등
	var pageNum = document.getElementById("pageNum");	// 페이지 묶음 12345 시작번호~
	var listNum = document.getElementById("listNum");	//뿌려지는 Row의 개수
	index.value = 0;
	pageNum.value = 1;
	listNum.value= document.getElementById("list").value;
//	alert(index.value + " : " + pageNum.value + " : " + listNum.value);
	
	var selList = document.getElementById("list");
//	alert(selList.selectedIndex);
	
//	alert(selList.options[selList.selectedIndex].selected);
	pageAjax();
	
}



function pageFirst(){
//	alert("작동");
	var index = document.getElementById("index");
	var pageNum = document.getElementById("pageNum");
	index.value = 0;
	pageNum.value = 1;
	pageAjax();
}

function pageIndex(idx){
//	alert("작동");
	var index = document.getElementById("index");
	index.value = idx - 1;
	pageAjax();
}

function pagePre(num, pageList){
//	alert(pageNum, pageList);
	if(0 < num - pageList){
		alert("값이 들어와요");
		num -= pageList;
		var index = document.getElementById("index");
		var pageNum = document.getElementById("pageNum");
		pageNum.value = num;
		index.value = num-1;
	}
	pageAjax();
}

// 페이지번호, 페이지 나와있는 개수, 전체 글 갯수, 뿌려지는 글
function pageNext(num, pageList, total, listNum){
//	alert(pageNum +":"+ pageList +":"+ total +":"+ listNum);
	var index = Math.ceil(total/listNum); // 30/5  	6개의 페이지가 있음
	var max = Math.ceil(index/pageList); // 6/5   	두 그룹으로 나눌 수 있음 12345 / 6789
	// ceil은 최 근접 정수값 구하기
	
	
	if(max*pageList > num+pageList){
		num += pageList;
		
		var index = document.getElementById("index");
		var pageNum = document.getElementById("pageNum");
		
		pageNum.value = num;
		index.value = num -1;
		
	}
	pageAjax();
}


function pageLast(num, pageList, total, listNum){
	var idx = Math.ceil(total/listNum);
	var max = Math.ceil(idx/pageList);
	
	while(max*pageList > num+pageList){
		num += pageList;
	}

	var index = document.getElementById("index");
	var pageNum = document.getElementById("pageNum");
	pageNum.value = num;
	index.value = idx-1;

	pageAjax();
}





//----------------------------------------------- 삭제 -----------------------------------------------
function del(val){
	location.href="./del.do?seq="+val;
}


function checkAll(bool){
	var chkVals = document.getElementsByName("chkVal");
	for (var i = 0; i < chkVals.length; i++) {
		chkVals[i].checked = bool;
	}
}

function chkbox(){
	var chkVals = document.getElementsByName("chkVal");
	var n = 0;
	for (var i = 0; i < chkVals.length; i++) {
		if(chkVals[i].checked){
			n++;
		}
	}
	if(n>0){
		document.getElementById("frm").action="./multiDel.do";
		
	} else {
		swal("게시글 오류", "삭제할 값이 없습니다.");
		return false;
	}
	
}


//---------------------------------------------- 글 수정 --------------------------------------------------------

function modify(val){
//	alert(val);
	
	ajaxModify(val);
	
	$("#modify").modal();
}


var ajaxModify = function(val){
	$.ajax({
		url:"./modifyForm.do",
		type:"post",
		dataType:"json",
		data:{"seq":val},
		success:function(msg){
			  html = "<div class='form-group'>";
			  html += "<input type='hidden' name='seq' value='"+msg.seq+"'>";
			  html += "<label for='id'>아이디</label>";
			  html += "<p class='form-control'><strong>"+msg.id+"</strong></p>";
			  html += "</div>";
			  html += "";
			  html += "<div class='form-group'>";
			  html += "<label for='regdate'>작성일</label>";
			  html += "<p class='form-control'><strong>"+msg.regdate+"</strong></p>";
			  html += "</div>";
			  html += "";
			  html += "<div class='form-group'>";
			  html += "<label for='title'>제목</label>";
			  html += "<input type='text' class='form-control' id='title' name='title' value='"+msg.title+"' required='required'>";
			  html += "</div>";
			  html += "";
			  html += "<div class='form-group'>";
			  html += "<label for='content'>내용</label>";
			  html += "<textarea class='form-control' id='content' name='content' rows='5'>"+msg.content+"</textarea>";
			  html += "</div>";

			  html += "<div class='model-footer'>";
	          html += "<input class='btn btn-success' type='button' value='글수정 완료' onclick='update()'>";
	          html += "<input class='btn btn-info' type='reset' value='내용 초기화'>";
	          html += "<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>";
	          html += "</div>";
			  $("#frmModify").html(html);
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});
	
}

function update(){
	var frm = document.getElementById("frmModify");
	frm.action="./modify.do";
	var title = $("#title").val();
	if(title == ""){ // 자바랑 null 확인하는 방법이 다르다.
		swal("글 수정 오류", "제목은 필수입니다.");
	}else{
		frm.submit();
	}
}






// 첫번째 데이터는 lists로 하고 페이지 처리된 것들을 5개 칸에 5개의 글이 있을 때 10개의 글이 있으면 3페이지로 바꾸게 만들어주는 역할을 할 예정
function pageAjax(){
//	alert("아작아작");
	$.ajax({
		url:"./page.do",
		type:"post",
		async: true,
		data: $("#frm").serialize(), // 키는 벨류 형태로 자동으로 만들어준다.
		dataType: "json",
		success: function(msg){
//			alert(msg.lists[1].title);
//			alert(msg.row.total);
			$.each(msg, function(key,value){  // lists, {"",[]}  // row,{}
				var varHtml = "";
				var n = $(".table tr:eq(0) th").length; // 테이블에 첫번째 tr에 th의 개수
				if(key == "lists"){
					varHtml += "<tr>";
					varHtml += "	<th><input type='checkbox' onclick='checkAll(this.checked)'></th>";
					varHtml += "	<th>글번호</th>";
					varHtml += "	<th>제목</th>";
					varHtml += "	<th>작성자</th>";
					varHtml += "	<th>조회수</th>";
					if(n == 7){
						varHtml += "	<th>삭제 여부</th>";
					}
					varHtml += "	<th>작성일</th>";
					varHtml += "</tr>";
					
					// 위에 형태들을 보면
					//[{dto},{dto},{dto}]
					// 그래서 또 for문 필요
					$.each(value,function(k,v){
						varHtml += "<tr>";
						varHtml += "	<td><input type='checkbox' name='chkVal' value='"+v.seq+"'></td>";
						varHtml += "	<td>"+v.seq+"</td>";
						varHtml += "	<td><div class='panel-heading'><a data-toggle='collapse' data-parent='#accordion' href='#collapse"+v.seq+"' onclick='collapse(\""+v.seq+"\")'>"+v.title+"</a></div></td>";
						varHtml += "             <td>"+v.id+"</td>";
						varHtml += "	<td>"+v.readcount+"</td>";
						if(n == 7){
						varHtml += "	<td>"+v.delflag+"</td>";
						}
						varHtml += "	<td>"+v.regdate+"</td>";
						varHtml += "</tr>"; 
					
					
					
					
					// 글 내용 입력하기
					varHtml += "<tr>";
					varHtml += "	<td colspan='"+n+"'>";                                                                               		
					varHtml += "		<div id='collapse"+v.seq+"' class='panel-collapse'>";
					varHtml += "			<div class='form-group'>";
					varHtml += "			<label>내용</label>";
					varHtml += "			<textarea rows='7' class='form-control' readonly='readonly'>"+v.content+"</textarea>";
					varHtml += "			</div>";
					varHtml += "			<div>";
					varHtml += "				<div class ='form-group'>";
					if(v.id == v.meid){
					varHtml += "					<input class='btn btn-primary btn-center' type='button' value='글 수정' onclick='modify(\""+v.seq+"\")'>";
					}
					if(v.id == v.meid || n == 7){
					varHtml += "					<input class='btn btn-primary btn-center' type='button' value='글 삭제' onclick='del(\""+v.seq+"\")'>";
					}
					if(v.id == v.meid || n != 7){
					varHtml += "					<input class='btn btn-primary btn-center' type='button' value='답글' onclick='reply(\""+v.seq+"\")'>";
					}
					varHtml += "				</div>";
					varHtml += "			</div>";
					varHtml += "		</div>";             
					varHtml += "	</td>";
					varHtml += "</tr>";
					});
				} else {
					varHtml += "<li><a  href='#' onclick='pageFirst()'>&laquo;</a></li>";
					varHtml += "<li><a  href='#' onclick='pagePre("+value.pageNum+", "+value.pageList+")'>&lsaquo;</a></li>";
					for(var j = value.pageNum; j <= value.count; j++){
						varHtml += "	<li><a href='#' onclick='pageIndex("+j+")'>"+j+"</a></li>";
					}
					varHtml += "<li><a  href='#' onclick='pageNext("+value.pageNum+", "+value.pageList+", "+value.total+", "+value.listNum+")'>&rsaquo;</a></li>";
					varHtml += "<li><a  href='#' onclick='pageLast("+value.pageNum+", "+value.pageList+", "+value.total+", "+value.listNum+")'>&raquo;</a></li>";
			
				}
				
				
				if(key == "lists"){
					$("table > tbody").html(varHtml);
				} else{
					$(".pagination").html(varHtml);
				}
				
				
			});
		},
		error: function(){
			alert("데이터 처리 실패");
		} 
	});
}