package cn.xust.pojo;

import java.util.List;

public class DisplayHelper {

	private AnswerHelper first;
	private List<AnswerHelper> other;
	public AnswerHelper getFirst() {
		return first;
	}
	public void setFirst(AnswerHelper first) {
		this.first = first;
	}
	public List<AnswerHelper> getOther() {
		return other;
	}
	public void setOther(List<AnswerHelper> other) {
		this.other = other;
	}
	public DisplayHelper(AnswerHelper first, List<AnswerHelper> other) {
		super();
		this.first = first;
		this.other = other;
	}
	
	public DisplayHelper() {

	}
}
