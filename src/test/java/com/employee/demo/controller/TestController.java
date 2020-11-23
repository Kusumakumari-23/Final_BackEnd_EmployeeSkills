package com.employee.demo.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.employee.demo.empcontroller.EmpdataApi;
import com.employee.demo.empservices.EmpServiceInterface;
import com.employee.demo.empservices.SkillServiceImpl;
import com.employee.demo.empservices.SkillServiceInterface;
import com.employee.demo.exception.ResourceNotFoundException;
import com.employee.demo.model.Employee;
import com.employee.demo.model.Skill;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(EmpdataApi.class)
//@WithMockUser
public class TestController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmpServiceInterface empService;
	
	@MockBean
	private SkillServiceInterface skillService;
	
	ObjectMapper mapper=new ObjectMapper();
	
	List<Employee> employees= Arrays.asList(
			new Employee(5L, "devdev", "omkar", new Date(2019-04-11), "fdfkdnk", "mp", "56478"),
			new Employee(4L, "dev", "kumari", new Date(2020-04-11), "fdhjf", "ap", "2394ooo78"));
	
	List<Skill> skills= Arrays.asList(
			new Skill(101L,"c","Programming Language", 5L),
			new Skill(102L,"c++","oop", 4L));
	
	Employee employee=new Employee(5L, "devdev", "omkar", new Date(2019-04-11), "fdfkdnk", "mp", "56478");
	
	@BeforeEach
	public void setUp() throws Exception{
		 MockitoAnnotations.initMocks(this);
	}
	
//	@Test
//	public void addEmpTest() throws Exception {
//		
//		RequestBuilder request= MockMvcRequestBuilders.post("/empdata/emp/addemployee").accept(MediaType.APPLICATION_JSON).content(
//				"{\"empId\":\"1\","
//				+ "\"firstName\":\"mahadev\","
//				+ "\"lastName\":\"dev\","
//				+ "\"hireDate\":\"2020-05-05\","
//				+ "\"email\":\"kdjhu\","
//				+ "\"city\":\"ap\","
//				+ "\"phoneNo\":\"54682\"}").contentType(MediaType.APPLICATION_JSON);
//		
//		mockMvc.perform(request).andExpect(status().is(200)).andReturn();	
//				
//	}
	
	@Test
	public void getAllEmployees() throws Exception {
		
		Mockito.when(empService.getAllEmployees()).thenReturn(employees);
		
		RequestBuilder request= MockMvcRequestBuilders.get("/empdata/emp/getemployees");
		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void testUpdateEmployee() throws Exception {
		
		Employee emp=new Employee(5L, "devdev", "omkar", new Date(2019-04-11), "fdfkdnk", "mp", "56478");
		
		Mockito.when(empService.updateEmployeeDetails(emp)).thenReturn(emp);
		
		RequestBuilder request= MockMvcRequestBuilders.put("/empdata/emp/updateEmployee").accept(MediaType.APPLICATION_JSON).content(
				"{\"empId\":\"1\","
				+ "\"firstName\":\"mahadev\","
				+ "\"lastName\":\"dev\","
				+ "\"hireDate\":\"2020-05-05\","
				+ "\"email\":\"kdjhu\","
				+ "\"city\":\"ap\","
				+ "\"phoneNo\":\"54682\"}").contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
	}
	
	@Test
	public void testGetEmployee() throws Exception {
		
		when(empService.getEmployeeById(anyLong())).thenReturn(employee);
		String uri="/empdata/employeeById/5";
		
		RequestBuilder request=MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk())
		.andExpect(jsonPath("$.empId",Matchers.is(5)))
		.andExpect(jsonPath("$.*",Matchers.hasSize(8)));
	}
	
	@Test
	public void empById_notFound() throws Exception {
		when(empService.getEmployeeById(5L)).thenThrow(new ResourceNotFoundException(""));
		
		mockMvc.perform(get("/empdata/employeeById/{empId}",5L))
		.andExpect(status().isNotFound());
		
		verify(empService,times(1)).getEmployeeById(5L);
		verifyNoMoreInteractions(empService);
	}
	
	@Test
	public void deleteEmpAndSkill() throws Exception {
		
		when(empService.getEmployeeById(anyLong())).thenReturn(employee);
		when(empService.deleteEmployeeDetails(anyLong())).thenReturn(true);
		
		RequestBuilder request= MockMvcRequestBuilders.delete("/empdata/employee/deleteById/5");
		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
	}
	
	
	/* @@@@@@ Skill Test @@@@@@*/
	
	@Test
	public void addSkillTest() throws Exception {
		
		Skill skill = new Skill(101L,"c","Programming Language", 5L);
		
		RequestBuilder request = MockMvcRequestBuilders.post("/empdata/createSkills").accept(MediaType.APPLICATION_JSON).content(
				"{\"skillId\":\"101\", \"skillName\": \"java\", \"details\": \"pl\", \"empId\": \"1\"}")
				.contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
	}
	
	
	@Test
	public void getAllSkills() throws Exception{
		
		Mockito.when(skillService.getAllSkills()).thenReturn(skills);
		
		RequestBuilder request=MockMvcRequestBuilders.get("/empdata/skills/getskills");
		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
		
	}
	
	
	@Test
	public void testGetSkill() throws Exception {
		Skill skill = new Skill(101L,"c","Programming Language", 5L);
		
		when(skillService.findSkillById(anyLong())).thenReturn(skill);
		String uri="/empdata/skills/skillsById/101";
		
		RequestBuilder request=MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk())
		.andExpect(jsonPath("$.skillId",Matchers.is(101)))
		.andExpect(jsonPath("$.*",Matchers.hasSize(4)));
	}
	
	@Test
	public void testSkillUpdate() throws Exception {
		
		Skill skill = new Skill(101L,"c","Programming Language", 5L);
		
		when(skillService.findSkillById(anyLong())).thenReturn(skill);
		Mockito.when(skillService.updateSkill(5L,skill)).thenReturn(skill);
		
		RequestBuilder request = MockMvcRequestBuilders.put("/empdata/updateSkill/5").accept(MediaType.APPLICATION_JSON).content(
				"{\"skillId\":\"101\", \"skillName\": \"java\", \"details\": \"pl\", \"empId\": \"5\"}")
				.contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.empId",Matchers.is(5L)))
		.andReturn();
	}
	
} 
