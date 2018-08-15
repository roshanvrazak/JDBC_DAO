package com.dao.studentApp;

public interface StudentInfoDAO {
	Student login(int sid, String passwd);

	boolean createProfile(Student s);

	Student searchStudents(int sid);

	boolean deleteStudent(int sid, String passwd);
}
