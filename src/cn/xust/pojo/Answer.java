package cn.xust.pojo;

public class Answer {
	private int id;
	private String content;
	private int questionID;
	private int answerID;
	private String time;
	private int isAnswer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getQuestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public int getAnswerID() {
		return answerID;
	}
	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getIsAnswer() {
		return isAnswer;
	}
	public void setIsAnswer(int isAnswer) {
		this.isAnswer = isAnswer;
	}
	public Answer(int id, String content, int questionID, int answerID, String time, int isAnswer) {
		super();
		this.id = id;
		this.content = content;
		this.questionID = questionID;
		this.answerID = answerID;
		this.time = time;
		this.isAnswer = isAnswer;
	}
	public Answer() {
	
	}
	
	public Answer(int questionID, int answerID) {
		this.questionID = questionID;
		this.answerID = answerID;
	}
	
}
