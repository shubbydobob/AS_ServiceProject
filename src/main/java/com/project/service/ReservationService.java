package com.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.ReservationDTO;
import com.project.mapper.ReservationMapper;

@Service
public class ReservationService {

	@Autowired
	private ReservationMapper reservationMapper;

	// 예약 처리
	public void reserveSeats(String userName, List<Integer> seatIds, String date, String time) {
		for (int seatId : seatIds) {
			ReservationDTO reservationDTO = new ReservationDTO();
			reservationDTO.setUserName(userName);
			reservationDTO.setSeatId(seatId);
			reservationDTO.setDate(date);
			reservationDTO.setTime(time);

			// 예약 삽입
			reservationMapper.insertReservation(reservationDTO);

			// 좌석 상태를 예약 완료로 업데이트
			Map<String, Object> params = new HashMap<>();
			params.put("seatId", seatId);
			params.put("status", "reserved");
			reservationMapper.updateSeatStatus(params);
		}
	}

	// 특정 날짜와 시간에 예약된 좌석 조회
	public List<Integer> getReservedSeats(String date, String time) {
		Map<String, Object> params = new HashMap<>();
		params.put("date", date);
		params.put("time", time);
		return reservationMapper.getReservedSeats(params);
	}
}
