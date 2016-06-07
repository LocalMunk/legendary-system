package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.UserDAO;
import dto01917.UserDTO;

import java.util.ArrayList;

//
public class MySQLUserDAO implements UserDAO
{
	public UserDTO getUser(int userId) throws DALException
	{
		ResultSet rs = Connector.doQuery("SELECT * FROM user WHERE user_id = ?", userId);
		try
		{
			if (!rs.first())
				throw new DALException("User " + userId + " findes ikke");
			
			return new UserDTO(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("ini"),
					rs.getString("cpr"), rs.getString("password"), rs.getInt("user_level"));
		}
		catch (SQLException e)
		{
			throw new DALException(e);
		}
		
	}
	
	public void createUser(UserDTO user) throws DALException
	{
		Connector.doUpdate("INSERT INTO user(user_id, first_name, last_name, ini, cpr, password, user_level) VALUES (?, ?, ?, ?, ?, ?, ?)",
				user.userId, user.firstName, user.lastName, user.ini, user.cpr, user.password, user.level);
	}
	
	public void updateUser(UserDTO user) throws DALException
	{
		Connector.doUpdate("UPDATE user SET  first_name = ?, last_name = ?, ini =  ?, cpr = ?, password = ?, user_level = ? WHERE user_id = ?",
				user.firstName, user.lastName, user.ini, user.cpr, user.password, user.level, user.userId);
	}
	
	/*
	 * TODO: Modify to use view
	 * 
	 * @see daointerfaces01917.UserDAO#getUserList()
	 */
	public List<UserDTO> getUserList() throws DALException
	{
		List<UserDTO> list = new ArrayList<UserDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM user");
		try
		{
			while (rs.next())
			{
				list.add(new UserDTO(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("ini"),
						rs.getString("cpr"), rs.getString("password"), rs.getInt("user_level")));
			}
		}
		catch (SQLException e)
		{
			throw new DALException(e);
		}
		return list;
	}
	
}
