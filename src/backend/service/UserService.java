package backend.service;

import java.util.List;

import backend.entity.Response;

public interface UserService {
	public int loginCheck(String username, String inputPWD);
	public boolean userCheck(String username);
	
	public List queryUser(int userID);
	public boolean createUser(String username, String password, String firstName, String lastName, String email, int dailyWordsNum);
	public boolean userConfigModify(int userID, String username, String password, String firstName, String lastName, String email, int dailyWordsNum);
	public boolean deleteUser(String username);
	
}
