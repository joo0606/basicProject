package jdbc;


public class StringBuilderExample {
	
	// 항상 쿼리는 대문자 

	public static void main(String[] args) {
		String sql = " SELECT ";
		sql += "		MEM_ID,";
		sql += "		MEM_NAME,";
		sql += "		MEM_MAIL,";
		sql += "		MEM_HP,";
		sql += "		MEM_ADD1";
		sql += "FROM";
		sql += "		MEMBER";
		sql += "WHERE";
		sql += "		MEM_ADD1 LIKE '대전%'";

		StringBuilder builder = new StringBuilder();
		builder.append("select");
		builder.append("		MEM_ID,");
		builder.append("		 MEM_NAME,");
		builder.append("		 MEM_MAIL,");
		builder.append("		 MEM_HP,");
		builder.append("		 MEM_ADD1");
		builder.append("FROM");
		builder.append("		 MEMBER");
		builder.append("WHERE");
		builder.append("		 MEM_ADD1 LIKE '대전%'");
		System.out.println(builder.toString());

	}

}
