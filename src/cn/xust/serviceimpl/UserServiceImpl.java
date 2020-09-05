package cn.xust.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.xust.mapper.UserMapper;
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
import cn.xust.service.UserService;
import cn.xust.utils.EncryptKit;
@Transactional
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
    


	/**
	 * 
	 * 登录
	 */
	public User login(String account, String password) {
		User user = null;
		
		password  = EncryptKit.MD5(password);
		     user = userMapper.selectSingle(account, password);
		return user;
	}
	
	/**
	 * 注册
	 */
	@Override
	public int register(User user) {
		 
		if( userMapper.selectEverExit(user.getAccount())>0)  //注册失败,该账号已被注册
		  return -1;
		else
		{
			
			if (userMapper.addSingle(user)>0) //逻辑需要修改
			{
				if(user.getStatus() == 0)
						return 1;    // 注册成功，可以登录
				else
					    return 0;    // 注册成功，待审核 
			}

			
			
		}
		
		return -1;
	}


	/*����Realm��ѯ�����Ƿ���� �����ڷ��� �����ڷ���null
	 * 
	 * */
	
	@Override
	public String selectPassword(String account,String email) {
		
		return userMapper.selectPassword(account,email);
	}

	/**
	 * ����רҵ���ֲ�ѯרҵ��id
	 * 			��ѯ��������-1
	 */
	
	@Override
	public int selectMajorId(String majorName) {
		return userMapper.selectMajorId(majorName);
	}

	
	
	/*
	 * 上传|修改头像
	 */
	@Override
	public boolean uploadAVATARUR(User user) {
		
		int flag = userMapper.uploadAVATARURL(user);
		
		return flag>0?true:false;
	}


	/**
	 * 查询学院信息
	 */
	@Override
	public List<Acadamy> selectAcadamy() {
		
		return userMapper.selectAcadamy();
	}
	/**
	 * 根据学院id查询专业
	 */
	@Override
	public List<Major> selectMajor(int acadamyID) {
		
		return userMapper.selectMajor(acadamyID);
	}

	@Override
	public int insertAdvice(Advice advice) {
		 if(userMapper.insertAdvice(advice)>0)
			 return 1;
		 else
			 return 0;
	}
	
	@Override
	public boolean keep(int uuid, String avatarUrl) {
		int flag = userMapper.keep(uuid, avatarUrl);
		if(flag>0)
			return true;
		else
		   return false;
	}

	@Override
	public boolean changePW(User user) {
		int flag = userMapper.changePW(user);
		if(flag>0)
			return true;
		else
		   return false;
	}

	@Override
	public UserHelper detailUser(int uuid) {
		// TODO Auto-generated method stub
		return userMapper.detailUser(uuid);
	}
	
	
	//根据账户的专业id查询对应学院的老师
	@Override
	public List<User> selectTeachers(int major) {
		
		return userMapper.selectTeachers(major);
	}
	//增加问题
	@Override
	public boolean insertQuestion(Question question,List<Integer> answerIDS) {
		//先插入问题，得到返回的自增主键
		 userMapper.insertQuestion(question);
		int questionID = question.getId(); // 得到返回的自增主键
		//组装answers
		List<Answer> answers = new ArrayList<>();
		for(Integer answerID : answerIDS ) {
			answers.add(new Answer(questionID,answerID));
		}
		
		userMapper.insertAnswers(answers);
		
		return true;
	}
	/**
	 * 学生: 列出所有问题
	 */
	@Override
	public List<Question> selectQuestions(int uuid) {
		return userMapper.selectQuestions(uuid);
	}
	
	/***
	 * 学生:查看问题回复
	 */
	
	@Override
	public QuestionHelper selectDetailQuestion(int questionID) {
		QuestionHelper result = new QuestionHelper();
		List<DisplayHelper> list = new ArrayList<>();
		List<AnswerHelper> answerHelpers = userMapper.selectMainQuestion(questionID);
		//开始组装
		if(answerHelpers != null) {
			for(AnswerHelper item:answerHelpers) {
				DisplayHelper temp = new DisplayHelper();
				temp.setFirst(item);
				List<AnswerHelper> other = userMapper.selectOtherQuestion(item.getId());
				temp.setOther(other);
				list.add(temp);
			}
		}
		result.setQuestion(userMapper.selectQuestionHelp(questionID));
		result.setAnswers(list);
		return result;
	}
	
	/**
	 * 老师:列出所有问题
	 */
	@Override
	public List<Question> selectTeacherQuestions(int uuid) {
		// TODO Auto-generated method stub
		return userMapper.selectTeacherQuestions(uuid);
	}
	
	/**
	 * 老师:查看问题回复
	 */
	public QuestionHelper selectTeacherDetailQuestion(int questionID,int uuid) {
		QuestionHelper result = new QuestionHelper();
		List<DisplayHelper> list = new ArrayList<>();
		AnswerHelper answerHelper = userMapper.selectTeacherMainQuestion(questionID, uuid);
		//开始组装
		if(answerHelper != null) {
				DisplayHelper temp = new DisplayHelper();
				temp.setFirst(answerHelper);
				List<AnswerHelper> other = userMapper.selectOtherQuestion(answerHelper.getId());
				temp.setOther(other);
				list.add(temp);
			
		}
		result.setQuestion(userMapper.selectQuestionHelp(questionID));
		result.setAnswers(list);
		return result;
	}
	
	/**
	 * 老师:评论
	 */
	@Override
	public boolean teacherComment(String content, String time, int questionID, int answerID, int uuid) {
		boolean flag = false;
		if(answerID == -1) {
			flag = userMapper.teacherFirstComment(questionID, uuid, content, time)>0?true:false; 
			userMapper.updateQuestionStatus(questionID);//更新问题的转态信息
		}else {
			//即添加子回复
			flag = userMapper.teacherComments(uuid, content, time, answerID)>0?true:false;
		}
		
		
		return flag;
	}

	/**
	 * 学生:评论
	 */
	@Override
	public boolean studentComment(String content, String time, int answerID, int uuid) {
		
		boolean flag = false;
		flag = userMapper.teacherComments(uuid, content, time, answerID)>0?true:false;
		return flag;
	}
	/**
	 * 刷新子评论
	 */

	@Override
	public List<AnswerHelper> refreshComment(int preID) {
		return userMapper.refreshAnswer(preID);
	}

	/**
	 * 查找权限
	 */
	@Override
	public int selectAuthorizationInfo(String account) {
		return userMapper.selectAuthorizationInfo(account);
	}

	@Override
	public List<Question> selectAllQuestion() {
		return userMapper.selectAllQuestion();
	}

	@Override
	public List<Question> selectQuestionByaskID(int askId) {
		return userMapper.selectQuestionByaskID(askId);
	}

	@Override
	public List<Question> selectQuestionByanswerID(int answerId) {
		return userMapper.selectQuestionByanswerID(answerId);
	}


	

	

	
	
	
}
