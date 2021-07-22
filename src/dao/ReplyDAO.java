package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.BoardDAO;
import vo.BoardDTO;
import vo.ReplyDTO;
import util.JDBCUtil;

public class ReplyDAO {
	private void ReplyDAO() {
	}
	private static ReplyDAO instance = new ReplyDAO();
	public static ReplyDAO getInstance() {
		if (instance == null) {
			instance = new ReplyDAO();
		}
		return instance;
	}

	// 댓글 조회
	public ArrayList<HashMap<String, Object>> replyselect(int boardId) {
		String query = String.format(
				"SELECT BOARD_ID," + "        REPLY_ID, " + "        REPLY_CONTENT, " + "        REPLY_CREATIONDATE, "
						+ "        MEMBER_ID " + "FROM HONEY.REPLY " + "WHERE BOARD_ID LIKE '%d'",
				boardId);
		return jdbc.selectList(query);
	}

	ReplyDAO() {
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	// 댓글쓰기
	public int insertReply(ReplyDTO replyDTO) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO REPLY (");
		builder.append("			REPLY_ID,");
		builder.append("			REPLY_CONTENT,");
		builder.append("			REPLY_CREATIONDATE,");
		builder.append("			MEMBER_ID,");
		builder.append("			BOARD_ID");
		builder.append(") VALUES (");
		builder.append("          R_SEQ.NEXTVAL,");
		builder.append("          '" + replyDTO.getReplyContent() + "',");
		builder.append("          SYSDATE,");
		builder.append("          '" + replyDTO.getMemberId() + "',");
		builder.append("          '" + replyDTO.getBoardId()+ "'");
		builder.append(")");
	//	System.out.println(builder.toString());
		int executeUpdate = statement.executeUpdate(builder.toString());
		statement.close();
		connection.close();
		return executeUpdate;
	}

	// 댓글 수정
	public int updateReply(ReplyDTO replyDTO) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE REPLY ");
		builder.append("  		SET ");
		builder.append("  			REPLY_CONTENT = ?");
		builder.append(" WHERE ");
		builder.append("  			REPLY_ID =?");
		PreparedStatement statement = connection.prepareStatement(builder.toString());
		statement.setString(1, replyDTO.getReplyContent());
		statement.setString(2, replyDTO.getReplyId());
		int executeUpdate = statement.executeUpdate();
		statement.close();
		connection.close();
		return executeUpdate;

	}

	// 댓글 지우기
	public static int deleteReply(String ReplyId) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
		PreparedStatement statement = connection.prepareStatement("DELETE FROM REPLY WHERE REPLY_ID = ? ");
		statement.setString(1, ReplyId);
		int executeUpdate = statement.executeUpdate();
		statement.close();
		connection.close();
		return executeUpdate;
	}

}
