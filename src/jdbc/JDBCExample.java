package jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCExample {

	public static void main(String[] args)	throws Exception {
		// 1. JDBC 드라이버 로딩 
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2. 로딩된 드라이버를 통해  DBMS 접속 준비
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "yhj90", "java");
		// 3. Query 문장을 전송할 객체 ( sql developer에서 쿼리 작성 화면 보여주는겉과 같음) 생성 
		Statement statement = connection.createStatement();
		// 4. SQL문장을 Statement 객체를 이용하여 실행
		ResultSet resultSet = statement.executeQuery("select * from student");		// 세미콜론(;)을 반드시 제거 
		while (resultSet.next()) {   // 다음줄이 있으면 true,  반복문 실행
			String id = resultSet.getString("id");
			String name = resultSet.getString("name");
			String email  = resultSet.getString("email");
			String mobile_number  = resultSet.getString("mobile_number");
			System.out.println(String.format("%s\t%s\t%s\t%s", id, name, email, mobile_number));
		}
		// 6. 사용된 자원 반납
			resultSet.close();
			statement.close();
			connection.close();
	}

}
