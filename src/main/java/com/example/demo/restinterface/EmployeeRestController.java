/**
 * 
 */
package com.example.demo.restinterface;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.io.EmployeeIO;
import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

/**
 * Employee management services
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
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@ApiOperation(value = "All employee details", notes = "Avialable employees", response = EmployeeIO.class, responseContainer = "List", produces = MediaType.APPLICATION_JSON_VALUE, authorizations = {
			@Authorization(value = "basic"/* "security scope bounded to 'ROLE_USER' users " */) })
	Collection<EmployeeIO> getEmployees() {
		Collection<EmployeeIO> employeesResponse = new ArrayList<>();
		for(Employee each: employeeService.getEmployees()) {
			employeesResponse.add(new EmployeeIO(each.getId(), each.getName(), each.getDepartment()));
		}
		return employeesResponse;
	}

	@RequestMapping(value = "/employeeDetail", method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@ApiOperation(value = "Employee detail by id", notes = "Employee detail", response = EmployeeIO.class, authorizations = {
			@Authorization(value = "security scope bounded to 'ROLE_USER' users ") })
	EmployeeIO getEmployee(@RequestParam("id") Integer id) {
		Employee emp = employeeService.getEmployee(id);
		return new EmployeeIO(emp.getId(), emp.getName(), emp.getDepartment()) ;
	}

	@RequestMapping(value = "/addEmployee", method = {
			org.springframework.web.bind.annotation.RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Add Employee", notes = "Employee add", response = EmployeeIO.class, produces = MediaType.APPLICATION_JSON_VALUE, authorizations = {
			@Authorization(value = "security scope bounded to 'ROLE_ADMIN' users ") })
	@PreAuthorize("hasAuthority('ROLE_USER')")
	EmployeeIO addEmployee(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam(value = "department", required = false, defaultValue = "dev") String department) {
		Employee emp = new Employee(id, name, department);
		employeeService.addEmployee(id, name, department);
		return new EmployeeIO(emp.getId(), emp.getName(), emp.getDepartment()) ;
	}

	@RequestMapping(value = "/deleteEmployee", method = {
			org.springframework.web.bind.annotation.RequestMethod.DELETE })
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@ApiOperation(value = "Employee deletion", notes = "Employee delete", authorizations = {
			@Authorization(value = "security scope bounded to 'ROLE_USER' users ") })
	void deleteEmployee(@RequestParam("id") Integer id) {
		/* return */ employeeService.deleteEmployee(id);
	}

	@RequestMapping(value = "/updateEmployee", method = {
			org.springframework.web.bind.annotation.RequestMethod.PUT }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	// @Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@ApiOperation(value = "Employee details update", notes = "present scope: Only department details updation", response = EmployeeIO.class, produces = MediaType.APPLICATION_JSON_VALUE, authorizations = {
			@Authorization(value = "security scope bounded to 'ROLE_USER' users ") })
	EmployeeIO updateEmployee(@RequestParam("id") Integer id, @RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "department", required = true) String department) {
		Employee emp = new Employee(id, name, department);
		employeeService.updateEmployee(id, name, department);
		return new EmployeeIO(emp.getId(), emp.getName(), emp.getDepartment()) ;
	}

}
