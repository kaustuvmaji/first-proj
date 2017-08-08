package com.example.demo;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
public class DemoApplication {

	private final ArrayList<Employee> employees = new ArrayList<>();

	public DemoApplication() {
		employees.add(new Employee(1, "jacko", "dev"));
		employees.add(new Employee(2, "jacko 3", "dev"));
		employees.add(new Employee(3, "shelly", "qa"));
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/spring-boot-demo")
	@ResponseBody
	String home() {

		StringBuffer welcome = new StringBuffer();
		welcome.append("<center><h1>Welcome to Spring boot example. !!!</h1></center></br>");
		welcome.append(" This poc will cover spring boot app with scrud examples. </br>");
		welcome.append(" Default media type is jason </br>");
		welcome.append("List of services and urls are as following </br>");
		welcome.append("<ul>");
		welcome.append("<li> List of Employees url will be -> /spring-boot-demo/listOfEmployees </li>");
		welcome.append("<li> Employee Detail url will be id match -> /spring-boot-demo/employeeDetail </li>");
		welcome.append("<li> Add employee  url will be id match -> /spring-boot-demo/addEmployee </li>");
		welcome.append("</ul>");
		return welcome.toString();
	}

	@RequestMapping(value = "/spring-boot-demo/listOfEmployees")
	@ResponseBody
	ArrayList<Employee> getEmployees(Integer id) {
		return employees;
	}

	@RequestMapping(value = "/spring-boot-demo/employeeDetail", method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	Employee getEmployee(@RequestParam("id") Integer id) {
		Employee emp = null;

		for (Employee each : employees) {
			if (id.equals(each.id)) {
				emp = each;
			}
		}

		return emp;
	}

	@RequestMapping(value = "/spring-boot-demo/addEmployee", method = {
			org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	Employee getEmployees1(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam("department") String department) {
		Employee emp = new Employee(id, name, department);
		employees.add(emp);
		return emp;
	}

}

class Employee implements Serializable {

	private static final long serialVersionUID = 7098286166079680079L;

	public Employee(Integer id, String name, String department) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
	}

	Integer id;
	String name;
	String department;

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
		result = prime * result + ((department == null) ? 0 : department.hashCode());
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
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
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

}