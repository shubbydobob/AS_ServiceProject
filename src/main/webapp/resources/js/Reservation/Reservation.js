   // Initialize flatpickr for date selection
        flatpickr("#concertDate", {
            dateFormat: "Y-m-d",
            minDate: "today", // Ensure the date is not in the past
            onChange: function(selectedDates, dateStr, instance) {
                if (dateStr) {
                    // Call a function to check available seats for the selected date
                    fetchAvailableSeats(dateStr);
                }
            }
        });

        // Function to simulate seat availability based on the selected date
        function fetchAvailableSeats(date) {
            // Simulate an API call or database query to get available seats for the selected date
            const availableSeats = {
                "2024-01-15": ["A1", "A2", "A3", "B1", "B2"],
                "2024-02-20": ["C1", "C2", "C3", "D1", "D2"]
            };

            // Check if there are available seats for the selected date
            if (availableSeats[date]) {
                const seatsContainer = document.getElementById("seatsContainer");
                seatsContainer.innerHTML = ""; // Clear previous options

                // Add available seats to the container
                availableSeats[date].forEach(function(seat) {
                    const seatDiv = document.createElement("div");
                    seatDiv.classList.add("seat-option");
                    seatDiv.textContent = seat;
                    seatDiv.onclick = function() {
                        toggleSeatSelection(seatDiv);
                    };
                    seatsContainer.appendChild(seatDiv);
                });

                // Show the seat selection and submit button
                document.getElementById("seatSelection").style.display = "block";
                document.getElementById("submitBtn").style.display = "inline-block";
            } else {
                alert("선택한 날짜에는 예약 가능한 좌석이 없습니다.");
                document.getElementById("seatSelection").style.display = "none";
                document.getElementById("submitBtn").style.display = "none";
            }
        }

        // Toggle seat selection
        function toggleSeatSelection(seatDiv) {
            seatDiv.classList.toggle("selected");
        }