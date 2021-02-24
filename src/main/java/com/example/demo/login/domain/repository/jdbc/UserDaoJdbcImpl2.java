package com.example.demo.login.domain.repository.jdbc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.example.demo.login.domain.model.User;

public class UserDaoJdbcImpl2 extends UserDaoJdbcImpl {

	@Autowired
	private JdbcTemplate jbdc;
	
	@Override
	public User selectOne(String userId) {
		//1件取得SQL
		String sql = "SELECT * FROM m_user WHERE user_id = ?";
	
		//	RowMapperの生成
		UserRowMapper rowMapper = new UserRowMapper();
		
		//SQLの実行
		return jdbc.queryForObject(sql, rowMapper, userId);
	}
	
	@Override
	public List<User> selectMany(){
		
		//M_USERテーブルのデータを全件取得するSQL
		String sql = "SELECT * FROM m_user";
		
		//RowMapperの生成
		RowMapper<User> rowMapper = new UserRowMapper();
		
		//SQL実行
		return jdbc.query(sql, rowMapper);
	}
	
}
