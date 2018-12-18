package dao;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import model.*;

public class RoomDAO {

	// check if booking already exist
	
	public boolean verifyBookings(int userId, String startat, String endat, String date) {
		boolean test = true;
		Connection conn = null;
		System.out.println("in verifyBookings");
		try {

			conn = DBUtil.getConnection();
			Roombooking booking = new Roombooking();
			String sql = "SELECT * FROM roombooking WHERE startat = ? and endat = ? and date = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, startat);
			pStmt.setString(2, endat);
			pStmt.setString(3, date);

			ResultSet rset = pStmt.executeQuery();

			if (rset.next()) {
				System.out.println("Booking not Available!");
				test = false;

			} else {
				booking.setUserid(userId);
				booking.setStartat(startat);
				booking.setEndat(endat);
				booking.setBookingDate(date);
				addBooking(booking, userId);
				test = true;
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			DBUtil.closeConnection(conn);
		}
		return test;
	}

	// add booking
	public void addBooking(Roombooking booking, int userId) {
		Connection conn = null;
	
		try {
			System.out.println("in addBooking");
			conn = DBUtil.getConnection();
			System.out.println("in INSERT");
			String sql = "INSERT INTO roombooking (userid, startat, endat, date) VALUES (?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
			pStmt.setString(2, booking.getStartat());
			pStmt.setString(3, booking.getEndat());
			// pStmt.setDate(4, new java.sql.Date(booking.getBookingDate().getTime()));
			pStmt.setString(4, booking.getBookingDate());
			pStmt.executeUpdate();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	// select roomBookings
	public List<Roombooking> getRoomBookings(){
		System.out.println("In Select all 1");
		List<Roombooking> bookings = new ArrayList<>();
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * from roombooking";
			
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
		
			while (rset.next()) {
				
				Roombooking booking= new Roombooking();
			
				booking.setBookingid(rset.getInt("bookingid"));
			
				booking.setUserid(rset.getInt("userid"));
				
				booking.setStartat(rset.getString("startat"));
			
				booking.setEndat(rset.getString("endat"));
			
				booking.setBookingDate(rset.getString("date"));
			
				bookings.add(booking);
			
			}
			
		}
		catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			DBUtil.closeConnection(conn);
		}
		return bookings;
	}

}
