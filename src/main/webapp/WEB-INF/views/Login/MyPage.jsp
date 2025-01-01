<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="/resources/css/Login/MyPage.css">
</head>
<body>
<h2>마이페이지</h2>
<div class="mypage-container">
    <div class="mypage-card">
        <h3>회원 정보</h3>
        <div class="info">
            <div class="info-item">
                <label for="userId">아이디:</label>
                <p id="userId">${user.userId}</p>
            </div>
            <div class="info-item">
                <label for="name">이름:</label>
                <p id="name">${user.name}</p>
            </div>
            <div class="info-item">
                <label for="gender">성별:</label>
                <p id="gender">${user.gender}</p>
            </div>
            <div class="info-item">
                <label for="phone">휴대폰 번호:</label>
                <p id="phone">${user.phone}</p>
            </div>
            <div class="info-item">
                <label for="email">이메일:</label>
                <p id="email">${user.email}</p>
            </div>
        </div>
        <button class="edit-button">수정하기</button>
        <a href="/Logout" class="logout-button">로그아웃</a>
    </div>
</div>
</body>
</html>