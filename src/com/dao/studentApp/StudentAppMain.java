package com.dao.studentApp;

import java.util.Scanner;

public class StudentAppMain {

	public static void main(String[] args) {
		int ch = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter choice:-\n1.Login\n2.Create Profile\n3.Search Students\n4.Delete Student\n5.Exit\n");
		ch = sc.nextInt();
		switch (ch) {
		case 1: {
			// Login Module
			System.out.println("LOGIN....\nEnter sid: ");
			int sid = sc.nextInt();
			System.out.println("Enter password: ");
			String pass = sc.next();

			StudentInfoDAO db = new JDBCImpl();
			Student ls = db.login(sid, pass);
			System.out.println(ls);
		}
			break;

		case 2: {
			// Create Profile module

			System.out.println("Create Profile.....\nEnter sid:");
			int sid = sc.nextInt();
			System.out.println("Enter first name:");
			String fnam = sc.next();
			System.out.println("Enter last name:");
			String lnam = sc.next();
			System.out.println("Is Admin:");
			String isA = sc.next();
			System.out.println("Enter password:");
			String pass = sc.next();
			StudentInfoDAO db = new JDBCImpl();
			Student s = new Student();
			s.setSid(sid);
			s.setFname(fnam);
			s.setLname(lnam);
			s.setIsA(isA);
			s.setPass(pass);

			boolean b = db.createProfile(s);
			if (b) {
				System.out.println("Data Inserted succesfully");
			} else {
				System.out.println("Data insertion FAILED!!!!");
			}
		}
			break;

		case 3: {
			// Search Student module
			System.out.println("SEARCH.....\nEnter student id:");
			int sid = sc.nextInt();
			StudentInfoDAO db = new JDBCImpl();
			Student ob = db.searchStudents(sid);
			System.out.println(ob);
		}
			break;

		case 4: {
			// student delete module
			System.out.println("DELETE STUDENT ...\nEnter student id:");
			int sid = sc.nextInt();
			System.out.println("Enter password:");
			String pass = sc.next();
			StudentInfoDAO db = new JDBCImpl();

			Boolean res = db.deleteStudent(sid, pass);
			if (res) {
				System.out.println("Student Deleted !!!");
			} else {
				System.out.println("Student data deletion Failed!!!!");
			}
		}
			break;
		case 5: {
			System.exit(0);
		}
			break;

		}
		sc.close();
	}

}
