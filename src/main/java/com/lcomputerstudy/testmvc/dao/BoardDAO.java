package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;
import com.lcomputerstudy.testmvc.vo.Board;

public class BoardDAO {

	private static BoardDAO dao = null;
	
	private BoardDAO() {
		
	}
	
	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	public ArrayList<Board> getBoards(Pagination pagination) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = null;
		int pageNum = pagination.getPageNum();
		int perPage = pagination.getPerPage();
		
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT b.b_idx, b.b_title, b.b_date, u.u_name as b_writer, b.b_views "
					+ "FROM board b "
					+ "INNER JOIN user u ON b.u_idx = u.u_idx "
					+ "ORDER BY b.b_date DESC "
					+ "LIMIT ?, ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pageNum);
			pstmt.setInt(2, perPage);
			rs = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rs.next()) {
				Board board = new Board();
				board.setB_idx(rs.getInt("b_idx"));
				board.setB_title(rs.getString("b_title"));
				board.setB_date(rs.getString("b_date"));
				board.setB_writer(rs.getString("b_writer"));
				board.setB_views(rs.getInt("b_views"));
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public ArrayList<Board> getBoardsBySearch(Pagination pagination) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = null;
		String gso = pagination.getSearchOption(); 
		String gsk = pagination.getSearchKeyword();
		
		try {
			conn = DBConnection.getConnection();
			StringBuilder query = new StringBuilder("SELECT b.b_idx, b.b_title, b.b_date, u.u_name as b_writer, b.b_views "
					+ "FROM board b "
					+ "INNER JOIN user u ON b.u_idx = u.u_idx "
					+ "WHERE 1=1");
			
			if ("b_title".equals(gso)) {
				query.append(" AND b.b_title LIKE ?");
			} else if ("b_title_content".equals(gso)) {
				query.append(" AND b.b_title LIKE ? OR b.b_content LIKE ?");
			} else if ("b_writer".equals(gso)) {
				query.append(" AND u.u_name LIKE ?");
			}
			query.append(" ORDER BY b.b_date DESC");
			query.append(" LIMIT ?,?");
			pstmt = conn.prepareStatement(query.toString());
			
			if ("b_title".equals(gso)) {
				pstmt.setString(1, "%"+gsk+"%");
				pstmt.setInt(2, pagination.getPageNum());
				pstmt.setInt(3, pagination.getPerPage());
			} else if ("b_title_content".equals(gso)) {
				pstmt.setString(1, "%"+gsk+"%");
				pstmt.setString(2, "%"+gsk+"%");
				pstmt.setInt(3, pagination.getPageNum());
				pstmt.setInt(4, pagination.getPerPage());
			} else if ("b_writer".equals(gso)) {
				pstmt.setString(1, "%"+gsk+"%");
				pstmt.setInt(2, pagination.getPageNum());
				pstmt.setInt(3, pagination.getPerPage());
			}
			
			rs = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rs.next()) {
				Board board = new Board();
				board.setB_idx(rs.getInt("b_idx"));
				board.setB_title(rs.getString("b_title"));
				board.setB_date(rs.getString("b_date"));
				board.setB_writer(rs.getString("b_writer"));
				board.setB_views(rs.getInt("b_views"));
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public int getBoardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) COUNT FROM board";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("count");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int getBoardCountBySearch(Pagination pagination) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String gso = pagination.getSearchOption(); 
		String gsk = pagination.getSearchKeyword();
		
		try {
			conn = DBConnection.getConnection();
			StringBuilder query = new StringBuilder
					("SELECT COUNT(*) count "
					+ "FROM board b "
					+ "INNER JOIN user u ON b.u_idx=u.u_idx "
					+ "WHERE 1=1");
			if ("b_title".equals(gso)) {
				query.append(" AND b.b_title LIKE ?");
			} else if ("b_title_content".equals(gso)) {
				query.append(" AND b.b_title LIKE ? OR b.b_content LIKE ?");
			} else if ("b_writer".equals(gso)) {
				query.append(" AND u.u_name LIKE ?");
			}
			
			pstmt = conn.prepareStatement(query.toString());
			
			if ("b_title".equals(gso)) {
				pstmt.setString(1, "%"+gsk+"%");
			} else if ("b_title_content".equals(gso)) {
				pstmt.setString(1, "%"+gsk+"%");
				pstmt.setString(2, "%"+gsk+"%");
			} else if ("b_writer".equals(gso)) {
				pstmt.setString(1, "%"+gsk+"%");
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("count");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public void insertBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "INSERT INTO board (b_title, b_content, b_date, u_idx) VALUES(?,?,NOW(),?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setInt(3, board.getU_idx());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void countViews(int b_idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "UPDATE board SET b_views = b_views + 1 WHERE b_idx=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b_idx);
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Board detailBoard(int b_idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT b.b_idx, b.b_title, b.b_content, b.b_date, b.b_views, u.u_name as b_writer "
					+ "FROM board b "
					+ "INNER JOIN user u ON b.u_idx = u.u_idx "
					+ "WHERE b.b_idx=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b_idx);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new Board();
				board.setB_idx(rs.getInt("b_idx"));
				board.setB_title(rs.getString("b_title"));
				board.setB_views(rs.getInt("b_views"));
				board.setB_content(rs.getString("b_content"));
				board.setB_date(rs.getString("b_date"));
				board.setB_writer(rs.getString("b_writer"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return board;
	}
	
	public void deleteBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "DELETE FROM board WHERE b_idx=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board.getB_idx());
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Board editBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT * FROM board WHERE b_idx=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board.getB_idx());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board.setB_idx(rs.getInt("b_idx"));
				board.setB_title(rs.getString("b_title"));
				board.setB_content(rs.getString("b_content"));
				board.setB_date(rs.getString("b_date"));
				board.setU_idx(rs.getInt("u_idx"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return board;
	}
	
	public void updateBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "UPDATE board SET b_title=?, b_content=?, b_date=NOW() WHERE b_idx=?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setInt(3, board.getB_idx());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
