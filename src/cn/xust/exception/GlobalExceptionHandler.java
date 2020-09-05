package cn.xust.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.ShiroException;
import cn.xust.utils.EasyMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	 // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public EasyMessage handle401() {
        return new EasyMessage(401,"您没有权限访问！");
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public EasyMessage globalException(HttpServletRequest request, Throwable ex) {
    	 return new EasyMessage(0,"访问出错，无法访问: " + ex.getMessage());
    }

   
	
	
}
