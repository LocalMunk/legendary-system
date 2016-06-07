package test01917;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import connector01917.Constant;
import daoimpl01917.MySQLProduktBatchDAO;
import daoimpl01917.MySQLProduktBatchKompDAO;
import daointerfaces01917.DALException;
import dto01917.ProduktBatchDTO;
import dto01917.ProduktBatchKompDTO;

public class ProduktBatch {
	Connector db;
	
	@Before
	public void setup() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		db = new Connector(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);
	}
	@Test
	public void GetProduktBatch() throws DALException {
		MySQLProduktBatchDAO conn = new MySQLProduktBatchDAO();
		ProduktBatchDTO  op = conn.getProduktBatch(1);
		assertNotNull(op);
	}
	@Test
	public void GetProduktBatchKList() throws DALException {
		MySQLProduktBatchDAO conn = new MySQLProduktBatchDAO();
		List<ProduktBatchDTO> op = conn.getProduktBatchList();
		assertNotNull(op);
	}
	
	@Test
	public void UpdateProduktBatch() throws DALException {
		ProduktBatchDTO x = new ProduktBatchDTO(1, 2, 1);
		MySQLProduktBatchDAO conn = new MySQLProduktBatchDAO();
		conn.updateProduktBatch(x);
	}
	@Test
	public void createProduktBatchKomp() throws DALException {
		ProduktBatchDTO x = new ProduktBatchDTO(0, 3, 3);
		MySQLProduktBatchDAO conn = new MySQLProduktBatchDAO();
		conn.createProduktBatch(x);
	}
}
