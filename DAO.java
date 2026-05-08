package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DAO {
	private final String SERVER = "192.168.100.13";
	private final String PORT = "3306";
	private final String DB= "Maze";
	private final String USER = "root";
	private final String PASS = "Pikachumu0869cj#";
	private final String URL = "jdbc:mysql://" + SERVER + ":" + PORT + "/" + DB;
	
	public User checkLogin(String username, String password){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URL, USER, PASS);	
			String query= "Select * from user where username = ? and password = ?;";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if((rs.next())){
				User u = new User(rs.getInt("id"),
								  rs.getString("username"),
								  rs.getString("name"),
								  rs.getString("nif"),
								  rs.getString("email"),
								  rs.getString("address"),
								  rs.getString("birthdate"),
								  rs.getString("role"));
				rs.close();
				pstmt.close();
				conn.close();
				return u;
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			return null;
			
		}catch(Exception e) {
			System.out.print("Error: " + e);
		}
		return null;
	}
	
	public boolean checkUser(User u) {
		String username = u.getUsername();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URL, USER, PASS);	
			String query= "Select username from user where username = ?;";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if((rs.next())){
				rs.close();
				pstmt.close();
				conn.close();
				return true;
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			return false;
			
		}catch(Exception e) {
			System.out.print("Error: " + e);
		}
		return false;
	}
	
	public boolean signupUser(String username, String password, String name, String nif, String email, String address, String birthdate) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URL, USER, PASS);	
			String insert = "Insert into user (username, password, name, nif, email, address, birthdate) values (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(insert);
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, nif);
			pstmt.setString(5, email);
			if (address == null || address.isEmpty()) {
			    pstmt.setNull(6, java.sql.Types.VARCHAR);
			} else {
			    pstmt.setString(6, address);
			}
			pstmt.setString(7, birthdate);
			
			int rows = pstmt.executeUpdate();
			
			if(rows > 0) {
				return true;
			} 
			
		}catch(Exception e) {
			System.out.print("Error: " + e);
		}
		return false;
	}
	
	public User info(String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URL, USER, PASS);	
			String query= "Select * from user where username = ? and password = ?;";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			if((rs.next())){
				User u = new User(rs.getInt("id"),
								  rs.getString("username"),
								  rs.getString("name"),
								  rs.getString("nif"),
								  rs.getString("email"),
								  rs.getString("address"),
								  Utils.formatDateEU(rs.getString("birthdate")),
								  rs.getString("role"));
				rs.close();
				pstmt.close();
				conn.close();
				return u;
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			return null;
			
		}catch(Exception e) {
			System.out.print("Error: " + e);
		}
		return null;
	}
}

