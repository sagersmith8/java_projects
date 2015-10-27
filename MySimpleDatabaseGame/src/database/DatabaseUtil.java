package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;

public class DatabaseUtil {
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public DatabaseUtil(){
		deleteDatabase();
		createDatabase();
	}
	
	public ResultSet profile(String username, String password){
		openConnection();
		try {
			pstmt = con.prepareStatement("SELECT * FROM PROFILE WHERE NAME LIKE ? AND PASSWORD LIKE ?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			return pstmt.executeQuery();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null;
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
			stmt.executeUpdate("DROP TABLE PLAYER");
			stmt.executeUpdate("DROP TABLE PROFILE");
			stmt.executeUpdate("DROP TABLE CHARACTER");
			stmt.executeUpdate("DROP TABLE CLASS");
			stmt.executeUpdate("DROP TABLE GAME_PIECE");
			stmt.executeUpdate("DROP TABLE ITEM");
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
			stmt.executeUpdate("CREATE TABLE PROFILE(PROFILE_ID NUMBER, NAME VARCHAR(20), PASSWORD VARCHAR(20), DATE_JOINED DATE)");
			stmt.executeUpdate("CREATE TABLE PLAYER(PLAYER_ID NUMBER, PROFILE_ID NUMBER, CHARACTER_ID NUMBER)");
			stmt.executeUpdate("CREATE TABLE CHARACTER(CHARACTER_ID NUMBER, CLASS_ID NUMBER, GAME_PIECE_ID NUMBER)");
			stmt.executeUpdate("CREATE TABLE CLASS(CLASS_ID NUMBER, NAME VARCHAR(20))");
			stmt.executeUpdate("CREATE TABLE GAME_PIECE(GAME_PIECE_ID NUMBER)");
			stmt.executeUpdate("CREATE TABLE ITEM(ITEM_ID NUMBER, GAME_PIECE_ID NUMBER)");
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
}
