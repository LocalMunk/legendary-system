package test01917;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import connector01917.Constant;
import daoimpl01917.MySQLRaavareDAO;
import daoimpl01917.MySQLReceptKompDAO;
import daointerfaces01917.DALException;
import dto01917.RaavareDTO;
import dto01917.ReceptDTO;
import dto01917.ReceptKompDTO;

public class ReceptKomp {

	
	Connector db;

	@Before
	public void setup() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		db = new Connector(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);
	}

	@Test
	public void GetReceptKomp() throws DALException {
		MySQLReceptKompDAO conn = new MySQLReceptKompDAO();
		ReceptKompDTO op = conn.getReceptKomp(1, 1);

		assertNotNull(op);
	}

	@Test
	public void GetReceptKompList() throws DALException {

		MySQLReceptKompDAO conn = new MySQLReceptKompDAO();
		List<ReceptKompDTO> op =  conn.getReceptKompList();
		assertNotNull(op);
	}

	@Test
	public void CreateReceptKomp() throws DALException {
		ReceptKompDTO x = new ReceptKompDTO(1, 1, 1, 1);
		MySQLReceptKompDAO conn = new MySQLReceptKompDAO();
		conn.createReceptKomp(x);
	}

	@Test
	public void UpdateReceptKomp() throws DALException {
		ReceptKompDTO x = new ReceptKompDTO(1, 1, 1, 1);
		MySQLReceptKompDAO conn = new MySQLReceptKompDAO();
		conn.updateReceptKomp(x);
	}
}
