package cn.edu.ahpu.utils.reflect;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TUser {
	private Long id;
	private String username;
	private Integer age;
	private Date birthdate;
	private Double salary;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public TUser(Long id, String username, Integer age, Date birthdate,
			Double salary) {
		super();
		this.id = id;
		this.username = username;
		this.age = age;
		this.birthdate = birthdate;
		this.salary = salary;
	}
	
	public String toString(){
		return "id:"+ id+
				",username:"+username+
				",age:"+age+
				",birthdate:"+new SimpleDateFormat("yyyy-MM-dd").format(birthdate)+
				",salary:"+salary;
	}
	
}
