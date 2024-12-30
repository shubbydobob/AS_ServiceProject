<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/home.css">
<script src="/resources/js/home.js"></script>
<script>
document.addEventListener("DOMContentLoaded", () => {
    // 로그인 여부 확인 및 처리
    window.handleReservation = function(url) {
        const isLoggedIn = ${not empty isLoggedIn ? 'true' : 'false'}; // JSTL 변수를 JavaScript로 전달
        if (isLoggedIn) {
            window.location.href = url; // 로그인 상태라면 예약 페이지로 이동
        } else {
            alert("로그인이 필요합니다. 로그인 페이지로 이동합니다."); // 알림창 띄우기
            window.location.href = "/Login"; // 로그인 페이지로 리다이렉트
        }
    };
});
</script>
<title>콘서트 예매</title>
</head>
<body>
	<header>
		<div class="container">
			<h1>
				<a href="/home">🎵 콘서트 예매</a>
			</h1>
			<nav>
				<ul class="main-menu">
					<li class="menu-item"><span>콘서트 정보</span>
						<ul class="sub-menu">
							<li><a href="#">콘서트 일정</a></li>
						</ul></li>
					<li class="menu-item"><span>예매하기</span>
						<ul class="sub-menu">
							<li><a href="/check-reservation">예약 조회</a></li>
							<li><a href="/cancel-reservation">예약 취소</a></li>
						</ul></li>
					<li class="menu-item"><c:choose>
							<c:when test="${not empty isLoggedIn}">
								<span>사용자 메뉴</span>
								<ul class="sub-menu">
									<li><a href="/Logout">로그아웃</a></li>
									<li><a href="/profile">내 정보</a></li>
								</ul>
							</c:when>
							<c:otherwise>
								<span>로그인</span>
								<ul class="sub-menu">
									<li><a href="/Sign">회원가입</a></li>
									<li><a href="/Login">로그인</a></li>
								</ul>
							</c:otherwise>
						</c:choose></li>
				</ul>
			</nav>
		</div>
	</header>
	<main>
		<section class="hero">
			<div class="hero-content">
				<h2>🎤 실시간 콘서트 예약</h2>
				<p>좋아하는 아티스트의 공연을 놓치지 마세요! 지금 바로 예약하세요.</p>
			</div>
		</section>
		<section class="concert-info">
			<h2>🎶 추천 콘서트</h2>
			<div class="concert-list">
				<div class="concert-item">
					<h3>Artist A</h3>
					<p>날짜: 2024-01-15</p>
					<p>장소: 서울 공연장</p>
					<a href="javascript:void(0);"
						onclick="handleReservation('/reservation?artist=A')"
						class="btn-secondary">예매하기</a>
				</div>
				<div class="concert-item">
					<h3>Artist B</h3>
					<p>날짜: 2024-02-20</p>
					<p>장소: 부산 공연장</p>
					<a href="javascript:void(0);"
						onclick="handleReservation('/reservation?artist=B')"
						class="btn-secondary">예매하기</a>
				</div>
			</div>
		</section>
	</main>
	<footer>
		<div class="container">
			<p>© 2024 콘서트 예매 시스템. 모든 권리 보유.</p>
		</div>
	</footer>
</body>
</html>