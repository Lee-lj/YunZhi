package cn.xust.pojo;

public class Introduction {
	public int id;
	public String content;
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
	public Introduction(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	
	public Introduction() {
		
	}
}
