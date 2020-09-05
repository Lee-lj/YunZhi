package cn.xust.pojo;
/**
 * 学院类
 * @author galgaddott
 *
 */
public class Acadamy {
	private int id;
	private String collegeName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public Acadamy(int id, String collegeName) {
		super();
		this.id = id;
		this.collegeName = collegeName;
	}
	
	public Acadamy() {
	
	}
	
	
}
