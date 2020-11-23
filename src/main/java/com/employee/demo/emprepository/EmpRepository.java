package com.employee.demo.emprepository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.employee.demo.model.Employee;

@Repository
public class EmpRepository implements EmpRepositoryInterface {

	private static final Logger LOGGER = LogManager.getLogger(EmpRepository.class.getName());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Employee> getAllEmployees() {
		
		String sql = "SELECT * FROM employees";
		RowMapper<Employee> rowMapper=new BeanPropertyRowMapper<>(Employee.class);
		
		LOGGER.info("Returning list of employees");
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	@Override
	public Employee getEmployeeById(long empId) {
		
		try {
		final String EMP_BY_ID="select * from employees where empId=?;"; 
	    List<Employee> query = jdbcTemplate.query(EMP_BY_ID, new BeanPropertyRowMapper<Employee>(Employee.class), empId);  
	    return DataAccessUtils.uniqueResult(query);
		}catch (EmptyResultDataAccessException e) 
		{
			return null;
	}
	}
	
	//INSERT EMPLOYEE AND SKILL TOGETHER

//	@Override
//	public Employee addEmployee(Employee emp) {
//		
//		final String EMP_INSERT= "insert into employees (empId, firstName, lastName, hireDate, city, email, phoneNo) values (?, ?, ?, ?, ?, ?, ?) ";
//		final String SKILL_INSERT="insert into skills (skillId, skillName, details,empId) values(?, ?, ?, ?)";
//		
//		jdbcTemplate.update(EMP_INSERT, emp.getEmpId(), emp.getFirstName(), emp.getLastName(), emp.getHireDate(), emp.getCity(), emp.getEmail(), emp.getPhoneNo());
//		LOGGER.info("Inserted into Employee Table Successfully");
//		
//		jdbcTemplate.update(SKILL_INSERT, emp.getSkills().getSkillId(), emp.getSkills().getSkillName(), emp.getSkills().getDetails(), emp.getSkills().getEmpId());
//		LOGGER.info("Inserted into Skill Table Successfully");
//		
//		return emp;
//		
//	}

	@Override
	public Employee updateEmployeeDetails(Employee employee) {
		
		final String UPDATE_EMPLOYEE="update employees set firstName=?, lastName=?, hireDate=?, email=?, city=?, phoneNo=? where empId=?;";
		jdbcTemplate.update(UPDATE_EMPLOYEE, employee.getFirstName(), employee.getLastName(), employee.getHireDate(), employee.getEmail(), employee.getCity(), employee.getPhoneNo(), employee.getEmpId());
		return employee;
		
		
	}
	
	@Override
	public boolean deleteEmployeeDetails(long empId) {
		 
		final String DELETE_BY_ID = "delete from employees where empId=?";
		final String DELETESKILL_BY_ID = " delete from skills where empId=?";
		
		
		int skillSize= jdbcTemplate.update(DELETESKILL_BY_ID, empId);
		int size= jdbcTemplate.update(DELETE_BY_ID, empId);
		if(skillSize==0 || size==0) {
			return false;
		}
		else
			return true;
		
	}

	@Override
	public Employee createEmployee(Employee employee) {
		
		final String INSERT_EMP_QUERY = "insert into employees (empId,firstName, lastName, hireDate, city, email, phoneNo) values ( ?, ?, ?, ?, ?, ?, ?) ";
		
		jdbcTemplate.update(INSERT_EMP_QUERY, employee.getEmpId(), employee.getFirstName(), employee.getLastName(), employee.getHireDate(), employee.getCity(), employee.getEmail(), employee.getPhoneNo());
		
		LOGGER.info(" Data inserted successfully");
		
		return employee;
		
	}

	@Override
	public Employee updateEmployee(long empId, Employee employee) {
		
		final String UPDATE_EMPLOYEE="update employees set firstName=?, lastName=?, hireDate=?, email=?, city=?, phoneNo=? where empId=?;";
		int size = jdbcTemplate.update(UPDATE_EMPLOYEE, employee.getFirstName(), employee.getLastName(), employee.getHireDate(), employee.getEmail(), employee.getCity(), employee.getPhoneNo(), empId);
		
		if(size > 0) {
			System.out.println("rows updated:"+size);
			return employee;
		}
		else {
			System.out.println("zero rows updated");
			return null;
		}
		
	}

	@Override
	public boolean deleteEmp(long empId) {
		final String DELETE_BY_ID = "delete from employees where empId=?";
		
		int size= jdbcTemplate.update(DELETE_BY_ID, empId);
		if(size==0) {
			return false;
		}
		
		else
			return true;
		
	}

}
