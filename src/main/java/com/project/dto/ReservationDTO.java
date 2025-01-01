package com.project.dto;

public class ReservationDTO {

	private int id;
	private String userName;
	private int seatId;
	private String date;
	private String time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
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
		return "ReservationDTO [id=" + id + ", userName=" + userName + ", seatId=" + seatId + ", date=" + date
				+ ", time=" + time + "]";
	}

}
