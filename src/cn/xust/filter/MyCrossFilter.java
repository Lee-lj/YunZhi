package cn.xust.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyCrossFilter implements Filter {

    public void destroy() {  
          
    }  
  
    public void doFilter(ServletRequest req, ServletResponse res,  
            FilterChain chain) throws IOException, ServletException {  

            HttpServletResponse response = (HttpServletResponse) res;  
            HttpServletRequest request = (HttpServletRequest) req; 
            
            response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
        	response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,OPTIONS");  
            response.setHeader("Access-Control-Max-Age", "3600");  
            response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,x-requested-with,Authorization");
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            chain.doFilter(req, res);  
           
    }  
  
    public void init(FilterConfig arg0) throws ServletException {  
          
    }  


}
