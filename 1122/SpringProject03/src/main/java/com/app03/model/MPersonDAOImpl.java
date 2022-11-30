package com.app03.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MPersonDAOImpl  implements MPersonDAO{
     @Autowired
	private JdbcTemplate template;
	@Override
	public void per_insert(PersonDTO person) {
		 String sql = "insert into person values(?,?,?,?,?)";
		    Object[] param = new Object[] {
		    		person.getId(), person.getName(),
		    		person.getPassword(),person.getGender(),
		    		person.getJob()
		    };
		    template.update(sql, param);
		
	}

	@Override
	public List<PersonDTO> per_list() {
		String sql = "select * from person";
		List<PersonDTO> personlist=template.query(sql, new RowMapper<PersonDTO>() {
			@Override
			public PersonDTO mapRow(ResultSet rs, int arg1) throws SQLException {
				PersonDTO user = new PersonDTO();
			    user.setGender(rs.getString("gender"));
			    user.setId(rs.getString("id"));
			    user.setJob(rs.getString("job"));
			    user.setName(rs.getString("name"));
			    user.setPassword(rs.getString("password"));
				return user;
			}
			
		});
		return personlist;
	}

	@Override
	public int per_count() {
		String sql ="select count(*) from person";
		int count = template.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public PersonDTO per_view(String id) {
		String sql ="select * from person where id='"+id+"'";
		PersonDTO person= 	template.queryForObject(sql, new RowMapper<PersonDTO>() {
				@Override
				public PersonDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					PersonDTO user = new PersonDTO();
				     	user.setGender(rs.getString("gender"));
					    user.setId(rs.getString("id"));
					    user.setJob(rs.getString("job"));
					    user.setName(rs.getString("name"));
					    user.setPassword(rs.getString("password"));
						return user;
				}
			});
			return  person;
	}

	@Override
	public void per_update(PersonDTO person) {
		String sql="update person "
				+ " set name=?, job=?, password=?, gender=? where id=?";
		Object[] param = new Object[] {
				person.getName(), person.getJob(),
				person.getPassword(), person.getGender(), person.getId()
		};
		template.update(sql, param);
		
	}

	@Override
	public void per_delete(String id) {
		String sql="delete from person where id = '"+id+"'";
		template.update(sql);	
		
	}

}
