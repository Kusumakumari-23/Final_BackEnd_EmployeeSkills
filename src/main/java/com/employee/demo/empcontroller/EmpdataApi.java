/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.17).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.employee.demo.empcontroller;

import com.employee.demo.model.Employee;
import com.employee.demo.model.Skill;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-11T14:18:13.684Z")

@Api(value = "empdata")
@RequestMapping(value = "")
public interface EmpdataApi {

//    @ApiOperation(value = "inserting both employee and skill together", nickname = "addEmployee", notes = "inserting both together", response = Employee.class, tags={ "emp", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "successful operation", response = Employee.class) })
//    @RequestMapping(value = "/empdata/emp/addemployee",
//        consumes = { "application/json" },
//        method = RequestMethod.POST)
//    ResponseEntity<Employee> addEmployee(@ApiParam(value = "employee object that needs to be added to data base" ,required=true )  @Valid @RequestBody Employee employee);


    @ApiOperation(value = "Add a new employee to database", nickname = "createEmployee", notes = "creates new employee", tags={ "emp", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Inserted Suuccesfully"),
        @ApiResponse(code = 405, message = "Invalid input"),
        @ApiResponse(code = 500, message = "Internal Server Error") })
    @RequestMapping(value = "/empdata/createEmp",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> createEmployee(@ApiParam(value = "employee object that needs to be added to data base" ,required=true )  @Valid @RequestBody Employee employee);


    @ApiOperation(value = "Add a new skill to database", nickname = "createSkills", notes = "creates new skill", tags={ "skill", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successfull insertion"),
        @ApiResponse(code = 405, message = "Invalid input"),
        @ApiResponse(code = 500, message = "Internal Server Error") })
    @RequestMapping(value = "/empdata/createSkills",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> createSkills(@ApiParam(value = "skill object that needs to be added to data base" ,required=true )  @Valid @RequestBody Skill skill);


    @ApiOperation(value = "Deletes employee details", nickname = "deleteEmp", notes = "deletes employee based on empId", tags={ "emp", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation"),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "employee not found") })
    @RequestMapping(value = "/empdata/empOnly/deleteById/{empId}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteEmp(@ApiParam(value = "employee id to delete",required=true) @PathVariable("empId") Long empId);


    @ApiOperation(value = "Deletes both employee and skill", nickname = "deleteEmployeeDetails", notes = "deletes both data", tags={ "emp", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation"),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "employee not found") })
    @RequestMapping(value = "/empdata/employee/deleteById/{empId}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteEmployeeDetails(@ApiParam(value = "employee id to delete",required=true) @PathVariable("empId") Long empId);


    @ApiOperation(value = "Delete skill details by Id", nickname = "deleteSkillDetails", notes = "delete skill details", tags={ "skill", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "employee not found") })
    @RequestMapping(value = "/empdata/skill/deleteById/{skillId}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteSkillDetails(@Min(1L)@ApiParam(value = "skill Id od the skills that needs to be deleted",required=true) @PathVariable("skillId") Long skillId);


    @ApiOperation(value = "get details of all employees", nickname = "getAllEmployees", notes = "Returns list of employees", tags={ "emp", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation"),
        @ApiResponse(code = 404, message = "Employee not found"),
        @ApiResponse(code = 500, message = "Internal Server Error") })
    @RequestMapping(value = "/empdata/emp/getemployees",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Employee>> getAllEmployees();


    @ApiOperation(value = "get details of all skills", nickname = "getAllSkills", notes = "Returns list of skills", tags={ "skill", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation"),
        @ApiResponse(code = 404, message = "skill not found"),
        @ApiResponse(code = 500, message = "Internal Server Error") })
    @RequestMapping(value = "/empdata/skills/getskills",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Skill>> getAllSkills();


    @ApiOperation(value = "Finds employee by Id", nickname = "getEmployeeById", notes = "returns a single employee", response = Employee.class, responseContainer = "List", tags={ "emp", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Employee.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid status value") })
    @RequestMapping(value = "/empdata/employeeById/{empId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Employee> getEmployeeById(@ApiParam(value = "Id of employee to return an individual employee",required=true) @PathVariable("empId") Integer empId);


    @ApiOperation(value = "Finds skill by skill Id", nickname = "getSkillById", notes = "returns a single skill details", response = Skill.class, responseContainer = "List", tags={ "skill", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Skill.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid status value") })
    @RequestMapping(value = "/empdata/skills/skillsById/{skillId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Skill> getSkillById(@ApiParam(value = "Id of skills to return an individual skill details",required=true) @PathVariable("skillId") Integer skillId);


    @ApiOperation(value = "Finds skill by employee Id", nickname = "getSkillByempId", notes = "returns a single skill details", response = Skill.class, responseContainer = "List", tags={ "skill", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Skill.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid status value") })
    @RequestMapping(value = "/empdata/skillsByempId/{empId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Skill> getSkillByempId(@ApiParam(value = "get skill details based on employee Id",required=true) @PathVariable("empId") Integer empId);


    @ApiOperation(value = "update employee by empId", nickname = "updateEmployee", notes = "employee update by empId", tags={ "emp", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation"),
        @ApiResponse(code = 400, message = "Invalid tag value") })
    @RequestMapping(value = "/empdata/updateEmp/{empId}",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Employee> updateEmployee(@ApiParam(value = "update by empId",required=true) @PathVariable("empId") Integer empId,@ApiParam(value = "employee object needs to update in database" ,required=true )  @Valid @RequestBody Employee employee);


    @ApiOperation(value = "Update an existing employee", nickname = "updateEmployeeDetails", notes = "Updating employee based on object", tags={ "emp", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Updated Successfully"),
        @ApiResponse(code = 400, message = "Invalid object supplied"),
        @ApiResponse(code = 404, message = "Employee not found"),
        @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/empdata/emp/updateEmployee",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Employee> updateEmployeeDetails(@ApiParam(value = "employee object needs to update in database" ,required=true )  @Valid @RequestBody Employee employee);


    @ApiOperation(value = "update skill by empId", nickname = "updateSkill", notes = "skill update by empId", tags={ "skill", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation"),
        @ApiResponse(code = 400, message = "Invalid tag value") })
    @RequestMapping(value = "/empdata/updateSkill/{empId}",
    	produces = { "application/json" },
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Skill> updateSkill(@ApiParam(value = "update by empId",required=true) @PathVariable("empId") Integer empId,@ApiParam(value = "skill object needs to update in database" ,required=true )  @Valid @RequestBody Skill skill);

}
