package com.example.demo.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.domain.util.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

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
	private ObjectId objectId;
	private String firstName;
	private String secondName;
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateOfBirth;
	private List<ContactDetail> contactDetails;
	private String department = "development";
	private int salary;
	@DBRef
	private List<Assignment> assignments;

	public ObjectId getObjectId() {
		return objectId;
	}

	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
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

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
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
			String department, int salary, List<Assignment> assignments) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.dateOfBirth = dateOfBirth;
		this.contactDetails = contactDetails;
		this.department = department;
		this.salary = salary;
		this.assignments = assignments;
	}

	@Override
	public String toString() {
		return "Employee [" + (objectId != null ? "objectId=" + objectId + ", " : "")
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (secondName != null ? "secondName=" + secondName + ", " : "")
				+ (dateOfBirth != null ? "dateOfBirth=" + dateOfBirth + ", " : "")
				+ (contactDetails != null ? "contactDetails=" + contactDetails + ", " : "")
				+ (department != null ? "department=" + department + ", " : "") + "salary=" + salary + ", "
				+ (assignments != null ? "assignments=" + assignments : "") + "]";
	}
}
