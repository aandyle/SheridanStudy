package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.User;
import util.DBUtil;

import java.sql.ResultSet;

public class UserDAO {
	
	public User checkUser(User user){
		
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM user WHERE userid = ? AND password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user.getUserId());
			pStmt.setString(2, user.getPassword());
			ResultSet rset = pStmt.executeQuery();
			if (rset.next()){
				user.setFirstName(rset.getString("first_name"));
				user.setLastName(rset.getString("last_name"));
				user.setAdmin(checkAdmin(rset.getInt("userid")));
				user.setValid(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return user;
	}
	
	public boolean checkAdmin(int userId){
		Connection conn = null;
		boolean isAdmin = false;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM admin WHERE adminid = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
			ResultSet rset = pStmt.executeQuery();
			if (rset.next()){
				isAdmin = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return isAdmin;
	}
}
