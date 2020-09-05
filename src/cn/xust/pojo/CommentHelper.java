package cn.xust.pojo;
/**
 * 仅为解决2020/5/1凌晨莫名其妙的类型转换bug ^~^
 * @author 周蔚
 *
 */
public class CommentHelper {
	private String time;
	private String content;
	private int questionID;
	private int answerID;
	private int uuid;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public CommentHelper(String time, String content, int questionID, int answerID, int uuid) {
		super();
		this.time = time;
		this.content = content;
		this.questionID = questionID;
		this.answerID = answerID;
		this.uuid = uuid;
	}
	public CommentHelper() {

	}
	
}
