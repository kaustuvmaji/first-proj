package com.example.demo;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public /* Mockito failed to mock this class because of final keyword */ class EmployeeServiceImpl implements EmployeeService {

	EmployeeServiceImpl() {

		employees.put(1, new Employee(1, "Josh", "dev"));
		employees.put(2, new Employee(2, "Rev", "qa"));
		employees.put(3, new Employee(3, "Kaustuv", "dev"));
		employees.put(4, new Employee(4, "Sam", "Hr"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#getEmployee(java.lang.Integer)
	 */
	@Cacheable(value = "employeeCache", key = "#id", sync = true)
	@Override
	@LogMethodExecution
	public Employee getEmployee(Integer id) {
		return employees.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#addEmployees(com.example.demo.Employee)
	 */
	@Override
	@LogMethodExecution
	public Employee addEmployees(Employee newEmp) {
		return employees.put(newEmp.getId(), newEmp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#deleteEmployee(java.lang.Integer)
	 */
	@LogMethodExecution
	public void deleteEmployee(Integer id) {
		employees.remove(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#updateEmployee(java.lang.Integer,
	 * java.lang.String, java.lang.String)
	 */
	@LogMethodExecution
	public Employee updateEmployee(Integer id, String name, String department) {
		return employees.compute(id, (k, v) -> v.updateEmployee(name, department));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#getEmployees()
	 */
	@Cacheable(value = "employeeCache", sync = true)
	@LogMethodExecution
	public Collection<Employee> getEmployees() {
		return employees.values();
	}
}
