/**
 * 
 */
package com.example.demo.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.demo.application.aop.LogMethodExecution;
import com.example.demo.application.io.AssignmentDetail;
import com.example.demo.domain.Assignment;
import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeService;
import com.example.demo.domain.WorkforceManagementService;
import com.example.demo.infrastructure.notification.email.EmailMessage;
import com.example.demo.infrastructure.notification.email.EmailNotificationService;

/**
 * @author kama0717
 *
 */
@Service
public class WorkForceApplicationService {

	@Autowired
	WorkforceManagementService workforceManagementService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	private EmailNotificationService emailNotificationService;

	@LogMethodExecution
	public List<AssignmentDetail> getAllAssingment(String firstName, String lastName) {

		List<AssignmentDetail> all = null;

		List<Assignment> ongoingAssignments = workforceManagementService
				.getOngoingAssignments(employeeService.getEmployee(firstName, lastName, null).getDocumentId());

		if (!CollectionUtils.isEmpty(ongoingAssignments)) {
			all = new ArrayList<>();
			for (Assignment assignment : ongoingAssignments) {
				all.add(new AssignmentDetail(assignment.getProjectCode(), assignment.getStartDate(),
						assignment.getEndDate(), assignment.getPerformanceMark(), assignment.getEmployeeId()));
			}
		}
		return all;
	}

	@LogMethodExecution
	public AssignmentDetail assign(AssignmentDetail assignmentDetail) {
		Assignment assignment = workforceManagementService.assign(new Assignment(assignmentDetail.getProjectCode(),
				assignmentDetail.getStartDate(), assignmentDetail.getEndDate(), assignmentDetail.getPerformanceMark(),
				assignmentDetail.getEmployeeId()));
		AssignmentDetail assignmentData = new AssignmentDetail(assignment.getProjectCode(), assignment.getStartDate(),
				assignment.getEndDate(), assignment.getPerformanceMark(), assignment.getEmployeeId());

		// email notification to employeee mail id
		if (null != assignment) {
			Employee empoyeeData = employeeService.getEmployee(null, null, assignmentData.getEmployeeId());
			if (!CollectionUtils.isEmpty(empoyeeData.getContactDetails())) {
				EmailMessage email = new EmailMessage("Welcome a board !!!", "workforceassignment.vm",
						empoyeeData.getContactDetails().get(0).getEmailId());
				Map<String, String> propertyHolder = new HashMap<>();
				propertyHolder.put("firstName", empoyeeData.getFirstName());
				propertyHolder.put("projectCode", assignmentData.getProjectCode());
				propertyHolder.put("startDate", assignmentData.getStartDate().toString());
				propertyHolder.put("sender", "Admin Work Force Manager");
				email.setPropertyHolder(propertyHolder);
				emailNotificationService.sendSimpleMail(email);
			}
		}

		return assignmentData;
	}
}
