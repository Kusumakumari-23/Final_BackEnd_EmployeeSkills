package com.employee.demo.empservices;

import java.util.List;

import com.employee.demo.model.Employee;

public interface EmpServiceInterface {

	List<Employee> getAllEmployees();
	//Employee addEmployee(Employee employee);
	Employee updateEmployeeDetails(Employee employee);
    boolean deleteEmployeeDetails(long empId);	// Deleting both employee and skills together
    Employee getEmployeeById(long empId);
    public Employee createEmployee(Employee employee); 		// changed return type from void to Employee
    boolean deleteEmp(long empId);	//Deleting only employee
     
    Employee updateEmployee(long empId,Employee employee);
}
