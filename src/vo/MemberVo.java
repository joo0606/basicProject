package vo;

import java.util.ArrayList;
import java.util.List;

public class MemberVo {

   private String memberId; // 아이디
   private String memberPassword; // 비밀번호
   private String name; // 이름
   private String isMarried; // 커플 여부
   private String memberTel; // 폰번호
   private String memberAge; // 나이
   private String hight; // 키
   private String job; // 직업
   private String idealAge; // 이상형 나이
   private String idealHight; // 이상형 키
   private String idealJob; // 이상형 직업
   private String memberClassification; // 회원 및 매니저 성별

   public MemberVo(String memberId, String memberPassword) {
      this.memberId = memberId;
      this.memberPassword = memberPassword;
   }


   public MemberVo(String memberId) {
      this.memberId = memberId;
   }

   public MemberVo(String memberId, String name, String isMarried, String memberTel, String memberAge, String hight,
         String job) {
      this.memberId = memberId;
      this.name = name;
      this.isMarried = isMarried;
      this.memberTel = memberTel;
      this.memberAge = memberAge;
      this.hight = hight;
      this.job = job;

   }

   public MemberVo(String memberId, String memberPassword, String name, String isMarried, String memberTel,
         String memberAge, String hight, String job, String idealAge, String idealHight,
         String idealJob, String memberClassification) {
      this.memberId = memberId;
      this.memberPassword = memberPassword;
      this.name = name;
      this.isMarried = isMarried;
      this.memberTel = memberTel;
      this.memberAge = memberAge;
      this.hight = hight;
      this.job = job;
      this.idealAge = idealAge;
      this.idealHight = idealHight;
      this.idealJob = idealJob;
      this.memberClassification = memberClassification;
   }

   public MemberVo(String idealAge, String idealHight, String idealJob) {
      this.idealAge = idealAge;
      this.idealHight = idealHight;
      this.idealJob = idealJob;
   }
   
   public MemberVo(String idealAge, String idealHight, String idealJob, String memberId) {
      this.idealAge = idealAge;
      this.idealHight = idealHight;
      this.idealJob = idealJob;
      this.memberId = memberId;
   }
   
   public List<String> getColumNames() {
      List<String> list = new ArrayList<>();
      list.add("MEMBER_ID");
      list.add("MEMBER_PASSWORD");
      list.add("MEMBER_NAME");
      list.add("ISMARRIED");
      list.add("MEMBER_TEL");
      list.add("MEMBER_AGE");
      list.add("HIGHT");
      list.add("JOB");
      list.add("IDEAL_AGE");
      list.add("IDEAL_HIGHT");
      list.add("IDEAL_JOB");
      list.add("MEMBER_CLASSIFICATION");
      return list;
   }

   public String getMemberId() {
      return memberId;
   }

   public void setMemberId(String memberId) {
      this.memberId = memberId;
   }

   public String getMemberPassword() {
      return memberPassword;
   }

   public void setMemberPassword(String memberPassword) {
      this.memberPassword = memberPassword;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getIsMarried() {
      return isMarried;
   }

   public void setIsMarried(String isMarried) {
      this.isMarried = isMarried;
   }

   public String getMemberTel() {
      return memberTel;
   }

   public void setMemberTel(String memberTel) {
      this.memberTel = memberTel;
   }

   public String getMemberAge() {
      return memberAge;
   }

   public void setMemberAge(String memberAge) {
      this.memberAge = memberAge;
   }

   public String getHight() {
      return hight;
   }

   public void setHight(String hight) {
      this.hight = hight;
   }

   public String getJob() {
      return job;
   }

   public void setJob(String job) {
      this.job = job;
   }

   public String getIdealAge() {
      return idealAge;
   }

   public void setIdealAge(String idealAge) {
      this.idealAge = idealAge;
   }

   public String getIdealHight() {
      return idealHight;
   }

   public void setIdealHight(String idealHight) {
      this.idealHight = idealHight;
   }

   public String getIdealJob() {
      return idealJob;
   }

   public void setIdealJob(String idealJob) {
      this.idealJob = idealJob;
   }

   public String getMemberClassification() {
      return memberClassification;
   }

   public void setMemberClassification(String memberClassification) {
      this.memberClassification = memberClassification;
   }

   @Override
   public String toString() {
      return "MemberDTO [memberId=" + memberId + ", memberPassword=" + memberPassword + ", name=" + name
            + ", isMarried=" + isMarried + ", memberTel=" + memberTel + ", memberAge=" + memberAge + ", hight="
            + hight + ", job=" + job + ", idealAge=" + idealAge + ", idealHight=" + idealHight + ", idealJob="
            + idealJob + ", memberClassification=" + memberClassification + "]";
   }

}