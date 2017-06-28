package com.acad.spring4Assignment2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
//import oracle.jdbc.*;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

//sample for CRUD operations using Spring-JDBC 

public class SpringJDBCTemplateExample {
	
	public static void main(String[] args) throws SQLException
	{
		SimpleDriverDataSource dataSource=new SimpleDriverDataSource();
		dataSource.setDriver(new oracle.jdbc.driver.OracleDriver());
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1522:xe");
		dataSource.setUsername("HR");
		dataSource.setPassword("hr");
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		//sample INSERT operation
		String sqlInsert = "INSERT INTO contact (contact_id,name,email,address,telephone)"+" values (?,?,?,?,?)";
		
		jdbcTemplate.update(sqlInsert,"11","Tom","tomea@mail.com","USA","12345");
		jdbcTemplate.update(sqlInsert,"12","Tom","tomea@mail.com","USA","12345");
		
		
		//sample UPDATE operation
		String sqlUpdate = "UPDATE contact set email=? where name=?";
		jdbcTemplate.update(sqlUpdate,"tomee@mail.com","TOM");
		
		
		//SELECT operation
		String sqlSelect = "SELECT * from contact";
		List<Contact> listContact = jdbcTemplate.query(sqlSelect, new RowMapper<Contact>(){
			
			public Contact mapRow(ResultSet result,int rowNum) throws SQLException
			{
				Contact contact=new Contact();
				contact.setName(result.getString("name"));
				contact.setEmail(result.getString("email"));
				contact.setAddress(result.getString("address"));
				contact.setPhone(result.getString("telephone"));
				return contact;
			}
		});
		
		for (Contact aContact:listContact)
		{
			System.out.println(aContact);
		}
		
		
		/*//DELETE Operation
		String sqlDelete="DELETE from contact where name=?";
		jdbcTemplate.update(sqlDelete,"Tom");
		System.out.println("Records deleted successfully");*/
	}

}
