package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Card;
import model.CardSet;
import model.User;
import util.DBUtil;

public class FlashcardDao {
	
	public void addSet(String setName, int userId) {
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO cardset (userid, setname) values (?,?)");
			pStmt.setInt(1, userId);
			pStmt.setString(2, setName);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	
	public void addCard(int setId, String front, String back) {
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO card (fronttext, backtext, setid) values (?,?,?)");
			pStmt.setString(1, front);
			pStmt.setString(2, back);
			pStmt.setInt(3, setId);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	
	public void deleteCard(int cardId) {
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("DELETE FROM card WHERE cardid=?");
			pStmt.setInt(1, cardId);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	
	public void deleteSet(int setId) {
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("DELETE FROM cardset WHERE setid=?");
			pStmt.setInt(1, setId);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public void editCard(int cardId, String front, String back) {
		Connection conn = null;

		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("UPDATE project.card SET fronttext=?, backtext=? WHERE cardid=?");
			pStmt.setString(1, front);
			pStmt.setString(2, back);
			pStmt.setInt(3, cardId);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBUtil.closeConnection(conn);
		}

	}

	public Card getCard(int cardId) {
		Connection conn = null;
		Card c = new Card();

		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM card WHERE cardid=?");
			pStmt.setInt(1, cardId);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				c.setCardId(cardId);
				c.setSetId(rs.getInt("setid"));
				c.setFrontText(rs.getString("fronttext"));
				c.setBackText(rs.getString("backtext"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DBUtil.closeConnection(conn);
		}

		return c;
	}

	public String getSetName(int setId) {
		Connection conn = null;
		String setName = "";

		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT setname FROM cardset WHERE setid=?");
			pStmt.setInt(1, setId);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				setName = rs.getString("setName");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DBUtil.closeConnection(conn);
		}

		return setName;
	}

	public ArrayList<Card> getCards(int setId) {
		Connection conn = null;
		ArrayList<Card> cards = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM card WHERE setid=?");
			pStmt.setInt(1, setId);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Card c = new Card();
				c.setCardId(rs.getInt("cardid"));
				c.setSetId(setId);
				c.setFrontText(rs.getString("fronttext"));
				c.setBackText(rs.getString("backtext"));

				cards.add(c);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DBUtil.closeConnection(conn);
		}

		return cards;
	}

	public ArrayList<CardSet> getAllCardSets(int userId) {
		Connection conn = null;
		ArrayList<CardSet> sets = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM cardset WHERE userid=?");
			PreparedStatement pStmt2 = conn.prepareStatement("SELECT DATE(datecreated) as datecreated FROM cardset WHERE userid=?");
			pStmt.setInt(1, userId);
			pStmt2.setInt(1, userId);

			ResultSet rs = pStmt.executeQuery();
			ResultSet rs2 = pStmt2.executeQuery();

			while (rs.next() && rs2.next()) {
				CardSet cs = new CardSet();
				cs.setSetId(rs.getInt("setid"));
				cs.setUserId(rs.getInt("userid"));
				cs.setSetName(rs.getString("setname"));
				cs.setDate(rs2.getString("datecreated"));
				sets.add(cs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DBUtil.closeConnection(conn);
		}

		return sets;
	}
}
