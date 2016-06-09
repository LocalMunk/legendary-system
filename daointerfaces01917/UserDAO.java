package daointerfaces01917;

import java.util.List;

import dto01917.UserDTO;

public interface UserDAO {
	UserDTO getUser(int userId) throws DALException;
	List<UserDTO> getUserList() throws DALException;
	void createUser(UserDTO user) throws DALException;
	void updateUser(UserDTO user) throws DALException;
}
