package backend.service;

import java.io.File;
import java.util.List;

import backend.entity.Word;

public interface WordService {
	public List queryDailyWords(int userID);//User custom daily number of words to memorize; e.g. 50,100,150..
	public List queryTargetUserWords(int userID);
	
	public boolean wordsCSVImport(int userID, File f);
	public boolean wordsManualImport(int userID, String name, String wordEnMean, String wordCnMean, String wordClass);
	
	public boolean wordSubscriptionDeletion(int userID, String word);
	
	public boolean wordsBatchInsert(int userID, List WordsList);
	public boolean wordsModify(int userID, String name, String wordEnMean, String wordCnMean, String wordClass);//Word word
	public boolean wordsDelete(int userID, String name);//Word word
	
	
	public List wordsQuiz(int userID);
	public List wordsCompletion(int userID);
	
	public boolean wordsScoreUpdate(int userID, int type, String word);
	
	
	public List queryWordInfoByWordName(int userID, String word);
	
	public List wordPriorityQueue(int userID);
}
