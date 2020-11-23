package com.employee.demo.emprepository;

import java.util.List;

//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;

import com.employee.demo.model.Skill;

//@Mapper
public interface SkillRepositoryInterface {

	public List<Skill> getAllSkills();
	Skill findSkillById(long skillId);
	Skill getSkillByempId(long empId);
    boolean deleteSkillDetails(long skillId); //optional
    Skill addSkillDetails(Skill skill);
    
    Skill updateSkill(long empId,Skill skill);
}
