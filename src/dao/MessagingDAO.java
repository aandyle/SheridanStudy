package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import model.MessageBean;
import model.User;

public class MessagingDAO {

	// 1. ADD Message (Create Message)
	
	public void addMessage(MessageBean message) {
		Connection conn = null;

		try {
			conn = DBUtil.getConnection();

			PreparedStatement pStmt = conn.prepareStatement(
					"insert into messages" + "(Messages,isRead,date,SenderID,RecipientID)" + "values(?,?,?,?,?,?)");

			System.out.println(message.getSenderID());
			
			pStmt.setString(1, message.getMessages());
			pStmt.setInt(2, message.getIsRead());
			pStmt.setDate(3, new java.sql.Date(message.getDate().getTime())); 												
			pStmt.setInt(4, message.getSenderID());
			pStmt.setInt(5, message.getRecipientID());
			pStmt.executeUpdate(); //problem ****
			
			System.out.println("INSIDE the addmessage MessagingDAO");
			
			//Something in this section is not adding the message 
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeConnection(conn);
		}

	}

	// Get all users (display all the users EmailHome)

	public ArrayList<User> getAllEmailUsers() {

		Connection conn = null;
		ArrayList<User> users = new ArrayList<User>();

		try {
			conn = DBUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from user");

			ResultSet rSet = stmt.executeQuery();

			while (rSet.next()) {
				User user = new User();

				user.setUserId(rSet.getInt("userid"));
				user.setFirstName(rSet.getString("first_name"));
				user.setLastName(rSet.getString("last_name"));

				users.add(user); // we add the users

			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeConnection(conn);

		}

		return users;

	}

	// 2. Returns a Message History for the specific User (Displays message history)
	public List<MessageBean> getMessageHistory(int userID) {

		Connection conn = null;
		List<MessageBean> messages = new ArrayList<MessageBean>();
		System.out.println(userID);

		try {
			conn = DBUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from messages where RecipientID=?");
			stmt.setInt(1, userID);
			ResultSet rSet = stmt.executeQuery();

			while (rSet.next()) {
				MessageBean message = new MessageBean();

				message.setMessageID(rSet.getInt("MessageID"));
				message.setSenderID(rSet.getInt("SenderID"));
				message.setMessages(rSet.getString("Messages"));

//				message.setSubject(rSet.getString("Subject"));
				message.setRecipientID(rSet.getInt("RecipientID"));
				message.setIsRead(rSet.getInt("isRead"));
				message.setDate(rSet.getDate("date"));

				messages.add(message);

				System.out.println(message.getMessages());

			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeConnection(conn);
		}

		return messages;
	}
	
	
	
	
	
	
	
	
	
	
	
	public List<MessageBean> getPrivate(int userID, int resId) {

		Connection conn = null;
		List<MessageBean> messages = new ArrayList<MessageBean>();
		System.out.println(userID);
		try {
			conn = DBUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from messages where SenderID = ? AND RecipientID = ? ORDER BY date");
			stmt.setInt(1, userID);
			stmt.setInt(2, resId);
			ResultSet rSet = stmt.executeQuery();

			while (rSet.next()) {
				MessageBean message = new MessageBean();

				message.setMessageID(rSet.getInt("MessageID"));
				message.setSenderID(rSet.getInt("SenderID"));
				message.setMessages(rSet.getString("Messages"));

//				message.setSubject(rSet.getString("Subject"));
				message.setRecipientID(rSet.getInt("RecipientID"));
				message.setIsRead(rSet.getInt("isRead"));
				message.setDate(rSet.getDate("date"));

				messages.add(message);

				System.out.println(message.getMessages());

			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeConnection(conn);
		}

		return messages;
	}
	
	
	
	
	
	
	
	
	
	
	
	///Things needed to be fixed below

	// 3. Delete Message

	public void DeleteMessage(int messageID) {
		Connection conn = null;

		try {
			conn = DBUtil.getConnection();

			PreparedStatement pStmt = conn.prepareStatement("delete from messages where MessageID=?");

			pStmt.setInt(1, messageID);// whatever value is going to the question mark

			pStmt.executeUpdate();

		}

		catch (Exception e) {
			e.getMessage();

		}

		finally {
			DBUtil.closeConnection(conn);
		}

	}

	
	
	
	
	
	
	
	// 4. Display message by date
	// Message Order by date

	public void recentMessages(MessageBean message) {
		Connection conn = null;

		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("select * from messages order by date");

			ResultSet rSet = pStmt.executeQuery("select * from messages order by date");

			while (rSet.next()) {
				MessageBean display = new MessageBean();

				display.setMessageID(rSet.getInt("MessageID"));
				display.setSenderID(rSet.getInt("SenderID"));
				display.setMessages(rSet.getString("Messages"));

//				display.setSubject(rSet.getString("Subject"));
				display.setRecipientID(rSet.getInt("RecipientID"));
				display.setIsRead(rSet.getInt("isRead"));
				display.setDate(rSet.getDate("date"));

			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}

	}

	// 5. Possible Edit Message?

	// student can use
//	public void updateMessage(MessageBean message) {
//		Connection conn = null;
//
//		try {
//			conn = DBUtil.getConnection();
//
//			PreparedStatement pStmt = conn
//					.prepareStatement("update messages set Messages=?," + "isRead=?,date=?");
//
//			pStmt.setString(1, message.getMessages());
////			pStmt.setString(2, message.getSubject());
//			pStmt.setInt(2, message.getIsRead());
//			pStmt.setDate(3, new java.sql.Date(message.getDate().getTime())); // ask about this how to change to get
//																				// time and date, not just time
//
//			pStmt.executeUpdate();
//
//		}
//
//		catch (Exception e) {
//
//		}
//
//		finally {
//			DBUtil.closeConnection(conn);
//		}
//
//	}

	// //Teacher can use (Secondary)
	//
	// public void RemoveUser(int userID) {
	// Connection conn = null;
	//
	//
	//
	// try {
	// conn=DBUtil.getConnection();
	//
	//
	//
	// PreparedStatement pStmt = conn.prepareStatement("delete from messages where
	// MessagesID=?");
	//
	//
	// pStmt.setInt(1, userID);// whatever value is going to the question mark
	// pStmt.executeUpdate();
	// //THIS SECTION WILL DELETE THE USER
	// // It is not setting it to 1, it is deleting the UserID but that is in the
	// first position
	//
	// }
	//
	// catch (Exception e) {
	// e.getMessage();
	//
	// }
	//
	//
	// finally {
	// DBUtil.closeConnection(conn);
	// }
	//
	// }
	//

	// Add display all messages section

	// add message
	// get all messages (Get Message History)
	// delete message
	// Edit Messages???
	// Possible Search for specific message in messages (need to do still)

}
