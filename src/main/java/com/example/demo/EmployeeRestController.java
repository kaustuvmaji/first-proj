/**
 * 
 */
package com.example.demo;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Employee management services
 * 
 * @author KMaji
 *
 */
@RequestMapping("/employee/services")
@Api(value = "/employee/services", description = "Employee management services", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController()
public class EmployeeRestController {

	private final CopyOnWriteArrayList<Employee> employees = new CopyOnWriteArrayList<>();

	public EmployeeRestController() {
		employees.add(new Employee(1, "Josh", "dev"));
		employees.add(new Employee(2, "Rev", "qa"));
		employees.add(new Employee(3, "Kaustuv", "dev"));
		employees.add(new Employee(4, "Sam", "Hr"));
	}

	@RequestMapping(value = "/listOfEmployee", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "All employee details", notes = "Avialable employees", response = Employee.class, responseContainer = "List", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	CopyOnWriteArrayList<Employee> getEmployees() {
		return employees;
	}

	@RequestMapping(value = "/employeeDetail", method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	@ApiOperation(value = "Employee detail by id", notes = "Employee detail", response = Employee.class)
	Employee getEmployee(@RequestParam("id") Integer id) {
		Employee emp = null;

		for (Employee each : employees) {
			if (id.equals(each.id)) {
				emp = each;
			}
		}

		return emp;
	}

	@RequestMapping(value = "/addEmployee", method = { /* org.springframework.web.bind.annotation.RequestMethod.GET, */
			org.springframework.web.bind.annotation.RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Add Employee", notes = "Employee add", response = Employee.class, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Employee addEmployees(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam(value = "department", required = false, defaultValue = "dev") String department) {
		Employee emp = new Employee(id, name, department);
		employees.add(emp);
		return emp;
	}

	@RequestMapping(value = "/deleteEmployee", method = {
			org.springframework.web.bind.annotation.RequestMethod.DELETE }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Employee deletion", notes = "Employee delete", response = Employee.class, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

	@RequestMapping(value = "/updateEmployee", method = { /*org.springframework.web.bind.annotation.RequestMethod.GET,*/
			org.springframework.web.bind.annotation.RequestMethod.PUT }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Employee details update", notes = "present scope: Only department details updation", response = Employee.class, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
