package com.example.demo.restinterface;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.WorkForceApplicationService;
import com.example.demo.application.io.AssignmentDetail;
import com.example.demo.application.io.EmployeeData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RequestMapping("/workforce/services")
@Api(value = "/workforce/services", description = "Employee management services", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController()
public class WorkForceManagementController {

	@Autowired
	WorkForceApplicationService workForceApplicationService;

	@RequestMapping(value = "/assignmentDetail", method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "Assignment detail by employee id", notes = "Assignment detail", response = EmployeeData.class, authorizations = {
			@Authorization(value = "security scope bounded to 'ROLE_ADMIN' users ") })
	List<AssignmentDetail> getEmployee(@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName) {
		return workForceApplicationService.getAllAssingment(firstName, lastName);
	}

	@RequestMapping(value = "/assignmentDetail", method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "Assignment detail by employee id", notes = "Assignment detail", response = EmployeeData.class, authorizations = {
			@Authorization(value = "security scope bounded to 'ROLE_ADMIN' users ") })
	AssignmentDetail addEmployee(@RequestBody AssignmentDetail assignmentDetail) {
		return workForceApplicationService.assign(assignmentDetail);
	}
}
