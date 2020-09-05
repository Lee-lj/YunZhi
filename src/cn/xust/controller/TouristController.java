package cn.xust.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.xust.pojo.Advice;
import cn.xust.pojo.Event;
import cn.xust.pojo.Example;
import cn.xust.pojo.Introduction;
import cn.xust.pojo.Policy;
import cn.xust.pojo.Teacher;
import cn.xust.service.TouristService;
import cn.xust.utils.Code;
import cn.xust.utils.EasyMessage;
import cn.xust.utils.Message;

@Controller
@RequestMapping("/tourist")
public class TouristController {
	@Autowired
	public TouristService touristService;
	
	//定义每页显示的条数
	public final int pageSize = 9;
	
	/**
	 *显示所有专家信息
	 * @return
	 */
	@RequestMapping("display_teachers")
	@CrossOrigin
	@ResponseBody
	public Object displayTeachers(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		List<Teacher> teachers = touristService.displayTeachers(pageNum,pageSize);
		PageInfo page = new PageInfo(teachers);
		message.setData(page);
		return message;
	}
	
	
	/**
	 * 查询专家信息
	 */
	@RequestMapping("query_teacher")
	@CrossOrigin
	@ResponseBody
	public Object queryTeacher(@RequestParam("id") int teacherId) {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		Teacher teacher = touristService.queryTeacher(teacherId);
		message.setData(teacher);
		
		return message;
		
		
	}
	
	
	
	
	/**
	 * 显示所有政策
	 */
	@RequestMapping("display_policys")
	@CrossOrigin
	@ResponseBody
	public Message displayPolicys(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		List<Policy> policys = touristService.displayPolicy(pageNum,pageSize);
		PageInfo page = new PageInfo(policys);
		message.setData(page);
		
		
		return message;
	}
	
	
	
	/**
	 * 查询政策
	 */
	@RequestMapping("query_policy")
	@CrossOrigin
	@ResponseBody
	public Message queryPolicy(@RequestParam("policyId") int policyId) {
		
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		Policy policy = touristService.queryPolicy(policyId);
		message.setData(policy);
		
		return message;
		
		
	}
	
	
	
	/**
	 * 显示所有案例
	 */
	@RequestMapping("display_examples")
	@CrossOrigin
	@ResponseBody
	public Message displayExamples(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		List<Example> examples = touristService.displayExample(pageNum,pageSize);
		PageInfo page = new PageInfo( examples);
		message.setData(page);
		
		
		return message;
	}
	
	
	
	
	/**
	 * 查询案例
	 */
	@RequestMapping("query_example")
	@CrossOrigin
	@ResponseBody
	public Message queryExample(@RequestParam("exampleId") int exampleId) {
		
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		Example example =touristService.queryExample(exampleId);
		message.setData(example);
		
		return message;
		
		
	}
	
	
	
	
	
	
	
	/**
	 * 显示所有活动
	 */
	@RequestMapping("display_events")
	@CrossOrigin
	@ResponseBody
	public Message displayEvents(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,@RequestParam(name = "type", defaultValue = "2") int type) {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		List<Event> events = touristService.displayEvent(pageNum,pageSize,type,df.format(new Date()));
		PageInfo page = new PageInfo(events);
		message.setData(page);
		
		
		return message;
	}
	
	
	
	
	
	/**
	 * 查询活动
	 */
	@RequestMapping("query_event")
	@CrossOrigin
	@ResponseBody
	public Message queryEvent(@RequestParam("eventId") int eventId) {
		
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		Event event = touristService.queryEvent(eventId);
		message.setData(event);
		
		return message;
		
		
	}
	
	
	
	
	/**
	 * 查询简介
	 */
	@RequestMapping("query_introduction")
	@CrossOrigin
	@ResponseBody
	public Message queryIntroduction() {
		Message message = new Message();
		message.setStatus(Code.OPERATE_SUCCESS.getCode());
		message.setMsg(Code.OPERATE_SUCCESS.getMsg());
		
		Introduction introduction = touristService.queryIntroduction();
		message.setData(introduction);
		
		
		return message;
	}
	
	
    @RequestMapping(path = "/unauthorized/{message}")
    public EasyMessage unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return new EasyMessage(0,message);
    }
	
	
}
