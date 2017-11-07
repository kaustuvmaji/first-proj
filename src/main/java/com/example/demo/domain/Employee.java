package com.example.demo.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Employee domain class
 * 
 * @author KMaji
 *
 */
@Document(collection = "employees")
public class Employee implements Serializable {

	private static final long serialVersionUID = 7098286166079680079L;

	@Id
	private String documentId;
	private String firstName;
	private String secondName;
	// @JsonSerialize(using = ToStringSerializer.class)
	// @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateOfBirth;
	private List<ContactDetail> contactDetails;
	private String department = "development";
	private int salary;

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public LocalDateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public List<ContactDetail> getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(List<ContactDetail> contactDetails) {
		this.contactDetails = contactDetails;
	}

	public Employee() {
	}

	public Employee(String firstName, String secondName, LocalDateTime dateOfBirth, List<ContactDetail> contactDetails,
			String department, int salary) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.dateOfBirth = dateOfBirth;
		this.contactDetails = contactDetails;
		this.department = department;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [" + (documentId != null ? "documentId=" + documentId + ", " : "")
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (secondName != null ? "secondName=" + secondName + ", " : "")
				+ (dateOfBirth != null ? "dateOfBirth=" + dateOfBirth + ", " : "")
				+ (contactDetails != null ? "contactDetails=" + contactDetails + ", " : "")
				+ (department != null ? "department=" + department + ", " : "") + "salary=" + salary + "]";
	}
}
