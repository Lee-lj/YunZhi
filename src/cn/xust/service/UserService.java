package cn.xust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xust.pojo.Acadamy;
import cn.xust.pojo.Advice;
import cn.xust.pojo.Answer;
import cn.xust.pojo.AnswerHelper;
import cn.xust.pojo.DisplayHelper;
import cn.xust.pojo.Major;
import cn.xust.pojo.Question;
import cn.xust.pojo.QuestionHelper;
import cn.xust.pojo.User;
import cn.xust.pojo.UserHelper;

public interface UserService {
// 登录
	User login (String account,String password);
//	
//	//�û�ע��
	int register(User user);
	
	//Realm�����û����������ѯ����
	
	String selectPassword(String account,String email);
//	
	//上传|修改头像
	
	boolean uploadAVATARUR(User user);
//	
//	//�޸��û���Ϣ
//	boolean updateUser(User user);
	
	//查询专业id
	int selectMajorId(String majorName);
	
	//查询学院
	List<Acadamy> selectAcadamy();
	
	
	//根据学院id查询专业
	List<Major> selectMajor(int acadamyID);
	
	
	//存储意见反馈
	int insertAdvice(Advice advice);
	
	
	//保存当前个人中心的信息
	boolean keep(int uuid,String avatarUrl);
	
	//更改密码
	boolean changePW(User user);
	
	//个人中心查询
	public UserHelper detailUser(int uuid);
	
	//根据账户的专业id查询对应学院的老师
	public List<User> selectTeachers(int major);
	
	//增加问题
	public boolean insertQuestion(Question question,List<Integer> answerIDS);
	
	//学生 :显示所有问题
	public List<Question>  selectQuestions(int uuid);
	
	//学生:查看回复
	public QuestionHelper selectDetailQuestion(int questionID);
	 
	//教师:显示所有问题
	public List<Question>  selectTeacherQuestions(int uuid);
	
	//教师:查看回复
	public QuestionHelper selectTeacherDetailQuestion(int questionID,int uuid);
	
	//教师:评论
	public boolean teacherComment(String content,String time,int questionID,int answerID,int uuid);
	
	//学生:评论
	public boolean studentComment(String content,String time,int answerID,int uuid);
	
	//子评论刷新
	public List<AnswerHelper> refreshComment(int preID);
	
	//查找权限
	public int selectAuthorizationInfo(String account);
	
	public List<cn.xust.pojo.Question> selectAllQuestion();
	public List<cn.xust.pojo.Question> selectQuestionByaskID(int askId);//根据提问者ID查问题
	public List<cn.xust.pojo.Question> selectQuestionByanswerID(int answerId);//根据回答者ID查问题
}
