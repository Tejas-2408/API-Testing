package _09_Serialization_Deserialization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Student {

	// variables
	String name;
	String location;
	String phone;
	String courses[];
	
	// constructors
	public Student() {
		
	}
	
	public Student(String name,String location, String phone, String[] courses) {
		this.name = name;
		this.location = location;
		this.phone = phone;
		this.courses = courses;
	}

	
	// setters & getters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String[] getCourses() {
		return courses;
	}

	public void setCourses(String[] courses) {
		this.courses = courses;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(" ");
		sb.append(location);
		sb.append(" ");
		sb.append(phone);
		sb.append(" ");
		if(courses!=null && courses.length>0) {
			for(String i:courses) {
				sb.append(i).append(" ");
			}
		}
		
		return sb.toString();
	}
}
