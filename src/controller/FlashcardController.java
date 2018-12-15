package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FlashcardDao;
import model.Card;
import model.CardSet;
import model.User;

@WebServlet("/FlashcardController")
public class FlashcardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST_SETS = "/cardsets.jsp";
	private static String VIEW_SET = "/set.jsp";
	private static String EDIT_CARD = "/editcard.jsp";
	private static String DELETE_SET = "/confirmdeleteset.jsp";
	private static String DELETE_CARD = "/confirmdeletecard.jsp";
	private static String ADD_CARD = "/addcard.jsp";
	private static String ADD_SET = "/addset.jsp";
	private static String START_QUIZ = "/quiz.jsp";
	
	FlashcardDao fcd = new FlashcardDao();

	public FlashcardController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String action = request.getParameter("action");
		String forward;
		
		if (action.equalsIgnoreCase("listsets")) { // get all sets of flashcard sets for specific user
			ArrayList<CardSet> csList = fcd.getAllCardSets(user.getUserId());
			for (CardSet cs : csList) {
				cs.setCards(fcd.getCards(cs.getSetId()));
			}
			
			request.setAttribute("csList", csList);

			forward = LIST_SETS;
			rd = request.getRequestDispatcher(forward);
		} else if (action.equalsIgnoreCase("viewset")) {				//view cards in a set
			int setId = Integer.parseInt(request.getParameter("setid"));
			ArrayList<Card> cards = fcd.getCards(setId);
			String setName = fcd.getSetName(setId);

			if(session.getAttribute("setid") != null) {
				session.removeAttribute("setid");
			}
			
			if(session.getAttribute("cards") != null) {
				session.removeAttribute("cards");
			}
			
			request.setAttribute("setname", setName);
			request.setAttribute("setid", setId);
			request.setAttribute("cards", cards);

			forward = VIEW_SET;
			rd = request.getRequestDispatcher(forward);
		} else if (action.equalsIgnoreCase("editcard")) { 				// send to editcard page
			int cardId = Integer.parseInt(request.getParameter("cardid"));
			Card c = fcd.getCard(cardId);
						
			session.setAttribute("card", c);

			forward = EDIT_CARD;
			rd = request.getRequestDispatcher(forward);
		} else if (action.equalsIgnoreCase("savecardedit")) { 			// after saving card changes...
			int cardId = Integer.parseInt(request.getParameter("cardid"));
			String newFrontTxt = request.getParameter("fronttext");
			String newBackTxt = request.getParameter("backtext");

			fcd.editCard(cardId, newFrontTxt, newBackTxt);

			forward = VIEW_SET;
			rd = request.getRequestDispatcher(forward);
		} else if(action.equalsIgnoreCase("confirmdeleteset")) {		//delete set, return to set list
			int setId = (Integer)session.getAttribute("setid");
			
			fcd.deleteSet(setId);
			if (session.getAttribute("setid") != null | !session.getAttribute("setid").equals("")) {
				session.removeAttribute("setid");
			}
			
			ArrayList<CardSet> csList = fcd.getAllCardSets(user.getUserId());
			for (CardSet cs : csList) {
				cs.setCards(fcd.getCards(cs.getSetId()));
			}

			request.setAttribute("csList", csList);

			forward = LIST_SETS;
			rd = request.getRequestDispatcher(forward);
		} else if(action.equalsIgnoreCase("deleteset")) {			//confirm set deletion first
			int setId = Integer.parseInt(request.getParameter("setid"));
			String setName = fcd.getSetName(setId);
			
			request.setAttribute("setname", setName);
			session.setAttribute("setid", setId);
			
			forward = DELETE_SET;
			rd = request.getRequestDispatcher(forward);
		} else if(action.equalsIgnoreCase("deletecard")) {			//confirm card deletion first
			int cardId = Integer.parseInt(request.getParameter("cardid"));
			Card c = fcd.getCard(cardId);
			session.setAttribute("card", c);
			
			forward = DELETE_CARD;
			rd = request.getRequestDispatcher(forward);
		} else if(action.equalsIgnoreCase("confirmdeletecard")) {	//delete card
			Card c = (Card)session.getAttribute("card");
			int setId = c.getSetId();
			
			fcd.deleteCard(c.getCardId());
			
			if (session.getAttribute("card") != null) {
				session.removeAttribute("setid");
			}
			
			ArrayList<Card> cards = fcd.getCards(setId);
			String setName = fcd.getSetName(setId);

			request.setAttribute("setname", setName);
			request.setAttribute("cards", cards);
			request.setAttribute("setid", setId);

			forward = VIEW_SET;
			rd = request.getRequestDispatcher(forward);
		} else if(action.equalsIgnoreCase("addcard")) {				//redirect to addcard page 
			int setId = Integer.parseInt(request.getParameter("setid"));

			session.setAttribute("setid", setId);
			
			forward = ADD_CARD;
			rd = request.getRequestDispatcher(forward);
		} else if(action.equalsIgnoreCase("newset")) {				//redirect to new set form
			
			forward = ADD_SET;
			rd = request.getRequestDispatcher(forward);
		} else if(action.equalsIgnoreCase("saveset")) {
			String setName = request.getParameter("setname");
			
			fcd.addSet(setName, user.getUserId());
			
			ArrayList<CardSet> csList = fcd.getAllCardSets(user.getUserId());
			for (CardSet cs : csList) {
				cs.setCards(fcd.getCards(cs.getSetId()));
			}
			
			request.setAttribute("csList", csList);

			forward = LIST_SETS;
			rd = request.getRequestDispatcher(forward);
		} else if(action.equalsIgnoreCase("startquiz")) {			//start flashcard quiz mode
			int setId = Integer.parseInt(request.getParameter("setid"));
			session.setAttribute("setid", setId);
			
			//load all cards into an arraylist from fcd, shuffle, get first card
			ArrayList<Card> cards = fcd.getCards(setId);
			Collections.shuffle(cards);
			session.setAttribute("cards", cards);
			int itr = 0;
			session.setAttribute("itr", itr);
			Card card = cards.get(itr);
			request.setAttribute("card", card);
			String setName = fcd.getSetName(setId);
			session.setAttribute("setname", setName);
			
			forward = START_QUIZ;
			rd = request.getRequestDispatcher(forward);
		} else if(action.equalsIgnoreCase("quiznextcard")) {
			ArrayList<Card> cards = (ArrayList<Card>)session.getAttribute("cards");
			int itr = (Integer)session.getAttribute("itr");
			Card card = (Card)session.getAttribute("card");
			
			if(itr < cards.size()-1) {
				itr++;
				card = cards.get(itr);
				request.setAttribute("card", card);
				session.setAttribute("itr", itr);
				
				forward = START_QUIZ;
				rd = request.getRequestDispatcher(forward);
			} else {
				session.removeAttribute("itr");
				session.removeAttribute("setname");
				
				int setId = (Integer)session.getAttribute("setid");
				cards = fcd.getCards(setId);
				String setName = fcd.getSetName(setId);

				if(session.getAttribute("setid") != null) {
					session.removeAttribute("setid");
				}
				
				if(session.getAttribute("cards") != null) {
					session.removeAttribute("cards");
				}
				
				request.setAttribute("setname", setName);
				request.setAttribute("setid", setId);
				request.setAttribute("cards", cards);
				
				forward = VIEW_SET;
				rd = request.getRequestDispatcher(forward);
			}
			
			
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//edit and saving cards handeled here. POST for longer string support.
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		String forward = "";
		
		String edit = request.getParameter("editcard");
		String add = request.getParameter("addcard");
		int setId = 0;
		
		if (edit != null) {
			//save card edit and redirect to set.jsp			
			Card c = (Card)session.getAttribute("card");
			
			int cardId = c.getCardId();
			String newFrontTxt = request.getParameter("front");
			String newBackTxt = request.getParameter("back");
			
			fcd.editCard(cardId, newFrontTxt, newBackTxt);
			setId = c.getSetId();
			
			if (session.getAttribute("card") != null | !session.getAttribute("card").equals("")) {
				session.removeAttribute("card");
			}
		} else if (add != null) {
			String frontTxt = request.getParameter("front");
			String backTxt = request.getParameter("back");
			setId = (Integer)session.getAttribute("setid");
			
			fcd.addCard(setId, frontTxt, backTxt);
			
			if(session.getAttribute("setid") != null) {
				session.removeAttribute("setid");
			}
		}
		
		ArrayList<Card> cards = fcd.getCards(setId);
		String setName = fcd.getSetName(setId);
		
		request.setAttribute("setname", setName);
		request.setAttribute("cards", cards);
		request.setAttribute("setid", setId);
		
		forward = VIEW_SET;
		rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
	}

}
