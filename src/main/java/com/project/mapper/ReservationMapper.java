package com.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.dto.ReservationDTO;

@Mapper
public interface ReservationMapper {

	void insertReservation(ReservationDTO reservationDTO); // 예매하기

	List<Integer> getReservedSeats(Map<String, Object> params); // 선택한 좌석 배열

	void updateSeatStatus(Map<String, Object> params); // 예약 후 좌석 업데이트
}
