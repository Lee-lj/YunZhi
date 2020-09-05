package cn.xust.pojo;

public class Example {
		public int id;
		public String introduction;
		public String content;
		public String time;
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
		public Example(int id, String introduction, String content, String time) {
			super();
			this.id = id;
			this.introduction = introduction;
			this.content = content;
			this.time = time;
		}
		public Example() {
			
		}
		
		
}
