/**
 * 
 */
package com.example.demo.restinterface;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

/**
 * Employee management services
 * 
 * @author KMaji
 *
 */
@RequestMapping("/employee/services")
@Api(value = "/employee/services", description = "Employee management services", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController()
public class EmployeeRestController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/listOfEmployee", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "All employee details", notes = "Avialable employees", response = Employee.class, responseContainer = "List", produces = MediaType.APPLICATION_JSON_VALUE, authorizations = {
			@Authorization(value = "basic"/* "security scope bounded to 'ROLE_USER' users " */) })
	Collection<Employee> getEmployees() {
		return employeeService.getEmployees();
	}

	@RequestMapping(value = "/employeeDetail", method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "Employee detail by id", notes = "Employee detail", response = Employee.class, authorizations = {
			@Authorization(value = "security scope bounded to 'ROLE_ADMIN' users ") })
	Employee getEmployee(@RequestParam("id") Integer id) {
		return employeeService.getEmployee(id);
	}

	@RequestMapping(value = "/addEmployee", method = {
			org.springframework.web.bind.annotation.RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Add Employee", notes = "Employee add", response = Employee.class, produces = MediaType.APPLICATION_JSON_VALUE, authorizations = {
			@Authorization(value = "security scope bounded to 'ROLE_ADMIN' users ") })
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	Employee addEmployees(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam(value = "department", required = false, defaultValue = "dev") String department) {
		return employeeService.addEmployees(new Employee(id, name, department));
	}

	@RequestMapping(value = "/deleteEmployee", method = {
			org.springframework.web.bind.annotation.RequestMethod.DELETE })
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "Employee deletion", notes = "Employee delete", authorizations = {
			@Authorization(value = "security scope bounded to 'ROLE_ADMIN' users ") })
	void deleteEmployee(@RequestParam("id") Integer id) {
		/* return */ employeeService.deleteEmployee(id);
	}

	@RequestMapping(value = "/updateEmployee", method = {
			org.springframework.web.bind.annotation.RequestMethod.PUT }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	// @Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "Employee details update", notes = "present scope: Only department details updation", response = Employee.class, produces = MediaType.APPLICATION_JSON_VALUE, authorizations = {
			@Authorization(value = "security scope bounded to 'ROLE_ADMIN' users ") })
	Employee updateEmployee(@RequestParam("id") Integer id, @RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "department", required = true) String department) {
		return employeeService.updateEmployee(id, name, department);
	}

}
