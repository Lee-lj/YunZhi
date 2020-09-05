package cn.xust.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.xust.pojo.Advice;
import cn.xust.pojo.Answer;
import cn.xust.pojo.AnswerHelper;
import cn.xust.pojo.CommentHelper;
import cn.xust.pojo.Question;
import cn.xust.pojo.QuestionHelper;
import cn.xust.pojo.User;
import cn.xust.pojo.UserHelper;
import cn.xust.serviceimpl.UserServiceImpl;
import cn.xust.utils.Code;
import cn.xust.utils.EasyMessage;
import cn.xust.utils.EmailHelper;
import cn.xust.utils.EncryptKit;
import cn.xust.utils.JWTUtil;
import cn.xust.utils.Message;
import cn.xust.utils.UploadUtils;
import cn.xust.utils.Verification;

/**
 *   用户类
 * add by galgaddott
 * last time 2020/1/1
 *
 */
	@Controller
	@RequestMapping("/user")
	public class UserController {
	    
		@Autowired
	    private UserServiceImpl userService;
		
		@Autowired
		private JavaMailSender javaMailSender;

		@RequestMapping(value="login",method= {RequestMethod.POST})
		@ResponseBody
		@CrossOrigin
		 public Map<String ,Object> Login(@RequestBody Map<String ,Object> str,HttpServletResponse response) {

			Map<String ,Object> map =new HashMap<String ,Object>();
				
							
							User user = userService.login((String)str.get("account"),(String)str.get("password"));
							if(user != null) {
								
								
								map.put("status",Code.Login_SUCCESS.getCode());
								map.put("msg",Code.Login_SUCCESS.getMsg());
								map.put("data",user);
								map.put("token",JWTUtil.createToken((String)str.get("account")));
					
								
							}else
							{
								 map.put("status",Code.Login_FAIL.getCode());
						         map.put("msg",Code.Login_FAIL.getMsg());
							}
							
						
	

								return map;
		}
		
		

		@RequestMapping(value="register",method= {RequestMethod.POST})
		@ResponseBody
		@Transactional
		@CrossOrigin
		public Map<String,Object> Register( @RequestBody User user) {
			Map<String ,Object> map =new HashMap<String ,Object>();
			
			user.setPassword(EncryptKit.MD5(user.getPassword()));

			
			
			int status = user.getStatus();
			if (status == 0) {
				user.setIsActivate(1);
			}else
				user.setIsActivate(0);
			
			
			int flag = userService.register(user);
			

				map.put("status",flag);
				map.put("msg","操作成功");
		
			
			return map;
		}

		

		@RequestMapping(value="email",method= {RequestMethod.POST,RequestMethod.GET})
		@ResponseBody
		@CrossOrigin
	    public Map<String,Object> sendMail03(@RequestParam("email") String email){
		
			Map<String,Object> map = new HashMap<>();
	        MimeMessage mMessage=javaMailSender.createMimeMessage();//创建邮件对象
	        MimeMessageHelper mMessageHelper;
	        Properties prop = new Properties();
	        String from;
	     
	        String text = Verification.getCode();
	        try {
	        	 //从配置文件中拿到发件人邮箱地址ַ
	            prop.load(this.getClass().getResourceAsStream("/Properties/mail.properties"));
	            from = prop.get("mail.smtp.username")+"";
	            mMessageHelper=new MimeMessageHelper(mMessage,true);
	            
	            mMessageHelper.setFrom(from);//发件人邮箱
	            
	            mMessageHelper.setTo(email);//收件人邮箱
	            
	            mMessageHelper.setSubject("云智舒心驿站验证码");//邮件的主题
	          
	            mMessageHelper.setText(text);//邮件的文本内容
	            
	            javaMailSender.send(mMessage);//发送邮件
	       
	        	map.put("status",Code.EMAIL_SUCCESS.getCode());
	        	map.put("msg",Code.EMAIL_SUCCESS.getMsg());
	        	map.put("data",EmailHelper.encryption(text));
	            
	            
	        }  catch (Exception e) {
	        	map.put("status",Code.EMAIL_FAIL.getCode());
	        	map.put("msg",Code.EMAIL_FAIL.getMsg());
	            e.printStackTrace();
	        }
	  
	        return map;
	    }
	
	
		 
		 /**
		  * 	给前端传学院
		  */
		 @RequestMapping("getAcadamy")
		 @CrossOrigin
		 @ResponseBody
		 public Object getAcadamy() {
			 Map<String,Object> map = new HashMap<>();
			 map.put("status",1);
			 map.put("msg","操作成功!");
			 map.put("data",userService.selectAcadamy());
			 return map;
		 } 
		 
		 
		 /**
		  * 	根据学院id查询专业
		  */
		 @RequestMapping("getMajor")
		 @CrossOrigin
		 @ResponseBody
		 public Object getMajor(@RequestParam("acadamyId") int acadamyID) {
	
			 Map<String,Object> map = new HashMap<>();
			 if(acadamyID>0) {
				 map.put("status",1);
				 map.put("msg","操作成功!");
				 map.put("data",userService.selectMajor(acadamyID));
			 }else {
				 map.put("status",0);
				 map.put("msg","操作失败!");;
			 }
			 
			 
			 return map;
		 }
		 
		 
		 /**
		  * 意见反馈
		  */
		 @RequiresRoles("student")
		 @RequestMapping("feedBack")
		 @CrossOrigin
		 @ResponseBody
		 public Object feedBack(@RequestBody Advice advice) {
			 EasyMessage message = new EasyMessage();
			 if(advice.getContent() == null || advice.getName() == null || advice.getContent().equals("") || advice.getName().equals("") ) {
				 message.setStatus(0);
			 	 message.setMsg("反馈内容为空!");
			 
			 }
			 else {
				  int flag = userService.insertAdvice(advice);
				  
				  if(flag>0) {
					   	 message.setStatus(1);
					 	 message.setMsg("反馈成功");
				  }else {
					     message.setStatus(0);
					 	 message.setMsg("反馈失败");
				  }
				 
				 
			 }
			 
			 
			 
			 
			 return message;
		 }
		 
		 /**
		  * 上传用户头像      
		  */
			//@RequiresRoles("student")                 //BUG 前端无法携带Token 只能放开权限
			@RequestMapping("upload_image")
			@CrossOrigin
			@ResponseBody
			public Object getImage(HttpServletRequest request,HttpServletResponse response) {
				
				Message message = new Message();
				
				 //获取文件解析器
				 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
				 
				 //判断request里是否有文件
				 
				 if(multipartResolver.isMultipart(request)) {
					 
					 //转化request
					 MultipartHttpServletRequest multrequest = (MultipartHttpServletRequest) request;
					 
					 //获取文件
					 MultipartFile file = multrequest.getFile("file");
					 
					 
					
					 
			         //上传文件  
					  String imageURL = UploadUtils.upload(file, request, "/AVATAR_URL");
					 
					  if(imageURL==null) {
						 
					 		message.setStatus(Code.UPLOAD_FAIL.getCode());
					 		message.setMsg(Code.UPLOAD_FAIL.getMsg());
						 
					  }else {
					 		message.setStatus(Code.UPLOAD_SUCCESS.getCode());
					 		message.setMsg(Code.UPLOAD_SUCCESS.getMsg());
					 		message.setData(imageURL);
					  }
				
				 }
				 else {
					   message.setStatus(Code.UPLOAD_FAIL.getCode());
					   message.setMsg(Code.UPLOAD_FAIL.getMsg());
				 }
			
				
				return message;
			}
			
			/**
			 * 保存当前个人中心的信息
			 */
			@RequiresRoles("student")
			@RequestMapping("keep")
			@CrossOrigin
			@ResponseBody
			public EasyMessage keep(@RequestParam("uuid") int uuid,@RequestParam("avatarUrl") String avatarUrl) {
				EasyMessage message = new EasyMessage();
				boolean flag = userService.keep(uuid, avatarUrl);
				if(flag) {
					message.setStatus(Code.OPERATE_SUCCESS.getCode());
					message.setMsg(Code.OPERATE_SUCCESS.getMsg());
				}else {
					message.setStatus(Code.OPERATE_FAIL.getCode());
					message.setMsg(Code.OPERATE_FAIL.getMsg());
				}
				
				return message;
			}
			
			
			/**
			 * 更改密码
			 */
			@RequiresRoles("student")
			@RequestMapping(value="change_pw",method= {RequestMethod.POST})
			@CrossOrigin
			@ResponseBody
			public EasyMessage changePW(@RequestBody User user) {
				EasyMessage message = new EasyMessage();
				//进行密码加密
				user.setPassword(EncryptKit.MD5(user.getPassword()));
				boolean flag = userService.changePW(user);
				if(flag) {
					message.setStatus(Code.OPERATE_SUCCESS.getCode());
					message.setMsg(Code.OPERATE_SUCCESS.getMsg());
				}else {
					message.setStatus(Code.OPERATE_FAIL.getCode());
					message.setMsg(Code.OPERATE_FAIL.getMsg());
				}
				
				return message;
			}
			
			/**
			 * 个人中心查询数据
			 */
			@RequiresRoles("student")
			@RequestMapping(value="center",method= {RequestMethod.GET})
			@CrossOrigin
			@ResponseBody
			public Message detailUser(@RequestParam("uuid") int uuid) {
				Message message = new Message();
				message.setStatus(Code.OPERATE_SUCCESS.getCode());
				message.setMsg(Code.OPERATE_SUCCESS.getMsg());
				UserHelper uhelper = userService.detailUser(uuid);
				message.setData(uhelper);
				return message;
			}
			

			
			
			/**
			 * 拿取对应学院得教师用户
			 */
			@RequiresRoles("student")
			@RequestMapping(value="getTeachers")
			@CrossOrigin
			@ResponseBody
			public Message getTeachers(HttpServletResponse response,HttpServletRequest request,@RequestParam("majorID") int majorID) {
			
				Message message = new Message();
				List<User> teachers = userService.selectTeachers(majorID);
				message.setStatus(Code.OPERATE_SUCCESS.getCode());
				message.setMsg(Code.OPERATE_SUCCESS.getMsg());
				message.setData(teachers);
				return message;
			
			}
			
			
			/**
			 *学生提出问题
			 */
			@RequiresRoles("student")
			@RequestMapping(value="question",method= {RequestMethod.POST})
			@CrossOrigin
			@ResponseBody
			public EasyMessage question(@RequestBody Map<String,Object> map) {
				EasyMessage message = new EasyMessage();
				//得到老师的数组
				List<Integer> answerIDS = (List<Integer>) map.get("answerIDS");
				Question question = new Question();
				question.setAskID((int)map.get("uuid"));
				question.setContent((String)map.get("content"));
				question.setTime((String)map.get("time"));
				userService.insertQuestion(question, answerIDS);
				message.setStatus(Code.OPERATE_SUCCESS.getCode());
				message.setMsg(Code.OPERATE_SUCCESS.getMsg());
				return message;
			}
			
			/**
			 * 学生: 列出所有问题
			 */
			@RequiresRoles("student")
			@RequestMapping(value="student_question",method= {RequestMethod.GET})
			@CrossOrigin
			@ResponseBody
			public Message studentQuestion(HttpServletResponse response,HttpServletRequest request,@RequestParam("uuid") int uuid) {
				Message message = new Message();

					List<Question> questions = userService.selectQuestions(uuid);
					message.setStatus(Code.OPERATE_SUCCESS.getCode());
					message.setMsg(Code.OPERATE_SUCCESS.getMsg());
					message.setData(questions);

				return message;
			}
			
			/**
			 * 学生:查看回复
			 */
			@RequiresRoles("student")
			@RequestMapping(value="detail_question",method= {RequestMethod.GET})
			@CrossOrigin
			@ResponseBody
			public Message detailQuestion(@RequestParam("questionID") int questionID) {
				Message message = new Message();
				QuestionHelper items = userService.selectDetailQuestion(questionID);
				message.setStatus(Code.OPERATE_SUCCESS.getCode());
				message.setMsg(Code.OPERATE_SUCCESS.getMsg());
				message.setData(items);
				return message;
			}
			
			/**
			 * 老师:显示问题
			 */
			@RequiresRoles("teacher")
			@RequestMapping(value="teacher_question",method= {RequestMethod.GET})
			@CrossOrigin
			@ResponseBody
			public Message teacherQuestion(HttpServletResponse response,HttpServletRequest request,@RequestParam("uuid") int uuid) {
				
				Message message = new Message();

					List<Question> questions = userService.selectTeacherQuestions(uuid);
					message.setStatus(Code.OPERATE_SUCCESS.getCode());
					message.setMsg(Code.OPERATE_SUCCESS.getMsg());
					message.setData(questions);

				return message;
			}
			
			
			
			/**
			 * 老师:查询具体问题
			 */
			@RequiresRoles("teacher")
			@RequestMapping(value="teacher_detail",method= {RequestMethod.GET})
			@CrossOrigin
			@ResponseBody
			public Message teacherQuestionDetail(@RequestParam("questionID") int questionID,HttpServletResponse response,HttpServletRequest request,@RequestParam("uuid") int uuid) {
						
						Message message = new Message();
		
							QuestionHelper item = userService.selectTeacherDetailQuestion(questionID, uuid);
							message.setStatus(Code.OPERATE_SUCCESS.getCode());
							message.setMsg(Code.OPERATE_SUCCESS.getMsg());
							message.setData(item);

						return message;
			
					}
					
			/**
			 * 老师:评论
			 */
			@RequiresRoles("teacher")
			@RequestMapping(value="teacher_comment",method= {RequestMethod.POST})
			@CrossOrigin
			@ResponseBody
			public EasyMessage teacherComment(HttpServletResponse response,HttpServletRequest request,@RequestBody CommentHelper comment) {
						
						EasyMessage message = new EasyMessage();
							String content = comment.getContent();
							String time = comment.getTime();
							int questionID =  comment.getQuestionID();
							int answerID = comment.getAnswerID() ;//与数据库answerID两者意义不同(实际为MainAnswer的ID)  如果前端没得到，那么传入-1(说明该教师从没评论过)
							boolean flag = userService.teacherComment(content, time, questionID, answerID,comment.getUuid());
							if(flag == false) {
								message.setStatus(Code.OPERATE_FAIL.getCode());
								message.setMsg(Code.OPERATE_FAIL.getMsg());
							}else {
								message.setStatus(Code.OPERATE_SUCCESS.getCode());
								message.setMsg(Code.OPERATE_SUCCESS.getMsg());
							}
//						}
		
						return message;
					}
			
			
			/**
			 * 学生:评论(必须是老师评论的子评论)
			 */
			@RequiresRoles("student")
			@RequestMapping(value="student_comment",method= {RequestMethod.POST})
			@CrossOrigin
			@ResponseBody
			public EasyMessage studentComment(HttpServletResponse response,HttpServletRequest request,@RequestBody CommentHelper comment) {
				EasyMessage message = new EasyMessage();
				//String uuid = CookieUtil.getCookieValue(response, request, "token"); //得到uuid
				String content = comment.getContent();
				String time = comment.getTime();
				int answerID = comment.getAnswerID();//与数据库answerID两者意义不同(实际为MainAnswer的ID)

					boolean flag = userService.studentComment(content, time, answerID,comment.getUuid());
					if(flag == false) {
						message.setStatus(Code.OPERATE_FAIL.getCode());
						message.setMsg(Code.OPERATE_FAIL.getMsg());
					}else {
						message.setStatus(Code.OPERATE_SUCCESS.getCode());
						message.setMsg(Code.OPERATE_SUCCESS.getMsg());
					}

				return message;
			}
			
			/**
			 * MainAnswer局部刷新(即只要更新主评论的子评论即可) -- 用于回复主评论时
			 */
			@RequestMapping(value="refresh_comment",method= {RequestMethod.GET})
			@CrossOrigin
			@ResponseBody
			public Message refreshComment(@RequestParam("answerID") int answerID) {
				
				Message message = new Message();
					
				List<AnswerHelper> items = userService.refreshComment(answerID);
				message.setStatus(Code.OPERATE_SUCCESS.getCode());
				message.setMsg(Code.OPERATE_SUCCESS.getMsg());
				message.setData(items);
				return message;
	
			}
			
			
			/*
			 * 查询全部问题
			 */
			@RequiresRoles("admin")
			@RequestMapping(value = "selectAllQuestion",method={RequestMethod.GET})
			@CrossOrigin
			@ResponseBody
			public Object selectAllQuestion() {
				Message message = new Message();
				message.setStatus(Code.OPERATE_SUCCESS.getCode());
				message.setMsg(Code.OPERATE_SUCCESS.getMsg());
				List<Question> questions = userService.selectAllQuestion();
				message.setData(questions);
				return message;
			}
			
			/*
			 * 按照提问者ID查询问题
			 */
			@RequiresRoles("admin")
			@RequestMapping(value = "selectQuestionByaskID",method={RequestMethod.POST})
			@CrossOrigin
			@ResponseBody
			public Message selectQuestionByaskID(@RequestParam("askId") int askId) {
				
				Message message = new Message();
				message.setStatus(Code.OPERATE_SUCCESS.getCode());
				message.setMsg(Code.OPERATE_SUCCESS.getMsg());
				
				List<Question> questions = userService.selectQuestionByaskID(askId);
				message.setData(questions);

				return message;
				
				
			}
			
			
			
			/*
			 * 按照回答者ID查询问题
			 */
			@RequiresRoles("admin")
			@RequestMapping(value = "selectQuestionByanswerID",method={RequestMethod.POST})
			@CrossOrigin
			@ResponseBody
			public Message selectQuestionByanswerID(@RequestParam("answerId") int answerId) {
				
				Message message = new Message();
				message.setStatus(Code.OPERATE_SUCCESS.getCode());
				message.setMsg(Code.OPERATE_SUCCESS.getMsg());
				
				List<Question> questions = userService.selectQuestionByanswerID(answerId);
				message.setData(questions);

				return message;
				
				
			}
	}
