package cn.xust.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xust.pojo.Advice;
import cn.xust.pojo.Event;
import cn.xust.pojo.Example;
import cn.xust.pojo.Introduction;
import cn.xust.pojo.Policy;
import cn.xust.pojo.Teacher;

public interface TouristMapper {


	public List<Teacher> displayTeachers();
	public Teacher queryTeacher(@Param("teacherId") int teacherId);

	

	public List<Policy> displayPolicys();
	public Policy queryPolicy(@Param("policyId") int policyId);

	
	

	public List<Example> displayExamples();
	public Example queryExample(@Param("exampleId") int exampleId);

	
	

	public List<Event> displayEvents(@Param("type") int type,@Param("nowTime") String nowTime);
	public Event queryEvent(@Param("eventId") int eventId);

	
	
	public Introduction queryIntroduction();



}
