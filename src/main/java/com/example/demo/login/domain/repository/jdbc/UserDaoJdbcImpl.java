package com.example.demo.login.domain.repository.jdbc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Repository
public class UserDaoJdbcImpl implements UserDao {
	
	@Autowired
	JdbcTemplate jdbc;
	
	//Userテーブルの件数を取得
	@Override
	public int count() throws DataAccessException{
		int count = jdbc.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
		
		return count;
	}
	
	//Userテーブルにデータを1件insert
	@Override
	public int insertOne(User user) throws DataAccessException{
		
		//1件登録
		int rowNumber = jdbc.update("INSERT INTO m_user(user_id,"
				+ " password,"
				+ " user_name,"
				+ " birthday,"
				+ " age,"
				+ " marriage,"
				+ " role)"
				+ " VALUES(?,?,?,?,?,?,?)"
				, user.getUserId()
				, user.getPassword()
				, user.getUserName()
				, user.getBirthday()
				, user.getAge()
				, user.isMarriage()
				, user.getRole());
		
		return rowNumber;
	}

	//Userテーブルのデータを1件取得
	@Override
	public User selectOne(String userId) throws DataAccessException{
		//ポイント： 1件取得
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM m_user"
				+ " WHERE user_id=?"
				, userId);
		
		//結果返却用の変数
		User user = new User();
		
		//取得したデータを結果返却用の変数にセットしていく
		user.setUserId((String)map.get("user_id"));//ユーザーID
		user.setPassword((String)map.get("password"));
		user.setUserName((String)map.get("user_name"));
		user.setBirthday((Date)map.get("birthday"));
		user.setAge((Integer)map.get("age"));
		user.setMarriage((Boolean)map.get("marriage"));
		user.setRole((String)map.get("role"));		
		
		return user;
	}
	
	
	//Userテーブルの全データを取得
	@Override
	public List<User> selectMany() throws DataAccessException{
		
		//複数件のselect
		List<Map<String, Object>>  getList = jdbc.queryForList("SELECT * FROM m_user");
		
		//結果返却用の変数
		List<User> userList= new ArrayList<>();
		
		for(Map<String, Object> map:getList) {
			
			User user = new User();
			
			//Userインスタンスの生成
			user.setUserId((String)map.get("user_id"));
			user.setPassword((String)map.get("password"));
			user.setUserName((String)map.get("user_name"));
			user.setBirthday((Date)map.get("birthday"));
			user.setAge((Integer)map.get("age"));
			user.setMarriage((Boolean)map.get("marriage"));
			user.setRole((String)map.get("role"));
			
			userList.add(user);
		}
		
		return userList;
	}
	
	//Userテーブルを１件更新
	@Override
	public int updateOne(User user) throws DataAccessException{
		//1件更新
		int rowNumber = jdbc.update("UPDATE M_USER "
				+ " SET"
				+ " password= ?,"
				+ " user_name= ?,"
				+ " birthday= ?,"
				+ " age= ?,"
				+ " marriage= ?"
				+ " WHERE user_id= ?"
				, user.getPassword()
				, user.getUserName()
				, user.getBirthday()
				, user.getAge()
				, user.isMarriage()
				, user.getUserId()
				);
				
		
		return rowNumber;
	}
	
	//Userテーブルを１件更新
	@Override
	public int deleteOne(String userId) throws DataAccessException{
		
		int numNumber = jdbc.update("DELETE FROM M_USER"
				+ " WHERE user_id=?"
				, userId);
		
		return numNumber;
	}
	
	//Userテーブルの全データをCSVに出力する
	public void userCsvOut() throws DataAccessException{
		
	}
}
