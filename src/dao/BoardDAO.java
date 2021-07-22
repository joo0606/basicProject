package dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.BoardDTO;


public class BoardDAO {
	private BoardDAO(){}
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance(){
		if(instance == null){
			instance = new BoardDAO();
		}
		return instance;
	}
	
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	
	   //게시판조회(공지, 자유, Q&A 선택가능)
		public List<BoardDTO> selectBoard(BoardDTO dto) throws Exception {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
			Statement statement = connection.createStatement();
			String CATEGORY = "";	
		
			ResultSet resultSet = statement.executeQuery("SELECT BOARD_ID,"
					+ "        TITLE,"
					+ "        CREATIONDATE,"
					+ "        MEMBER_ID "
					+ "FROM HONEY.BOARD "
					+ "WHERE CATEGORY LIKE '"+dto.getCategory()+"' ");
			List<BoardDTO> list = new ArrayList<>();
			while (resultSet.next()) {   
				int boardId = resultSet.getInt("BOARD_ID");
				String title = resultSet.getString("TITLE");
				String creationdate  = resultSet.getString("CREATIONDATE");
				String memberId  = resultSet.getString("MEMBER_ID");
				list.add(new BoardDTO(boardId, title, creationdate, memberId));
			}
			resultSet.close();
			statement.close();
			connection.close();
			return list;
		}

		
		   //게시판조회(공지)
			public List<BoardDTO> allselectNotice(BoardDTO boardDTO) throws Exception {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
				Statement statement = connection.createStatement();

				ResultSet resultSet = statement.executeQuery("SELECT BOARD_ID,"
						+ "        TITLE,"
						+ "        CREATIONDATE,"
						+ "        MEMBER_ID "
						+ "FROM HONEY.BOARD "
						+ "WHERE CATEGORY LIKE '"+"공지사항"+"' ");
				List<BoardDTO> list = new ArrayList<>();
				while (resultSet.next()) {   
					int boardId = resultSet.getInt("BOARD_ID");
					String title = resultSet.getString("TITLE");
					String creationdate  = resultSet.getString("CREATIONDATE");
					String memberId  = resultSet.getString("MEMBER_ID");
					list.add(new BoardDTO(boardId, title, creationdate, memberId));
				}
				resultSet.close();
				statement.close();
				connection.close();
				return list;
			}
			
			   //게시판조회(자유)
				public List<BoardDTO> allselectFree(BoardDTO boardDTO) throws Exception {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
					Statement statement = connection.createStatement();

					ResultSet resultSet = statement.executeQuery("SELECT BOARD_ID,"
							+ "        TITLE,"
							+ "        CREATIONDATE,"
							+ "        MEMBER_ID "
							+ "FROM HONEY.BOARD "
							+ "WHERE CATEGORY LIKE '"+"자유게시판"+"' ");
					List<BoardDTO> list = new ArrayList<>();
					while (resultSet.next()) {   
						int boardId = resultSet.getInt("BOARD_ID");
						String title = resultSet.getString("TITLE");
						String creationdate  = resultSet.getString("CREATIONDATE");
						String memberId  = resultSet.getString("MEMBER_ID");
						list.add(new BoardDTO(boardId, title, creationdate, memberId));
					}
					resultSet.close();
					statement.close();
					connection.close();
					return list;
				}
				
				   //게시판조회(QnA)
				public List<BoardDTO> allselectQnA(BoardDTO boardDTO) throws Exception {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
					Statement statement = connection.createStatement();

					ResultSet resultSet = statement.executeQuery("SELECT BOARD_ID,"
							+ "        TITLE,"
							+ "        CREATIONDATE,"
							+ "        MEMBER_ID "
							+ "FROM HONEY.BOARD "
							+ "WHERE CATEGORY LIKE 'QnA' ");
					List<BoardDTO> list = new ArrayList<>();
					while (resultSet.next()) {   
						int boardId = resultSet.getInt("BOARD_ID");
						String title = resultSet.getString("TITLE");
						String creationdate  = resultSet.getString("CREATIONDATE");
						String memberId  = resultSet.getString("MEMBER_ID");
						list.add(new BoardDTO(boardId, title, creationdate, memberId));
					}
					resultSet.close();
					statement.close();
					connection.close();
					return list;
				}
				
			   //게시판 선택조회
				public ArrayList<HashMap<String, Object>> select(int boardId) {
					String query = String.format("SELECT BOARD_ID,"
							+ "        TITLE, "
							+ "        CONTENT, "
							+ "        CREATIONDATE, "
							+ "        MEMBER_ID "
							+ "FROM HONEY.BOARD "
							+ "WHERE BOARD_ID LIKE '%d'", boardId);
					return jdbc.selectList(query);
				}
		   
				
				
		   //글쓰기
		   public int insertBoard(BoardDTO dto) {
				String query = String.format("INSERT INTO BOARD (BOARD_ID, TITLE, CONTENT, CREATIONDATE, CATEGORY, MEMBER_ID) "
											+ "VALUES ('%s','%s','%s','%s','%s','%s')"
						,dto.getBoardId(), dto.getTitle(),dto.getContent(),dto.getCreationDate(), dto.getCategory(),dto.getMemberId());
				return jdbc.Update(query);
			}
		   
		   //글쓰기 (공지)
		   public int insertNoticeBoard(BoardDTO dto) throws Exception{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
				Statement statement = connection.createStatement();
				StringBuilder builder = new StringBuilder();
				builder.append("INSERT INTO BOARD (");
				builder.append("			BOARD_ID,");
				builder.append("			TITLE,");
				builder.append("			CONTENT,");
				builder.append("			CREATIONDATE,");
				builder.append("			CATEGORY,");
				builder.append("			MEMBER_ID");
				builder.append(") VALUES (");
				builder.append("            BOARD_SEQ.NEXTVAL,");
				builder.append("          '"+ dto.getTitle() + "',");
				builder.append("          '"+ dto.getContent() + "',");
				builder.append("          	SYSDATE,");
				builder.append("            '공지사항',");
				builder.append("          '"+ dto.getMemberId() + "'");
				builder.append(")");
				int executeUpdate = statement.executeUpdate(builder.toString());
				statement.close();
				connection.close();
				return executeUpdate;
				

		   }
		   
		   //글쓰기(자유)
		   public int insertFreeBoard(BoardDTO dto) throws Exception{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
				Statement statement = connection.createStatement();
				StringBuilder builder = new StringBuilder();
				builder.append("INSERT INTO BOARD (");
				builder.append("			BOARD_ID,");
				builder.append("			TITLE,");
				builder.append("			CONTENT,");
				builder.append("			CREATIONDATE,");
				builder.append("			CATEGORY,");
				builder.append("			MEMBER_ID");
				builder.append(") VALUES (");
				builder.append("            BOARD_SEQ.NEXTVAL,");
				builder.append("          '"+ dto.getTitle() + "',");
				builder.append("          '"+ dto.getContent() + "',");
				builder.append("          	SYSDATE,");
				builder.append("            '자유게시판',");
				builder.append("          '"+ dto.getMemberId() + "'");
				builder.append(")");
				int executeUpdate = statement.executeUpdate(builder.toString());
				statement.close();
				connection.close();
				return executeUpdate;
		   }
		   

		   
		   //글쓰기(QnA)
		   public int insertQnABoard(BoardDTO dto) throws Exception{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
				Statement statement = connection.createStatement();
				StringBuilder builder = new StringBuilder();
				builder.append("INSERT INTO BOARD (");
				builder.append("			BOARD_ID,");
				builder.append("			TITLE,");
				builder.append("			CONTENT,");
				builder.append("			CREATIONDATE,");
				builder.append("			CATEGORY,");
				builder.append("			MEMBER_ID");
				builder.append(") VALUES (");
				builder.append("            BOARD_SEQ.NEXTVAL,");
				builder.append("          '"+ dto.getTitle() + "',");
				builder.append("          '"+ dto.getContent() + "',");
				builder.append("          	SYSDATE,");
				builder.append("            'QnA',");
				builder.append("          '"+ dto.getMemberId() + "'");
				builder.append(")");
				int executeUpdate = statement.executeUpdate(builder.toString());
				statement.close();
				connection.close();
				return executeUpdate;
		   }
		   
	
		   
		   //게시글 수정
		   public int updateBoard(BoardDTO dto) throws Exception {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
				StringBuilder builder = new StringBuilder();
				builder.append(" UPDATE BOARD ");
				builder.append("  		SET ");
				builder.append("  			TITLE = ?,");
				builder.append("  			CONTENT = ?");
				builder.append(" WHERE ");
				builder.append("  			BOARD_ID =?");
				PreparedStatement statement = connection.prepareStatement(builder.toString());
				statement.setString(1, dto.getTitle());
				statement.setString(2, dto.getContent());
				statement.setInt(3, dto.getBoardId());
				int executeUpdate = statement.executeUpdate();
				statement.close();
				connection.close();
				return executeUpdate;
				
			}
		   
		   
		   
		   //게시글 번호를 이용한 글 지우기
			public int deleteBoard(String BoardId) throws Exception {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
				PreparedStatement statement = connection.prepareStatement("DELETE FROM BOARD WHERE BOARD_ID = ? ");
				statement.setString(1, BoardId);
				int executeUpdate = statement.executeUpdate();
				statement.close();
				connection.close();
				return executeUpdate;
			}



	   

}