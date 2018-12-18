package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAO;
import model.User;

@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ADMIN_HOME = "/admin.jsp";
	private static String NEW_USER = "/newuser.jsp";
	private static String UNAUTH = "/unauthorized.jsp";
	
	
	AdminDAO adminDao = new AdminDAO();
	
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("action");
    	HttpSession session = request.getSession();
    	User u = (User)session.getAttribute("user");
    	RequestDispatcher rd = null;
    	
    	if(!u.isAdmin()) {
    		rd = request.getRequestDispatcher(UNAUTH);
    	} else if(action.equalsIgnoreCase("load")) {
    		ArrayList<User> users = adminDao.getAllUsers();
    		request.setAttribute("users", users);
    		
    		rd = request.getRequestDispatcher(ADMIN_HOME);
    	}
    	
    	rd.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
    	User u = (User)session.getAttribute("user");
    	RequestDispatcher rd = null;
    	String forward;
    	
    	if(action.equalsIgnoreCase("Add Student")) {
    		System.out.println("adding student");
    		User newUser = new User();
    		newUser.setAdmin(false);
    		
    		session.setAttribute("newuser", newUser);
    		forward = NEW_USER;
    		rd = request.getRequestDispatcher(forward);
    	} else if(action.equalsIgnoreCase("Add Admin")) {
    		System.out.println("adding admin");
    		User newUser = new User();
    		newUser.setAdmin(true);
    		
    		session.setAttribute("newuser", newUser);
    		forward = NEW_USER;
    		rd = request.getRequestDispatcher(forward);
    	} else if(action.equalsIgnoreCase("Add")) {
    		System.out.println("saving user...");
    		User newUser = (User)session.getAttribute("newuser");
    		
    		String fName = request.getParameter("fname");
    		String lName = request.getParameter("lname");
    		String pw = request.getParameter("password");
    		
    		newUser.setFirstName(fName);
    		newUser.setLastName(lName);
    		newUser.setPassword(pw);

    		//save user to db, get new id
    		newUser = adminDao.addUser(newUser);
    		request.setAttribute("newuser", newUser);
    		ArrayList<User> users = adminDao.getAllUsers();
    		request.setAttribute("users", users);
    		
    		forward = ADMIN_HOME;
    		rd = request.getRequestDispatcher(forward);
    	} else if(action.equalsIgnoreCase("Delete")) {
    		System.out.println("deleting user...");
    		int userId = Integer.parseInt(request.getParameter("id"));
    		boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin")); 
    		User user = new User();
    		user.setUserId(userId);
    		user.setAdmin(isAdmin);
    		adminDao.deleteUser(user);
    		
    		ArrayList<User> users = adminDao.getAllUsers();
    		request.setAttribute("users", users);
    		
    		forward = ADMIN_HOME;
    		rd = request.getRequestDispatcher(forward);
    	}
    	
    	rd.forward(request, response);
	}

}
