package cn.xust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xust.pojo.Advice;
import cn.xust.pojo.Answer;
import cn.xust.pojo.Event;
import cn.xust.pojo.Example;
import cn.xust.pojo.Introduction;
import cn.xust.pojo.Policy;
import cn.xust.pojo.Teacher;
import cn.xust.pojo.User;
import cn.xust.pojo.UserHelper;

public interface AdminService {
	public boolean insertTeacher(Teacher teacher) ;
	public boolean deleteTeacher(int teacherId);
	public List<Teacher> displayTeachers();
	public Teacher queryTeacher(int teacherId);
	public boolean editTeacher(Teacher teacher);
	
	
	public boolean insertPolicy(Policy policy);
	public List<Policy> displayPolicy();
	public boolean deletePolicy(int policyId);
	public Policy queryPolicy(int policyId);
	public boolean updatePolicy(Policy policy);
	
	
	public boolean insertExample(Example example);
	public List<Example> displayExample();
	public boolean deleteExample(int exampleId);
	public Example queryExample(int exampleId);
	public boolean updateExample(Example example);
	
	
	public boolean insertEvent(Event event);
	public List<Event> displayEvent();
	public boolean deleteEvent(int eventId);
	public Event queryEvent(int eventId);
	public boolean updateEvent(Event event);
	
	public Introduction queryIntroduction();
	public boolean updateIntroduction(Introduction introduction);
	
	public List<Advice> displayAdvice();
	public Advice queryAdvice(int adviceId);
	public boolean deleteAdvice(int adviceId);
	
	
	public List<User> queryUsers(int acadamyID,int status,int pageNum,int isActivate);
	public boolean deleteUsers(List<Integer> ids);
	public boolean activateUsers(List<Integer> ids);
	public List<User> queryAdmins(int pageNum,int isActivate);
	public List<User> query(int pageNum,String key);
	public List<User> queryAll();
	public UserHelper detailUser(int uuid);
	
	public boolean addUser(User user);
	
	/*public List<cn.xust.pojo.Question> selectAllQuestion();
	public List<cn.xust.pojo.Question> selectQuestionByaskID(int askId);//根据提问者ID查问题
	public List<Answer> selectQuestionByanswerID(int answerId);//根据回答者ID查问题*/
}
