package cn.xust.pojo;

import java.util.List;

public class QuestionHelper {
	List<DisplayHelper> answers;
	AnswerHelper question;
	public List<DisplayHelper> getAnswers() {
		return answers;
	}
	public void setAnswers(List<DisplayHelper> answers) {
		this.answers = answers;
	}
	public AnswerHelper getQuestion() {
		return question;
	}
	public void setQuestion(AnswerHelper question) {
		this.question = question;
	}
	public QuestionHelper(List<DisplayHelper> answers, AnswerHelper question) {
		super();
		this.answers = answers;
		this.question = question;
	}
	
	public QuestionHelper() {

	}
	
}
