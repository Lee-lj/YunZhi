package cn.xust.pojo;

public class Teacher {
		public int id;
		public String name;
		public String content;
		public String image;
		public String time;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public Teacher(int id, String name, String content, String image, String time) {
			super();
			this.id = id;
			this.name = name;
			this.content = content;
			this.image = image;
			this.time = time;
		}
		
		public Teacher() {
			
		}
		
		
}
