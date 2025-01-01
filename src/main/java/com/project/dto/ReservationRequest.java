package com.project.dto;

import java.util.List;

public class ReservationRequest {

	private String userName;
	private List<Integer> seatIds;
	private String date;
	private String time;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Integer> getSeatIds() {
		return seatIds;
	}

	public void setSeatIds(List<Integer> seatIds) {
		this.seatIds = seatIds;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ReservationRequest [userName=" + userName + ", seatIds=" + seatIds + ", date=" + date + ", time=" + time
				+ "]";
	}

}
