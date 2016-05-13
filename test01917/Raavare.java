package test01917;



import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlXid;

import connector01917.Connector;
import connector01917.Constant;
import daoimpl01917.MySQLOperatoerDAO;
import daoimpl01917.MySQLRaavareBatchDAO;
import daoimpl01917.MySQLRaavareDAO;

import daointerfaces01917.DALException;
import daointerfaces01917.RaavareDAO;
import dto01917.OperatoerDTO;
import dto01917.RaavareBatchDTO;
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
		RaavareDTO x = new RaavareDTO(23,"Tomat","Heinz");
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		conn.createRaavare(x);
	}	
	
	@Test
	public void UpdateRaavare() throws DALException {
		RaavareDTO x = new RaavareDTO(23, "Tomat","Heinz");
	MySQLRaavareDAO conn = new MySQLRaavareDAO();
		conn.updateRaavare(x);
	}
	
	
	
		
		

	

	

}

