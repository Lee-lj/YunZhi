package cn.xust.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.xust.mapper.AdminMapper;
import cn.xust.mapper.UserMapper;
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
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	public AdminMapper adminMapper;
	@Autowired
	public UserMapper userMapper;
	
	//定义每页显示的条数
		public final int pageSize = 9;
	
	/**
	 * 增加专家
	 * @param teacher 专家封装信息
	 */
	@Override
	@Transactional
	public boolean insertTeacher(Teacher teacher) {
		int flag = adminMapper.insertTeacher(teacher);
		if(flag>0)
			return true;
		else
			return false;
	}


	/**	
	 *  删除专家
	 *  先查询再删除
	 * @param teacherID 专家id
	 */
	@Override
	@Transactional
	public boolean deleteTeacher(int teacherId) {
		boolean flag = true;
		
		int isExit = adminMapper.queryTeacherExit(teacherId);
		
		if(isExit >0) {
			
			int isDelete = adminMapper.deleteTeacher(teacherId);
			
			flag=isDelete>0 ?true:false;
			
		}else {
			flag = false; 
			
		}
		
		
		return flag;
	}


	
	/**
	 * 
	 * 显示所有专家信息
	 */
	@Override
	public List<Teacher> displayTeachers() {
		
		return adminMapper.displayTeachers();
	}

	/**
	 * 查询专家
	 */
	@Override
	public Teacher queryTeacher(int teacherId) {
		
		return adminMapper.queryTeacher(teacherId);
	}

	/**
	 * 编辑（修改）专家 
	 * 先查询再修改 
	 */
	@Override
	@Transactional
	public boolean editTeacher(Teacher teacher) {
		
		boolean flag = true;
		//查询专家是否存在
		int isExit = adminMapper.queryTeacherExit(teacher.getId());
		if(isExit >0) {
		
		int isUpdate = adminMapper.updateTeacher(teacher);
		flag = isUpdate>0?true:false;
			
			
		}else {
			
			flag = false;
		}
		
		return flag;
	}

	/**
	 * 添加政策
	 */
	@Override
	public boolean insertPolicy(Policy policy) {
		int isInsert = adminMapper.insertPolicy(policy);
		boolean flag = isInsert>0?true:false;
		
		return flag;
	}

	/**
	 * 显示所有政策
	 */
	@Override
	public List<Policy> displayPolicy() {
		
		return adminMapper.displayPolicys();
	}

	/**
	 * 删除政策
	 * 先查询在删除
	 */
	@Override
	public boolean deletePolicy(int policyId) {
		int isExit = adminMapper.queryPolicyExit(policyId);
		boolean flag = true;
		if(isExit>0) {
			int isDelete = adminMapper.deletePolicy(policyId);
			
			flag = isDelete>0?true:false;
			
		}else {
			flag = false;
		}
		
		
		return flag;
	}

	/**
	 * 查询政策
	 */
	@Override
	public Policy queryPolicy(int policyId) {
		
		return adminMapper.queryPolicy(policyId);
	}

	/**
	 * 编辑(修改)政策
	 * 先查询在修改
	 */
	@Override
	public boolean updatePolicy(Policy policy) {
		boolean flag = true;
		
		int isExit = adminMapper.queryPolicyExit(policy.getId());
		
		if(isExit>0) {
			int isUpdate = adminMapper.updatePolicy(policy);
			
			flag = isUpdate>0?true:false;
		}else {
			flag = false;
		}
		
		
		
		
		return flag;
	}

	/**
	 * 添加案例
	 */
	@Override
	public boolean insertExample(Example example) {
		int isInsert = adminMapper.insertExample(example);
		boolean flag = isInsert>0?true:false;
		
		return flag;
	}

	/**
	 * 显示所有案例
	 */
	@Override
	public List<Example> displayExample() {
		return adminMapper.displayExamples();
	}

	/**
	 * 删除案例
	 */
	@Override
	public boolean deleteExample(int exampleId) {
		int isExit = adminMapper.queryExampleExit(exampleId);
		boolean flag = true;
		if(isExit>0) {
			int isDelete = adminMapper.deleteExample(exampleId);
			
			flag = isDelete>0?true:false;
			
		}else {
			flag = false;
		}
		
		
		return flag;
	}

	/**
	 * 查询案例
	 */
	@Override
	public Example queryExample(int exampleId) {
		
		return adminMapper.queryExample(exampleId);
	}
	
	
	/**
	 * 编辑(修改)案例
	 * 先查询在修改
	 */
	@Override
	public boolean updateExample(Example example) {
		boolean flag = true;
		
		int isExit = adminMapper.queryExampleExit(example.getId());
		
		if(isExit>0) {
			int isUpdate = adminMapper.updateExample(example);
			
			flag = isUpdate>0?true:false;
		}else {
			flag = false;
		}
		
		
		
		
		return flag;
	}

	/**
	 * 添加活动
	 */
	@Override
	public boolean insertEvent(Event event) {
		int isInsert = adminMapper.insertEvent(event);
		boolean flag = isInsert>0?true:false;
		
		return flag;
		
	}

	/**
	 * 显示所有活动
	 */
	@Override
	public List<Event> displayEvent() {
		return adminMapper.displayEvents();
	}

	/**
	 * 删除活动
	 */
	@Override
	public boolean deleteEvent(int eventId) {
		int isExit = adminMapper.queryEventExit(eventId);
		boolean flag = true;
		if(isExit>0) {
			int isDelete = adminMapper.deleteEvent(eventId);
			
			flag = isDelete>0?true:false;
			
		}else {
			flag = false;
		}
		
		
		return flag;
	}

	/**
	 * 查询活动
	 */
	@Override
	public Event queryEvent(int eventId) {
		return adminMapper.queryEvent(eventId);
	}

	
	/**
	 *编辑修改活动
	 */
	@Override
	public boolean updateEvent(Event event) {
		
	boolean flag = true;
		
		int isExit = adminMapper.queryEventExit(event.getId());
		
		if(isExit>0) {
			int isUpdate = adminMapper.updateEvent(event);
			
			flag = isUpdate>0?true:false;
		}else {
			flag = false;
		}
		
		
		
		
		return flag;
	}

	/**
	 * 查询简介
	 */
	@Override
	public Introduction queryIntroduction() {
		
		return adminMapper.queryIntroduction();
	}
	
	/**
	 * 编辑修改简介
	 */
	@Override
	public boolean updateIntroduction(Introduction introduction) {
		int isUpdate = adminMapper.updateIntroduction(introduction);
		boolean flag = isUpdate>0?true:false;
		
		return flag;
	}

	/**
	 * 显示意见
	 */
	@Override
	public List<Advice> displayAdvice() {
		
		return adminMapper.displayFeedback();
	}

	/**
	 * 查询具体意见
	 */
	@Override
	public Advice queryAdvice(int adviceId) {
		return adminMapper.queryFeedback(adviceId);
	}

	/**
	 * 删除意见
	 */
	@Override
	public boolean deleteAdvice(int adviceId) {
		int isExit = adminMapper.queryFeedbackExit(adviceId);
		boolean flag = true;
		if(isExit>0) {
			int isDelete = adminMapper.deleteFeedback(adviceId);
			
			flag = isDelete>0?true:false;
			
		}else {
			flag = false;
		}
		
		
		return flag;
	}

	/**
	 * 查询用户
	 */
	@Override
	public List<User> queryUsers(int acadamyID, int status, int pageNum,int isActivate) {
		PageHelper.startPage(pageNum, pageSize);
		return adminMapper.selectUsers(acadamyID, status, isActivate) ;
	}
	
	/**
	 * 批量删除用户
	 */
	@Override
	public boolean deleteUsers(List<Integer> ids) {
		int count = adminMapper.deleteUsers(ids);
		if(count == ids.size())
			return true;
		return false;
	}

	/**
	 * 批量激活账号
	 */
	@Override
	public boolean activateUsers(List<Integer> ids) {
		int count = adminMapper.activateUsers(ids);
		if(count == ids.size())
			return true;
		return false;
	}

	/**
	 * 查询管理员账号
	 */
	@Override
	public List<User> queryAdmins(int pageNum,int isActivate) {
		PageHelper.startPage(pageNum, pageSize);
		return adminMapper.selectAdmins(isActivate);
	}

	/**
	 * 模糊查询
	 */
	@Override
	public List<User> query(int pageNum, String key) {
		PageHelper.startPage(pageNum, pageSize);
		return adminMapper.query(key);
	}

	/**
	 * 一次性查询
	 */
	@Override
	public List<User> queryAll() {
		return adminMapper.queryAll();
	}

	/**
	 * 账号详情
	 */
	@Override
	public UserHelper detailUser(int uuid) {

		return adminMapper.detailUser(uuid);
	}

	/**
	 * 添加用户
	 */
	@Override
	public boolean addUser(User user) {
		if(userMapper.selectEverExit(user.getAccount())>0)
			return false;
		else
			if(adminMapper.insertUser(user)>0)
				return true;
		return false;
	}


	/*@Override
	public List<Question> selectAllQuestion() {
		return adminMapper.selectAllQuestion();
	}


	@Override
	public List<Question> selectQuestionByaskID(int askId) {
		return adminMapper.selectQuestionByaskID(askId);
	}


	@Override
	public List<Answer> selectQuestionByanswerID(int answerId) {
		return adminMapper.selectQuestionByanswerID(answerId);
	}
*/
	


}
