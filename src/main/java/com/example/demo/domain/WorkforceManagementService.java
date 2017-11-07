package com.example.demo.domain;

import java.util.List;


public interface WorkforceManagementService {

	List<Assignment> getOngoingAssignments(String employeeId);

	Assignment assign(Assignment assignmentDetail);

}
