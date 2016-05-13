package test01917;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import connector01917.Constant;
import daoimpl01917.MySQLReceptDAO;
import daointerfaces01917.DALException;
import dto01917.ReceptDTO;

public class Recept {
	Connector db;
	
	@Before
	public void setup() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		db = new Connector(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);
	}

	@Test
	public void GetRecept() throws DALException {
		MySQLReceptDAO conn = new MySQLReceptDAO();

		ReceptDTO op = conn.getRecept(1);
		assertNotNull(op);
	}

	@Test
	public void GetReceptList() throws DALException {

		MySQLReceptDAO conn = new MySQLReceptDAO();
		List<ReceptDTO> op = conn.getReceptList();
		assertNotNull(op);
	}
	
	@Test
	public void UpdateRecept() throws DALException {
		ReceptDTO x = new ReceptDTO(1, "Testing");
		MySQLReceptDAO conn = new MySQLReceptDAO();
		conn.updateRecept(x);
	}

	@Test
	public void CreateRecept() throws DALException {
		ReceptDTO x = new ReceptDTO(0, "Recept for test");
		MySQLReceptDAO conn = new MySQLReceptDAO();
		conn.createRecept(x);
	}

}

