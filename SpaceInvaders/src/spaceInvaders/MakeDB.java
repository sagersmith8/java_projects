package spaceInvaders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MakeDB {
	public static void main(String[] args) throws Exception {
		Connection con;
		Statement stmt;
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:8080:XE", "system",  "hello");
			stmt = con.createStatement();
		} catch (SQLException e) {
			System.err.println("Could not make a connection");
		}
		
		System.out.println("DROPPING TABLES ...");
		try {
			stmt.executeUpdate("DROP TABLE HighScoreTable");
		} catch(Exception e) {
			System.err.println("Could not drop HSTable table");
		}
		
		System.out.println("CREATING TABLES ...");
		try {
			stmt.executeUpdate("CREATE TABLE HighScoreTable (Username TEXT(20) NOT NULL CONSTRAINT PK_HighScoreTable PRIMARY KEY, HighScore TEXT(10) NOT NULL)");
		} catch(Exception e) {
			System.err.println("Could not create HighScoreTable table : " + e.getMessage());
		}
		
		System.out.println("ADDING DATA ...");
		String name = "AAA";
		String score = "000";
		try {
			stmt.executeUpdate("INSERT INTO HighScoreTable VALUES('" + name + "', '" + score + "')");
		} catch(Exception e) {
			System.err.println("Colud not insert data " + e.getMessage());
		}
		
		con.close();
		stmt.close();
	}
}
