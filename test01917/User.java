package test01917;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import connector01917.Constant;
import daoimpl01917.MySQLUserDAO;
import daointerfaces01917.DALException;
import dto01917.UserDTO;
public class User {
	Connector db;
	
	@Before
	public void setup() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		db = new Connector(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);
	}

	@Test
	public void GetUser() throws DALException {
		MySQLUserDAO conn = new MySQLUserDAO();

		UserDTO op = conn.getUser(1);
		assertNotNull(op);
	}

	@Test
	public void GetUserList() throws DALException {
		MySQLUserDAO conn = new MySQLUserDAO();
		List<UserDTO> op = conn.getUserList();
		assertNotNull(op);
	}
	
	@Test
	public void UpdateOperator() throws DALException {
		UserDTO x = new UserDTO(5, "updatefirst", "updatelast", "uu", "1234567891", "hallo", 37);
		MySQLUserDAO conn = new MySQLUserDAO();
		conn.updateUser(x);
	}

	@Test
	public void CreateUser() throws DALException {
		UserDTO x = new UserDTO(0, "testfirst", "testlast", "tt", "1234567890", "000000", 100);
		MySQLUserDAO conn = new MySQLUserDAO();
		conn.createUser(x);
	}

}
