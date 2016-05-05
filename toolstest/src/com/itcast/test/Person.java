package com.itcast.test;

public class Person {

	private Integer pid;
	private String pname;
	private Integer age;
	private String sex;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Person [pid=" + pid + ", pname=" + pname + ", age=" + age
				+ ", sex=" + sex + "]";
	}

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(Integer pid, String pname, Integer age, String sex) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.age = age;
		this.sex = sex;
	}

}
