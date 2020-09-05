package cn.xust.realm;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.xust.pojo.User;
import cn.xust.serviceimpl.UserServiceImpl;
import cn.xust.utils.EncryptKit;
import cn.xust.utils.JWTUtil;
import cn.xust.utils.RedisUtil;
import redis.clients.jedis.Jedis;
/**
 * shiro
 * last time 2020/1/2
 * add by galgaddott
 *
 */
public class accountRealm extends AuthorizingRealm {
	
		@Autowired
		private UserServiceImpl userService;
//		@Autowired
//		RedisUtil redisUtil;
		
		/**
	      	必须重写此方法，不然会报错
	     */
	    @Override
	    public boolean supports(AuthenticationToken token) {
	        return token instanceof JWTToken;
	    }
	    
	
		//授权
		//当用户进入权限页面时就会调用此方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		/*
		 * 1.从PrincipalCollection获取用户信息
		 * 2.利用用户的信息来查询角色或权限
		 * 3.创建AuthorizationInfo对象，并设置其roles属性
		 * 4.返回此对象
		 * 
		 * */
		System.out.println("进入权限认证界面");
	
		
        String account = JWTUtil.getAccount(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        
        int status = 0;
//        // 链接缓存
//	        Jedis jedis = redisUtil.getJedis();
//	     try {
//	    	   // 查询缓存
//	         String userRole = "user:"+account+":role";
//	         String role = jedis.get(userRole);
//	         
//	         
//	         
//	         if(role!=null && !role.equals("")){
//	             status =Integer.parseInt(role);
//	         }else{
//	             	// 如果缓存中没有，查询mysql
//	         		//去数据库查找权限
	         	     status = userService.selectAuthorizationInfo(account);
//	         	     jedis.set("user:"+account+":role",String.valueOf(status));
//	         }
//	     }finally {
//	    	 jedis.close();
//	     }
	        
		 //定义权限集合
		 Set<String> roles = new HashSet<>();
		
		if(status == 0) {
			roles.add("student");
		}
		if(status == 1) {
			roles.add("student");
			roles.add("teacher");
		}
		if(status == 2) {
			roles.add("student");
			roles.add("teacher");
			roles.add("admin");
		}
		
		info.setRoles(roles);
		
		return info;
	}

	//进行用户验证֤
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("进行登入********************");
		 String token = (String) authenticationToken.getCredentials();
	        // 解密获得account，用于和数据库进行对比
	        String account = JWTUtil.getAccount(token);
	        if (account  == null || !JWTUtil.verify(token, account )) {
	            throw new AuthenticationException("token认证失败！");
	        }
	
	        //Jedis jedis = redisUtil.getJedis();
	       // String key = "user:"+account+":password";
	       // String password = jedis.get(key);
//	        
//	        if(password!=null && !password.equals("")){
//
//	        }else{
	            	
	          String password = userService.selectPassword(account ,null);;
//	           jedis.set("user:"+account+":password",password);
//	        }
//	        jedis.close();

	        if (password == null) {
	            throw new AuthenticationException("该用户不存在！");
	        }
	        return new SimpleAuthenticationInfo(token, token, "MyRealm");

	}

}
