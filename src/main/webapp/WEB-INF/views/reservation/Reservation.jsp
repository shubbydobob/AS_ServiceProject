<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>콘서트 예매</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.6.9/flatpickr.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.6.9/flatpickr.min.js"></script>
<link rel="stylesheet" href="/resources/css/Reservation/reservation.css">
<script src="/resources/js/Reservation/Reservation.js"></script>
</head>
<body>
	<main>
		<section class="date-selection">
			<h2>1️⃣ 날짜 및 시간 선택</h2>
			<label for="concert-date">날짜:</label> 
			<input type="date" id="concert-date"> 
			<label for="concert-time">시간:</label> 
			<select id="concert-time">
				<option value="18:00">18:00</option>
			</select>
		</section>
		<section class="seat-selection">
			<h2>2️⃣ 좌석 선택</h2>
			<div id="seat-map"></div>
		</section>
		<section class="summary">
			<h2>3️⃣ 선택 내역</h2>
			<div class="summary-content">
				<p>선택한 날짜: <span id="summary-date">-</span></p>
				<p>선택한 시간: <span id="summary-time">-</span></p>
				<p>선택한 좌석:</p>
				<ul id="selected-seats"></ul>
				<button id="reserve-button" disabled>예약하기</button>
			</div>
		</section>
	</main>
	<footer>
		<p>© 2024 콘서트 좌석 예약 시스템</p>
	</footer>
</body>
</html>