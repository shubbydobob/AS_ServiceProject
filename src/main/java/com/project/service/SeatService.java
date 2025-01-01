package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.SeatDTO;
import com.project.mapper.SeatMapper;

@Service
public class SeatService {

	@Autowired
	private SeatMapper seatMapper;

	// 좌석 데이터를 삽입하는 메서드
	public void addSeats(List<SeatDTO> seats) {
		for (SeatDTO seatDTO : seats) {
			seatMapper.insertSeat(seatDTO); // SeatMapper를 통해 데이터 삽입
		}
	}

	// Optionally, you can add other methods to retrieve seats, etc.
	public List<SeatDTO> getAllSeats() {
		return seatMapper.findAllSeats();
	}
}
