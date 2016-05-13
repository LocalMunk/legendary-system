package test01917;
import static org.junit.Assert.*;

import java.sql.SQLException;

import static org.junit.Assert.*;

import org.junit.Test;

import connector01917.Connector;
import connector01917.Constant;

public class Database
{
	private Connector db;
	
	@Test
	public void testConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		db = new Connector(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);
	}
}