package backend.entity;

public class Word {
//	private String word;
//	private String wordEn;
//	private String wordCn;
//	private String wordClass;
//	private int score;
//	
//	public String getWord() {
//		return word;
//	}
//	public void setWord(String word) {
//		this.word = word;
//	}
//	public String getWordEn() {
//		return wordEn;
//	}
//	public void setWordEn(String wordEn) {
//		this.wordEn = wordEn;
//	}
//	public String getWordCn() {
//		return wordCn;
//	}
//	public void setWordCn(String wordCn) {
//		this.wordCn = wordCn;
//	}
//	public String getWordClass() {
//		return wordClass;
//	}
//	public void setWordClass(String wordClass) {
//		this.wordClass = wordClass;
//	}
//	public int getScore() {
//		return score;
//	}
//	public void setScore(int score) {
//		this.score = score;
//	}
	
	public int score;
	public String word;
	public String wordCn;
	public String wordEn;
	public String wordClass;
	
	
	public Word(String word, String wordEn, String wordCn, String wordClass, int score) {
		this.score = score;
		this.wordCn = wordCn;
		this.wordEn = wordEn;
		this.wordClass = wordClass;
		this.score = score;
	}

}
