package cn.xust.pojo;

public class Question {
	private int id;
	private String content;
	private int askID;
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
	public int getAskID() {
		return askID;
	}
	public void setAskID(int askID) {
		this.askID = askID;
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
	public Question(int id, String content, int askID, String time, int isAnswer) {
		super();
		this.id = id;
		this.content = content;
		this.askID = askID;
		this.time = time;
		this.isAnswer = isAnswer;
	}
	
	
	public Question() {
	
	}
	
}
