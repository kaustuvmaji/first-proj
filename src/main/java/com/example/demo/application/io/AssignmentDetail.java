package com.example.demo.application.io;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.demo.domain.util.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class AssignmentDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3610521799783024488L;
	private String projectCode;
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime startDate;
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime endDate;
	private Long performanceMark;
	private String employeeId;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Long getPerformanceMark() {
		return performanceMark;
	}

	public void setPerformanceMark(Long performanceMark) {
		this.performanceMark = performanceMark;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public AssignmentDetail() {
	}

	public AssignmentDetail(String projectCode, LocalDateTime startDate, LocalDateTime endDate, Long performanceMark,
			String empployeeId) {
		super();
		this.projectCode = projectCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.performanceMark = performanceMark;
		this.employeeId = empployeeId;
	}

	@Override
	public String toString() {
		return "Assignment [" + (projectCode != null ? "projectCode=" + projectCode + ", " : "")
				+ (startDate != null ? "startDate=" + startDate + ", " : "")
				+ (endDate != null ? "endDate=" + endDate + ", " : "")
				+ (performanceMark != null ? "performanceMark=" + performanceMark : "") + "]";
	}

	public boolean updateAssignment(AssignmentDetail newOne) {
		boolean flag = false;
		if (null != newOne.getStartDate()) {
			this.setStartDate(newOne.getStartDate());
			flag = true;
		}
		if (null != newOne.getEndDate()) {
			this.setStartDate(newOne.getEndDate());
			flag = true;
		}
		if (null != newOne.getPerformanceMark()) {
			this.performanceMark = newOne.getPerformanceMark();
			flag = true;
		}
		return flag;
	}
}
