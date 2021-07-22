package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import util.JDBCUtil;
import vo.DressVo;
import vo.MakeupVo;
import vo.StudioVo;

public class MakeupDAO {
	
	private static MakeupDAO instance = new MakeupDAO();
	public static MakeupDAO getInstance() {
		return instance;
	}
	private MakeupDAO() {
	}
	 
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	// 메이크업샵 등록 
	public int insertMakeup(MakeupVo vo){
		String query = String.format("INSERT INTO MAKEUP VALUES (SEQ_MAKEUP.NEXTVAL,'%s','%s','%s','%s','%s')"
									,vo.getMakeupName(),vo.getMakeupTel(),vo.getMakeupPrice(),vo.getRehersalMakeup(),vo.getOptionService());
		return jdbc.Update(query);
	}
	
	// 예산별 메이크업샵 조회 
		public ArrayList<HashMap<String, Object>> selectMakeup (int budget) {
			String query = String.format("SELECT * FROM MAKEUP WHERE MAKEUP_PRICE <= %d", budget);
			return jdbc.selectList(query);
		}
		
	// 메이크업샵 일부 수정 
		public int updateMaekup(int updateNumber, String updateContent, String updateName) throws Exception{
			Connection connection = jdbc.getConnection(); 
			String columnName = null;
			switch (updateNumber) {
			case 1:
				columnName = "MAKEUP_NAME";
				break;
			case 2:
				columnName = "MAKEUP_TEL";
				break;
			case 3:
				columnName = "MAKEUP_PRICE";
				break;
			case 4:
				columnName = "REHEARSAL_MAKEUP";	
				break;
			case 5:
				columnName = "OPTION_SERVICE";	
				break;
			}
			String query = "UPDATE MAKEUP SET "+columnName+" = '"+updateContent+"' WHERE MAKEUP_NAME = '"+updateName+"'";
			PreparedStatement statement = connection.prepareStatement(query);
			int executeUpdate = statement.executeUpdate();
			jdbc.DBclose(connection, statement, null);
			return executeUpdate;
			}

	// 메이크업샵 존재여부 판별
	public boolean hasId(MakeupVo vo) {
		String query = String.format("SELECT * FROM MAKEUP WHERE MAKEUP_NAME ='%s'", vo.getMakeupName());
		return jdbc.SelectBoolean(query);
	}
	
	// 메이크업샵 삭제 
		public int deleteMakeup(MakeupVo vo) throws Exception {
			String query = String.format("DELETE FROM MAKEUP WHERE MAKEUP_NAME='"+vo.getMakeupName()+"'");
			return jdbc.Update(query);
		}
	
		
}