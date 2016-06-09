package test01917;



import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import connector01917.Constant;
import daoimpl01917.MySQLRaavareDAO;
import daointerfaces01917.DALException;
import dto01917.RaavareDTO;

public class Raavare {
Connector db;
	
	@Before
	public void setup() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		db = new Connector(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);
	}

	@Test
	public void GetRaavare() throws DALException {
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		RaavareDTO op = conn.getRaavare(1);
		
		assertNotNull(op);
	}

	@Test
	public void GetRaavareList() throws DALException {
		
		MySQLRaavareDAO  conn = new MySQLRaavareDAO();
		List<RaavareDTO> op = conn.getRaavareList();
		assertNotNull(op);
	}
	@Test
	public void CreateRaavare() throws DALException {
		RaavareDTO x = new RaavareDTO(0,"Tomat","Heinz");
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		conn.createRaavare(x);
	}	
	
	@Test
	public void UpdateRaavare() throws DALException {
		RaavareDTO x = new RaavareDTO(1, "Tomat","Heinz");
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		conn.updateRaavare(x);
	}
	
	
	
		
		

	

	

}

