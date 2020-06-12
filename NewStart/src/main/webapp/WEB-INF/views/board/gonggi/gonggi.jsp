<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 게시판</title>
<link type="text/css" rel="stylesheet" href="./../../css/gonggi.css">
</head>
<body>
	<%@include file="/WEB-INF/Header.jsp"%>
	<table>
		<thead>
			<tr>
				<th>NO</th>
				<th>제목</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="num" value="${pageMaker.totalCount - ((pageMaker.cri.page - 1) * 10)}"></c:set>
			<c:forEach var="dto" items="${lists}" varStatus="vs">
				<tr>
					<td> 
						<c:if test='${dto.gi_category == "Y"}'>
							<img style="height: 16px; width: 16px;" src="./img/important.jpg">
						</c:if>
						<c:if test='${dto.gi_category == "N"}'>
<%-- 							${fn:length(lists) - vs.index} --%>
							${num}
						</c:if>
					</td>
					<td>
						<a href="./gonggiOneSel.do?seq=${dto.gi_seq}&category=${dto.gi_category}">
							${dto.gi_title}
						</a>
					</td>
					<td>${dto.gi_regdate}</td>
				</tr>
				<c:set var="num" value="${num-1}"></c:set>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<input class="btn btn-success" type="button" value="세글작성" onclick="writeForm()">
	</div>
	
	<script type="text/javascript">
		function writeForm(){
			location.href="./write.do";
		}
	</script>
	<div>
		<ul class="pagination">
		     <c:if test="${pageMaker.prev}">
		    	<li>
		    		<a href="gongGiList.do${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a>
		    	</li>
		    </c:if> 
		
		    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
		    	<li>
		    		<a href="gongGiList.do${pageMaker.makeQuery(idx)}">${idx}</a>
		    	</li>
		    </c:forEach>
		
		    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
		    	<li>
		    		<a href="gongGiList.do${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a>
		    	</li>
		    </c:if> 
		</ul>
	</div>
</body>
</html>