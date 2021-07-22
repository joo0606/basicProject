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

public class DressDAO {

	private static DressDAO instance = new DressDAO();			//싱글톤 
	public static  DressDAO getInstance() {
		return instance;
	}
	private DressDAO() {
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();

	//드레스 등록
	public int insertDress(DressVo vo){
		String query = String.format("INSERT INTO DRESS VALUES (SEQ_DRESS.NEXTVAL,'%s','%s','%s','%s','%s')"
									,vo.getName(),vo.getTel(),vo.getPrice(),vo.getFittingNumber(),vo.getRemark());
		return jdbc.Update(query);
	}

	
	// 예산별 드레스 조회 
	public ArrayList<HashMap<String, Object>> selectDress (int budget) {
		String query = String.format("SELECT * FROM DRESS WHERE DRESS_PRICE <= %d", budget);
		return jdbc.selectList(query);
	}
	
	//드레스 삭제
	public int deleteDress(DressVo vo) throws Exception {
		String query = String.format("DELETE FROM DRESS WHERE DRESS_NAME='"+vo.getName()+"' ");
		return jdbc.Update(query);
	}
	
	//드레스 전체 수정
	public int updateDress(DressVo vo) {
		String query = String.format("UPDATE DRESS SET DRESS_NAME ='"+vo.getName()+"' , DRESS_TEL='"+vo.getTel()+"', DRESS_PRICE='"+vo.getPrice()+"', FITTINGNUMBER='"+vo.getFittingNumber()+"', REMARK='"+vo.getFittingNumber()+"'  WHERE DRESS_ID='"+vo.getId()+"'");
		return jdbc.Update(query);
	}
		
	// 드레스 일부 수정 
	public int updateDress(int updateNumber, String updateContent, String updateName) throws Exception{
		Connection connection = jdbc.getConnection(); 
		String columnName = null;
		switch (updateNumber) {
		case 1:
			columnName = "DRESS_NAME";
			break;
		case 2:
			columnName = "DRESS_TEL";
			break;
		case 3:
			columnName = "DRESS_PRICE";
			break;
		case 4:
			columnName = "FITTINGNUMBER";	
			break;
		case 5:
			columnName = "REMARK";	
			break;
		}
		String query = "UPDATE DRESS SET "+columnName+" = '"+updateContent+"' WHERE DRESS_NAME= '"+updateName+"'";
		PreparedStatement statement = connection.prepareStatement(query);
		int executeUpdate = statement.executeUpdate();
		jdbc.DBclose(connection, statement, null);
		return executeUpdate;
		}
	
	// 드레스 존재여부 판별
		public boolean hasId(DressVo vo) {
			String query = String.format("SELECT * FROM DRESS WHERE DRESS_NAME ='%s'", vo.getName());
			return jdbc.SelectBoolean(query);
		}
	}
	
	
	
	

