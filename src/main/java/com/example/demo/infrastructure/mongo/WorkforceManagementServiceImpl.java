package com.example.demo.infrastructure.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Assignment;
import com.example.demo.domain.WorkforceManagementService;

@Service
public class WorkforceManagementServiceImpl implements WorkforceManagementService {

	@Autowired
	AssingmnetRepository assingmnetRepository;

	@Override
	public List<Assignment> getOngoingAssignments(String employeeId) {
		return assingmnetRepository.findByEmployeeId(employeeId);
	}

	@Override
	public Assignment assign(Assignment assignmentDetail) {
		return assingmnetRepository.save(assignmentDetail);
	}

}
