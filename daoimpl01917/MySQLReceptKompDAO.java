package daoimpl01917;

import daointerfaces01917.ReceptKompDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import dto01917.ReceptKompDTO;

public class MySQLReceptKompDAO implements ReceptKompDAO {

	@Override
	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException
	{
		ResultSet rs = Connector.doQuery(
			"SELECT recept_id, raavare_id, nom_netto, tolerance fROM receptkomponent WHERE recept_id = ? AND raavare_id = ?",
			receptId, raavareId
		);
		
		try {
			if (!rs.first())
				throw new DALException("Receptkomponenten" + receptId + ", " + raavareId + " findes ikke");
		
			return new ReceptKompDTO (rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance"));
		} 
		catch (SQLException e)
		{
			throw new DALException(e);
		}
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException
	{
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		ResultSet rs = Connector.doQuery
		(
			"SELECT recept_id, raavare_id, nom_netto, tolerance FROM projekt.receptkomponent WHERE recept_id = ?",
			receptId
		);
		
		try {
			while(rs.next())
				list.add(new ReceptKompDTO(
						rs.getInt("recept_id"),
						rs.getInt("raavare_id"),
						rs.getDouble("nomNetto"),
						rs.getDouble("tolerance")));
		} catch(SQLException e) {
			throw new DALException(e);
		}
		
		return list;
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList() throws DALException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException
	{
		Connector.doQuery(
			"INSERT INTO receptkomponent (recept_id, raavare_id, nom_netto, tolerance) VALUES (?, ?, ?)",
			receptkomponent.receptId, receptkomponent.raavareId, receptkomponent.nomNetto, receptkomponent.tolerance
		);
		
	}

	@Override
	public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException
	{
		Connector.doQuery(
			"UPDATE receptkomponent SET raavare_id = ?, recept_id = ?, nom_netto = ?, tolerance = ? WHERE recept_id = ? AND raavare_id = ?",
				receptkomponent.raavareId, receptkomponent.receptId, receptkomponent.nomNetto, receptkomponent.tolerance, receptkomponent.receptId, receptkomponent.raavareId
		);
		
	}
}
