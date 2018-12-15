package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.User;
import model.Comment;
import model.Discussion;
import util.DBUtil;

public class DiscussionDAO {
	
	public void newDiscussion(User user, Discussion discussion){
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "INSERT INTO discussion(title, ownerid) VALUES(?, ?)";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, discussion.getTitle());
			
			pStmt.setInt(2, user.getUserId());
			
			pStmt.executeUpdate();
		
		} catch (SQLException e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
	}
	
	public void newComment(int userID, int discussionID, Comment comment){
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "INSERT INTO comment (discussionid, posttime, comment, userid) VALUE(?,?,?, ?)";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, discussionID);
			
			pStmt.setString(2, comment.getPostTime());
			
			pStmt.setString(3, comment.getComment());
			
			pStmt.setInt(4, userID);
			
			pStmt.executeUpdate();
		
		} catch (Exception e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
		
	}
	
	public void setDiscussionId(Discussion discussion,String title){
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "SELECT discussionid FROM discussion WHERE title = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, title);
			
			ResultSet rset = pStmt.executeQuery();
			
			if (rset.next()) {
				
				discussion.setDiscussionId(rset.getInt("discussionid"));
				
			}
			
		} catch (Exception e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
		
	}
	
	public Discussion getDiscussion(int discussionId){
		
		Connection conn = null;
		Discussion discussion = new Discussion();
		
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM discussion WHERE discussionId = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, discussionId);
			
			ResultSet rset = pStmt.executeQuery();
			
			if (rset.next()) {
				discussion.setDiscussionId(rset.getInt("discussionid"));
				discussion.setTitle(rset.getString("title"));
			}
			
		} catch (Exception e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
		
		return discussion;
		
	}
	
	public ArrayList<Comment> getComments(int discussionId, int pageNumber){
		
		Connection conn = null;
		int numberOfPost = 5;
		ArrayList<Comment>  comment= new ArrayList<Comment>();
		
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM comment WHERE discussionid = ? LIMIT ? OFFSET ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, discussionId);
			
			pStmt.setInt(2, numberOfPost);
			
			pStmt.setInt(3, (pageNumber-1)*numberOfPost);
			
			ResultSet rset = pStmt.executeQuery();
			
			UserDAO userCheck = new UserDAO();
			
			while (rset.next()) {
				Comment comm = new Comment();
				comm.setCommentId(rset.getInt("commentid"));
				comm.setDiscussionId(rset.getInt("discussionid"));
				comm.setPostTime(rset.getString("posttime"));
				comm.setComment(rset.getString("comment"));
				comm.setUserId(rset.getInt("userid"));
				comm.setUsername(getUserDetail(rset.getInt("userid")));
				comm.setCheck(userCheck.checkAdmin(rset.getInt("userid")));
				comment.add(comm);
			}
			
		} catch (Exception e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
		return comment;
		
	}
	
	public int getNumberOfPost(int discussionId){
		
		Connection conn = null;
		int numberOfPosts = 0;
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "SELECT COUNT(*) AS numberOfPosts FROM comment WHERE discussionid = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, discussionId);
			
			ResultSet rset = pStmt.executeQuery();
			
			if (rset.next()) {
				numberOfPosts = rset.getInt("numberOfPosts");
			}
			
		} catch (Exception e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
		return numberOfPosts;
		
	}
	
	public String getUserDetail(int userId){
		
		Connection conn = null;
		String firstName = "";
		String lastName = "";
		
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "SELECT first_name, last_name FROM user WHERE userid = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, userId);
			
			ResultSet rset = pStmt.executeQuery();
			
			if (rset.next()) {
				firstName = rset.getString("first_name");
				lastName = rset.getString("last_name");
			}
			
		} catch (Exception e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
		
		return firstName+" "+lastName;
		
	}
	
	public ArrayList<Discussion> getDiscussionsList(int pageNumber){
		
		Connection conn = null;
		int numberOfDiscussions = 5;
		ArrayList<Discussion>  discussionList= new ArrayList<Discussion>();
		
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM discussion LIMIT ? OFFSET ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, numberOfDiscussions);
			
			pStmt.setInt(2, (pageNumber - 1) * numberOfDiscussions);
			
			ResultSet rset = pStmt.executeQuery();
			
			while (rset.next()) {
				Discussion discussion = new Discussion();
				discussion.setDiscussionId(rset.getInt("discussionid"));
				discussion.setTitle(rset.getString("title"));
				discussion.setNumberOfComments(getNumberOfPost(rset.getInt("discussionid")));
				discussion.setOwnerName(getUserDetail(rset.getInt("ownerid")));
				discussion.setPostDate(getPostDate(rset.getInt("discussionid")));
				discussion.setComment(getFirstComment(rset.getInt("discussionid")));
				discussionList.add(discussion);
			}
			
		} catch (Exception e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
		
		return discussionList;
		
	}
	
	public int getNumberOfDiscussions(){
		
		Connection conn = null;
		int numberOfDiscussions = 0;
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "SELECT COUNT(*) AS numberOfDiscussions FROM discussion";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rset = pStmt.executeQuery();
			
			if (rset.next()) {
				numberOfDiscussions = rset.getInt("numberOfDiscussions");
			}
			
		} catch (Exception e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
		return numberOfDiscussions;
		
	}
	
	public void deleteDiscussion(int discussionId){
		deleteCommentsByDiscussion(discussionId);
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "DELETE FROM discussion WHERE discussionId = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, discussionId);
			
			pStmt.executeUpdate();
			
		} catch (Exception e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
		
	}
	
	public void deleteCommentsByDiscussion(int discussionId){
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "DELETE FROM comment WHERE discussionId = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, discussionId);
			
			pStmt.executeUpdate();
			
		} catch (Exception e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
		
	}
	
	public void deleteComment(int commentId){
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "DELETE FROM comment WHERE commentid = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, commentId);
			
			pStmt.executeUpdate();
			
		} catch (Exception e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
		
	}
	
	public ArrayList<Discussion> searchDiscussion(String title, int pageNumber){
		
		Connection conn = null;
		int numberOfDiscussions = 5;
		ArrayList<Discussion>  discussionList= new ArrayList<Discussion>();
		
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM discussion WHERE title LIKE ?  LIMIT ? OFFSET ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, "%"+title+"%");
			
			pStmt.setInt(2, numberOfDiscussions);
			
			pStmt.setInt(3, (pageNumber - 1) * numberOfDiscussions);
			
			ResultSet rset = pStmt.executeQuery();
			
			while (rset.next()) {
				
				Discussion discussion = new Discussion();
				discussion.setDiscussionId(rset.getInt("discussionid"));
				discussion.setTitle(rset.getString("title"));
				discussion.setNumberOfComments(getNumberOfPost(rset.getInt("discussionid")));
				discussion.setOwnerName(getUserDetail(rset.getInt("ownerid")));
				discussion.setPostDate(getPostDate(rset.getInt("discussionid")));
				discussion.setComment(getFirstComment(rset.getInt("discussionid")));
				discussionList.add(discussion);
				
			}
			
		} catch (Exception e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
		
		return discussionList;
		
	}
	
	public int getNumberOfDiscussions(String title){
		
		Connection conn = null;
		int numberOfDiscussions = 0;
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "SELECT COUNT(*) AS numberOfDiscussions FROM discussion WHERE title = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, title);
			
			ResultSet rset = pStmt.executeQuery();
			
			if (rset.next()) {
				numberOfDiscussions = rset.getInt("numberOfDiscussions");
			}
			
		} catch (Exception e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
		return numberOfDiscussions;
		
	}
	
	public String getPostDate(int discussionId){
		
		Connection conn = null; 
		String postTime = "";
		
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "SELECT posttime FROM comment WHERE discussionid = ? limit 1";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, discussionId);
			
			ResultSet rset = pStmt.executeQuery();
			
			if (rset.next()) {
				postTime = rset.getString("posttime");
			}
			
		} catch (Exception e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
	    
		return postTime;
		
	}
	
	public String getFirstComment(int discussionId){
		
		Connection conn = null;
		String comment = "";
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "SELECT comment FROM comment WHERE discussionid = ? limit 1";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, discussionId);
			
			ResultSet rset = pStmt.executeQuery();
			
			if (rset.next()) {
				comment = rset.getString("comment");
			}
			
		} catch (Exception e) {
		
			e.getMessage();
		
		} finally {
			
			DBUtil.closeConnection(conn);
	
		}
		return comment;
		
	}

}
