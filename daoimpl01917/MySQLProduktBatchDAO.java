package daoimpl01917;

import daointerfaces01917.ProduktBatchDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import dto01917.ProduktBatchDTO;

public class MySQLProduktBatchDAO implements ProduktBatchDAO{

	@Override
	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException
	{
		ResultSet rs = Connector.doQuery("Select pb_id, status, recept_id FROM produktbatch WHERE pb_id = ?", pbId);
		try {
			if (!rs.first())
				throw new DALException("Produktbatchen" + pbId + "findes ikke");
		
			return new ProduktBatchDTO (rs.getInt("pb_id"), rs.getInt("recept_id"), rs.getInt("status"));
		} 
		catch (SQLException e)
		{
			throw new DALException(e);
		}
	}

	@Override
	public List<ProduktBatchDTO> getProduktBatchList() throws DALException
	{
		List<ProduktBatchDTO> list = new ArrayList<ProduktBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatch");
		try
		{
			while (rs.next())
			{
				list.add(new ProduktBatchDTO(rs.getInt("pb_id"), rs.getInt("recept_id"), rs.getInt("status")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException
	{
		Connector.doUpdate
		(
			"INSERT INTO produktbatch(pb_id, recept_id, status) VALUES (?,?,?)",
				produktbatch.pbId, produktbatch.receptId, produktbatch.status
				);
	}

	@Override
	public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException
	{
		Connector.doUpdate(
				"UPDATE produktbatch SET status = ?,  recept_id = ? WHERE pb_id = ?",
				produktbatch.status, produktbatch.receptId, produktbatch.pbId
				);
	}
	
	
	
	
}
