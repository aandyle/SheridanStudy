package model;

import java.util.*;


public class Roombooking {
	
	private int bookingid;
	private int userid;
	private String startat;
	private String endat;
	private String bookingDate;
	
	public Roombooking () {
		
	}

	public Roombooking(int bookingid, int userid, String startat, String endat, String bookingDate) {
		
		this.bookingid = bookingid;
		this.userid = userid;
		this.startat = startat;
		this.endat = endat;
		this.bookingDate = bookingDate;
	}

	public int getBookingid() {
		return bookingid;
	}

	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getStartat() {
		return startat;
	}

	public void setStartat(String startat) {
		this.startat = startat;
	}

	public String getEndat() {
		return endat;
	}

	public void setEndat(String endat) {
		this.endat = endat;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	

}
