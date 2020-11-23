package com.employee.demo.emprepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.employee.demo.model.Skill;

@Repository
public class SkillRepositoryImpl implements SkillRepositoryInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Skill> getAllSkills() {
        String sql = "SELECT * FROM skills";
		
		RowMapper<Skill> rowMapper=new BeanPropertyRowMapper<>(Skill.class);
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	@Override
	public Skill findSkillById(long skillId) {
		
		try {
		
		final String SKILL_BY_ID="select * from skills where skillId=?;"; 
		
		List<Skill> query = jdbcTemplate.query(SKILL_BY_ID, new BeanPropertyRowMapper<Skill>(Skill.class), skillId);  
	    return DataAccessUtils.uniqueResult(query);
	    
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public boolean deleteSkillDetails(long skillId) {
		
     final String DELETE_BY_ID = "delete from skills where skillId=?";
		
		int size= jdbcTemplate.update(DELETE_BY_ID, skillId);
		if(size==0) {
			return false;
		}
		
		return true;
		
	}

	@Override
	public Skill getSkillByempId(long empId) {
		
		try {
			
			final String SKILL_BY_ID="select * from skills where empId=?;"; 
			
			List<Skill> query = jdbcTemplate.query(SKILL_BY_ID, new BeanPropertyRowMapper<Skill>(Skill.class), empId);  
		    return DataAccessUtils.uniqueResult(query);
		    
			}catch(EmptyResultDataAccessException e) {
				return null;
			}
	}

	@Override
	public Skill addSkillDetails(Skill skill) {
		
		final String INSERT_SKILLS = "insert into skills (skillId, skillName, details, empId) values(?, ?, ?, ?)";
		jdbcTemplate.update(INSERT_SKILLS, skill.getSkillId(), skill.getSkillName(), skill.getDetails(), skill.getEmpId());
		return skill;
	}

	@Override
	public Skill updateSkill(long empId,Skill skill) {
		
		final String UPDATE_SKILL="update skills set skillId=?, skillName=?, details=? where empId=?";
		int size = jdbcTemplate.update(UPDATE_SKILL, skill.getSkillId(), skill.getSkillName(), skill.getDetails(), empId);
		
		if(size > 0) {
			System.out.println("rows updated:"+size);
			return skill;
		}
		else {
			System.out.println("zero rows updated");
			return null;
		}
		
	}
	

}
