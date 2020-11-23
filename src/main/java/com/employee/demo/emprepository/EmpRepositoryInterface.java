package com.employee.demo.emprepository;

import java.util.List;

//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;

import com.employee.demo.model.Employee;

//@Mapper
public interface EmpRepositoryInterface {

	List<Employee> getAllEmployees();
//    Employee addEmployee(Employee employee);
    Employee updateEmployeeDetails(Employee employee);
    boolean deleteEmployeeDetails(long empId);
    Employee getEmployeeById(long empId);
    public Employee createEmployee(Employee employee);
    boolean deleteEmp(long empId);
   
    Employee updateEmployee(long empId, Employee employee);
}
