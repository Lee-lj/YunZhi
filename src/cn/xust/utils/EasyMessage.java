package cn.xust.utils;

public class EasyMessage {
	private int status ;
	private String msg;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public EasyMessage(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	
	public EasyMessage() {
		
	}
}
