package cn.xust.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.github.pagehelper.PageInfo;

import cn.xust.pojo.Advice;
import cn.xust.pojo.Answer;
import cn.xust.pojo.Event;
import cn.xust.pojo.Example;
import cn.xust.pojo.Introduction;
import cn.xust.pojo.Policy;
import cn.xust.pojo.Question;
import cn.xust.pojo.Teacher;
import cn.xust.pojo.User;
import cn.xust.pojo.UserHelper;
import cn.xust.service.AdminService;
import cn.xust.serviceimpl.AdminServiceImpl;
import cn.xust.utils.Code;
import cn.xust.utils.EasyMessage;
import cn.xust.utils.EncryptKit;
import cn.xust.utils.Message;
import cn.xust.utils.UploadUtils;


/**
 * 后台管理员类
 * add by galgaddott
 * last time 2020/1/6
 *
 */
@Controller
@RequestMapping("/admin")
public class AdmainController {
	
	@Autowired
	public AdminService adminService;
	
	//@RequiresRoles("admin")
	@RequestMapping("image")
	@CrossOrigin
	@ResponseBody
	public Object getImage(HttpServletRequest request,HttpServletResponse response) {
		
		Message message = new Message();
		
		 //获取文件解析器
		 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
		 
		 //判断request里是否有文件
		 
		 if(multipartResolver.isMultipart(request)) {
			 
			 //转化request
			 MultipartHttpServletRequest multrequest = (MultipartHttpServletRequest) request;
			 
			 //获取文件
			 MultipartFile file = multrequest.getFile("file");
			
			 
	         //上传文件  
			 String imageURL = UploadUtils.upload(file, request, "/TEACHER_IMAGE");
			 
			 if(imageURL==null) {
				 
					message.setStatus(Code.UPLOAD_FAIL.getCode());
					message.setMsg(Code.UPLOAD_FAIL.getMsg());
				 
			 }else {
					message.setStatus(Code.UPLOAD_SUCCESS.getCode());
					message.setMsg(Code.UPLOAD_SUCCESS.getMsg());
					message.setData(imageURL);
			 }
		
		 }
		 else {
			   message.setStatus(Code.UPLOAD_FAIL.getCode());
			   message.setMsg(Code.UPLOAD_FAIL.getMsg());
		 }
		
		return message;
	}

	@RequiresRoles("admin")
	@RequestMapping("teacher")
	@CrossOrigin
	@ResponseBody
	public Object getTeacher(@RequestBody Teacher teacher) {
		EasyMessage message = new EasyMessage();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		teacher.setTime(df.format(new Date()));
		
		boolean flag =adminService.insertTeacher(teacher);
		
		if(flag == true) {
			message.setStatus(Code.UPLOAD_SUCCESS.getCode());
			message.setMsg(Code.UPLOAD_SUCCESS.getMsg());
		}else {
			message.setStatus(Code.UPLOAD_FAIL.getCode());
			message.setMsg(Code.UPLOAD_FAIL.getMsg());
		}
		
		return  message;
		
	}
	
	/**
	 * 	编辑（修改）专家信息
	 * @param teacher 修改后的信息
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping("edit_teacher")
	@CrossOrigin
	@ResponseBody
	public Object editTeacher(@RequestBody Teacher teacher) {
		EasyMessage message = new EasyMessage();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		teacher.setTime(df.format(new Date()));
		
		boolean flag =adminService.editTeacher(teacher);
		
		if(flag == true) {
			message.setStatus(Code.UPDATE_SUCCESS.getCode());
			message.setMsg(Code.UPDATE_SUCCESS.getMsg());
		}else {
			message.setStatus(Code.UPDATE_FAIL.getCode());
			message.setMsg(Code.UPDATE_FAIL.getMsg());
		}
		
		
		
		return message;
	}
	
	/**
	 * 	删除专家信息
	 * @param teacherId  专家ID
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping("delete_teacher")
	@CrossOrigin
	@ResponseBody
	public Object deleteTeacher(@RequestParam("teacherId") int teacherId) {
		EasyMessage message = new EasyMessage();
		boolean flag = adminService.deleteTeacher(teacherId);
		
		if(flag == true) {
			message.setStatus(Code.DELETE_SUCESS.getCode());
			message.setMsg(Code.DELETE_SUCESS.getMsg());
		}else {
			message.setStatus(Code.DELETE_FAIL.getCode());
			message.setMsg(Code.DELETE_FAIL.getMsg());
			
		}
		

		return message;
	}
	
	/**
	 *显示所有专家信息
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping("display_teachers")
	@CrossOrigin
	@ResponseBody
	public Object displayTeachers() {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		List<Teacher> teachers = adminService.displayTeachers();
		
		message.setData(teachers);
		return message;
	}
	
	
	/**
	 * 查询专家信息
	 */
	@RequiresRoles("admin")
	@RequestMapping("query_teacher")
	@CrossOrigin
	@ResponseBody
	public Object queryTeacher(@RequestParam("id") int teacherId) {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		Teacher teacher = adminService.queryTeacher(teacherId);
		message.setData(teacher);
		
		return message;
		
		
	}
	
	/**
	 * 添加政策
	 */
	@RequiresRoles("admin")
	@RequestMapping("policy")
	@CrossOrigin
	@ResponseBody
	public EasyMessage addPolicy(@RequestBody Policy policy) {
		EasyMessage message = new EasyMessage();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		policy.setTime(df.format(new Date()));
		
		boolean flag = adminService.insertPolicy(policy);
		
		if(flag == true) {
			message.setStatus(Code.OPERATE_SUCCESS.getCode());
			message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		}else {
			message.setStatus(Code.OPERATE_FAIL.getCode());
			message.setMsg(Code.OPERATE_FAIL.getMsg());
		}
		
		return message;
	}
	
	
	/**
	 * 显示所有政策
	 */
	@RequiresRoles("admin")
	@RequestMapping("display_policys")
	@CrossOrigin
	@ResponseBody
	public Message displayPolicys() {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		List<Policy> policys = adminService.displayPolicy();
		
		message.setData(policys);
		
		
		return message;
	}
	
	
	/**
	 * 删除政策
	 */
	@RequiresRoles("admin")
	@RequestMapping("delete_policy")
	@CrossOrigin
	@ResponseBody
	public EasyMessage deletePolicy(@RequestParam("policyId") int policyId) {
		EasyMessage message = new EasyMessage();
		boolean flag = adminService.deletePolicy(policyId);
		
		if(flag == true) {
			message.setStatus(Code.DELETE_SUCESS.getCode());
			message.setMsg(Code.DELETE_SUCESS.getMsg());
		}else {
			message.setStatus(Code.DELETE_FAIL.getCode());
			message.setMsg(Code.DELETE_FAIL.getMsg());
		}
		
		return message;
	}
	
	
	/**
	 * 查询政策
	 */
	@RequiresRoles("admin")
	@RequestMapping("query_policy")
	@CrossOrigin
	@ResponseBody
	public Message queryPolicy(@RequestParam("policyId") int policyId) {
		
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		Policy policy = adminService.queryPolicy(policyId);
		message.setData(policy);
		
		return message;
		
		
	}
	
	
	/**
	 * 编辑(修改)政策
	 */
	@RequiresRoles("admin")
	@RequestMapping("edit_policy")
	@CrossOrigin
	@ResponseBody
	public EasyMessage editPolicy(@RequestBody Policy policy) {
		EasyMessage message = new EasyMessage();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		policy.setTime(df.format(new Date()));
		
		boolean flag =adminService.updatePolicy(policy);
		
		if(flag == true) {
			message.setStatus(Code.UPDATE_SUCCESS.getCode());
			message.setMsg(Code.UPDATE_SUCCESS.getMsg());
		}else {
			message.setStatus(Code.UPDATE_FAIL.getCode());
			message.setMsg(Code.UPDATE_FAIL.getMsg());
		}
		
		
		
		return message;
	}
	
	/**
	 * 添加案例
	 */
	@RequiresRoles("admin")
	@RequestMapping("example")
	@CrossOrigin
	@ResponseBody
	public EasyMessage addExample(@RequestBody Example example) {
		EasyMessage message = new EasyMessage();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		example.setTime(df.format(new Date()));
		
		boolean flag = adminService.insertExample(example);
		
		if(flag == true) {
			message.setStatus(Code.OPERATE_SUCCESS.getCode());
			message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		}else {
			message.setStatus(Code.OPERATE_FAIL.getCode());
			message.setMsg(Code.OPERATE_FAIL.getMsg());
		}
		
		return message;
	}
	
	
	
	/**
	 * 显示所有案例
	 */
	@RequiresRoles("admin")
	@RequestMapping("display_examples")
	@CrossOrigin
	@ResponseBody
	public Message displayExamples() {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		List<Example> examples = adminService.displayExample();
		
		message.setData(examples);
		
		
		return message;
	}
	
	
	/**
	 * 删除案例  
	 */
	@RequiresRoles("admin")
	@RequestMapping("delete_example")
	@CrossOrigin
	@ResponseBody
	public EasyMessage deleteExample(@RequestParam("exampleId") int exampleId) {
		EasyMessage message = new EasyMessage();
		boolean flag = adminService.deleteExample(exampleId);
		
		if(flag == true) {
			message.setStatus(Code.DELETE_SUCESS.getCode());
			message.setMsg(Code.DELETE_SUCESS.getMsg());
		}else {
			message.setStatus(Code.DELETE_FAIL.getCode());
			message.setMsg(Code.DELETE_FAIL.getMsg());
		}
		
		return message;
	}
	
	
	/**
	 * 查询案例
	 */
	@RequiresRoles("admin")
	@RequestMapping("query_example")
	@CrossOrigin
	@ResponseBody
	public Message queryExample(@RequestParam("exampleId") int exampleId) {
		
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		Example example = adminService.queryExample(exampleId);
		message.setData(example);
		
		return message;
		
		
	}
	
	
	/**
	 * 编辑(修改)案例
	 */
	@RequiresRoles("admin")
	@RequestMapping("edit_example")
	@CrossOrigin
	@ResponseBody
	public EasyMessage editExample(@RequestBody Example example) {
		EasyMessage message = new EasyMessage();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		example.setTime(df.format(new Date()));
		
		boolean flag =adminService.updateExample(example);
		
		if(flag == true) {
			message.setStatus(Code.UPDATE_SUCCESS.getCode());
			message.setMsg(Code.UPDATE_SUCCESS.getMsg());
		}else {
			message.setStatus(Code.UPDATE_FAIL.getCode());
			message.setMsg(Code.UPDATE_FAIL.getMsg());
		}
		
		
		
		return message;
	}
	
	
	/**
	 *  添加活动
	 * @throws ParseException 
	 */
	@RequiresRoles("admin")
	@RequestMapping("event")
	@CrossOrigin
	@ResponseBody
	public EasyMessage addEvent(@RequestBody Event event) {
		EasyMessage message = new EasyMessage();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		event.setTime(df.format(new Date()));
		
		boolean flag = adminService.insertEvent(event);
		
		if(flag == true) {
			message.setStatus(Code.OPERATE_SUCCESS.getCode());
			message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		}else {
			message.setStatus(Code.OPERATE_FAIL.getCode());
			message.setMsg(Code.OPERATE_FAIL.getMsg());
		}
		
		return message;
		
	}
	
	
	/**
	 * 显示所有活动
	 */
	@RequiresRoles("admin")
	@RequestMapping("display_events")
	@CrossOrigin
	@ResponseBody
	public Message displayEvents() {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		List<Event> events = adminService.displayEvent();
		
		message.setData(events);
		
		
		return message;
	}
	
	
	
	/**
	 * 删除活动 
	 */
	@RequiresRoles("admin")
	@RequestMapping("delete_event")
	@CrossOrigin
	@ResponseBody
	public EasyMessage deleteEvent(@RequestParam("eventId") int eventId) {
		EasyMessage message = new EasyMessage();
		boolean flag = adminService.deleteEvent(eventId);
		
		if(flag == true) {
			message.setStatus(Code.DELETE_SUCESS.getCode());
			message.setMsg(Code.DELETE_SUCESS.getMsg());
		}else {
			message.setStatus(Code.DELETE_FAIL.getCode());
			message.setMsg(Code.DELETE_FAIL.getMsg());
		}
		
		return message;
	}
	
	
	
	/**
	 * 查询活动
	 */
	@RequiresRoles("admin")
	@RequestMapping("query_event")
	@CrossOrigin
	@ResponseBody
	public Message queryEvent(@RequestParam("eventId") int eventId) {
		
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		Event event = adminService.queryEvent(eventId);
		message.setData(event);
		
		return message;
		
		
	}
	
	
	/**
	 * 编辑(修改)活动
	 */
	@RequiresRoles("admin")
	@RequestMapping("edit_event")
	@CrossOrigin
	@ResponseBody
	public EasyMessage editEvent(@RequestBody Event event) {
		EasyMessage message = new EasyMessage();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		event.setTime(df.format(new Date()));
		boolean flag =adminService.updateEvent(event);
		
		if(flag == true) {
			message.setStatus(Code.UPDATE_SUCCESS.getCode());
			message.setMsg(Code.UPDATE_SUCCESS.getMsg());
		}else {
			message.setStatus(Code.UPDATE_FAIL.getCode());
			message.setMsg(Code.UPDATE_FAIL.getMsg());
		}
		
		
		
		return message;
	}
	
	/**
	 * 查询简介
	 */
	@RequiresRoles("admin")
	@RequestMapping("query_introduction")
	@CrossOrigin
	@ResponseBody
	public Message queryIntroduction() {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		Introduction introduction = adminService.queryIntroduction();
		message.setData(introduction);
		
		
		return message;
	}
	
	/**
	 * 编辑修改简介
	 */
	@RequiresRoles("admin")
	@RequestMapping("edit_introduction")
	@CrossOrigin
	@ResponseBody
	public EasyMessage queryIntroduction(@RequestBody Introduction introduction) {
		EasyMessage message = new EasyMessage();
	
		boolean flag = adminService.updateIntroduction(introduction);
		if(flag == true) {
			message.setStatus(Code.UPDATE_SUCCESS.getCode());
			message.setMsg(Code.UPDATE_SUCCESS.getMsg());
			
		}else {
			message.setStatus(Code.UPDATE_FAIL.getCode());
			message.setMsg(Code.UPDATE_FAIL.getMsg());
		}
		
		return message;
	}
	
	/**
	 * 显示所有意见
	 */
	@RequiresRoles("admin")
	@RequestMapping("display_advice")
	@CrossOrigin
	@ResponseBody
	public Message displayAdvice() {
		
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		List<Advice> advices = adminService.displayAdvice();
		message.setData(advices);
		
		return message;
		
	}
	
	/**
	 * 查询具体意见
	 */
	@RequiresRoles("admin")
	@RequestMapping("query_advice")
	@CrossOrigin
	@ResponseBody
	public Message queryAdvice(@RequestParam("adviceId") int adviceId ) {
		
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		Advice advice = adminService.queryAdvice(adviceId);
		message.setData(advice);
		
		return message;
		
	}
	
	
	/**
	 * 删除意见
	 */
	@RequiresRoles("admin")
	@RequestMapping("delete_advice")
	@CrossOrigin
	@ResponseBody
	public EasyMessage deleteAdvice(@RequestParam("adviceId") int adviceId ) {
		
		EasyMessage message = new EasyMessage();
		boolean flag = adminService.deleteAdvice(adviceId);
		
		if(flag == true) {
			message.setStatus(Code.DELETE_SUCESS.getCode());
			message.setMsg(Code.DELETE_SUCESS.getMsg());
		}else {
			message.setStatus(Code.DELETE_FAIL.getCode());
			message.setMsg(Code.DELETE_FAIL.getMsg());
		}
		
		return message;
	}
	
	
	/**
	 * 根据传入的学院id查询老师和学生账号 (默认是学生)
	 */
	@RequiresRoles("admin")
	@RequestMapping(value="query_users",method= {RequestMethod.GET})
	@CrossOrigin
	@ResponseBody
	public Message queryActivateUsers(@RequestParam("acadamyID") int acadamyID ,@RequestParam(name = "status", defaultValue = "0") int status,@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "isActivate", defaultValue = "1") int isActivate) {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		List<User> users = adminService.queryUsers(acadamyID, status, pageNum, isActivate);
		PageInfo page = new PageInfo(users);
		message.setData(page);
		return message;
	}
	
	
	/**
	 * 注销账号 状态置2即可
	 */
	@RequiresRoles("admin")
	@RequestMapping(value="delete_users",method= {RequestMethod.POST})
	@CrossOrigin
	@ResponseBody
	public EasyMessage deleteUsers(@RequestBody List<Integer> ids ) {
		EasyMessage message = new EasyMessage();
		boolean flag =adminService.deleteUsers(ids);
		if(flag) {
			message.setStatus(Code.DELETE_SUCESS.getCode());
			message.setMsg(Code.DELETE_SUCESS.getMsg());
		}else {
			message.setStatus(Code.DELETE_FAIL.getCode());
			message.setMsg(Code.DELETE_FAIL.getMsg());
		}
		return message;
	}
	
	/**
	 * 激活账号 状态置为1
	 */
	@RequiresRoles("admin")
	@RequestMapping(value="activate_users",method= {RequestMethod.POST})
	@CrossOrigin
	@ResponseBody
	public EasyMessage activateUsers(@RequestBody List<Integer> ids ) {
		EasyMessage message = new EasyMessage();
		boolean flag =adminService.activateUsers(ids);
		if(flag) {
			message.setStatus(Code.OPERATE_SUCCESS.getCode());
			message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		}else {
			message.setStatus(Code.OPERATE_FAIL.getCode());
			message.setMsg(Code.OPERATE_FAIL.getMsg());
		}
		return message;
		
		
	}
	
	
	/**
	 * 查询管理员账号
	 */
	@RequiresRoles("admin")
	@RequestMapping(value="query_admins",method= {RequestMethod.GET})
	@CrossOrigin
	@ResponseBody
	public Message queryAdmins(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,@RequestParam(name = "isActivate", defaultValue = "1") int isActivate) {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		List<User> users = adminService.queryAdmins(pageNum, isActivate);
		PageInfo page = new PageInfo(users);
		message.setData(page);
		return message;

	}
	
	/**
	 * 模糊查询
	 */
	@RequiresRoles("admin")
	@RequestMapping(value="query",method= {RequestMethod.GET})
	@CrossOrigin
	@ResponseBody
	public Message query(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,@RequestParam("key") String key) {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		List<User> users = adminService.query(pageNum, key);
		PageInfo page = new PageInfo(users);
		message.setData(page);
		return message;
	}
	
	/**
	 * 一次性查询全部数据 学生 和老师
	 */
	@RequiresRoles("admin")
	@RequestMapping(value="queryAll",method= {RequestMethod.GET})
	@CrossOrigin
	@ResponseBody
	public Message query() {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		List<User> users = adminService.queryAll();
		message.setData(users);
		return message;
	}
	
	
	/**
	 * 查询详情
	 */
	@RequiresRoles("admin")
	@RequestMapping(value="detail_user",method= {RequestMethod.GET})
	@CrossOrigin
	@ResponseBody
	public Message detailUser(@RequestParam("uuid") int uuid) {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		UserHelper uhelper = adminService.detailUser(uuid);
		message.setData(uhelper);
		return message;
	}
	
	/**
	 * 添加用户
	 */
	@RequiresRoles("admin")
	@RequestMapping(value="add_user",method= {RequestMethod.POST})
	@CrossOrigin
	@ResponseBody
	public EasyMessage addUser(@RequestBody User user) {
		EasyMessage message = new EasyMessage();
		user.setPassword(EncryptKit.MD5(user.getPassword()));
		
		boolean flag = adminService.addUser(user);
		if(flag) {
			message.setStatus(Code.OPERATE_SUCCESS.getCode());
			message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		}else {
			message.setStatus(Code.OPERATE_FAIL.getCode());
			message.setMsg(Code.OPERATE_FAIL.getMsg());
		}
		
		return message;
	}
	
	
}
