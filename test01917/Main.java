package test01917;

import java.sql.SQLException;

import connector01917.Connector;
import daoimpl01917.MySQLUserDAO;
import daointerfaces01917.DALException;
import dto01917.UserDTO;

public class Main {
	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		System.out.println("Operatoer nummer 3:");
		MySQLUserDAO user = new MySQLUserDAO();
		try { System.out.println(user.getUser(3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny operatoer med opr_id =  4");
		UserDTO userDTO = new UserDTO(10,"Don", "Juan","DJ","0000000000","iloveyou", 42);
		try { user.createUser(userDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("User nummer 10:");
		try { System.out.println(user.getUser(10)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af initialer for user nummer 10");
		//oprDTO.setIni("DoJu");
		try { user.updateUser(userDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("User nummer 10:");
		try { System.out.println(user.getUser(10)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle users:");
		try { System.out.println(user.getUserList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("User nummer 5:");
		try { System.out.println(user.getUser(5)); }
		catch (DALException e) { System.out.println(e.getMessage()); }		
		
	}
}
