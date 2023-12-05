package com.bezkoder.spring.security.jwt.payload.request;

import java.time.LocalTime;

public class WorktimeRequest {
	private LocalTime time;
	
	private Long workday;

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Long getWorkday() {
		return workday;
	}

	public void setWorkday(Long workday) {
		this.workday = workday;
	}

	
	
	
}
