/**
 * 
 */
package com.example.demo.application.io;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.domain.ContactDetail;
import com.example.demo.domain.util.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author kaustuv
 *
 */
public class EmployeeData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1602661192445633249L;

	public EmployeeData() {

	}

	public EmployeeData(String employeeId, String firstName, String secondName, LocalDateTime dateOfBirth,
			List<ContactDetail> contactDetails, String department, int salary, List<AssignmentDetail> assignments) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.secondName = secondName;
		this.dateOfBirth = dateOfBirth;
		this.contactDetails = contactDetails;
		this.department = department;
		this.salary = salary;
		this.assignments = assignments;
	}

	private String employeeId;
	private String firstName;
	private String secondName;
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateOfBirth;
	private List<ContactDetail> contactDetails;
	private String department = "development";
	private int salary;
	// @DBRef(lazy = true)
	private List<AssignmentDetail> assignments;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public List<ContactDetail> getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(List<ContactDetail> contactDetails) {
		this.contactDetails = contactDetails;
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

	public List<AssignmentDetail> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<AssignmentDetail> assignments) {
		this.assignments = assignments;
	}

	@Override
	public String toString() {
		return "EmployeeIO [" + (employeeId != null ? "employeeId=" + employeeId + ", " : "")
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (secondName != null ? "secondName=" + secondName + ", " : "")
				+ (dateOfBirth != null ? "dateOfBirth=" + dateOfBirth + ", " : "")
				+ (contactDetails != null ? "contactDetails=" + contactDetails + ", " : "")
				+ (department != null ? "department=" + department + ", " : "") + "salary=" + salary + ", "
				+ (assignments != null ? "assignments=" + assignments : "") + "]";
	}

}
