package controller;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import model.*;
import model.Roombooking;

@WebServlet("/availability")
public class availability extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public availability() {

	}

	RoomDAO dao = new RoomDAO();
	Roombooking booking = new Roombooking();
	RequestDispatcher rd;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (request.getParameter("select") != null) {
			
			List<Roombooking> bookings = new ArrayList<>();
			bookings = dao.getRoomBookings();
			session.setAttribute("bookings", bookings);
			rd = request.getRequestDispatcher("seeBookings.jsp");
			rd.forward(request, response);
		} else {
			boolean test;
			String comment = null;
			String startat = request.getParameter("startat");
			String endat = request.getParameter("endat");
			String date = request.getParameter("date");
			Date today = new Date();
		//	System.out.println(today);
			Date d;
			try {
				d = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		//		System.out.println(d);
				if (d.compareTo(today) < 0) {
					String dError = "Wrong date!";
					session.setAttribute("dError", dError);
					rd = request.getRequestDispatcher("availableRooms.jsp");
					rd.forward(request, response);

		//			System.out.println("tt");
				} else {
					User user = (User)session.getAttribute("user");
					int userId = user.getUserId();
					booking.setUserid(userId);
					booking.setStartat(startat);
					booking.setEndat(endat);
					booking.setBookingDate(date);

					request.setAttribute("booking", booking);
					test = dao.verifyBookings(userId, startat, endat, date);

					if (test) {
						comment = "You successfully booked the room. Please note your booking details.";
						session.setAttribute("comment", comment);
						rd = request.getRequestDispatcher("success.jsp");
						rd.forward(request, response);
					} else {
						comment = "Room not available at your desired time, please select another time.";
						session.setAttribute("comment", comment);

						rd = request.getRequestDispatcher("availableRooms.jsp");
						rd.forward(request, response);
					}
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

	}

}
