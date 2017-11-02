package com.example.demo.domain;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "assignments")
public class Assignment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3610521799783024488L;
	@Id
	private ObjectId documentId;
	private String projectCode;
	private Date startDate;
	private Date endDate;
	private Long performanceMark;

	
	public ObjectId getDocumentId() {
		return documentId;
	}

	public void setDocumentId(ObjectId documentId) {
		this.documentId = documentId;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getPerformanceMark() {
		return performanceMark;
	}

	public void setPerformanceMark(Long performanceMark) {
		this.performanceMark = performanceMark;
	}

	public Assignment() {
	}

	public Assignment(String projectCode, Date startDate, Date endDate, Long performanceMark) {
		super();
		this.projectCode = projectCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.performanceMark = performanceMark;
	}

	@Override
	public String toString() {
		return "Assignment [" + (projectCode != null ? "projectCode=" + projectCode + ", " : "")
				+ (startDate != null ? "startDate=" + startDate + ", " : "")
				+ (endDate != null ? "endDate=" + endDate + ", " : "")
				+ (performanceMark != null ? "performanceMark=" + performanceMark : "") + "]";
	}

}
