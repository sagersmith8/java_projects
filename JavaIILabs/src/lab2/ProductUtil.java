package lab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;

public class ProductUtil {
	Statement statement;
	Connection con;
	ResultSet resultSet;
	PreparedStatement preparedStatement;
	String poop = "poop";
	final String connectionUrl = "jdbc:oracle:thin:@localhost:1521:XE";
	String userName, password;
	
	public void openConnection(){
		try{
			DriverManager.registerDriver(new OracleDriver());
			con = DriverManager.getConnection(connectionUrl,"system","system");
		}
		
		catch(Exception ex){
			System.out.println("Error opening connection " + ex.getMessage());
		}
	}
	
	public void closeConnection(){
		try{
			if(!con.isClosed())
				con.close();
		}
		catch(Exception ex){
			System.out.println("Error closing connection" + ex.getMessage());
		}
	}
	
	public void insertProduct(int id, String name, String description, int price,int qoh){
		try{
			openConnection();
			System.out.println("Inserting...");
			preparedStatement = con.prepareStatement("INSERT INTO PRODUCTS VALUES(?,?,?,?,?)");
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, description);
			preparedStatement.setDouble(4, price);
			preparedStatement.setInt(5, qoh);
			preparedStatement.executeUpdate();
		}
		catch(Exception ex){
			System.out.println("problem inserting values" + ex.getMessage());
			ex.printStackTrace();
		}
		finally{
			closeConnection();
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void deleteProduct(int id) {
		try{
			openConnection();
			System.out.println("deleting...");
			preparedStatement = con.prepareStatement("DELETE FROM Products WHERE PRODUCTID LIKE ?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		}
		catch(Exception ex){
			System.out.println("Error deleting product " + ex.getMessage());
		}
		finally{
			closeConnection();
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void showProduct(){
		try{
			openConnection();
			System.out.println("printing...");
			statement = con.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM PRODUCTS");
			while(resultSet.next()){
				System.out.println(resultSet.getInt("PRODUCTID")+" "+resultSet.getString("NAME")+" "+resultSet.getInt("QOH"));
			}
		}
		catch(Exception ex){
			System.out.println("Problem printing product" + ex.getMessage());
		}
		
		finally{
			closeConnection();
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
