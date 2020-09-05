package cn.xust.utils;

/**
 * 状态码类
 * @author galgaddott
 *
 */
public enum Code {

	
	Login_SUCCESS(1,"登陆成功!"),
    Login_FAIL(0,"登录失败！"),
    Register_SUCCESS(1,"注册成功！"),
    Register_FAIL(0,"注册失败！"),
    EMAIL_SUCCESS(1,"发送邮件成功！"),
    EMAIL_FAIL(0,"发送邮件失败！"),
    UPLOAD_SUCCESS(1,"上传成功!"),
    UPLOAD_FAIL(0,"上传失败"),
    UPDATE_SUCCESS(1,"更新成功"),
    UPDATE_FAIL(0,"更新失败"),
    DELETE_SUCESS(1,"删除成功"),
    DELETE_FAIL(0,"删除失败"),
    CREATE_SUCCESS(1,"创建成功"),
    CREATE_FAIL(0,"创建失败"),
    OPERATE_SUCCESS(1,"操作成功"),
    OPERATE_FAIL(0,"操作失败"),
    ERROR_CODE(500,"服务器内部错误！"),
    SERVER_PATH(1,"http://server.versewow.cn/yunzhi");
  

	private Integer code;
    private String msg;
    
	private Code(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	 
    public Integer getCode() {
		return code;
	}


	public String getMsg() {
		return msg;
	}

	
	
	
}
