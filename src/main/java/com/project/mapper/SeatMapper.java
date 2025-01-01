package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dto.SeatDTO;

@Mapper
public interface SeatMapper {

	void insertSeat(SeatDTO seatDTO);

	// You can add other queries if needed, like fetching seats, etc.
	List<SeatDTO> findAllSeats();
}
