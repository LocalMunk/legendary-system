package test01917;
import static org.junit.Assert.assertNotNull;
import java.sql.SQLException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import connector01917.Connector;
import connector01917.Constant;
import daoimpl01917.MySQLProduktBatchKompDAO;
import daointerfaces01917.DALException;
import dto01917.ProduktBatchKompDTO;
public class ProduktBatchKomp {
	Connector db;
	
	@Before
	public void setup() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		db = new Connector(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);
	}
	@Test
	public void GetProduktBatchKomp() throws DALException {
		MySQLProduktBatchKompDAO conn = new MySQLProduktBatchKompDAO();
		ProduktBatchKompDTO op = conn.getProduktBatchKomp(1, 1);
		assertNotNull(op);
	}
	@Test
	public void GetProduktBatchKompList() throws DALException {
		MySQLProduktBatchKompDAO conn = new MySQLProduktBatchKompDAO();
		List<ProduktBatchKompDTO> op = conn.getProduktBatchKompList();
		assertNotNull(op);
	}
	
	@Test
	public void UpdateProduktBatchKomp() throws DALException {
		ProduktBatchKompDTO x = new ProduktBatchKompDTO(1, 2, 3.4, 4.3, 5);
		MySQLProduktBatchKompDAO conn = new MySQLProduktBatchKompDAO();
		conn.updateProduktBatchKomp(x);
	}
	@Test
	public void CreateProduktBatchKomp() throws DALException {
		ProduktBatchKompDTO x = new ProduktBatchKompDTO(1, 3, 3.4, 4.3, 5);
		MySQLProduktBatchKompDAO conn = new MySQLProduktBatchKompDAO();
		conn.createProduktBatchKomp(x);
	}
}
