package cn.xust.pojo;

public class Major {
	private int id;
	private String majorName;
	private int collegeId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public int getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}
	public Major(int id, String majorName, int collegeId) {
		super();
		this.id = id;
		this.majorName = majorName;
		this.collegeId = collegeId;
	}
	
	public Major() {
		
	}
	
	
	
	
}

