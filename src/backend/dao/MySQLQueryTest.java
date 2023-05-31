package backend.dao;

import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.List;

public class MySQLQueryTest {  
    
    public static void main(String[] args) {  
        
    	//查询返回用户设定好的单词数量
//        queryWordsListTest();
  
    	//当插入单词时，需要提前检查单词在数据库中是否重复
//    	queryWordCheckTest();
    	
    	//单个插入单词
//        insertTest();

    	//单个修改单词
//       modifyTest();

    	//单个删除单词
//       deleteTest();

    	//单词测试 模块
    	wordQuizTest();
    }
    
    static void queryWordsListTest(){
    	System.out.println("================== Query Words List Test ==================");
    	List result = WordTableSQL.queryWords(3);
    	for(int i = 0; i < result.size(); i++) {
    		//System.out.println(result.get(i).toString());
    		List result_i = (List)result.get(i);
    		System.out.print("|");
    		for(int j = 0; j < result_i.size(); j++) {
    			System.out.print(result_i.get(j) + "|");
    		}
            System.out.println();
            
    	}
        System.out.println("================== Query Words List Test ==================");
        System.out.println();
        System.out.println();

    }
    
    static void queryWordCheckTest(){
    	String name = "word";
    	System.out.println("================== Query Word Check Test ==================");
    	int result = WordTableSQL.queryWordCheck(name);
    	if(result == 0) {
    		System.out.println("数据库内没有相同单词，可执行插入操作！");
    	}else if(result == 1) {
    		System.out.println("数据库内有相同单词，此处不可插入，请进行修改操作！");
    	}else {
    		System.out.println("数据异常，请联系我们!");
    	}
    	//System.out.println("Result:\t" + result);    
    	
        System.out.println("================== Query Word Check Test ==================");
        System.out.println();
        System.out.println();

    }
    
    
    static void insertTest(){
    	System.out.println("================== Insert Test ==================");
    	int result = WordTableSQL.singleWordInsert("happy", "happy", "开心1", "noun");
    	if(result == 0) {
    		System.out.println("Result:\t" + "SUCCESS!");
    	}
    	
    	System.out.println("================== Insert Test ==================");
    	System.out.println();
    	System.out.println();
    	
    }
    
    
    static void modifyTest() {
        System.out.println("================== Modify Test ==================");
        int result = WordTableSQL.wordsModify("happy", "happy!!!", "开心吶", "名词");
        if(result > 0) {
        	System.out.println("Result:\t 修改了 " + result + "条!");
        }
        System.out.println("================== Modify Test ==================");
        System.out.println();
        System.out.println();
    }
    	
    static void deleteTest() {
        System.out.println("================== Delete Test ==================");
        String name = "happy";
        int result = WordTableSQL.wordsDelete(name);
        if(result > 0) {
        	System.out.println("Result:\t 删除了 " + result + "条!");
        }else {
        	System.out.println("数据库中并没有 " + name);
        }
        System.out.println("================== Delete Test ==================");
    }
    
    static void wordQuizTest() {
        System.out.println("================== Word Quiz Test ==================");
        int userID = 1;
        List result = WordTableSQL.wordsQuiz(userID);
        System.out.println(result.toString());
        System.out.println("================== Word Quiz Test ==================");
    }
    
}
