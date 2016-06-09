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
		ResultSet rs = Connector.doQuery("Select rb_id, maengde, raavare_id FROM raavarebatch WHERE rb_id = ?", rbId);
		try {
			if (!rs.first())
				throw new DALException("Raavaretbatchen" + rbId + "findes ikke");
		
			return new RaavareBatchDTO (rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getInt("maengde"));
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
		ResultSet rs = Connector.doQuery("SELECT * FROM raavarebatch");
		try
		{
			while (rs.next())
			{
				list.add(new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getFloat("maengde")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException
	{
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT rb_id, maengde, raavare_id FROM raavareBatch WHERE raavare_id = ?",
				raavareId
				);
		try
		{
			while (rs.next())
			{
				list.add(new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getFloat("maengde")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException
	{
		Connector.doUpdate
		(
			"INSERT INTO raavarebatch(rb_id, maengde, raavare_id) VALUES (?,?,?)",
				raavarebatch.rbId, raavarebatch.maengde, raavarebatch.raavareId
				);
		
	}

	@Override
	public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException
	{
		Connector.doUpdate(
				"UPDATE raavarebatch SET maengde = ?,  raavare_id = ? WHERE rb_id = ?",
				raavarebatch.maengde, raavarebatch.raavareId, raavarebatch.rbId
				);
		
	}

}
