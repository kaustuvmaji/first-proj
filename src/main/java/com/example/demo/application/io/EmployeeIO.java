/**
 * 
 */
package com.example.demo.application.io;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.domain.Assignment;
import com.example.demo.domain.ContactDetail;
import com.example.demo.domain.Employee;

/**
 * @author kaustuv
 *
 */
public class EmployeeIO extends Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1602661192445633249L;

	public EmployeeIO(){
		
	}
	public EmployeeIO(String firstName, String secondName, LocalDateTime dateOfBirth,
			List<ContactDetail> contactDetails, String department, int salary, List<Assignment> assignments) {
		super(firstName, secondName, dateOfBirth, contactDetails, department, salary, assignments);
	}

}
