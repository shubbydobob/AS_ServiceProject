<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<link rel="stylesheet" href="/resources/css/Sign/Sign.css">
<script src="/resources/js/Sign/Sign.js"></script>
</head>
<body>
	<div class="signup-container">
		<h2>회원가입</h2>
		<form id="signupForm" method="post" action="/Sign">
			<!-- 아이디 -->
			<div class="form-group">
				<label for="userId">아이디 (6-12자)</label> <input type="text"
					id="userId" name="userId" placeholder="아이디를 입력해주세요" required
					minlength="6" maxlength="12">
				<button type="button" id="checkId">중복 확인</button>
			</div>

			<!-- 비밀번호 -->
			<div class="form-group">
				<label for="password">비밀번호</label> <input type="password"
					id="password" name="password" placeholder="비밀번호를 입력해주세요" required
					minlength="8">
			</div>

			<!-- 이름 -->
			<div class="form-group">
				<label for="name">이름</label> <input type="text" id="name"
					name="name" placeholder="이름을 입력해주세요" required>
			</div>

			<!-- 성별 -->
			<div class="form-group">
				<label>성별</label> <label><input type="radio" name="gender"
					value="male" required> 남성</label> <label><input
					type="radio" name="gender" value="female"> 여성</label>
			</div>

			<!-- 휴대폰 번호 -->
			<div class="form-group">
				<label for="phone">휴대폰 번호</label> <input type="tel" id="phone"
					name="phone" placeholder="-를 빼고 입력해주세요" required
					pattern="\d{3}\d{3,4}\d{4}">
			</div>
			<!-- 이메일 -->
			<div class="form-group">
				<label for="email">이메일</label> <input type="email" id="email"
					name="email" placeholder="example@example.com" required>
			</div>

			<!-- 가입 버튼 -->
			<div class="form-actions">
				<button type="submit" class="signup-button">회원가입</button>
			</div>
		</form>
	</div>
</body>
</html>