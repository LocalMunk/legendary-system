package daoimpl01917;

import daointerfaces01917.ProduktBatchKompDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import dto01917.ProduktBatchKompDTO;

public class MySQLProduktBatchKompDAO implements ProduktBatchKompDAO {

	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException
	{
		ResultSet rs = Connector.doQuery("Select * FROM produktbatchkomp WHERE pbId = " + pbId + "AND rbId = " + rbId);
		try {
			if (!rs.first()) throw new DALException("Produktbatchkomp " + pbId + " findes ikke");
			return new ProduktBatchKompDTO (rs.getInt("pbId"), rs.getInt("rbId"), rs.getInt("tara"), rs.getInt("netto"),rs.getInt("opr_id"));
		} catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException
	{
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = Connector.doQuery("Select pbId, rbId, tara, netto FROM ProduktBatchKomp WHERE pbId = ?", pbId);
		try
		{
			while(rs.next())
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pbId"), rs.getInt("rbId"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("oprId")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException
	{
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = Connector.doQuery("Select pbId, rbId, tara, netto FROM ProduktBatchKomp");
		try
		{
			while(rs.next())
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pbId"), rs.getInt("rbId"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("oprId")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createProduktBatchKomp(ProduktBatchKompDTO pbKomp) throws DALException
	{
//		Connector.doUpdate(
//				"INSERT INTO produktbatchkomp(pbId, rbId, tara, netto, opr_id) VALUES " + 
//				"(" + pbKomp.getPbId() +", '" + pbKomp.getRbId() + "', '" + pbKomp.getTara() + "', '" + 
//				pbKomp.getNetto() + "', '" + pbKomp.getOprId() + "')"
//				);
		
		Connector.doQuery(
			"INSERT INTO produktbatchkomp(pbId, rbId, tara, netto, opr_id) VALUES (?,?,?,?,?)",
			pbKomp.pbId, pbKomp.rbId, pbKomp.tara, pbKomp.netto, pbKomp.oprId
		);
	}

	@Override
	public void updateProduktBatchKomp(ProduktBatchKompDTO pbKomp) throws DALException
	{
		Connector.doQuery("UPDATE produktbatchkomp set tara = ?, netto = ?, opr_id = ?, WHERE pbId = ? AND rbId = ?",
				pbKomp.tara, pbKomp.netto, pbKomp.oprId, pbKomp.pbId, pbKomp.rbId
				);
//		
//		Connector.doUpdate(
//				"UPDATE produktbatchkomp set tara = '" + pbKomp.getTara() + "', netto = '" + pbKomp.getNetto() + 
//				"', opr_id = '" + pbKomp.getOprId() + "' WHERE pbId = " + pbKomp.getPbId() + 
//				" AND rbId = " + pbKomp.getRbId()
//				);
//	}

}
}
