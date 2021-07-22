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
import vo.MemberVo;

public class MemberDAO {

   private static MemberDAO instance = new MemberDAO();
   public static MemberDAO getInstance() {
      return instance;
   }
   private MemberDAO() {
   }

   JDBCUtil jdbc = JDBCUtil.getInstance();

   // 회원가입
   public int insertUser(MemberVo dto) {
      String query = String.format(
            "INSERT INTO MEMBER VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
            dto.getMemberId(), dto.getMemberPassword(), dto.getName(), dto.getIsMarried(), dto.getMemberTel(),
            dto.getMemberAge(), dto.getHight(), dto.getJob(), dto.getIdealAge(),dto.getIdealHight(),dto.getJob(),dto.getMemberClassification());
      return jdbc.Update(query);
   }

   // 회원 정보조회
   public List<MemberVo> selectMember() throws Exception {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT * FROM MEMBER"); // 세미콜론(;)을 반드시 제거
         List<MemberVo> list = new ArrayList<>();
         while (resultSet.next()) { // 다음줄이 있으면 true, 반복문 실행
            String MemberId = resultSet.getString("MEMBER_ID");
            String Name = resultSet.getString("MEMBER_NAME");
            String IsMarried = resultSet.getString("ISMARRIED");
            String MemberTel = resultSet.getString("MEMBER_TEL");
            String MemberAge = resultSet.getString("MEMBER_AGE");
            String Hight = resultSet.getString("HIGHT");
            String Job = resultSet.getString("JOB");
            list.add(new MemberVo(MemberId, Name, IsMarried, MemberTel, MemberAge, Hight, Job));
         }
         resultSet.close();
         statement.close();
         connection.close();
         return list;
      }

   // 삭제
   public int deleteId(MemberVo dto) {
      String query = "DELETE FROM MEMBER WHERE MEMBER_ID='" + dto.getMemberId() + "'";
      return jdbc.Update(query);
   }

   // 비밀번호 변경
   public int changePassword(MemberVo dto) {
      String query = "UPDATE MEMBER SET " + "MEMBER_PASSWORD = '" + dto.getMemberPassword() + "'"
            + " WHERE MEMBER_ID = '" + dto.getMemberId() + "'";
      return jdbc.Update(query);
   }

   // 로그인
   public boolean memberLogin(MemberVo dto) {
      String query = String.format("SELECT * FROM MEMBER WHERE MEMBER_ID ='%s'AND MEMBER_PASSWORD='%s'",
            dto.getMemberId(), dto.getMemberPassword());
      return jdbc.SelectBoolean(query);
   }

    //회원가입 아이디 중복검사
   public boolean hasId(MemberVo dto) {
      String query = String.format("SELECT * FROM MEMBER WHERE MEMBER_ID ='%s'", dto.getMemberId());
      return jdbc.SelectBoolean(query);
   }
   
   // 로그인 후 아이디 정보 저장 
   public MemberVo selectid(MemberVo dto) {
      String query = String.format("SELECT * FROM MEMBER WHERE MEMBER_ID = '%s'",dto.getMemberId());
      ArrayList<HashMap<String,Object>> list = jdbc.selectList(query);
      dto.setMemberId((String)list.get(0).get("MEMBER_ID"));
      dto.setName((String)list.get(0).get("MEMBER_NAME"));
      dto.setMemberClassification((String)list.get(0).get("MEMBER_CLASSIFICATION"));
      return dto;
   }
   
   // 솔로인 여성 조회 (매칭하기)
   public ArrayList<HashMap<String, Object>> searchsolo() {
      String query ="SELECT * FROM MEMBER WHERE ISMARRIED= 'N' AND MEMBER_CLASSIFICATION='여성회원'";
      return jdbc.selectList(query);
   }
   
   // 이상형 정보를 조회 
      public ArrayList<HashMap<String, Object>> searchmyIdeal(String memberId) {
         String query ="SELECT  IDEAL_AGE, IDEAL_HIGHT, IDEAL_JOB FROM MEMBER "
               + "WHERE MEMBER_ID= '"+memberId+"'";
         return jdbc.selectList(query);
   }
      
    //이상형 조회하기 (매니저)
      public ArrayList<HashMap<String, Object>> searchIdealPartner(String age, String hight, String job) {
         String query = "SELECT MEMBER_ID, MEMBER_NAME "
               + "FROM MEMBER "
               + "WHERE MEMBER_AGE = '"+age+"'"
               + "AND HIGHT = '"+hight+"'"
               + "AND JOB = '"+job+"'"
               + "AND ISMARRIED = 'N'"
               + "AND MEMBER_CLASSIFICATION = '남성회원'";
         return jdbc.selectList(query);
      }
      
      // 커플여부 수정 (매칭)
      public int updateCouple(String coupleId) {
         String query = String.format("UPDATE MEMBER SET ISMARRIED ='Y' WHERE MEMBER_ID='"+coupleId+"'");
         return jdbc.Update(query);
      }
      
      public ArrayList<HashMap<String, Object>> loginDao(Map<String, Object> map) {

         // Controller 에서 쓰는 방법
         // List<Map<String, Object>> list = memberDao.loginDao(map);
         // list.get(0).map("memberId");
         String query = " SELECT * FROM MEMBER WHERE MEMBER_ID = '" + map.get("id") + "'  AND MEMBER_PASSWORD = '"
               + map.get("password") + "'";

         return jdbc.selectList(query);
      }

      // 이상형 정보조회
      /*public List<MemberVo> selectidealMember() throws Exception {

         Class.forName("oracle.jdbc.driver.OracleDriver");

         // 2. 로딩된 드라이버를 통해 DBMS 접속 준비
         Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
         // 3. Query 문장을 전송할 객체 ( sql developer에서 쿼리 작성 화면 보여주는겉과 같음) 생성
         Statement statement = connection.createStatement();
         // 4. SQL문장을 Statement 객체를 이용하여 실행
         ResultSet resultSet = statement.executeQuery("SELECT * FROM MEMBER"); // 세미콜론(;)을 반드시 제거
         List<MemberVo> list = new ArrayList<>();

         while (resultSet.next()) { // 다음줄이 있으면 true, 반복문 실행
            String IdealAge = resultSet.getString("IDEAL_AGE");
            String IdealHight = resultSet.getString("IDEAL_HIGHT");
            String IdealJob = resultSet.getString("IDEAL_JOB");
            list.add(new MemberVo(IdealAge, IdealHight, IdealJob));
         }
         // 6. 사용된 자원 반납
         resultSet.close();
         statement.close();
         connection.close();

         return list;
      }*/

      // 회원 정보 수정 *추가*

      public int memberInformationChange(MemberVo vo) throws Exception {

         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
         StringBuilder builder = new StringBuilder();

         builder.append(" UPDATE MEMBER SET MEMBER_NAME= ?,");
         builder.append(" MEMBER_PASSWORD= ?,");
         builder.append(" MEMBER_TEL= ?,");
         builder.append(" MEMBER_AGE= ?,");
         builder.append(" HIGHT= ?,");
         builder.append(" JOB= ?");
         builder.append(" WHERE MEMBER_ID = ?");
         PreparedStatement statement = connection.prepareStatement(builder.toString());
         statement.setString(1, vo.getName());
         statement.setString(2, vo.getMemberPassword());
         statement.setString(3, vo.getMemberTel());
         statement.setString(4, vo.getMemberAge());
         statement.setString(5, vo.getHight());
         statement.setString(6, vo.getJob());
         statement.setString(7, vo.getMemberId());
         int executeUpdate = statement.executeUpdate();
         statement.close();
         connection.close();
         return executeUpdate;

      }

      // 등록된 이상형 수정
      public int insertidealmemebr(MemberVo vo) throws Exception {

         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
         StringBuilder builder = new StringBuilder();
         builder.append(" UPDATE MEMBER SET IDEAL_AGE = ?,");
         builder.append(" IDEAL_HIGHT= ?,");
         builder.append(" IDEAL_JOB= ?");
         builder.append(" WHERE MEMBER_ID = ?");

         PreparedStatement statement = connection.prepareStatement(builder.toString());
         statement.setString(1, vo.getIdealAge());
         statement.setString(2, vo.getIdealHight());
         statement.setString(3, vo.getIdealJob());
         statement.setString(4, vo.getMemberId());
         int executeUpdate = statement.executeUpdate();
         statement.close();
         connection.close();
         return executeUpdate;

      }

      // 여성 회원조회 *추가*
      public List<String> memberWomanClassification() throws Exception {
         // 1. JDBC 드라이버 로딩
         Class.forName("oracle.jdbc.driver.OracleDriver");
         // 2. 로딩된 드라이버를 통해 DBMS 접속 준비
         Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
         // 3. Query 문장을 전송할 객체 ( sql developer에서 쿼리 작성 화면 보여주는겉과 같음) 생성
         Statement statement = connection.createStatement();
         // 4. SQL문장을 Statement 객체를 이용하여 실행
         ResultSet resultSet = statement
               .executeQuery("SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_CLASSIFICATION = '여성회원'"); // 세미콜론(;)을
         // 반드시
         // 제거
         List<String> list = new ArrayList<>();
         while (resultSet.next()) { // 다음줄이 있으면 true, 반복문 실행
            list.add(resultSet.getString("MEMBER_ID"));
         }
         // 6. 사용된 자원 반납

         resultSet.close();
         statement.close();
         connection.close();
         return list;
      }

      // 자기 정보조회 *추가*
      public List<MemberVo> MypageLookup(MemberVo dto) throws Exception {

         // 1. JDBC 드라이버 로딩
         Class.forName("oracle.jdbc.driver.OracleDriver");
         // 2. 로딩된 드라이버를 통해 DBMS 접속 준비
         Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
         StringBuilder builder = new StringBuilder();
         // 4. SQL문장을 Statement 객체를 이용하여 실행
         builder.append(" SELECT * FROM MEMBER WHERE MEMBER_ID = ?");
         PreparedStatement statement = connection.prepareStatement(builder.toString());
         statement.setString(1, dto.getMemberId());
         ResultSet resultSet = statement.executeQuery();
         List<MemberVo> list = new ArrayList<>();
         while (resultSet.next()) { // 다음줄이 있으면 true, 반복문 실행
            String MemberId = resultSet.getString("MEMBER_ID");
            String Name = resultSet.getString("MEMBER_NAME");
            String isMarried = resultSet.getString("ISMARRIED");
            String MemberTel = resultSet.getString("MEMBER_TEL");
            String hight = resultSet.getString("HIGHT");
            String job = resultSet.getString("JOB");
            list.add(new MemberVo(MemberId,Name,isMarried, MemberTel, isMarried, hight, job));

         }
         statement.close();
         connection.close();
         return list;

      }

      // 본인 이상형 조회 *추가* 0719 1402시
      public List<MemberVo> myIdealSelect(MemberVo vo) throws Exception {

         // 1. JDBC 드라이버 로딩
         Class.forName("oracle.jdbc.driver.OracleDriver");
         // 2. 로딩된 드라이버를 통해 DBMS 접속 준비
         Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.46.27:1521:xe", "honey", "java");
         StringBuilder builder = new StringBuilder();
         // 4. SQL문장을 Statement 객체를 이용하여 실행
         builder.append(" SELECT * FROM MEMBER WHERE MEMBER_ID = ?");
         PreparedStatement statement = connection.prepareStatement(builder.toString());
         statement.setString(1, vo.getMemberId());
         ResultSet resultSet = statement.executeQuery();
         List<MemberVo> list = new ArrayList<>();
         while (resultSet.next()) { // 다음줄이 있으면 true, 반복문 실행
            String idealAge = resultSet.getString("IDEAL_AGE");
            String idealHight = resultSet.getString("IDEAL_HIGHT");
            String idealJob = resultSet.getString("IDEAL_JOB");
            list.add(new MemberVo(idealAge, idealHight, idealJob));

         }
         statement.close();
         connection.close();
         return list;

      }

      // 이상형 등록
      public int IdealtUser(MemberVo vo) {
         String query = String.format("INSERT INTO MEMBER VALUES ('%s','%s','%s')", vo.getIdealAge(),
               vo.getIdealHight(), vo.getIdealJob());
         return jdbc.Update(query);
      }
}