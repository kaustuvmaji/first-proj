package com.example.demo.domain;

import java.io.Serializable;

/**
 * Employee domain class
 * 
 * @author KMaji
 *
 */
public class Employee implements Serializable {

	private static final long serialVersionUID = 7098286166079680079L;

	public Employee(Integer id, String name, String department) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
	}

	private Integer id;
	private String name;
	private String department;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", department=" + department + "]";
	}

	public Employee updateEmployee(String name2, String department2) {
		this.department = department2;
		this.name = name2;
		return this;
	}
}
