/**
 * 
 */
package com.example.demo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/employee/services")
@RestController()
public class EmployeeRestController {

	private final CopyOnWriteArrayList<Employee> employees = new CopyOnWriteArrayList<>();

	public EmployeeRestController() {
		employees.add(new Employee(1, "Josh", "dev"));
		employees.add(new Employee(2, "Rev", "qa"));
		employees.add(new Employee(3, "Kaustuv", "dev"));
		employees.add(new Employee(4, "Sam", "Hr"));
	}

	@RequestMapping("/")
	@ResponseBody
	String home() {
		StringBuffer welcome = new StringBuffer();
		welcome.append("<center><h1>Welcome to Spring boot example. !!!</h1></center></br>");
		welcome.append(" This poc will cover spring boot app with scrud examples. </br>");
		welcome.append(" Default media type is jason </br>");
		welcome.append("List of services and urls are as following </br>");
		welcome.append("<ul>");
		welcome.append("<li> List of Employees url will be  Example of Read -> /listOfEmployee </li>");
		welcome.append("<li> Employee Detail url will be id match Example of Read -> /employeeDetail </li>");
		welcome.append("<li> Add employee  url will be id match Example of Create-> /addEmployee </li>");
		welcome.append("<li> Add employee  url will be id match Example of Update-> /updateEmployee </li>");
		welcome.append("<li> Add employee  url will be id match Example of delete-> /deleteEmployee </li>");
		welcome.append("</ul>");
		welcome.append("Date -> " + LocalDate.now() + " :: " + LocalTime.now());
		return welcome.toString();
	}

	@RequestMapping(value = "/listOfEmployee",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	CopyOnWriteArrayList<Employee> getEmployees(Integer id) {
		return employees;
	}

	@RequestMapping(value = "/employeeDetail", method = { org.springframework.web.bind.annotation.RequestMethod.GET })
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

	@RequestMapping(value = "/addEmployee", method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	Employee getEmployees1(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam(value = "department", required = false, defaultValue = "dev") String department) {
		Employee emp = new Employee(id, name, department);
		employees.add(emp);
		return emp;
	}

	@RequestMapping(value = "/deleteEmployee", method = { org.springframework.web.bind.annotation.RequestMethod.DELETE})
	@ResponseBody
	Employee deleteEmployee(@RequestParam("id") Integer id) {

		for (Employee emp : employees) {
			if (null != emp) {
				if (id.equals(emp.getId())) {
					employees.remove(emp);
					return emp;

				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/updateEmployee", method = {
			org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	Employee updateEmployee(@RequestParam("id") Integer id, @RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "department", required = true) String department) {
		for (Employee emp : employees) {
			if (emp != null) {
				if (id.equals(emp.getId()) || name.equalsIgnoreCase(emp.getName())) {
					emp.setDepartment(department);
					return emp;
				}
			}
		}
		return null;
	}

}
