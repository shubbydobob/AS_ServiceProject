package com.project.dto;

public class SeatDTO {

	private Integer id; // Make it Integer instead of int to allow null
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SeatDTO [id=" + id + ", status=" + status + "]";
	}

}
