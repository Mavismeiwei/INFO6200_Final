package backend.service;

import java.util.List;

import backend.dao.*;
import backend.exception.BasicException;
import backend.exception.CustomSQLException;
import backend.exception.UserException;
import backend.entity.*;



public class UserServiceImpl implements UserService{

	@Override
	public int loginCheck(String username, String inputPWD) {
		// TODO Auto-generated method stub
		
		int userID = -999;
		
		// check userame exists or not
		int userCount = UserTableSQL.userCheck(username);
		if(userCount == 1) {
			List info = UserTableSQL.queryUserPassword(username);

			userID = Integer.parseInt(info.get(0) + ""); ;
			String password = (String) info.get(1);
			
			//System.out.println(userID + "userid" + password);
			//System.out.println("查询得 PASSWORD 为:\t" + password + "\n用户输入 PASSWORD 为：\t" + inputPWD);
			if(!password.equals(inputPWD)) {//字符串 String 不能使用 == 比较 | password == inputPWD wrong!
				//System.out.println("login success!");
				userID = -999;
				//System.out.println("Your username or password not incorrect!");
				throw new UserException("Your username or password is incorrect!");
			}
		}else if(userCount > 1){
			throw new UserException("User Exception: Too many same username accounts in database! Please contact us ASAP!");
		}else {
			throw new UserException("User Exception: Your username or password is incorrect!");
		}

		
		return userID;
	}

	@Override
	public boolean createUser(
			String username, String password, String firstName, String lastName, String email, int dailyWordsNum) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int userCount = UserTableSQL.userCheck(username);//check username exist in database or not
		if(userCount == 0) {
			
			int insertRes = UserTableSQL.userInsert(firstName, lastName, username, password, email, dailyWordsNum);
			//System.out.println("INSERT RESULT:\t" + insertRes);
			if(insertRes == 1) {
				flag = true;
			}else {
				throw new UserException("User Exception: Insert Error!");
			}
		}else if(userCount == 1) {
			throw new UserException("User Exception: Target username " + username + " already exists!");
		}else {
			throw new UserException("User Exception: Unexpected Error!");
		}
		
		return flag;
		
	}

	@Override
	public boolean userCheck(String username) {
		int userCount = UserTableSQL.userCheck(username);//check username exist in database or not
		return (userCount == 1) ;
	}

	@Override
	public List queryUser(int userID) {
		// TODO Auto-generated method stub
		List userInfo = UserTableSQL.queryUser(userID);
		
		return userInfo;
	}

	@Override
	public boolean userConfigModify(int userID, String username, String password, String firstName, String lastName, String email,
			int dailyWordsNum) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		//ID 下的 username
		int queryUserIDByUserName = UserTableSQL.queryUserIDByUsername(username);
		if(userID != queryUserIDByUserName && queryUserIDByUserName != -999) {
//			System.out.println("userID" + userID);
//			System.out.println("userQueryID" + queryUserIDByUserName);
			throw new UserException("User Exception: Username " + username + " belongs to another user!!");
			//return false;
		}else {
			//检查username 不同之后，检查重复
			//userCheck
			int userCheck = UserTableSQL.userCheck(username);
//			if() {
////				throw new UserException("User Exception: Username '" + username +"' not exists in User Table!");
//			}else 
			if(userCheck == 1 || userCheck == 0){
				int modifyRes = UserTableSQL.userModify(userID, username, password, firstName, lastName, email, dailyWordsNum);
				
				//System.out.println("MODIFY RESULT!!!!" + modifyRes);
				
				if(modifyRes == 1) {
					flag = true;
				}else if(modifyRes == 0) {
					//flag = false;
					throw new UserException("User Exception: No change to User Table!");
				}else if(modifyRes == -999) {
					throw new UserException("User Exception: Database Error!");
				}else {
					throw new UserException("User Exception: Unexpected Error!");
				}
			}else if(userCheck > 1){
				throw new UserException("User Exception: Too many same username accounts in this Database! Please contact us ASAP!");
			}else {
				throw new UserException("User Exception: Unexpected Error!");
			}
		}
		
		
		
		return flag;
	}
	
	

	
	@Override
	public boolean deleteUser(String username) {
		// TODO Auto-generated method stub
		return false;
	}



}
