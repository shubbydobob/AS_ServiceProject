document.addEventListener("DOMContentLoaded", () => {
    const seatMap = document.getElementById("seat-map");
    const selectedSeatsList = document.getElementById("selected-seats");
    const reserveButton = document.getElementById("reserve-button");
    const summaryDate = document.getElementById("summary-date");
    const summaryTime = document.getElementById("summary-time");
    const concertDate = document.getElementById("concert-date");
    const concertTime = document.getElementById("concert-time");

    // 날짜 초기 설정
    const predefinedDate = "2024-01-15";
    concertDate.value = predefinedDate;

 const seats = Array.from({ length: 100 }, (_, i) => ({
    id: i + 1,  // 좌석 ID (1부터 100까지)
    status: "available" // 모든 좌석을 "available" 상태로 설정
}));

// 예시로 출력된 좌석 상태 확인
console.log(seats);


    // 서버에 좌석 데이터를 전송하는 예시 (fetch API 사용)
     fetch("/reservation/addSeats", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(seats) // Send seat data as JSON
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            console.log("Seats have been successfully added.");
        } else {
            console.log("Failed to add seats:", data.message);
        }
    })
    .catch(error => {
        console.error("Server error:", error);
    });

    // 좌석 요소 생성
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

        const userName = "${name}"; // JSP에서 전달된 사용자 이름을 JavaScript 변수에 저장
        const date = concertDate.value;
        const time = concertTime.value;

        const reservationData = {
            userName: userName,
            seatIds: selectedSeats,
            date: date,
            time: time
        };

        fetch("/reservation/reserve", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(reservationData)
        })
        .then(response => response.json())  // 응답을 JSON으로 파싱
        .then(data => {
            if (data.success) {
                alert("예약이 완료되었습니다.");
            } else {
                alert("예약 실패: " + data.message);
            }
        })
        .catch(error => {
            alert("서버 오류: " + error);
        });
    });

   // Initialize flatpickr for date and time selection
    flatpickr("#concertDate", {
        dateFormat: "Y-m-d",
        minDate: "today", // Ensure the date is not in the past
        onChange: function(selectedDates, dateStr) {
            if (dateStr) {
                fetchReservedSeats(dateStr, concertTime.value); // Update seat availability based on selected date
            }
        }
    });

    flatpickr("#concertTime", {
        enableTime: true,
        noCalendar: true,
        dateFormat: "H:i",
        onChange: function(selectedDates, dateStr) {
            if (dateStr) {
                fetchReservedSeats(concertDate.value, dateStr); // Update seat availability based on selected time
            }
        }
    });

    // Function to fetch reserved seats from the server
    function fetchReservedSeats(date, time) {
        fetch(`/reservation/reserved-seats?date=${date}&time=${time}`)
            .then(response => response.json())
            .then(reservedSeats => {
                // Update seat map based on reserved seats
                seats.forEach(seat => {
                    const seatElement = document.querySelector(`.seat[data-id='${seat.id}']`);
                    if (reservedSeats.includes(seat.id)) {
                        seatElement.classList.add("unavailable");
                        seatElement.classList.remove("available");
                        seatElement.removeEventListener("click", () => toggleSeatSelection(seat, seatElement));
                    } else {
                        seatElement.classList.add("available");
                        seatElement.classList.remove("unavailable");
                        seatElement.addEventListener("click", () => toggleSeatSelection(seat, seatElement));
                    }
                });
            })
            .catch(error => {
                console.error("Error fetching reserved seats:", error);
            });
    }

    // Initial seat state update based on current selected date and time
    updateSummary();
});