package backend.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import backend.dao.*;
import backend.entity.Word;
import backend.exception.*;

public class WordServiceImpl implements WordService {

	@Override
	public boolean wordsCSVImport(int userID, File f) {

		return false;
	}

	@Override
	public boolean wordsManualImport(int userID, String name, String wordEnMean, String wordCnMean, String wordClass) {

		boolean flag = false;
		int wordID = -999;
		// TODO: check table first, then insert!
		int checkWordRes = WordTableSQL.queryWordCheck(name);
//		System.out.println("Check Word:\t=========" + checkWordRes);
		if (checkWordRes != 0) {
			//Word Table has this word_id
			// get wordID from word table
			wordID = WordTableSQL.queryWordIDbyWord(name);
			
			int bindCheckRes = WordTableSQL.userBindWordCheck(userID, wordID);
			
			//System.out.println("bind check==========" + bindCheckRes);
			
			if(bindCheckRes != -999) {
				throw new CustomSQLException("Database Exception: Word '" + name + "' is already in your subscribe!");
			}else {
				int bindInsertRes = WordTableSQL.userBindWordInsert(userID,wordID);
				if(bindInsertRes != 1) {
					throw new CustomSQLException("Database Exception: Unknown SQL Exception!");
				}else {
					flag = true;
				}
			}
			throw new CustomSQLException("Word '" + name + "' is already in word table!");

		} else { 
			
			int result = WordTableSQL.singleWordInsert(name, wordEnMean, wordCnMean, wordClass);
			
			//System.out.println("result======" + result);
			
			if (result == 1) {
				// User bind Word
				wordID = WordTableSQL.queryWordIDbyWord(name);
				if(wordID != -999) {
					int bindCheckRes = WordTableSQL.userBindWordCheck(userID, wordID);
					
					//System.out.println("bind check==========" + bindCheckRes);
					
					if(bindCheckRes != -999) {
						throw new CustomSQLException("Database Exception: Word '" + name + "' is already in your subscribe!");
					}else {
						int bindInsertRes = WordTableSQL.userBindWordInsert(userID,wordID);
						if(bindInsertRes != 1) {
							throw new CustomSQLException("Database Exception: Unknown SQL Exception!");
						}else {
							flag = true;
						}
					}
				}else {
					throw new WordException("Program cannot find word '" + name + "' Cannot delete it!");
				}
				
				
			} else {
				throw new WordException("Program cannot insert word '" + name + "'! Please contact us ASAP!");
			}
		}

		return flag;
	}

	/*
	 * @method: returnDailyWords
	 * 
	 * @author: Cheng Wang
	 * 
	 * @date: 4/10/2023
	 * 
	 * @description: input user's custom daily words number
	 * words back
	 * 
	 * @param: int wordsNum
	 * 
	 * @return: list wordsList
	 */
	@Override
	public List queryDailyWords(int userID) {

		int wordsNum = 0;
		// TODO query user.count
		wordsNum = WordTableSQL.userDailyWordsCount(userID);
		
		List wordsIDList = WordTableSQL.queryDailyTargetUserWordsID(userID, wordsNum);
		List<List<String>> wordsList = new ArrayList<>();
		
		
		//[[2, 1], [2, 3]] [userID, wordID]
		//TODO 检查每日表是否已经被插入当天需要背的单词
		int dailyTableCheckRes = WordTableSQL.dailyTableCheck(userID);
		
		//System.out.println("每日表检查========== " + dailyTableCheckRes);
		
		if(dailyTableCheckRes == 0) {
			//插入每日单词
			for(int i=0; i < wordsIDList.size(); i++) {
				int wordID = (int) wordsIDList.get(i);
				
				int insertDailyTableRes = WordTableSQL.importTargetDailyWordsList(userID, wordID);
				if(insertDailyTableRes == 1) {
					//System.out.println("插入 word_id " + wordID + " 成功!");
					//获取从每日表中拿到 wordID 并返回所有每日单词
					wordsList.add(WordTableSQL.queryWordbyWordID(wordID));
					
				}else {
					throw new CustomSQLException("Database SQL Exception: Fail to insert daily words list into daily_words_list table!");
				}
			}
		}else if(dailyTableCheckRes > 0){
			for(int i=0; i < wordsIDList.size(); i++) {
				int wordID = (int) wordsIDList.get(i);
				wordsList.add(WordTableSQL.queryWordbyWordID(wordID));
			}
		}else {
			throw new CustomSQLException("Database SQL Exception: Database Error!");
		}
		
		
		return wordsList;
	}

	@Override
	public boolean wordsBatchInsert(int userID, List wordsList) {
		// Check word if exists

		return false;
	}

	@Override
	public boolean wordsModify(int userID, String name, String wordEnMean, String wordCnMean, String wordClass) {
		// Check word if exists
		int checkRes = WordTableSQL.queryWordCheck(name);

//		if(checkRes == 0) {
//    		System.out.println("数据库内没有相同单词，可执行插入操作！");
//    	}else if(checkRes == 1) {
//    		System.out.println("数据库内有相同单词，此处不可插入，请进行修改操作！");
//    	}else {
//    		System.out.println("数据异常，请联系我们!");
//    	}

		boolean flag = false;

		if (checkRes == 1) {
			flag = true;
			int modifyRes = WordTableSQL.wordsModify(name, wordEnMean, wordCnMean, wordClass);

			if (modifyRes == 1) {
				// System.out.println("Result:\t 修改了 " + result + "条!");
			} else {
				flag = false;
				throw new CustomSQLException("Database Exception: More than 1 row of same word have been changed!!!");
			}

		} else if (checkRes > 1) {
			throw new CustomSQLException("Database Exception: More than 1 same word '" + name + "' in MySQL database!");
		} else if (checkRes == 0) {
			throw new CustomSQLException("Database Exception: There is no such word '" + name + "' in MySQL database!");
		} else {
			throw new CustomSQLException("Database Error!");
		}

		return flag;
	}

	@Override
	public boolean wordsDelete(int userID, String name) {
		// TODO Auto-generated method stub
		//To get wordID by wordname
		int wordID = WordTableSQL.queryWordIDbyWord(name);
		
		int delRes = WordTableSQL.userBindWordDelete(userID,wordID);
		boolean flag = false;
		if (delRes == 1) {
			flag = true;
		} else if (delRes < 1) {
			throw new CustomSQLException("Database Exception: There is no such word '" + name + "'!");
		} else {
			throw new CustomSQLException("Database Exception: Unknown Exception!");
		}

		return flag;
	}

	/**
	 * @author Cheng Wang
	 * @date 4/14/2023
	 * @description: words quiz | return a list contains 1 correct Chinese meaning and 3 wrong meanings
	 * @return wordsList
	 */
	@Override
	public List wordsQuiz(int userID) {
		
		
		List wordsList = WordTableSQL.wordsQuiz(userID);

		return wordsList;
	}

	/**
	 * @author Cheng Wang
	 * @date 4/14/2023
	 * @description: words completion | return a words list contains 10 words and
	 *               its Chinese meaning and its not completed format
	 * @return wordsCompletionList
	 */
	@Override
	public List wordsCompletion(int userID) {
		List wordsCompletionList = WordTableSQL.wordsQuizPre(userID);// same as words quiz part -> 

		int size = wordsCompletionList.size();

		for (int i = 0; i < size; i++) {
			List section = (List) wordsCompletionList.get(i);
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
		return wordsCompletionList;

	}

	@Override
	public boolean wordsScoreUpdate(int userID, int type, String word) {
		boolean flag = false;
		
		// 1. Words Memorize - each time correct worth 5
		// 2. Words Quiz - each time correct worth 3
		// 3. Words Completion - each time correct worth 1

		int updateScore = 0;
		int score = -999;

		score = WordTableSQL.querySingleWordScore(userID, word);

		if (score < 0) {
			throw new CustomSQLException("Database Score Exception: initial score is wrong!");
		}

		if (type == 1) {
			updateScore = 5;

		} else if (type == 2) {
			updateScore = 3;
		} else if (type == 3) {
			updateScore = 1;
		} else {
			throw new WordException("Word Exception: Wrong type!");
		}

		// update score
		score = score + updateScore;
		if(score >= 100) {
			wordsDelete(userID, word);
			//System.out.println("删除" + word + "成功! 因为已经大于等于100!");
			throw new WordException("Word '" + word + "' " + "score >= 100! You did it!");
		}
		// Get wordID from word table
		int wordID = WordTableSQL.queryWordIDbyWord(word);
		if(wordID != -999) {
			// update score in database
			int scoreUpdateRes = WordTableSQL.updateSingleWordScore(userID, wordID, score);

			if(scoreUpdateRes == 1) {
				flag = true;
			}
		}
		
		return flag;
	}

	@Override
	public List queryTargetUserWords(int userID) {
		// TODO Auto-generated method stub
		List wordsList = WordTableSQL.queryTargetUserWords(userID);
		//System.out.println("User " + userID + "Words ID list" + wordsID.toString());

		//List<ArrayList<String>> wordsList = new ArrayList<>();
	
//		for(int i=0; i< wordsID.size(); i++) {
//			int wordID = Integer.parseInt(wordsID.get(i).toString());
//			List s = WordTableSQL.queryUserSingleWordListbyWordID(wordID);
//			wordsList.add((ArrayList<String>) s); 
//			
//		}
		
		return wordsList;
	}

	@Override
	public boolean wordSubscriptionDeletion(int userID, String word) {
		boolean flag = false;
		// check word first if exists
		
		
		int wordID = WordTableSQL.queryWordIDbyWord(word);
		
		
		int bindCheckRes = WordTableSQL.userBindWordCheck(userID, wordID);
		if(bindCheckRes != -999) {
			throw new CustomSQLException("Database Exception: Word '" + word + "' DOES NOT exits!!");
		}else {
			
			int delRes = WordTableSQL.userBindWordDelete(userID, wordID);
			if(delRes == 1) {
				//System.out.println("删除一条" + word);
				flag = true;
			}else {
				throw new CustomSQLException("Database Exception:\t" + "Word '" + word +"' cannot be Deleted!");
			}
		}
		return flag;
	}

	@Override
	public List queryWordInfoByWordName(int userID, String name) {
		// TODO Auto-generated method stub
		
		List wordInfo = WordTableSQL.queryUserSingleWordListbyWordName(name);
		
		return wordInfo;
	}

	@Override
	public List wordPriorityQueue(int userID) {
		List wordsList = queryDailyWords(userID);
		List<String> words = new ArrayList<>();
		 
		 for(int i = 0; i < wordsList.size(); i++) {
			 String word = ((List) wordsList.get(i)).get(0)+"";
			 //System.out.println(word);
			 words.add(word);
//			 String wordEn = ((List) wordsList.get(i)).get(1)+"";
//			 String wordCn = ((List) wordsList.get(i)).get(2)+"";
//			 String wordClass = ((List) wordsList.get(i)).get(3)+"";
			 //int score = Integer.parseInt(((List) wordsList.get(i)).get(4) + "");
			 
			 //wordPriorityQueue.offer(new Word(word,wordEn,wordCn,wordClass,0));
			 
		 }
		 
		 PriorityQueue<String> wordPriorityQueue = new PriorityQueue<>(words);//new PriorityQueue<>(new WordComparator())
		 //System.out.println(wordPriorityQueue.peek());
		 List<List<String>> finalWordsList = new ArrayList<>();
		 
		 for(int i = 0; i < wordPriorityQueue.size(); i++) {
				String word = wordPriorityQueue.poll();
				List wordInfo = queryWordInfoByWordName(userID, word);
				finalWordsList.add(wordInfo);
				
		}
		 return finalWordsList;
	}
	
	
	

}
