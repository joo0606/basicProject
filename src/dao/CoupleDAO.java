package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import homeservice.LoginView;
import util.JDBCUtil;

public class CoupleDAO {

   private static CoupleDAO instance = new CoupleDAO();
   public static CoupleDAO getInstance() {
      return instance;
   }
   private CoupleDAO() {
   }

   JDBCUtil jdbc = JDBCUtil.getInstance();

   // 커플테이블에 등록
   public int insertCouple(String men, String women) {
      String query = String.format(
            "INSERT INTO COUPLE VALUES (SEQ_COUPLE.NEXTVAL,SYSDATE,'%s','%s','%s','%s','%s','%s')", men, women, "0",
            "0", "0", "0");
      return jdbc.Update(query);
   }

   // 나의 커플 이름조회 남자 입장
   public String selectMemberMAN() throws Exception {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
            "SELECT B.MEMBER_NAME"
            + "  FROM COUPLE A, MEMBER B"
            + "  WHERE A.MEMBER_WOMEN = B.MEMBER_ID"
            + "  AND A.COUPLE_ID IN (SELECT COUPLE.COUPLE_ID"
            + "                       FROM COUPLE, MEMBER"
            + "                      WHERE MEMBER_ID = '"+LoginView.loginId.getMemberId()+"'"
            + "                        AND COUPLE.MEMBER_MEN = MEMBER.MEMBER_ID) "); 
      String memberId = "";
      while (resultSet.next()) { 
         memberId = resultSet.getString("MEMBER_NAME");
      }
      resultSet.close();
      statement.close();
      connection.close();
      return memberId;
   }



}