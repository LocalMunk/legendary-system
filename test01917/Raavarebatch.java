package test01917;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import connector01917.Constant;
import daoimpl01917.MySQLRaavareBatchDAO;
import daointerfaces01917.DALException;
import dto01917.RaavareBatchDTO;

public class Raavarebatch
{
	
	Connector db;
	
	@Before
	public void setup() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		db = new Connector(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);
	}
	
	@Test
	public void GetRaavare() throws DALException
	{
		MySQLRaavareBatchDAO conn = new MySQLRaavareBatchDAO();
		
		RaavareBatchDTO op = conn.getRaavareBatch(1);
		assertNotNull(op);
	}
	
	@Test
	public void GetRaavareBatchList() throws DALException
	{
		MySQLRaavareBatchDAO conn = new MySQLRaavareBatchDAO();
		List<RaavareBatchDTO> op = conn.getRaavareBatchList();
		assertNotNull(op);
	}
	
	@Test
	public void UpdateRaavareBatch() throws DALException
	{
		RaavareBatchDTO x = new RaavareBatchDTO(23, 233, 3);
		MySQLRaavareBatchDAO conn = new MySQLRaavareBatchDAO();
		conn.updateRaavareBatch(x);
		
	}
	
	@Test
	public void CreateRaavarebatch() throws DALException
	{
		RaavareBatchDTO x = new RaavareBatchDTO(0, 1, 94);
		MySQLRaavareBatchDAO conn = new MySQLRaavareBatchDAO();
		conn.createRaavareBatch(x);
		
	}
}