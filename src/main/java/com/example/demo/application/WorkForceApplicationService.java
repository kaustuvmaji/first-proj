/**
 * 
 */
package com.example.demo.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.demo.application.aop.LogMethodExecution;
import com.example.demo.application.io.AssignmentDetail;
import com.example.demo.domain.Assignment;
import com.example.demo.domain.EmployeeService;
import com.example.demo.domain.WorkforceManagementService;

/**
 * @author kama0717
 *
 */
@Service
public class WorkForceApplicationService {

	@Autowired
	WorkforceManagementService workforceManagementService;
	@Autowired
	EmployeeService employeeApplicationService;

	@LogMethodExecution
	public List<AssignmentDetail> getAllAssingment(String firstName, String lastName) {

		List<AssignmentDetail> all = null;

		List<Assignment> ongoingAssignments = workforceManagementService
				.getOngoingAssignments(employeeApplicationService.getEmployee(firstName, lastName).getDocumentId());

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

		return new AssignmentDetail(assignment.getProjectCode(), assignment.getStartDate(), assignment.getEndDate(),
				assignment.getPerformanceMark(), assignment.getEmployeeId());
	}
}
