package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ReservationRequest;
import com.project.dto.SeatDTO;
import com.project.service.ReservationService;
import com.project.service.SeatService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private SeatService seatService;

	// Add seats (POST /reservation/addSeats)
	@PostMapping("/addSeats")
	public ResponseEntity<String> addSeats(@RequestBody List<SeatDTO> seats) {
		try {
			seatService.addSeats(seats); // Insert seats
			return ResponseEntity.ok("{\"success\": true, \"message\": \"좌석이 성공적으로 추가되었습니다.\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"success\": false, \"message\": \"좌석 추가에 실패했습니다.\"}");
		}
	}

	// Seat reservation (POST /reservation/reserve)
	@PostMapping("/reserve")
	public ResponseEntity<String> reserveSeats(@RequestBody ReservationRequest reservationRequest) {
		try {
			reservationService.reserveSeats(reservationRequest.getUserName(), reservationRequest.getSeatIds(),
					reservationRequest.getDate(), reservationRequest.getTime());
			return ResponseEntity.ok("{\"success\": true, \"message\": \"예약이 완료되었습니다.\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"success\": false, \"message\": \"예약에 실패했습니다.\"}");
		}
	}

	// Get reserved seats (GET /reservation/reserved-seats)
	@GetMapping("/reserved-seats")
	public ResponseEntity<List<Integer>> getReservedSeats(@RequestParam String date, @RequestParam String time) {
		try {
			List<Integer> reservedSeats = reservationService.getReservedSeats(date, time);
			return ResponseEntity.ok(reservedSeats);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}