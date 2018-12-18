package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDAO dao;

    public LoginController() {
        super();
        dao = new UserDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	User u = (User)session.getAttribute("user");
    	String action = request.getParameter("action");
    	
    	if(action.equalsIgnoreCase("logout") && session != null) {
    		session.invalidate();
    		response.sendRedirect("index.html");
    	}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		user.setUserId(Integer.parseInt(request.getParameter("username")));
		user.setPassword(request.getParameter("password"));
		
		user  = dao.checkUser(user);
		
		if (!user.isValid()) {
			response.sendRedirect("index.html");
		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			response.sendRedirect("home.jsp");
		}
		
	}

}
