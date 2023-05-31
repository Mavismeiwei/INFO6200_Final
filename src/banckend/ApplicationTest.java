 	package banckend;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

import backend.dao.WordTableSQL;
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
		//wordsListReturnTest();
		
		
		// 单词测试模块
		// Words Quiz Test
		// wordsQuizTest();
		
		
		// 单词拼写模块
		// Words Completion Test
		 wordsCompletionTest();
		
		/*------------------------ User Action Test ------------------------*/
		//用于用户添加单词 1. 直接在单词 word 表里添加 2. 作为自己的单词订阅表 user_memorize_status 所以两张表都会更新
		// userBindSginleWordTest();//Insert both word and user_memorize_status
		
		// 用于用户删除单词 1. 不会删除 word 表里的任何单词，太麻烦了 2. 只删除自己单词订阅表里的单词
		// userWordSubscribeDeletion();
		
		
		// 用于用户每一个单词的分数更新，是否到 100 然后检查优先级没有做 TODO 仅仅在数据库中更新分数 type=1 是记单词页面 type=2 是单词测试 type=3 是单词拼写
		// userWordScoreUpdate();
		
		/*------------------------ User Action Test ------------------------*/
		
		
		
		/*------------------------ Word Test ------------------------*/
		// query target user's words list
		// 检索当前用户的所有订阅单词，这里会比每日的多，因为是所有的
		// queryTargetUserWordsTest();
		
		// 单个单词的插入，目前可以不用，因为上面已经通过指定用户新增 可以当作废弃接口
		// Single Word Insert Test
		// singleWordInsertTest();
		
		// 单个单词的修改，这个没有问题，可以直接使用
		// Words Modify Test
		// wordsModifyTest();
		
		
		// 单个单词的删除，目前可以不用，因为上面已经通过指定用户删除 可以当作废弃接口
		// Single Word Delete Test
		// wordsDeleteTest();
		/*------------------------ Word Test ------------------------*/
	}
	
	/**
	 * @Test: Words List Test
	 * @date 4/10/2023
	 * @author: Cheng Wang
	 *  
	 */
	public static void wordsListReturnTest(){
		WordServiceImpl wd = new WordServiceImpl();
		int userID = 1;
		List wordsList = wd.queryDailyWords(userID);//Customer set
//		for(int i = 0; i < wordsList.size(); i++) {
//			List a = (List) wordsList.get(i);
//			System.out.println(a.get(0));
//		}
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
		int userID = 1;
		
		
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
	public static void wordsModifyTest() {
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
	public static void wordsDeleteTest() {
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
		String name = "666";
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
	
	public static void queryTargetUserWordsTest() {
		WordServiceImpl ws = new WordServiceImpl();
		int userID = 1;
		List wordsList = ws.queryTargetUserWords(userID);
		
		System.out.println(wordsList.toString());
		
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
}
