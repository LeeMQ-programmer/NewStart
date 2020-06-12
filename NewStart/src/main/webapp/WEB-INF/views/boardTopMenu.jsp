<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link type="text/css" rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/sweetalert.min.js">
	
</script>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">NewStart</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Main</a></li>
			<li><a href="#">공고 게시판</a></li>
			<li><a href="#">공지 게시판</a></li>
			<li><a href="./reviewMain.do">후기게시판</a></li>
			<li><a href="#">문의게시판</a></li>
			<li><a href="#">FAQ게시판</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
		<!-- 로그아웃 상태 -->
		<c:if test="${newstart eq null }">
			<li>
				<a href="./singUpform.do">
					<span class="glyphicon glyphicon-user"></span>Sign Up
				</a>
			</li>
			<li>
				<a href="./loginForm.do">
					<span class="glyphicon glyphicon-log-in"></span>Login
				</a>
			</li>
		</c:if>
		
		<!-- 로그인 상태면 MyPage -->
		<c:if test="${newstart ne null  }">
			 <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">마이 페이지 <span class="caret"></span></a>
        		<ul class="dropdown-menu">
        			<li><a href="./myPage.do">마이 페이지</a></li>
					<li><a href="./proFile.do">프로필</a></li>
					<c:if test="${newstart.user_grade eq 'A' }">
						<li><a href="./userMain.do">유저 전체 조회</a> </li>
					</c:if>
					<c:if test="${newstart.user_grade ne 'A' && newstart.user_tchk eq 'Y' }">
						<li><a href="./userMain.do">스위칭</a> </li>
					</c:if>
          			<li><a href="./logout.do" style="color:red;">로그아웃</a></li>
        		</ul>
      		</li>

			</c:if>
		</ul>
	</div>
</nav>

