package daoimpl01917;

import daointerfaces01917.ReceptDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import dto01917.ReceptDTO;

public class MySQLReceptDAO implements ReceptDAO {

	@Override
	public ReceptDTO getRecept(int receptId) throws DALException
	{
		ResultSet rs = Connector.doQuery("SELECT recept_navn FROM recept WHERE recept_id = ?", receptId);
		try {
	    	if (!rs.first())
	    		throw new DALException("Recepten " + receptId + " findes ikke");
	    	
	    	return new ReceptDTO (receptId, rs.getString("recept_navn"));
	    }
	    catch (SQLException e)
	    {
	    	throw new DALException(e);
	    }
		
	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException
	{
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM get_all_recept");
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptDTO(rs.getInt("recept_id"), rs.getString("recept_navn")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRecept(ReceptDTO recept) throws DALException
	{
		Connector.doQuery
		(
			"INSERT INTO recept(recept_id, recept_navn) VALUES (?, ?)",
			recept.receptId, recept.receptNavn
		);
	}

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException
	{
		Connector.doUpdate(
				"UPDATE recept SET  recept_navn = '" + recept.receptNavn + "' WHERE recept_id = " +
				recept.receptId
		);
	}

}