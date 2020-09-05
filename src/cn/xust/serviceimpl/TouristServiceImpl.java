package cn.xust.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.xust.mapper.TouristMapper;
import cn.xust.pojo.Event;
import cn.xust.pojo.Example;
import cn.xust.pojo.Introduction;
import cn.xust.pojo.Policy;
import cn.xust.pojo.Teacher;
import cn.xust.service.TouristService;
@Service
public class TouristServiceImpl implements TouristService{
	@Autowired
	public TouristMapper touristMapper;
	


	
	/**
	 * 
	 * 显示所有专家信息
	 */
	@Override
	public List<Teacher> displayTeachers(int pageNum,int pageSize) {
		//开启分页插件拦截器
        PageHelper.startPage(pageNum, pageSize);
		return touristMapper.displayTeachers();
	}

	/**
	 * 查询专家
	 */
	@Override
	public Teacher queryTeacher(int teacherId) {
		
		return touristMapper.queryTeacher(teacherId);
	}

	

	/**
	 * 显示所有政策
	 */
	@Override
	public List<Policy> displayPolicy(int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return touristMapper.displayPolicys();
	}

	
	/**
	 * 查询政策
	 */
	@Override
	public Policy queryPolicy(int policyId) {
		
		return touristMapper.queryPolicy(policyId);
	}

	
	

	/**
	 * 显示所有案例
	 */
	@Override
	public List<Example> displayExample(int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return touristMapper.displayExamples();
	}

	

	/**
	 * 查询案例
	 */
	@Override
	public Example queryExample(int exampleId) {
		
		return touristMapper.queryExample(exampleId);
	}
	
	
	

	/**
	 * 显示所有活动
	 */
	@Override
	public List<Event> displayEvent(int pageNum,int pageSize,int type,String time) {
		PageHelper.startPage(pageNum, pageSize);
		return touristMapper.displayEvents(type,time);
	}

	

	/**
	 * 查询活动
	 */
	@Override
	public Event queryEvent(int eventId) {
		return touristMapper.queryEvent(eventId);
	}

	
	

	/**
	 * 查询简介
	 */
	@Override
	public Introduction queryIntroduction() {
		
		return touristMapper.queryIntroduction();
	}

	
	
	
}
