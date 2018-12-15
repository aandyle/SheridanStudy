package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DiscussionDAO;
import model.Comment;
import model.Discussion;
import model.User;

@WebServlet("/DiscussionController")
public class DiscussionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST = "/DiscussionsList.jsp";
	private static String DISCUSSION = "/Discussion.jsp";
	private static String SEARCH = "/Search.jsp";
	private DiscussionDAO dao;
       
    public DiscussionController() {
        super();
        dao = new DiscussionDAO(); 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		RequestDispatcher rd;
		if (action.equals("getDiscussion")) {
			int descussionId = Integer.parseInt(request.getParameter("discussionId"));
			String pNumber = request.getParameter("pageNumber");
			int pageNumber;
			if (pNumber == null) {
				pageNumber = 1;
			}else{
				pageNumber = Integer.parseInt(pNumber);
			}
			int numberOfPosts = dao.getNumberOfPost(descussionId);
			Discussion discussion = dao.getDiscussion(descussionId);
			ArrayList<Comment> comment = dao.getComments(descussionId, pageNumber);
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("discussion", discussion);
			request.setAttribute("comment", comment);
			request.setAttribute("numberOfPages", Math.ceil((double)numberOfPosts/5));
			rd = request.getRequestDispatcher(DISCUSSION);
			rd.forward(request, response);
		}
		else if(action.equals("ListDiscussion")){
			
			String pNumber = request.getParameter("pageNumber");
			int pageNumber;
			if (pNumber == null) {
				pageNumber = 1;
			}else{
				pageNumber = Integer.parseInt(pNumber);
			}
			
			ArrayList<Discussion> discussionsList = dao.getDiscussionsList(pageNumber);
			int numberOfDiscussions = dao.getNumberOfDiscussions();
			
			request.setAttribute("DiscussionsList", discussionsList);
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("numberOfPages", Math.ceil((double)numberOfDiscussions/5));
			
			rd = request.getRequestDispatcher(LIST);
			rd.forward(request, response);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("CreateNewDiscussion")) {
		
			String title = request.getParameter("title");
			String comment = request.getParameter("comment");
			
			Date date = new Date();  
		    SimpleDateFormat formatDateAndTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		    String currentDate = formatDateAndTime.format(date);
			
		    HttpSession session = request.getSession();
		    User newUser = (User)session.getAttribute("user");
			Discussion newDiscussion = new Discussion(title);
			
			dao.newDiscussion(newUser, newDiscussion);
			dao.setDiscussionId(newDiscussion, newDiscussion.getTitle());
			
			Comment newComment = new Comment(newDiscussion.getDiscussionId(), currentDate, comment);
			dao.newComment(newUser.getUserId(), newDiscussion.getDiscussionId(), newComment);
			response.sendRedirect("DiscussionController?action=getDiscussion&discussionId="+newDiscussion.getDiscussionId());
		
		}else if(action.equals("PostReply")){
			
			int descussionId = Integer.parseInt(request.getParameter("discussionId"));
			String comment = request.getParameter("comment");
			
			HttpSession session = request.getSession();
		    User user = (User)session.getAttribute("user");
			Date date = new Date();  
		    SimpleDateFormat formatDateAndTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		    String currentDate = formatDateAndTime.format(date);
		    
		    Comment newComment = new Comment(descussionId, currentDate, comment);
		    
		    dao.newComment(user.getUserId(), descussionId, newComment);
		    
		    response.sendRedirect("DiscussionController?action=getDiscussion&discussionId="+descussionId);
			
		} else if(action.equals("DeleteDiscussion")){
			
			int descussionId = Integer.parseInt(request.getParameter("discussionId"));
		    
		    dao.deleteDiscussion(descussionId);
		    
		    response.sendRedirect("DiscussionController?action=ListDiscussion");
			
		} else if(action.equals("DeleteComment")){
		
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		
		int discussionId = Integer.parseInt(request.getParameter("discussionId"));
	    
	    dao.deleteComment(commentId);
	    
	    response.sendRedirect("DiscussionController?action=getDiscussion&discussionId="+discussionId);
		
		} else if(action.equals("Search")){
			
			String title = request.getParameter("search");
			String pNumber = request.getParameter("pageNumber");
			int pageNumber;
			if (pNumber == null) {
				pageNumber = 1;
			}else{
				pageNumber = Integer.parseInt(pNumber);
			}
			
			ArrayList<Discussion> discussionsList = dao.searchDiscussion(title, pageNumber);;
			int numberOfDiscussions = dao.getNumberOfDiscussions(title);
			
			request.setAttribute("DiscussionsList", discussionsList);
			request.setAttribute("DiscussionsList", discussionsList);
			request.setAttribute("title", title);
			request.setAttribute("numberOfPages", Math.ceil((double)numberOfDiscussions/5));
		    
		    RequestDispatcher rd = request.getRequestDispatcher(SEARCH);
			rd.forward(request, response);
			
		}
		
	}

}
