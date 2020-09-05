package cn.xust.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import cn.xust.pojo.Question;
import cn.xust.pojo.Advice;
import cn.xust.pojo.Answer;
import cn.xust.pojo.Event;
import cn.xust.pojo.Example;
import cn.xust.pojo.Introduction;
import cn.xust.pojo.Policy;
import cn.xust.pojo.Teacher;
import cn.xust.pojo.User;
import cn.xust.pojo.UserHelper;

public interface AdminMapper {
	public int insertTeacher(Teacher teacher);
	public int deleteTeacher(@Param("teacherId") int teacherId);
	public int queryTeacherExit(@Param("teacherId") int teacherId);
	public List<Teacher> displayTeachers();
	public Teacher queryTeacher(@Param("teacherId") int teacherId);
	public int updateTeacher(Teacher teacher);
	
	public int insertPolicy(Policy policy);
	public List<Policy> displayPolicys();
	public int queryPolicyExit(@Param("policyId") int policyId);
	public int deletePolicy(@Param("policyId") int policyId);
	public Policy queryPolicy(@Param("policyId") int policyId);
	public int updatePolicy(Policy policy);
	
	
	public int insertExample(Example example);
	public List<Example> displayExamples();
	public int queryExampleExit(@Param("exampleId") int exampleId);
	public int deleteExample(@Param("exampleId") int exampleId);
	public Example queryExample(@Param("exampleId") int exampleId);
	public int updateExample(Example example);
	
	
	public int insertEvent(Event event);
	public List<Event> displayEvents();
	public int queryEventExit(@Param("eventId") int eventId);
	public int deleteEvent(@Param("eventId") int eventId);
	public Event queryEvent(@Param("eventId") int eventId);
	public int updateEvent(Event event);
	
	
	public Introduction queryIntroduction();
	public int updateIntroduction(Introduction introduction);
	
	public List<Advice> displayFeedback();
	public Advice queryFeedback(@Param("adviceId") int adviceId);
	public int queryFeedbackExit(@Param("adviceId") int adviceId);
	public int deleteFeedback(@Param("adviceId") int adviceId);
	
	public List<User> selectUsers(@Param("acadamyID") int acadamyID,@Param("status") int status, @Param("isActivate") int isActivate); 
	public int deleteUsers(@Param("ids") List<Integer> ids);
	public int activateUsers(@Param("ids") List<Integer> ids);
	public List<User>  selectAdmins( @Param("isActivate") int isActivate);
	public List<User> query(@Param("key") String key);
	public List<User> queryAll();
	public UserHelper detailUser(@Param("uuid") int uuid);
	
	public int insertUser(User user);
	
	/*public List<cn.xust.pojo.Question> selectAllQuestion();//查询所有问题，升序排列
	public List<cn.xust.pojo.Question> selectQuestionByaskID(@Param("askId") int askId);//根据提问者ID查问题
	public List<Answer> selectQuestionByanswerID(@Param("answerId") int answerId);//根据回答者ID查问题
	*/
}
