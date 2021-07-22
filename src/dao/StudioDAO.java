package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import util.JDBCUtil;
import vo.DressVo;
import vo.MemberVo;
import vo.StudioVo;

public class StudioDAO {

	private static StudioDAO instance = new StudioDAO();
	public static StudioDAO getInstance() {
		return instance;
	}
	private StudioDAO() {
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	// 스튜디오샵 등록
	public int insertStudio(StudioVo vo){
		String query = String.format("INSERT INTO STUDIO VALUES (SEQ_STUDIO.NEXTVAL,'%s','%s','%s','%s','%s')"
									,vo.getStudioName(),vo.getStudioTel(),vo.getStudioPrice(),vo.getStudioConcept(),vo.getStudioPlace());
		return jdbc.Update(query);
	}
	
	// 예산별 스튜디오샵 조회 
	public ArrayList<HashMap<String, Object>> selectStudio (int budget) {
		String query = String.format("SELECT * FROM STUDIO WHERE STUDIO_PRICE <= %d", budget);
		return jdbc.selectList(query);
	}
	
	// 스튜디오샵 일부 수정 
	public int updateStudio(int updateNumber, String updateContent, String updateName) throws Exception{
		Connection connection = jdbc.getConnection(); 
		String columnName = null;
		switch (updateNumber) {
		case 1:
			columnName = "STUDIO_NAME";
			break;
		case 2:
			columnName = "STUDIO_TEL";
			break;
		case 3:
			columnName = "STUDIO_PRICE";
			break;
		case 4:
			columnName = "STUDIO_CONCEPT";	
			break;
		case 5:
			columnName = "STUDIO_PLACE";	
			break;
		}
		String query = "UPDATE STUDIO SET "+columnName+" = '"+updateContent+"' WHERE STUDIO_NAME = '"+updateName+"'";
		PreparedStatement statement = connection.prepareStatement(query);
		int executeUpdate = statement.executeUpdate();
		jdbc.DBclose(connection, statement, null);
		return executeUpdate;
		}
	
	// 스튜디오샵 존재여부 판별
	public boolean hasId(StudioVo vo) {
		String query = String.format("SELECT * FROM STUDIO WHERE STUDIO_NAME ='%s'", vo.getStudioName());
		return jdbc.SelectBoolean(query);
	}
	
	// 스튜디오샵 삭제 
	public int deleteStudio(StudioVo vo) throws Exception {
		String query = String.format("DELETE FROM STUDIO WHERE STUDIO_NAME='"+vo.getStudioName()+"'");
		return jdbc.Update(query);
	}
}
