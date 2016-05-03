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
		ResultSet rs = Connector.doQuery("Select pbId, status, receptId FROM produktBatch WHERE pbId = ?", pbId);
		try {
			if (!rs.first())
				throw new DALException("Produktbatchen" + pbId + "findes ikke");
		
			return new ProduktBatchDTO (rs.getInt("pbId"), rs.getInt("status"), rs.getInt("receptId"));
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
		ResultSet rs = Connector.doQuery("SELECT pbId, status, receptId FROM produktbatch");
		try
		{
			while (rs.next())
			{
				list.add(new ProduktBatchDTO(rs.getInt("pbId"), rs.getInt("status"), rs.getInt("receptId")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException
	{
		Connector.doQuery
		(
			"INSERT INTO produktBatch(pbId, status, receptId) VALUES (?,?,?)",
				produktbatch.pbId, produktbatch.status, produktbatch.receptId
				);
	}

	@Override
	public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException
	{
		Connector.doQuery(
				"UPDATE produktbatch SET status = ?,  receptId = ? WHERE pbId = ?",
				produktbatch.status, produktbatch.receptId, produktbatch.pbId
				);
	}
	
	
	
	
}
