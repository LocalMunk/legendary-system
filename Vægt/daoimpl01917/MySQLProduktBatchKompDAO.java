package daoimpl01917;

import daointerfaces01917.ProduktBatchKompDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
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
		
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException
	{
		
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
			pbKomp.getPbId(), pbKomp.getRbId(), pbKomp.getTara(), pbKomp.getNetto(), pbKomp.getOprId()
		);
	}

	@Override
	public void updateProduktBatchKomp(ProduktBatchKompDTO pbKomp) throws DALException
	{
		Connector.doUpdate(
				"UPDATE produktbatchkomp set tara = '" + pbKomp.getTara() + "', netto = '" + pbKomp.getNetto() + 
				"', opr_id = '" + pbKomp.getOprId() + "' WHERE pbId = " + pbKomp.getPbId() + 
				" AND rbId = " + pbKomp.getRbId()
				);
	}

}
