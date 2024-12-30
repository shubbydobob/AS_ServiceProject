<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>콘서트 예매</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.6.9/flatpickr.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f9f9f9;
        color: #333;
        text-align: center;
    }

    main {
        padding: 2rem;
    }

    h2 {
        color: #3f51b5;
        margin-bottom: 1rem;
    }

    .date-selection label {
        display: inline-block;
        width: 80px;
        font-weight: bold;
    }

    .date-selection input,
    .date-selection select {
        padding: 0.5rem;
        margin: 0.5rem 0;
        font-size: 1rem;
    }

    #seat-map {
        display: grid;
        grid-template-columns: repeat(10, 40px);
        gap: 10px;
        justify-content: center;
        margin-top: 1rem;
    }

    .seat {
        width: 40px;
        height: 40px;
        border-radius: 5px;
        text-align: center;
        line-height: 40px;
        font-size: 0.9rem;
        cursor: pointer;
        font-weight: bold;
    }

    .seat.available {
        background-color: #4caf50;
        color: white;
    }

    .seat.reserved {
        background-color: #f44336;
        color: white;
        cursor: not-allowed;
    }

    .seat.selected {
        background-color: #ff9800;
        color: white;
    }

    .summary-content {
        background-color: #fff;
        padding: 1rem;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    button {
        background-color: #3f51b5;
        color: white;
        border: none;
        padding: 0.5rem 1rem;
        cursor: pointer;
        font-size: 1rem;
        margin-top: 1rem;
        border-radius: 5px;
    }

    button:disabled {
        background-color: #ccc;
        cursor: not-allowed;
    }
</style>
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
                <option value="20:00">20:00</option>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.6.9/flatpickr.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", () => {
            const seatMap = document.getElementById("seat-map");
            const selectedSeatsList = document.getElementById("selected-seats");
            const reserveButton = document.getElementById("reserve-button");
            const summaryDate = document.getElementById("summary-date");
            const summaryTime = document.getElementById("summary-time");
            const concertDate = document.getElementById("concert-date");
            const concertTime = document.getElementById("concert-time");

            const seats = Array.from({ length: 100 }, (_, i) => ({
                id: i + 1,
                status: Math.random() > 0.7 ? "reserved" : "available"
            }));

            seats.forEach(seat => {
                const seatElement = document.createElement("div");
                seatElement.classList.add("seat", seat.status);
                seatElement.textContent = seat.id;
                seatElement.dataset.id = seat.id;

                if (seat.status === "available") {
                    seatElement.addEventListener("click", () => toggleSeatSelection(seat, seatElement));
                }

                seatMap.appendChild(seatElement);
            });

            const selectedSeats = [];

            function toggleSeatSelection(seat, seatElement) {
                if (selectedSeats.includes(seat.id)) {
                    selectedSeats.splice(selectedSeats.indexOf(seat.id), 1);
                    seatElement.classList.remove("selected");
                } else {
                    selectedSeats.push(seat.id);
                    seatElement.classList.add("selected");
                }
                updateSummary();
            }

            function updateSummary() {
                selectedSeatsList.innerHTML = selectedSeats.map(seatId => `<li>좌석 ${seatId}</li>`).join("");
                reserveButton.disabled = selectedSeats.length === 0 || !concertDate.value || !concertTime.value;
                summaryDate.textContent = concertDate.value || "-";
                summaryTime.textContent = concertTime.value || "-";
            }

            reserveButton.addEventListener("click", () => {
                if (!concertDate.value || !concertTime.value) {
                    alert("날짜와 시간을 선택하세요!");
                    return;
                }
                alert(`다음 좌석이 예약되었습니다: ${selectedSeats.join(", ")}\n날짜: ${concertDate.value}\n시간: ${concertTime.value}`);
            });

            concertDate.addEventListener("change", updateSummary);
            concertTime.addEventListener("change", updateSummary);
        });
    </script>
</body>
</html>