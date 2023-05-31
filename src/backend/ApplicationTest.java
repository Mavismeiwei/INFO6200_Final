package backend;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import backend.dao.WordTableSQL;
import backend.entity.User;
import backend.entity.Word;
import backend.service.UserServiceImpl;
import backend.service.WordServiceImpl;

/**
 * 
 * @author Cheng Wang
 * @date 02/09/2023
 * @description 此 Test.java 文件只用于测试项目
 *
 */
public class ApplicationTest {
	public static void main(String[] args) {
		//System.out.println("Hello CSYE 6200!");
//		String word = "hello";
//		String audioAPI = "http://dict.youdao.com/dictvoice?audio=" + word;
//		String dictCSVPath = "https://raw.githubusercontent.com/skywind3000/ECDICT/master/ecdict.csv";
		
		
		/*       请无视 MySQLQueryTest ~~~ 所有接口都在这里写了测试如何调用，直接照抄即可~       */
		
		// 每日单词 通过 user 表中设置的每日单词 比如 5 然后从订阅表里随机检索5个单词 优先级未做 TODO
		// Words List Test
		// queryDailyWordsTest();
		
		
		// 单词测试模块
		// Words Quiz Test
		// wordsQuizTest();
		
		
		// 单词拼写模块
		// Words Completion Test
		// wordsCompletionTest();
		
		/*------------------------ User Action Test ------------------------*/
		//用于用户添加单词 1. 直接在单词 word 表里添加 2. 作为自己的单词订阅表 user_memorize_status 所以两张表都会更新
		// userBindSginleWordTest();//Insert both word and user_memorize_status
		
		// 用于用户删除单词 1. 不会删除 word 表里的任何单词，太麻烦了 2. 只删除自己单词订阅表里的单词
		// userWordSubscribeDeletion();
		
		
		// 用于用户每一个单词的分数更新，是否到 100 | type=1 是记单词页面 type=2 是单词测试 type=3 是单词拼写
		//userWordScoreUpdate();
		
		/*------------------------ User Action Test ------------------------*/
		
		
		
		/*------------------------ Word Test ------------------------*/
		// query target user's words list
		// 检索当前用户的所有订阅单词，这里会比每日的多，因为是所有的
		// queryTargetUserWordsTest();
		
		// 单个单词的插入，目前可以不用，因为上面已经通过指定用户新增 可以当作废弃接口
		// Single Word Insert Test
		 //singleWordInsertTest();
		
		// 单个单词的修改，这个没有问题，可以直接使用
		// Words Modify Test
		// wordsSubscriptionModifyTest();
		
		
		// 单个单词的删除，目前可以不用，因为上面已经通过指定用户删除 可以当作废弃接口
		// Single Word Delete Test
		// wordsDeleteTest();
		/*------------------------ Word Test ------------------------*/
		
		
		
		/*------------------------ User Test ------------------------*/
		//不会用在前端 UI 显示上，仅测试
		//User Check if exists
		//userCheck();
		
		//用户信息展示
		// User Info Query Test
		//queryUserInfo();
		
		//用户注册测试
		//User Registration
		//userResitration();

		//用户修改测试
		//User Config Modify
		//userConfigModify();
		
		//用户删除 已废弃
		//User Deletion @depreceted
		//userDelete();
		
		//用户登陆
		//User Login Check
		//userLoginCheck();//return userID!
		/*------------------------ User Test ------------------------*/
		
		//返回单词的优先级测试
		//queryDailyWords 的替代品!!!!!!!!!!!!!!!!!!!!!! 可以返回经过优先级判断之后的每日单词
		//Priority Queue Test
		//priorityQueueTest();
	}
	
	
	/**
	 * @Test: Daily Words List Test | 返回随机用户设置每日数量的订阅单词 TODO: 优先级 + 每日固定
	 * @date 4/10/2023
	 * @author: Cheng Wang
	 *  
	 */
	public static void queryDailyWordsTest(){
		WordServiceImpl wd = new WordServiceImpl();
		int userID = 2;
		List wordsList = wd.queryDailyWords(userID);

		System.out.println(wordsList.toString());
		
	}
	
	/**
	 * @Test: Words Quiz Test
	 * @date: 4/14/2023
	 * @author: Cheng Wang
	 * 
	 */
	public static void wordsQuizTest() {
		WordServiceImpl wd = new WordServiceImpl();
		int userID = 2;
		
		
		List wordsQuiz = wd.wordsQuiz(userID);
		System.out.println(wordsQuiz.toString());
	}
	
	/**
	 * @Test: Words Completion Test
	 * @date 4/14/2023
	 * @author Cheng Wang
	 */
	public static void wordsCompletionTest() {
		WordServiceImpl wd = new WordServiceImpl();
		int userID = 1;
		List wordsCompletion = wd.wordsCompletion(userID);
		System.out.println(wordsCompletion.toString());
	}
	
	/**
	 * @Test: Single Word Modify Test
	 * @date 4/14/2023
	 * @author Cheng Wang
	 */
	public static void wordsSubscriptionModifyTest() {
		WordServiceImpl wd = new WordServiceImpl();
		String name = "1";
		String wordENMean = "111";
		String wordCNMean = "111";
		String wordClass = "111";// old = 1 new = 2
		
		int userID = 1;
		
		boolean modRes = wd.wordsModify(userID, name, wordENMean, wordCNMean, wordClass);
		System.out.println("Word " + name +  " modify Result:\t" + modRes);
	}
	
	/**
	 * @Test Single Word Delete Test
	 * @date 4/14/2023
	 * @author Cheng Wang
	 */
	public static void wordsSubscriptionDeleteTest() {
		WordServiceImpl wd = new WordServiceImpl();
		String word = "1";
		int userID = 1;
		boolean delRes = wd.wordsDelete(userID, word);
		System.out.println("Word " + word + " delete Result:\t" + delRes);
	}
	
	/**
	 * @Test Single Word Insert Test
	 * @date 4/14/2023
	 * @author Cheng Wang
	 */
	public static void singleWordInsertTest() {
		WordServiceImpl wd = new WordServiceImpl();
		String name = "hhh12";
		String wordENMean = "666";
		String wordCNMean = "666";
		String wordClass = "666";
		int userID = 1;
		boolean insertRes = wd.wordsManualImport(userID, name, wordENMean, wordCNMean, wordClass);
		System.out.println("Word '" + name + "' insert Result:\t" + insertRes);
		
	}
	
	/**
	 * @Test user bind single word Test
	 * @date 4/15/2023
	 * @author Cheng Wang
	 */
	public static void userBindSginleWordTest(){
		WordServiceImpl wd = new WordServiceImpl();
		int userID = 1;
		String name = "hhh12";
		String wordEnMean = "hhh1";
		String wordCnMean = "hhh";
		String wordClass = "hhh";
		
		boolean userBindSingleWordRes = wd.wordsManualImport(userID, name, wordEnMean, wordCnMean, wordClass);

		System.out.println("User '" + userID +"' bind word '" + name + "'Result:\t" + userBindSingleWordRes);
		
		
	}
	
	/**
	 * @author Cheng Wang
	 * @date 4/13/2023
	 * @description: query target user's words subscription list
	 */
	public static void queryTargetUserWordsTest() {
		WordServiceImpl ws = new WordServiceImpl();
		int userID = 2;
		List wordsList = ws.queryTargetUserWords(userID);
		System.out.println("------------------------ User Words Subscription Test ------------------------");
//		System.out.println("------------------------ 用户单词订阅测试 ------------------------");
		System.out.println(wordsList.toString());
		System.out.println("------------------------ User Words Subscription Test ------------------------");
		
	}
	
	public static void userWordSubscribeDeletion() {
		WordServiceImpl ws = new WordServiceImpl();
		int userID = 1;
		String word = "hhh12";
		boolean flag = ws.wordSubscriptionDeletion(userID, word);
		System.out.println("Result:\t" + flag);
	}
	
	public static void userWordScoreUpdate() {
		WordServiceImpl ws = new WordServiceImpl();
		int userID = 1;
		String word = "hhh1234";
		int type = 1;//1. word memorize=5 2. word quiz=3 3. word completion=1
		boolean scoreUpdate = ws.wordsScoreUpdate(userID, type, word);
		System.out.println("User Score Update:\t" + scoreUpdate);
		
	}
	
	public static void queryUserInfo() {
		int userID = 4;
		UserServiceImpl us = new UserServiceImpl();
		List userInfo = us.queryUser(userID);
		System.out.println("--------------------- User Query Test ---------------------");
		System.out.println(userInfo.toString());
		System.out.println("--------------------- User Query Test ---------------------");
	}
	
	public static void userResitration() {
		String username = "user12";
		String password = "123";
		String firstName = "aaa";
		String lastName = "bbb";
		String email = "ccc@gmail.com";
		int dailyWordsNum = 3;
		UserServiceImpl us = new UserServiceImpl();
		boolean createUserResult = us.createUser(username, password, firstName, lastName, email, dailyWordsNum);
		System.out.println("--------------------- User Registration Test ---------------------");
		System.out.println("Reistration Result:\t" + createUserResult);
		System.out.println("--------------------- User Registration Test ---------------------");
		
		
	}
	
	public static void userCheck() {
		String username = "user1";
		UserServiceImpl us = new UserServiceImpl();
		
		boolean userCheckRes = us.userCheck(username);
		
		System.out.println("--------------------- User Check Test ---------------------");
		System.out.println("User Check Result(true -> User exists in `User` table, you cannot register a same username account!!!):\t" + userCheckRes);
		System.out.println("--------------------- User Check Test ---------------------");
		
	}
	
	public static void userConfigModify() {
		int userID = 4;
		
		String username = "user1234";
		String password = "123444";
		String firstName = "aaa";
		String lastName = "bbb";
		String email = "neu12@gmail.com";
		int dailyWordsNum = 5;
		UserServiceImpl us = new UserServiceImpl();
		boolean userModifyResult = us.userConfigModify(userID, username, password, firstName, lastName, email, dailyWordsNum);
		
		System.out.println("--------------------- User Config Modify Test ---------------------");
		System.out.println("User Config Modify:\t" + userModifyResult);
		System.out.println("--------------------- User Config Modify Test ---------------------");
		
	}
	
	
	public static void userDelete() {
		//I don't think we need delete user account! @depreceted
	}
	
	public static void userLoginCheck() {
		String username = "user123";
		String inputPWD = "123";
		
		UserServiceImpl us = new UserServiceImpl();
		int userID = us.loginCheck(username, inputPWD);
		
		System.out.println("Login Check User ID:\t" + userID);//return -999 -> login fail reutn > 0 -> login success 获得userID!
		
		User us1 = new User();
		us1.id = userID;
		
		User us2 = new User();
		System.out.println("static 关键字共享 id! + " + us2.id);
		
		
	}
	
	
	
	public static void priorityQueueTest() {
		//1. Get Words List
		WordServiceImpl wd = new WordServiceImpl();
		int userID = 1;
		
		List finalWordsList = wd.wordPriorityQueue(userID);
		 
		 System.out.println("------------------------- Word Priority Queue Test -------------------------");
		System.out.println(finalWordsList.toString());
		 System.out.println("------------------------- Word Priority Queue Test -------------------------");

//        ArrayList<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("v");
//        list.add("z");
//        list.add("aa");
//
//        //3、用 ArrayList 集合来创建一个优先级队列的对象
//        PriorityQueue<String> queue3 = new PriorityQueue<>(list);
//        //System.out.println(queue3.size());
//        PriorityQueue<String> queue34 = queue3;
//        for(int i = 0; i < list.size(); i++) {
//        	//System.out.println(queue3.poll());
//        	System.out.println(queue34.poll());
//        }
		
//		List wordsList = WordTableSQL.queryPriorityQueueWordsList(10);
//		PriorityQueue<String> priorityQueue = new PriorityQueue<>(wordsList);
//		for(int i = 0; i < priorityQueue.size();i++) {
//			System.out.println(priorityQueue.poll());
//			
//		}
		
        
        
	}
	
	
}

class WordComparator implements Comparator<Word> {
    public int compare(Word w1, Word w2) {
    	return w1.word.compareTo(w2.word);
    }
}
