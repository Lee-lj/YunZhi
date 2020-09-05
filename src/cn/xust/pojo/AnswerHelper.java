package cn.xust.pojo;

public class AnswerHelper {
	private String realName;
	private String avatarUrl;
	private int id;
	private String content;
	private String time;
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public AnswerHelper( String realName, String avatarUrl, int id, String content, String time) {
		super();
		this.realName = realName;
		this.avatarUrl = avatarUrl;
		this.id = id;
		this.content = content;
		this.time = time;
	}
	
	public AnswerHelper() {
	
	}
	
}
