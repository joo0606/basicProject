package jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class StudentDAO {

	public List<studentDTO> selectStudent() throws Exception   {
		// 1. JDBC 드라이버 로딩 
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2. 로딩된 드라이버를 통해  DBMS 접속 준비
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "yhj90", "java");
		// 3. Query 문장을 전송할 객체 ( sql developer에서 쿼리 작성 화면 보여주는겉과 같음) 생성 
		Statement statement = connection.createStatement();
		// 4. SQL문장을 Statement 객체를 이용하여 실행
		ResultSet resultSet = statement.executeQuery("select * from student");		// 세미콜론(;)을 반드시 제거 
		List<studentDTO> list = new ArrayList<>();
		while (resultSet.next()) {   // 다음줄이 있으면 true,  반복문 실행
			String id = resultSet.getString("id");
			String name = resultSet.getString("name");
			String email  = resultSet.getString("email");
			String mobileNumber  = resultSet.getString("mobile_number");
			list.add(new studentDTO(id, name, email, mobileNumber));
		}
		// 6. 사용된 자원 반납
			resultSet.close();
			statement.close();
			connection.close();
			return list;
	}
	
	public int insertStudents(studentDTO dto) throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "yhj90", "java");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO STUDENT (");
		builder.append("			ID,");
		builder.append("			NAME,");
		builder.append("			EMAIL,");
		builder.append("			MOBILE_NUMBER");
		builder.append(") VALUES (");
		builder.append("          '"+ dto.getId() + "',");
		builder.append("          '"+ dto.getName() + "',");
		builder.append("          '"+ dto.getEmail() + "',");
		builder.append("          '"+ dto.getMobileNumber() + "'");
		builder.append(")");
		int executeUpdate = statement.executeUpdate(builder.toString());
		statement.close();
		connection.close();
		return executeUpdate;
		
	}
	public int updateStudent(studentDTO dto) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "yhj90", "java");
		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE STUDENT ");
		builder.append("  		SET ");
		builder.append("  			email = ?,");
		builder.append("  			mobile_number = ?");
		builder.append(" WHERE ");
		builder.append("  			id =?");
		// 미리 sql 문장을 준비하는 객체 사용
		PreparedStatement statement = connection.prepareStatement(builder.toString());
		statement.setString(1, dto.getEmail());
		statement.setString(2, dto.getMobileNumber());
		statement.setString(3, dto.getId());
		int executeUpdate = statement.executeUpdate();
		statement.close();
		connection.close();
		return executeUpdate;
		
	}
	
	public int deleteStudent(String id) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "yhj90", "java");
		PreparedStatement statement = connection.prepareStatement("DELETE FROM STUDENT WHERE ID =? ");
		statement.setString(1, id);
		int executeUpdate = statement.executeUpdate();
		statement.close();
		connection.close();
		return executeUpdate;
	}
}
