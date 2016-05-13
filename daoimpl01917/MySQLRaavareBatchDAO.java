package daoimpl01917;

import daointerfaces01917.RaavareBatchDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import dto01917.ProduktBatchDTO;
import dto01917.RaavareBatchDTO;

public class MySQLRaavareBatchDAO implements RaavareBatchDAO {

	@Override
	public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException
	{
		ResultSet rs = Connector.doQuery("Select rbId, maengde, raavareId FROM raavareBatch WHERE rbId = ?", rbId);
		try {
			if (!rs.first())
				throw new DALException("Raavaretbatchen" + rbId + "findes ikke");
		
			return new RaavareBatchDTO (rs.getInt("pbId"), rs.getInt("status"), rs.getInt("receptId"));
		} 
		catch (SQLException e)
		{
			throw new DALException(e);
		}
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException
	{
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM get_all_raavarebatch");
		try
		{
			while (rs.next())
			{
				list.add(new RaavareBatchDTO(rs.getInt("rbId"), rs.getInt("raavareId"), rs.getFloat("maengde")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException
	{
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT rbId, maengde, raavareId FROM raavareBatch WHERE raavareId = ?",
				raavareId
				);
		try
		{
			while (rs.next())
			{
				list.add(new RaavareBatchDTO(rs.getInt("rbId"), rs.getInt("raavareId"), rs.getFloat("maengde")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException
	{
		Connector.doQuery
		(
			"INSERT INTO raavareBatch(rbId, maengde, raavareId) VALUES (?,?,?)",
				raavarebatch.rbId, raavarebatch.maengde, raavarebatch.raavareId
				);
		
	}

	@Override
	public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException
	{
		Connector.doQuery(
				"UPDATE produktbatch SET status = ?,  receptId = ? WHERE pbId = ?",
				raavarebatch.maengde, raavarebatch.raavareId, raavarebatch.rbId
				);
		
	}

}
