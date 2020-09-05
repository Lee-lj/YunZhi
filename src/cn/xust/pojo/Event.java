package cn.xust.pojo;

public class Event {
		public int id;
		public String introduction;
		public String content;
		public String time;
		public String begin;
		public String end;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getIntroduction() {
			return introduction;
		}
		public void setIntroduction(String introduction) {
			this.introduction = introduction;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getBegin() {
			return begin;
		}
		public void setBegin(String begin) {
			this.begin = begin;
		}
		public String getEnd() {
			return end;
		}
		public void setEnd(String end) {
			this.end = end;
		}
		public Event(int id, String introduction, String content, String time, String begin, String end) {
			super();
			this.id = id;
			this.introduction = introduction;
			this.content = content;
			this.time = time;
			this.begin = begin;
			this.end = end;
		}
		
		public Event() {
		
		}
		
}
