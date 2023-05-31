package backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserTableSQL {
//	public static void main(String[] args) {
//		int result = userCheck("123","123","admin","123");
//		System.out.println("Result:\t" + result);
//	}

	public static int userCheck(String username) {

		// import MySQLConnector.java and connect to database
		String sql = null;  
	    MySQLConnector db1 = null;  
	    ResultSet rset = null;
	    int result = -1;
	    
	    sql = "select COUNT(*) from user where" + " username = " + "'" + username + "';";// SQL statement
	    
        db1 = new MySQLConnector(sql);//create MySQLConnector object
        
        try {  
        	//Execute SQL statement and get result set
        	rset = db1.pst.executeQuery();
        	
            while (rset.next()) {        	
            	result = rset.getInt(1);
             
            }
            //close result set
            rset.close();  
            
            //close database connection
            db1.close(); 
            
        } catch (SQLException e) {  
        	e.printStackTrace();
        }

		return result;
	}

	public static int userInsert(String firstName, String lastName, String username, String password, String email, int count) {
		String sql = null;  
        MySQLConnector db1 = null; 
        int result = -1;
      
    	sql = "INSERT INTO user (first_name, last_name, username, password, email, daily_words_count) values(?,?,?,?,?,?)";//SQL语句  
        db1 = new MySQLConnector(sql);//创建 MySQLConnector 对象
        
        try {
        	
        	db1.pst.setString(1, firstName);
        	db1.pst.setString(2, lastName);
        	db1.pst.setString(3, username);
        	db1.pst.setString(4, password);
        	db1.pst.setString(5, email);
        	db1.pst.setInt(6, count);
   
        	
            result = db1.pst.executeUpdate();
           
            
            //System.out.println(result);
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
		
		
		return result;
	}
	
//	public static int userBatchInsert(String username) {
//		return 0;
//	}
	
	public static int userModify(int userID, String username, String password, String firstName, String lastName, String email, int dailyWordsNum) {
		int modifyRes = -999;
		
		String sql = null;
		MySQLConnector db1 = null;
		int result = -1;
		// Check new vs old -> same or not

		sql = "UPDATE USER \r\n"
				+ "	SET username = '" + username + "',"
				+ "	password = '" + password + "',"
				+ "	first_name = '" + firstName +"',"
				+ "	last_name = '" + lastName +"',"
				+ "	email = '"+ email +"',"
				+ "	daily_words_count = "+ dailyWordsNum 
				+ " WHERE"
				+ "	id = " + userID + ";";

		try {
			db1 = new MySQLConnector(sql);// 创建 MySQLConnector 对象
			result = db1.pst.executeUpdate();

			// System.out.println(result);
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static int userDelete(String username) {
		return 0;
	}
	
	
	/*===============================================*/

	public static List queryUserPassword(String username) {
		String password = "";
		int userID = -999;
		String sql = null;  
	    MySQLConnector db1 = null;  
	    ResultSet rset = null;
	    List info = new ArrayList<>();
	    sql = "SELECT id, password from user where" + " username = " + "'" + username + "';";// SQL statement
	    
        db1 = new MySQLConnector(sql);//create MySQLConnector object
        
        try {  
        	//Execute SQL statement and get result set
        	rset = db1.pst.executeQuery();
        	
            while (rset.next()) {
            	userID = rset.getInt(1);
            	password = rset.getString(2);
            	info.add(userID);
            	info.add(password);
            }
            //close result set
            rset.close();  
            
            //close database connection
            db1.close(); 
            
        } catch (SQLException e) {  
        	e.printStackTrace();
        }
		
		return info;
	}
	
	public static int queryUserIDByUsername(String username){
		int userID = -999;
		
		String sql = null;  
	    MySQLConnector db1 = null;  
	    ResultSet rset = null;
	    
	    sql = "SELECT id from user where" + " username = " + "'" + username + "';";// SQL statement
	    
        db1 = new MySQLConnector(sql);//create MySQLConnector object
        
        try {  
        	//Execute SQL statement and get result set
        	rset = db1.pst.executeQuery();
        	
            while (rset.next()) {
            	userID = rset.getInt(1);
            }
            //close result set
            rset.close();  
            
            //close database connection
            db1.close(); 
            
        } catch (SQLException e) {  
        	e.printStackTrace();
        }
		
		return userID;

	}
	
	public static List queryUser(int userID) {
		
		String sql = null;  
	       MySQLConnector db1 = null;  
	       ResultSet rset = null;
	       List userInfo = new ArrayList<>();
	       String firstName = "";
	       String lastName = "";
	       String username = "";
	       String password = "";
	       String email = "";
	       int dailyWordsCount = -999;
	       
	       sql = "SELECT first_name, last_name, username, password, email, daily_words_count from user where" + " id = " + "'" + userID + "';";// SQL statement
	       
	          db1 = new MySQLConnector(sql);//create MySQLConnector object
	          
	          try {  
	           //Execute SQL statement and get result set
	           rset = db1.pst.executeQuery();
	           
	              while (rset.next()) {
	               firstName = rset.getString(1);
	               lastName = rset.getString(2);
	               username = rset.getString(3);
	               password = rset.getString(4);
	               email = rset.getString(5);
	               dailyWordsCount = rset.getInt(6);
	               
	               userInfo.add(firstName);
	               userInfo.add(lastName);
	               userInfo.add(username);
	               userInfo.add(password);
	               userInfo.add(email);
	               userInfo.add(dailyWordsCount);
	              }
	              //close result set
	              rset.close();  
	              
	              //close database connection
	              db1.close(); 
	              
	          } catch (SQLException e) {  
	           e.printStackTrace();
	          }
	    
	    return userInfo;
	}
	
}
