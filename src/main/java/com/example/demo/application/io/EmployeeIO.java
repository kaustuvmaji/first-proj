/**
 * 
 */
package com.example.demo.application.io;

import com.example.demo.domain.Employee;

/**
 * @author kaust
 *
 */
public class EmployeeIO extends Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1602661192445633249L;

	/**
	 * @param id
	 * @param name
	 * @param department
	 */
	public EmployeeIO(Integer id, String name, String department) {
		super(id, name, department);
	}

}
