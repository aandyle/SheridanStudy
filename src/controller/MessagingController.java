package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;

import dao.MessagingDAO;
import util.DBUtil;
import model.MessageBean;
import model.User;

@WebServlet("/MessagingController")
public class MessagingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MessagingDAO messagingDAO = new MessagingDAO();
	private static String CREATE_MESSAGE = "/CreateMessages.jsp";
	private static String DISPLAY_MESSAGES = "/EmailHome.jsp";
	private static String SELECT_USER = "/SelectUser.jsp";

	

	public MessagingController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward = "";
		String action = request.getParameter("action");

		HttpSession session = request.getSession();
		RequestDispatcher view = null;

		if (action.equalsIgnoreCase("createmessage")) {			
			String resId = request.getParameter("userid");
			String usern = request.getParameter("usern");
			
			int respID = Integer.parseInt(resId);
			
			User user = (User) session.getAttribute("user");
			List<MessageBean> messages = new ArrayList<MessageBean>();
			messages = messagingDAO.getPrivate(user.getUserId(), respID);
			request.setAttribute("messages", messages);
			request.setAttribute("resId", resId);
			request.setAttribute("usern", usern);

			view = request.getRequestDispatcher(CREATE_MESSAGE);
		}

		else if (action.equalsIgnoreCase("SelectUser")) {
			ArrayList<User> users = messagingDAO.getAllEmailUsers();

			request.setAttribute("users", users);

			view = request.getRequestDispatcher(SELECT_USER); //select the user page 

			view.forward(request, response);
		}
		/// Get all users
		else if (action.equalsIgnoreCase("getAllUsers")) {
			List<User> users = new ArrayList<User>();


			users = messagingDAO.getAllEmailUsers(); // get all the list of users
			request.setAttribute("users", users); // the landing page will now have access to this object

			forward = SELECT_USER;
			view = request.getRequestDispatcher(forward);

		}
		// Show all the Messages

		else if (action.equalsIgnoreCase("getAllMessages")) {

			// load the session object

			User user = (User) session.getAttribute("user"); // cast as a user

			List<MessageBean> messages = new ArrayList<MessageBean>();

			System.out.println(user.getUserId());
			messages = messagingDAO.getMessageHistory(user.getUserId());
//			System.out.println(messages.get(0).getMessages());
			request.setAttribute("messages", messages);

			forward = DISPLAY_MESSAGES;
			view = request.getRequestDispatcher(forward);

		}
	
		view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String forward="";
		HttpSession session = request.getSession();
//		RequestDispatcher view = null;
		
		String submit = request.getParameter("submit");
		
		
		if(submit.equalsIgnoreCase("Send")) {
			Date date = new Date();
			MessageBean message = new MessageBean();
			
			User user = (User) session.getAttribute("user");
			
//			String subject =request.getParameter("subject");
			String emailMessage = request.getParameter("emailmessage");
			int resID = Integer.parseInt(request.getParameter("resID"));
			System.out.println(resID);
//			message.setSubject(subject);
			message.setMessages(emailMessage);
			message.setDate(date);
			//The value of the IDs is 0 
			//the ID is already, you have to get the ID from the session 
			session.setAttribute("user", user);
			
			//check these
			message.setSenderID(user.getUserId());
			
			message.setRecipientID(resID);			
			
			int isRead=message.getIsRead();
			  
			message.setIsRead(isRead);
						
			messagingDAO.addMessage(message);
			System.out.println("INSIDE the controller for submit section");

			
			response.sendRedirect("MessagingController?action=getAllUsers");
			
		}else if(submit.equalsIgnoreCase("Delete")) {			
			String uName = request.getParameter("uName");
			String msId = request.getParameter("msID");
			int messageID = Integer.parseInt(msId);
			String uid = request.getParameter("uid");
			int userID = Integer.parseInt(uid);
			String resid = request.getParameter("resid");
			int respid = Integer.parseInt(resid);
			messagingDAO.DeleteMessage(messageID);
			response.sendRedirect("MessagingController?action=createmessage&userid="+respid+"&usern="+uName);
		} else {
			System.out.println("ERRORR!!! inside the controller submit");
			
		}

	}

}
