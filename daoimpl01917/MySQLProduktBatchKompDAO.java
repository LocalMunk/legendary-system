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
		ResultSet rs = Connector.doQuery("Select * FROM produktbatchkomponent WHERE pb_id = ? AND rb_id = ?", pbId, rbId);
		try {
			if (!rs.first()) throw new DALException("Produktbatchkomp " + pbId + " findes ikke");
			return new ProduktBatchKompDTO (rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getInt("tara"), rs.getInt("netto"),rs.getInt("user_id"));
		} catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException
	{
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = Connector.doQuery("Select pb_id, rb_id, tara, netto FROM ProduktBatchKomponent WHERE pb_id = ?", pbId);
		try
		{
			while(rs.next())
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("user_id")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException
	{
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = Connector.doQuery("Select * FROM produktbatchkomponent");
		try
		{
			while(rs.next())
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("user_id")));
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
		
		Connector.doUpdate(
			"INSERT INTO produktbatchkomponent (pb_id, rb_id, tara, netto, user_id) VALUES (?,?,?,?,?)",
			pbKomp.pbId, pbKomp.rbId, pbKomp.tara, pbKomp.netto, pbKomp.oprId
		);
	}

	@Override
	public void updateProduktBatchKomp(ProduktBatchKompDTO pbKomp) throws DALException
	{
		Connector.doUpdate("UPDATE produktbatchkomponent set tara = ?, netto = ?, user_id = ? WHERE pb_id = ? AND rb_id = ?",
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
