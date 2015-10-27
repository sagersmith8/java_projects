package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleDriver;

public class DatabaseUtil {
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public DatabaseUtil(){
	}
	
	public boolean getProfile(String username, String password){
		openConnection();
		try {
			pstmt = con.prepareStatement("SELECT * FROM PROFILE WHERE EMAIL LIKE ? AND PASSWORD LIKE ?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				while(rs.next()){
					System.out.println(rs.getString(1)+" " +rs.getString(2));
				}
				return true;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
					
			try {
				pstmt.close();
				con.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}		
	
	public ArrayList<String> getProfile(String firstName, String lastName, String dob){
		ArrayList<String> strings = new ArrayList<String>();
		openConnection();
		try {
			pstmt = con.prepareStatement("SELECT EMAIL, PASSWORD FROM PROFILE WHERE FIRST_NAME LIKE ? AND LAST_NAME LIKE ? AND DOB LIKE ?");
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, dob);
			rs= pstmt.executeQuery();
				
			while(rs.next()){
				strings.add(rs.getString("EMAIL"));
				strings.add(rs.getString("PASSWORD"));
			}
				
			if(strings.size()>0){
				return strings;
			}
	
			return null;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		finally{
					
			try {
				pstmt.close();
				con.close();
			}
			
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}		
	
	private void openConnection(){
		try
	    {
		  DriverManager.registerDriver(new OracleDriver());
		  con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "System",  "System");
	    }
		
	    catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }
	}
	 
	public void initialValues(){
		
	}
	
	public void deleteDatabase(){
		openConnection();
		
		try
		{
			stmt = con.createStatement();
			stmt.executeUpdate("DROP TABLE PROFILE");
			stmt.executeUpdate("DROP TABLE AVATAR");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally{
			try {
				stmt.close();
				con.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createDatabase(){
		openConnection();
		
		try
		{
			stmt = con.createStatement();
			stmt.executeUpdate("CREATE TABLE PROFILE(FIRST_NAME VARCHAR(20), LAST_NAME VARCHAR(20), DOB VARCHAR(20),EMAIL VARCHAR(40), PASSWORD VARCHAR(20))");
			stmt.executeUpdate("CREATE TABLE AVATAR(IMAGE blob)");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally{
			try {
				stmt.close();
				con.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void createProfile(String firstName, String lastName, String dob,String username, String password) {
		openConnection();
		try {
			pstmt = con.prepareStatement("INSERT INTO PROFILE VALUES(?,?,?,?,?)");
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, dob);
			pstmt.setString(4, username);
			pstmt.setString(5, password);
			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally{
			try {
				pstmt.close();
				con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}		
		}
	}
}
