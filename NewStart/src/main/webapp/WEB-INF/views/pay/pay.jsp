<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 페이지</title>
<style type="text/css">
	input[radio]{
		 width:30px;height:30px;border:1px;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/Header.jsp"%>
	<form action="./payment.do" method="post">
	<section>
		<h1>캐시 충전</h1>
		<div>
			<img src="./img/cash.PNG" align="left">
			<div>
				<p>충전캐시</p>
				<div>
					<p>충전금액을 선택하세요.</p>
				</div>
				<div><!-- 1마넌 5마넌 10마넌 -->
					<div> <!-- 1마넌 -->
						<label>
							<div>
								<input type="radio" name="selCash" value="10000">
								<span>+1만원</span>
								<input type="radio" name="selCash" value="50000">
								<span>+5만원</span>
								<input type="radio" name="selCash" value="100000">
								<span>+10만원</span>
								<input type="radio" name="selCash" value="200000">
								<span>+20만원</span>
								<input type="radio" name="selCash" value="500000">
								<span>+50만원</span>
							</div>
						</label>
					</div>
				</div>
			</div>
			<div>
				<div>
					<p>결제수단</p>
					<ul>
						<li>
							<div>
								<label>
									<input type="radio" name="payment" value="토스">
									<span>토스</span>
								</label>
							</div>
						</li>
					</ul>
				</div>
				<div>
					<input type="submit" value="충전하기">
				</div>
			</div>
		</div>
	</section>
	</form>
</body>
</html>