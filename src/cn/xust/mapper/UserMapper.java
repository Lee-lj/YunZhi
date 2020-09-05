package cn.xust.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xust.pojo.Acadamy;
import cn.xust.pojo.Advice;
import cn.xust.pojo.Answer;
import cn.xust.pojo.AnswerHelper;
import cn.xust.pojo.Major;
import cn.xust.pojo.Question;
import cn.xust.pojo.User;
import cn.xust.pojo.UserHelper;

public interface UserMapper {

	User selectSingle(@Param("account") String account,@Param("password") String password);
	
	int  addSingle(User user);
	
	String selectPassword(@Param("account") String account,@Param("email") String email);
	
	int selectEverExit(@Param("account") String account);
	
	
    int uploadAVATARURL(User user);
    
    int updateUser(User user);
    
    int selectMajorId(@Param("majorName") String majorName);
    
    
    List<Acadamy> selectAcadamy();
    
    List<Major> selectMajor(@Param("acadamyID") int acadamyID);
    
    int insertAdvice(Advice advice);
    
    int keep(@Param("uuid") int uuid,@Param("avatarUrl") String avatarUrl);
    
    int changePW(User user);
    
    public UserHelper detailUser(@Param("uuid") int uuid);
    
    public List<User> selectTeachers(@Param("major") int major);
    
    public int insertQuestion(Question question);
    
    public int insertAnswers( @Param("answers") List<Answer> answers);
    
    public List<Question> selectQuestions(@Param("uuid") int uuid);
    
    public AnswerHelper selectQuestionHelp(@Param("questionID") int questionID);
    
    public List<AnswerHelper> selectMainQuestion(@Param("questionID") int questionID);
    
    public List<AnswerHelper> selectOtherQuestion(@Param("preID") int preID);
    
    public List<Question> selectTeacherQuestions(@Param("uuid") int uuid);
    
    public AnswerHelper selectTeacherMainQuestion(@Param("questionID") int questionID,@Param("uuid") int uuid);
    
    public List<AnswerHelper> selectTeacherOtherQuestion(@Param("preID") int preID);
    
    public int teacherFirstComment(@Param("questionID") int questionID,@Param("uuid") int uuid,@Param("content") String content,@Param("time") String time);
    
    public int updateQuestionStatus(@Param("questionID") int questionID);
    
    public int teacherComments(@Param("uuid") int uuid,@Param("content") String content,@Param("time") String time,@Param("preID") int preID);
    
    public List<AnswerHelper> refreshAnswer(@Param("preID") int preID);
    
    public int selectAuthorizationInfo(@Param("account") String account);
    
    public List<cn.xust.pojo.Question> selectAllQuestion();//查询所有问题，升序排列
	public List<cn.xust.pojo.Question> selectQuestionByaskID(@Param("askId") int askId);//根据提问者ID查问题
	public List<cn.xust.pojo.Question> selectQuestionByanswerID(@Param("answerId") int answerId);
}
