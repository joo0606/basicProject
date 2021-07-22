package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import util.JDBCUtil;
import vo.DressVo;
import vo.WeddinghallVo;

public class WeddingHallDAO {
	private static WeddingHallDAO instance = new WeddingHallDAO();
	public static WeddingHallDAO getInstance() {
		return instance;
	}
	private WeddingHallDAO() {
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	//웨딩홀 등록 
		public int insertHall(WeddinghallVo vo){
			String query = String.format("INSERT INTO WEDDINGHALL VALUES (SEQ_HALL.NEXTVAL,'%s','%s','%s','%s')"
										,vo.getHallName(),vo.getHallTel(),vo.getHallPrice(),vo.getHallCapacity());
			return jdbc.Update(query);
		}
		
		// 예산별 웨딩홀 조회 
		public ArrayList<HashMap<String, Object>> selectHall (int budget) {
			String query = String.format("SELECT * FROM WEDDINGHALL WHERE HALL_PRICE <= %d", budget);
			return jdbc.selectList(query);
		}

		// 웨딩홀 존재여부 판별
		public boolean hasId(WeddinghallVo vo) {
			String query = String.format("SELECT * FROM WEDDINGHALL WHERE HALL_NAME ='%s'", vo.getHallName());
			return jdbc.SelectBoolean(query);
				}	
		
		// 웨딩홀 일부 수정 
		public int updateHall(int updateNumber, String updateContent, String updateName) throws Exception{
			Connection connection = jdbc.getConnection(); 
			String columnName = null;
			switch (updateNumber) {
			case 1:
				columnName = "HALL_NAME";
				break;
			case 2:
				columnName = "HALL_TEL";
				break;
			case 3:
				columnName = "HALL_PRICE";
				break;
			case 4:
				columnName = "CAPACITY";	
				break;
			}
			String query = "UPDATE WEDDINGHALL SET "+columnName+" = '"+updateContent+"' WHERE HALL_NAME = '"+updateName+"'";
			PreparedStatement statement = connection.prepareStatement(query);
			int executeUpdate = statement.executeUpdate();
			jdbc.DBclose(connection, statement, null);
			return executeUpdate;
			}
		
		//웨딩홀 삭제
		public int deleteHall(WeddinghallVo vo) throws Exception {
			String query = String.format("DELETE FROM WEDDINGHALL WHERE HALL_NAME='"+vo.getHallName()+"' ");
			return jdbc.Update(query);
		}
		
}
