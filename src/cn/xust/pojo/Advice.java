package cn.xust.pojo;

public class Advice {
	public int id;
	public String name;
	public String content;
	public String time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Advice(int id, String name, String content, String time) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.time = time;
	}
	public Advice() {
	
	}
	
}
