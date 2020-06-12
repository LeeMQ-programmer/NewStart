<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 내역 페이지</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">
	$(function(){
		$("#selectCa").change(function(){
			var sel = $("#selectCa").val();
			$.ajax({
				url : "./listChange.do",
				type : "post",
				data : "sel="+sel,
				success : function(data){
					if(data == "true"){
						$('#second').hide();
						$('#first').show();
					}else{
						$('#second').show();
						$('#first').hide();
					}
				},
				error : function(request, status, error){
					alert("code: " + request.status + "\n message: " + request.responseText + "\n error: " + error);
				}
			});
				
		});
	});
	
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>
	<form action="./cancel.do" method="post">
	<div>
		<select id="selectCa" class="form-control input-xs">
			<option value="cash" selected="selected">결제</option>
			<option value="ref">환불</option>
		</select>
	</div>
	<div>
		<div id="first">
		<table class="table" id="cash">
			<thead>
				<tr>
					<th>NO</th>
					<th>유저 번호</th>
					<th>결제 금액</th>
					<th>결제일</th>
					<c:if test='${user.user_grade == "A" }'>
						<th>토큰</th>
						<th>환불여부</th>
						<th>환불</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:set var="num" value="${pageMaker.totalCount - ((pageMaker.cri.page - 1) * 10)}"></c:set>
				<c:forEach var="list" items="${lists}" varStatus="vs">
					<tr>
						<td><input type="hidden" name="seq" value="${list.pay_seq }">${num}&nbsp;&nbsp;</td>
						<td>${user.user_seq }&nbsp;&nbsp;</td>
						<td>${list.pay_amount }&nbsp;&nbsp;</td>
						<td>${list.pay_date }&nbsp;&nbsp;</td>
						<c:if test='${user.user_grade == "A" }'>
							<td><input type="hidden" name="token" value="${list.pay_token }">${list.pay_token }</td>
							<td>${list.delrefund}&nbsp;&nbsp;</td>
							<c:if test='${list.delrefund == "Y"}'>
								<td><input type="submit" value="환불">&nbsp;&nbsp;</td>
							</c:if>
						</c:if>
					</tr>
					<c:set var="num" value="${num-1}"></c:set>
				</c:forEach>
				
			</tbody>
		</table>
		<div>
			<ul class="pagination">
			     <c:if test="${pageMaker.prev}">
			    	<li>
			    		<a href="payList.do${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a>
			    	</li>
			    </c:if> 
			
			    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
			    	<li>
			    		<a href="payList.do${pageMaker.makeQuery(idx)}">${idx}</a>
			    	</li>
			    </c:forEach>
			
			    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			    	<li>
			    		<a href="payList.do${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a>
			    	</li>
			    </c:if> 
			</ul>
		</div>
		</div>
		</form>
		
		<div id="second" style="display: none;"> 
		<table class="table" id="ref">
			<thead>
				<tr>
					<th>NO</th>
					<th>유저 번호</th>
					<th>환불일</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="num" value="${pageMaker1.totalCount - ((pageMaker1.cri.page - 1) * 10)}"></c:set>
				<c:forEach var="list" items="${listss}" varStatus="vs">
					<tr>
						<td><input type="hidden" name="seq" value="${list.refund_seq }">${num}&nbsp;&nbsp;</td>
						<td>${list.user_seq }&nbsp;&nbsp;</td>
						<td>${list.refund_date }&nbsp;&nbsp;</td>
					</tr>
					<c:set var="num" value="${num-1}"></c:set>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<ul class="pagination">
			     <c:if test="${pageMaker1.prev}">
			    	<li>
			    		<a href="payList.do${pageMaker1.makeQuery(pageMaker1.startPage - 1)}">이전</a>
			    	</li>
			    </c:if> 
			
			    <c:forEach begin="${pageMaker1.startPage}" end="${pageMaker1.endPage}" var="idx">
			    	<li>
			    		<a href="payList.do${pageMaker1.makeQuery(idx)}">${idx}</a>
			    	</li>
			    </c:forEach>
			
			    <c:if test="${pageMaker1.next && pageMaker1.endPage > 0}">
			    	<li>
			    		<a href="payList.do${pageMaker1.makeQuery(pageMaker1.endPage + 1)}">다음</a>
			    	</li>
			    </c:if> 
			</ul>
		</div>
		</div>
		
	</div>
	
</body>
</html>