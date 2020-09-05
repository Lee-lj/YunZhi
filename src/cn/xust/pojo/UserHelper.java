package cn.xust.pojo;

public class UserHelper {
	private int uuid;
	private String account;
	private String realName;
	private String email;
	private String avatarUrl;
	private int status;
	private int isActivate;
	private String majorName;
	private String collegeName;
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIsActivate() {
		return isActivate;
	}
	public void setIsActivate(int isActivate) {
		this.isActivate = isActivate;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public UserHelper(int uuid, String account, String realName, String email, String avatarUrl, int status,
			int isActivate, String majorName, String collegeName) {
		super();
		this.uuid = uuid;
		this.account = account;
		this.realName = realName;
		this.email = email;
		this.avatarUrl = avatarUrl;
		this.status = status;
		this.isActivate = isActivate;
		this.majorName = majorName;
		this.collegeName = collegeName;
	}
	
	public UserHelper() {
	
	}
	
}
