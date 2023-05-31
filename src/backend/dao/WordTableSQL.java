package backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class WordTableSQL {
//	public static void main(String[] args) {
//		System.out.println(importTargetDailyWordsList(1,1)); 
//
//	}

//	public static int wordsICheck(String word) {
//		return 0;
//	};


	public static List queryWords(int wordsNum) {
		// Initial ArrayList
		List<ArrayList<String>> wordsList = new ArrayList<>();

		// import MySQLConnector.java and connect to database
		String sql = null;
		MySQLConnector db1 = null;
		ResultSet rset = null;

		sql = "SELECT * FROM word" + " ORDER BY rand() LIMIT " + wordsNum + ";";// SQL statement
		db1 = new MySQLConnector(sql);// create MySQLConnector object

		try {
			// Execute SQL statement and get result set
			rset = db1.pst.executeQuery();
			while (rset.next()) {
				// String wordID = rset.getString(1);
				String word = rset.getString(2);
				String wordEnMean = rset.getString(3);
				String wordCnMean = rset.getString(4);
				String wordClass = rset.getString(5);

				ArrayList<String> innerArraylist = new ArrayList<String>();
				innerArraylist.add(word);
				innerArraylist.add(wordEnMean);
				innerArraylist.add(wordCnMean);
				innerArraylist.add(wordClass);

				wordsList.add(innerArraylist);
			}

			// close result set
			rset.close();

			// close database connection
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return wordsList;
	}
	
	
	
//	public static List queryPriorityQueueWordsList(int userID, int wordsNum) {
//		// Initial ArrayList
//		List wordsList = new ArrayList<>();
//		PriorityQueue<String> queue1 = new PriorityQueue<>();
//		// import MySQLConnector.java and connect to database
//		String sql = null;
//		MySQLConnector db1 = null;
//		ResultSet rset = null;
//
//		sql = "SELECT name FROM word" + " ORDER BY rand() LIMIT " + wordsNum + ";";// SQL statement
//		db1 = new MySQLConnector(sql);// create MySQLConnector object
//
//		try {
//			// Execute SQL statement and get result set
//			rset = db1.pst.executeQuery();
//			while (rset.next()) {
//				// String wordID = rset.getString(1);
//				String word = rset.getString(1);
////				String wordEnMean = rset.getString(3);
////				String wordCnMean = rset.getString(4);
////				String wordClass = rset.getString(5);
//
////				ArrayList<String> innerArraylist = new ArrayList<String>();
////				innerArraylist.add(word);
////				innerArraylist.add(wordEnMean);
////				innerArraylist.add(wordCnMean);
////				innerArraylist.add(wordClass);
//				
//				wordsList.add(word);
//				
//			}
//
////			//1、创建一个空的优先级队列，默认底层容量是11
////	        PriorityQueue<String> queue1 = new PriorityQueue<>();
////
////	        //2、创建一个空的优先级队列，底层的容量是 initialCapacity
////	        PriorityQueue<String> queue2 = new PriorityQueue<>(50);
//	        
//	        //3、用 ArrayList 集合来创建一个优先级队列的对象
////	        PriorityQueue<String> queue3 = new PriorityQueue<>(wordsList);
////	        queue1 = queue3;
//			// close result set
//			rset.close();
//
//			// close database connection
//			db1.close();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		
//		return wordsList;
//	}

	/**
	 * @author Cheng Wang
	 * @date 4/11/2023
	 * @description query all required words from word table
	 * @param wordsNum
	 * @return List of words
	 */
	public static int queryWordCheck(String name) {

		// import MySQLConnector.java and connect to database
		String sql = null;
		MySQLConnector db1 = null;
		ResultSet rset = null;

		int result = -999;

		sql = "select COUNT(*) from word" + " where name = '" + name + "';";// SQL statement
		db1 = new MySQLConnector(sql);// create MySQLConnector object

		try {
			// Execute SQL statement and get result set
			rset = db1.pst.executeQuery();

			while (rset.next()) {
				result = rset.getInt(1);
			}

			// close result set
			rset.close();
			// close database connection
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * @author Cheng Wang
	 * @date 4/11/2023
	 * @description: insert target word into word table
	 * @param word
	 * @param wordEnMean
	 * @param wordCnMean
	 * @param wordClass
	 * @return List of return words
	 */
	public static int singleWordInsert(String name, String wordEnMean, String wordCnMean, String wordClass) {
		String sql = null;
		MySQLConnector db1 = null;
		sql = "INSERT INTO word (name, meaning_en, meaning_cn, word_class) values(?,?,?,?)";// SQL语句
		db1 = new MySQLConnector(sql);// 创建 MySQLConnector 对象
		int result = -999;
		try {

			db1.pst.setString(1, name);
			db1.pst.setString(2, wordEnMean);
			db1.pst.setString(3, wordCnMean);
			db1.pst.setString(4, wordClass);

			result = db1.pst.executeUpdate();

			// System.out.println(result);
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * @author Cheng Wang
	 * @date 4/11/2023
	 * @description: if needed, we can use batch insert for too many words to import
	 * @param word
	 * @return result in int type
	 */
	public static int wordsBatchInsert(String word, String wordEnMean, String wordCnMean, String wordClass) {
		int result = -999;
		return result;
	}

	/**
	 * @author Cheng Wang
	 * @date 4/11/2023
	 * @description: modify word and its meaning and class from word table
	 * @param name
	 * @param wordEnMean
	 * @param wordCnMean
	 * @param wordClass
	 * @return int number as how many rows be modified(TODO: Try to check before
	 *         modify)
	 */
	public static int wordsModify(String name, String wordEnMean, String wordCnMean, String wordClass) {

		String sql = null;
		MySQLConnector db1 = null;
		int result = -1;
		// Check new vs old -> same or not

		sql = "UPDATE word SET meaning_en ='" + wordEnMean + "', " + "meaning_cn ='" + wordCnMean + "', "
				+ "word_class ='" + wordClass + "' " + " where name = '" + name + "';";// SQL语句
		
		
		
		
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

	/**
	 * @author Cheng Wang
	 * @date 4/11/2023
	 * @description: delete word from word table
	 * @param name
	 * @return
	 */
	public static int wordsDelete(String name) {
		String sql = null;
		MySQLConnector db1 = null;
		int result = -999;
		sql = "DELETE FROM word WHERE name = '" + name + "';";

		try {
			db1 = new MySQLConnector(sql);// 创建 MySQLConnector 对象

			result = db1.pst.executeUpdate();// 0 success, 1 fail
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @author Cheng Wang
	 * @date 4/14/2023
	 * @description: Word Quiz Part | return a big list contains 10 words with 1
	 *               correct chinese meaning and 3 wrong chinese meanings
	 * @return wordsList
	 */
	public static List wordsQuizPre(int userID) {
		int wordsNum = 10;// set as 10
		// Initial ArrayList
		List<ArrayList<String>> wordsList = new ArrayList<>();

		// import MySQLConnector.java and connect to database
		String sql = null;
		MySQLConnector db1 = null;
		ResultSet rset = null;

		// TODO: 这里需要匹配账户 ID
		
		sql = "SELECT " + "	word.name, word.meaning_cn " + " FROM " + "	user_memorize_status "
				+ "	INNER JOIN word ON user_memorize_status.word_id = word.id"
				+ " WHERE user_memorize_status.user_id = " + userID + " order by rand() LIMIT " + wordsNum + ";";

		db1 = new MySQLConnector(sql);// create MySQLConnector object

		try {
			// Execute SQL statement and get result set
			rset = db1.pst.executeQuery();
			while (rset.next()) {
				// String wordID = rset.getString(1);
				String word = rset.getString(1);
				String wordCNMean = rset.getString(2);

				ArrayList<String> innerArraylist = new ArrayList<String>();
				innerArraylist.add(word);
				innerArraylist.add(wordCNMean);

				wordsList.add(innerArraylist);

			}

			// close result set
			rset.close();

			// close database connection
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return wordsList;
	}

	public static List wordsQuiz(int userID) {
		List wordsQuizList = wordsQuizPre(userID);

		int size = wordsQuizList.size();

		for (int i = 0; i < size; i++) {
			List section = (List) wordsQuizList.get(i);
			String word = (String) section.get(0);

			// -----------------------------------------
			String sql = null;
			MySQLConnector db1 = null;
			ResultSet rset = null;

			sql = "SELECT meaning_cn FROM word WHERE name != '" + word + "' order by rand() LIMIT 3";

			db1 = new MySQLConnector(sql);// create MySQLConnector object

			try {
				// Execute SQL statement and get result set
				rset = db1.pst.executeQuery();
				while (rset.next()) {
					// String wordID = rset.getString(1);
					String wordCNMeaning = rset.getString(1);

					section.add(wordCNMeaning);

				}

				// close result set
				rset.close();

				// close database connection
				db1.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			// -----------------------------------------

//			section.add("错误选项1");
//			section.add("错误选项2");
//			section.add("错误选项3");
		}
		return wordsQuizList;

	}

	public static List wordsCompletion(int userID) {
		List wordsQuizList = wordsQuizPre(userID);// same as words quiz part -> get a list contains 10 words

		int size = wordsQuizList.size();

		for (int i = 0; i < size; i++) {
			List section = (List) wordsQuizList.get(i);
			String word = (String) section.get(0);

//			Random rand = new Random();
//			rand.nextInt(word.length());

			int min = 0;
			int max = 0;
			int rmIndex = 0;
			StringBuilder sb = new StringBuilder(word);

			int split = word.length() / 3;

			if (split <= 1 && split > 0) {
				min = 0;
				max = word.length();
				rmIndex = min + (int) (Math.random() * (max - min));

				sb.setCharAt(rmIndex, '_');

			} else if (split < 0) {
				// TODO
				System.out.println("ERROR!!!");
			} else {
				// System.out.println("Word length:\t" + word.length());

				for (int j = 0; j < split + 1; j++) {
					if (j == 0) {
						continue;

					} else if (j == 1) {
						min = j;
						max = 3;
					} else {
						max = j * 3;
						min = max - 3;
					}

					// System.out.println("Min:\t" + min + " Max:\t" + max);

					rmIndex = min + (int) (Math.random() * (max - min));
					sb.setCharAt(rmIndex, '_');
					// System.out.println("RM Index:\t" + rmIndex);

				}
			}

			// System.out.println("==========================");

			// section.set(0, sb);
			section.add(sb);

		}
		return wordsQuizList;

	}

	public static List wordScoreList(int userID) {
		int score = 0;
		List<ArrayList<String>> scoreList = new ArrayList<>();

		String sql = null;
		MySQLConnector db1 = null;
		ResultSet rset = null;

		sql = "SELECT" + "	word.NAME, user_memorize_status.score" + " FROM user_memorize_status"
				+ "	INNER JOIN word ON user_memorize_status.word_id = word.id"
				+ "	WHERE user_memorize_status.user_id = " + userID + "';";

		db1 = new MySQLConnector(sql);// create MySQLConnector object

		try {
			// Execute SQL statement and get result set
			rset = db1.pst.executeQuery();
			while (rset.next()) {
				String wordName = rset.getString(1);
				score = rset.getInt(2);
				String s = score + "";

				ArrayList<String> innerArraylist = new ArrayList<String>();
				innerArraylist.add(wordName);
				innerArraylist.add(s);

				scoreList.add(innerArraylist);
			}

			// close result set
			rset.close();

			// close database connection
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return scoreList;
	}

	public static int querySingleWordScore(int userID, String word) {
		int score = 0;
		String sql = null;
		MySQLConnector db1 = null;
		ResultSet rset = null;

		sql = "SELECT" + "	word.NAME, user_memorize_status.score" + " FROM user_memorize_status"
				+ "	INNER JOIN word ON user_memorize_status.word_id = word.id"
				+ "	WHERE user_memorize_status.user_id = " + userID + " AND word.name = '" + word + "';";

		db1 = new MySQLConnector(sql);// create MySQLConnector object

		try {
			// Execute SQL statement and get result set
			rset = db1.pst.executeQuery();
			while (rset.next()) {
				score = rset.getInt(2);
			}

			List<String> s = new ArrayList<String>();
			s.add(word);
			s.add(score + "");

			// close result set
			rset.close();

			// close database connection
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return score;

	}

	public static int updateSingleWordScore(int userID, int wordID, int score) {
		String sql = null;
		MySQLConnector db1 = null;
		int result = -999;

		sql = "UPDATE user_memorize_status SET score =" + score + " where user_id = " + userID + " AND word_id = '"
				+ wordID + "';";// SQL语句

		try {
			db1 = new MySQLConnector(sql);// 创建 MySQLConnector 对象
			result = db1.pst.executeUpdate();

			db1.close();// 关闭连接

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static int queryWordIDbyWord(String word) {
		int score = 0;
		String sql = null;
		MySQLConnector db1 = null;
		ResultSet rset = null;

		sql = "SELECT id FROM word WHERE name = '" + word + "';";

		db1 = new MySQLConnector(sql);// create MySQLConnector object
		int wordID = -999;
		try {
			// Execute SQL statement and get result set
			rset = db1.pst.executeQuery();
			while (rset.next()) {
				wordID = rset.getInt(1);
			}
//
//			List<String> s = new ArrayList<String>();
//			s.add(word);
//			s.add(score + "");

			// close result set
			rset.close();

			// close database connection
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wordID;
	}
	
	public static int userDailyWordsCount(int userID) {
		int count = -999;
		
		String sql = null;
		MySQLConnector db1 = null;
		ResultSet rset = null;

		sql = "SELECT daily_words_count FROM user WHERE id = '" + userID + "';";

		db1 = new MySQLConnector(sql);// create MySQLConnector object
		
		try {
			// Execute SQL statement and get result set
			rset = db1.pst.executeQuery();
			while (rset.next()) {
				count = rset.getInt(1);
			}

			// close result set
			rset.close();

			// close database connection
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public static int userBindWordCheck(int userID, int wordID) {
		int result = -999;
		
		String sql = null;
		MySQLConnector db1 = null;
		ResultSet rset = null;

		sql = "SELECT id FROM user_memorize_status WHERE user_id = " + userID + " AND word_id = " + wordID + ";";

		db1 = new MySQLConnector(sql);// create MySQLConnector object
		
		try {
			// Execute SQL statement and get result set
			rset = db1.pst.executeQuery();
			while (rset.next()) {
				result = rset.getInt(1);
			}
			
			if(result > 0) {
				result = -999;
			}

			// close result set
			rset.close();

			// close database connection
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static int userBindWordInsert(int userID, int wordID) {
		int result = -999;
		int score = 0;
		
		String sql = null;
		MySQLConnector db1 = null;
		sql = "INSERT INTO user_memorize_status (user_id, word_id, score) values(?,?,?)";// SQL语句
		db1 = new MySQLConnector(sql);// 创建 MySQLConnector 对象

		try {

			db1.pst.setInt(1, userID);
			db1.pst.setInt(2, wordID);
			db1.pst.setInt(3, score);

			result = db1.pst.executeUpdate();

			
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public static int userBindWordDelete(int userID, int wordID) {
		String sql = null;
		MySQLConnector db1 = null;
		int result = -999;
		sql = "DELETE FROM user_memorize_status WHERE user_id = '" + userID + "' AND word_id = '" + wordID + "';";

		try {
			db1 = new MySQLConnector(sql);// 创建 MySQLConnector 对象

			result = db1.pst.executeUpdate();// 0 success, 1 fail
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	/**
	 * @author Cheng Wang
	 * @date 4/12/2023
	 * @description query all target user's subscription words list
	 * @param userID
	 * @return list wordsList
	 */
	public static List queryTargetUserWords(int userID) {
		List<List<String>> wordsList = new ArrayList<>();

		String sql = null;
		MySQLConnector db1 = null;
		ResultSet rset = null;

		sql = "SELECT"
				+ "	word.`name`,"
				+ "	word.meaning_en,"
				+ "	word.meaning_cn,"
				+ "	word.word_class"
				+ " FROM"
				+ "	user_memorize_status"
				+ " INNER JOIN word"
				+ " ON user_memorize_status.word_id = word.id"
				+ " WHERE"
				+ "	user_id = " + userID;

		db1 = new MySQLConnector(sql);// create MySQLConnector object

		try {
			// Execute SQL statement and get result set
			rset = db1.pst.executeQuery();
			while (rset.next()) {
				String name = rset.getString(1);
				String enMean = rset.getString(2);
				String cnMean = rset.getString(3);
				String wordClass = rset.getString(4);

				List inner = new ArrayList<>();
				
				inner.add(name);
				inner.add(enMean);
				inner.add(cnMean);
				inner.add(wordClass);
				
				wordsList.add(inner);
			}

			// close result set
			rset.close();

			// close database connection
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return wordsList;
	}
	
	/**
	 * #@author Cheng Wang
	 * @date 4/16/2023
	 * @description get user's subscription count daily random words 
	 * @param userID
	 * @param wordsNum
	 * @return List wordsList
	 */
	public static List queryDailyTargetUserWordsID(int userID, int wordsNum) {
		List wordsList = new ArrayList<>();

		String sql = null;
		MySQLConnector db1 = null;
		ResultSet rset = null;

		sql = "SELECT"
				//+ " user_memorize_status.user_id,"
				+ "	user_memorize_status.word_id"//,
				//+ " word.name"
				+ " FROM"
				+ "	user_memorize_status"
				+ " INNER JOIN word"
				+ " ON user_memorize_status.word_id = word.id"
				+ " WHERE"
				+ "	user_id = " + userID + " ORDER BY rand() LIMIT " + wordsNum;

		db1 = new MySQLConnector(sql);// create MySQLConnector object

		try {
			// Execute SQL statement and get result set
			rset = db1.pst.executeQuery();
			while (rset.next()) {
				//ArrayList<String> innerArraylist = new ArrayList<String>();
				int wordID = rset.getInt(1);
				//String name = rset.getString(3);
				//innerArraylist.add(userID+"");
				wordsList.add(wordID);
				//innerArraylist.add(name);
				
				//wordsList.add(innerArraylist);
			}

			// close result set
			rset.close();

			// close database connection
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return wordsList;
	}
	

	/**
	 * @author Cheng Wang
	 * @date 4/16/2023
	 * @description: query target single word list[name,en_mean,cn_mean,word_class] 
	 * @param wordID
	 * @return List user words list
	 */
	public static List queryUserSingleWordListbyWordID(int wordID) {
		List singlewordList = new ArrayList<>();

		String sql = null;
		MySQLConnector db1 = null;
		ResultSet rset = null;

		sql = "SELECT * FROM word WHERE id = " + wordID;

		db1 = new MySQLConnector(sql);// create MySQLConnector object

		try {
			// Execute SQL statement and get result set
			rset = db1.pst.executeQuery();
			while (rset.next()) {
				String word = rset.getString(2);
				String wordEnMean = rset.getString(3);
				String wordCnMean = rset.getString(4);
				String wordClass = rset.getString(5);

//				ArrayList<String> innerArraylist = new ArrayList<String>();
				singlewordList.add(word);
				singlewordList.add(wordEnMean);
				singlewordList.add(wordCnMean);
				singlewordList.add(wordClass);
				
				//TODO
				//wordsList.add(innerArraylist);
			}

			// close result set
			rset.close();

			// close database connection
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return singlewordList;
	}
	
	public static int importTargetDailyWordsList(int userID, int wordID) {
		//boolean flag = false;
		
		String sql = null;
		MySQLConnector db1 = null;
		sql = "INSERT INTO daily_words_list (user_id, word_id, create_date) values(?,?,?)";// SQL语句
		db1 = new MySQLConnector(sql);// 创建 MySQLConnector 对象
		int result = -999;
		
		Date today = new java.sql.Date(new java.util.Date().getTime());

		try {

			db1.pst.setInt(1, userID);
			db1.pst.setInt(2, wordID);
			db1.pst.setDate(3, (java.sql.Date) today);
			
			result = db1.pst.executeUpdate();

			// System.out.println(result);
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
		
	public static List queryWordbyWordID(int wordID) {
		int score = 0;
		String sql = null;
		MySQLConnector db1 = null;
		ResultSet rset = null;
		List singleWordList = new ArrayList<>();
		
		sql = "SELECT name, meaning_en, meaning_cn, word_class FROM word WHERE id = '" + wordID + "';";

		db1 = new MySQLConnector(sql);// create MySQLConnector object
		
		try {
			// Execute SQL statement and get result set
			rset = db1.pst.executeQuery();
			while (rset.next()) {
				String name = rset.getString(1);
				String enMean = rset.getString(2);
				String cnMean = rset.getString(3);
				String wordClass = rset.getString(4);
				
				singleWordList.add(name);
				singleWordList.add(enMean);
				singleWordList.add(cnMean);
				singleWordList.add(wordClass);
			}
//
//			List<String> s = new ArrayList<String>();
//			s.add(word);
//			s.add(score + "");

			// close result set
			rset.close();

			// close database connection
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return singleWordList;
	}
	
	public static int dailyTableCheck(int userID) {
	
		String sql = null;
		MySQLConnector db1 = null;
		ResultSet rset = null;
		int result = -999;
		
		Date today = new java.sql.Date(new java.util.Date().getTime());
		
		//System.out.println(today + "today!");
		
		sql = "SELECT count(*) FROM daily_words_list WHERE create_date = '" + today + "' AND user_id = " + userID;

		db1 = new MySQLConnector(sql);// create MySQLConnector object

		try {
			// Execute SQL statement and get result set
			rset = db1.pst.executeQuery();
			while (rset.next()) {
				result = rset.getInt(1);
				
			}

			// close result set
			rset.close();

			// close database connection
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static List queryUserSingleWordListbyWordName(String name) {
		List singlewordList = new ArrayList<>();

		String sql = null;
		MySQLConnector db1 = null;
		ResultSet rset = null;

		sql = "SELECT * FROM word WHERE name = '" + name + "';";

		db1 = new MySQLConnector(sql);// create MySQLConnector object

		try {
			// Execute SQL statement and get result set
			rset = db1.pst.executeQuery();
			while (rset.next()) {
				String word = rset.getString(2);
				String wordEnMean = rset.getString(3);
				String wordCnMean = rset.getString(4);
				String wordClass = rset.getString(5);

//				ArrayList<String> innerArraylist = new ArrayList<String>();
				singlewordList.add(word);
				singlewordList.add(wordEnMean);
				singlewordList.add(wordCnMean);
				singlewordList.add(wordClass);
				
				//TODO
				//wordsList.add(innerArraylist);
			}

			// close result set
			rset.close();

			// close database connection
			db1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return singlewordList;
	}

}
