package com.dao.studentApp;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class JDBCImpl implements StudentInfoDAO {
	@Override
	public Student login(int sid, String passwd) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student ls = null;
		String dbUrl = "jdbc:mysql://localhost:3306/capsV3_db";
		String qry = "select * from students_info where sid=? and password=?";
		try {
			FileReader fr = new FileReader("lib/db.properties");
			Properties prop = new Properties();
			prop.load(fr);

			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(dbUrl, prop);

			pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, sid);
			pstmt.setString(2, passwd);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int reg = rs.getInt("sid");
				String fnam = rs.getString("firstname");
				String lnam = rs.getString("lastname");
				String isAdm = (rs.getString("isadmin")).toUpperCase();
				String pass = rs.getString("password");
				ls = new Student();

				ls.setSid(reg);
				ls.setFname(fnam);
				ls.setLname(lnam);
				ls.setIsA(isAdm);
				ls.setPass(pass);

			}

		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ls;

	}

	@Override
	public boolean createProfile(Student s) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String dbUrl = "jdbc:mysql://localhost:3306/capsV3_db";
		String qry = "insert into students_info values(?,?,?,?,?)";
		try {
			FileReader fr = new FileReader("lib/db.properties");
			Properties prop = new Properties();
			prop.load(fr);

			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(dbUrl, prop);

			pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, s.getSid());
			pstmt.setString(2, s.getFname());
			pstmt.setString(3, s.getLname());
			pstmt.setString(4, s.getIsA());
			pstmt.setString(5, s.getPass());

			int an = pstmt.executeUpdate();
			if (an > 0) {
				return true;
			} else {
				return false;
			}

		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		} finally {

			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Student searchStudents(int sid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student ob = null;
		String dbUrl = "jdbc:mysql://localhost:3306/capsV3_db";
		String qry = "select * from students_info where sid=?";
		try {
			FileReader fr = new FileReader("lib/db.properties");
			Properties prop = new Properties();
			prop.load(fr);

			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(dbUrl, prop);

			pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, sid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int reg = rs.getInt("sid");
				String fnam = rs.getString("firstname");
				String lnam = rs.getString("lastname");
				String isAdm = (rs.getString("isadmin")).toUpperCase();
				String pass = rs.getString("password");

				ob = new Student();
				ob.setSid(reg);
				ob.setFname(fnam);
				ob.setLname(lnam);
				ob.setIsA(isAdm);
				ob.setPass(pass);

			}

		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ob;
	}

	@Override
	public boolean deleteStudent(int sid, String passwd) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			/*
			 * 1. Load the Driver
			 */
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver Loaded...");

			/*
			 * 2. Get the DB Connection via Driver
			 */
			String dbUrl = "jdbc:mysql://localhost:3306/capsV3_db";

			// 3rd Way to get a DB Connection
			String filePath = "lib/db.properties";
			FileReader reader = new FileReader(filePath);
			Properties prop = new Properties();
			prop.load(reader);

			con = DriverManager.getConnection(dbUrl, prop);

			System.out.println("Connected...");

			/*
			 * 3. Issue the SQL query via connection
			 */
			String sql = "delete from students_info where sid=? and password=?";

			int count = 0;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sid);
			pstmt.setString(2, passwd);

			count = pstmt.executeUpdate();

			/*
			 * 4. Process the results
			 */

			if (count > 0) {
//				/System.out.println("Student data deleted...");
				return true;
			} else {
				/* System.out.println("deletion Failed"); */
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*
			 * 5. Close all the JDBC Objects
			 */

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return false;
	}

}
