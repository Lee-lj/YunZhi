package cn.xust.service;

import java.util.List;

import cn.xust.pojo.Advice;
import cn.xust.pojo.Event;
import cn.xust.pojo.Example;
import cn.xust.pojo.Introduction;
import cn.xust.pojo.Policy;
import cn.xust.pojo.Teacher;

public interface TouristService {


	public List<Teacher> displayTeachers(int pageNum,int pageSize);
	public Teacher queryTeacher(int teacherId);

	
	

	public List<Policy> displayPolicy(int pageNum,int pageSize);
	public Policy queryPolicy(int policyId);

	
	

	public List<Example> displayExample(int pageNum,int pageSize);
	public Example queryExample(int exampleId);

	
	

	public List<Event> displayEvent(int pageNum,int pageSize,int type,String time);
	public Event queryEvent(int eventId);

	
	public Introduction queryIntroduction();


}
