package com.dao.studentApp;

public class Student {
	int sid;
	private String fname;
	private String lname;
	private String isA;
	private String pass;

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getIsA() {
		return isA;
	}

	public void setIsA(String isA) {
		this.isA = isA;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "StudentId= " + sid + "\nFirst Name= " + fname + "\nlast Name= " + lname + "\nis Admin= " + isA
				+ "\nPassword= " + pass;
	}

}
