package test01917;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import connector01917.Constant;
import daoimpl01917.MySQLOperatoerDAO;
import daointerfaces01917.DALException;
import dto01917.OperatoerDTO;
public class Operator {
	Connector db;
	
	@Before
	public void setup() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		db = new Connector(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);
	}

	@Test
	public void GetOperator() throws DALException {
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();

		OperatoerDTO op = conn.getOperatoer(1);
		assertNotNull(op);
	}

	@Test
	public void GetOperatorList() throws DALException {
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();
		List<OperatoerDTO> op = conn.getOperatoerList();
		assertNotNull(op);
	}
	
	@Test
	public void UpdateOperator() throws DALException {
		OperatoerDTO x = new OperatoerDTO(1, "Hej", "Hej", "123", "Hej");
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();
		conn.updateOperatoer(x);
	}

	@Test
	public void CreateOperator() throws DALException {
		OperatoerDTO x = new OperatoerDTO(0, "bob", "b", "2123213", "000000");
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();
		conn.createOperatoer(x);
	}

}
