package userService;

import java.util.List;
import dao.CoupleDAO;
import dao.MemberDAO;
import homeservice.LoginView;
import util.ScannerBuffer;
import util.View;
import vo.MemberVo;

public class UserController {
   private static UserController instance = new UserController(); // 싱글톤
   public static UserController getInstance() {
      return instance;
   }
   private UserController() {
   }

   private FreeBoardService freeboard = FreeBoardService.getInstance();
   private NoticeBoardService notice = NoticeBoardService.getInstance();
   private QnABoardService qna = QnABoardService.getInstance();
  private Shopcontroller sh = Shopcontroller.getInstance();
  private ScannerBuffer scanner = ScannerBuffer.getInstance();
   private String idealAge; // 이상형 나이
   private String idealHight; // 이상형 키
   private String idealJob; // 이상형 직업

   public int userMain() throws Exception {
      while (true) {
         System.out.println("♥♥♥♥♥자기야 회원 페이지 입니다♥♥♥♥♥");
         System.out.println("1.마이페이지 | 2.이상형 등록조회 | 3.업체 조회 및 계약 | 4.게시판 | 5.로그아웃");
         System.out.print("선택해 주세요 >> ");
         int menuNum = scanner.nextInt();

         switch (menuNum) {
	         case 1: myPageMain(); break;
	         case 2: CouplePage(); break;
	         case 3: ShopMain(); break;
	         case 4: userBoard(); break;
	         case 5:
	            LoginView.loginId = null;
	            return View.HOME;
	         default:
	            System.out.println("다시 입력해주세요.");
	         }
      }
   }

   public void myPageMain() throws Exception {
      while (true) {
         System.out.println("1.자기정보조회 | 2. 나의 커플 | 3.자기정보 수정 |4. 나가기");
         System.out.print("선택해 주세요 >> ");
         int menuNum = scanner.nextInt();
         switch (menuNum) {
	         case 1:
	            myLookup();
	            break;
	         case 2:
	            myCouple();
	            break;
	         case 3:
	            myModify();
	         case 4:
	            return;
         }
      }
   }

   public void CouplePage() throws Exception {
      while (true) {
         System.out.println("1.이상형 등록| 2. 이상형 조회 | 3. 이상형 수정 | 4.나가기");
         System.out.print("선택해 주세요 >> ");
         int menuNum = scanner.nextInt();
         switch (menuNum) {
	         case 1:
	            myIdealEnrollment();
	            break;
	         case 2:
	            myIdealSelect();
	            break;
	         case 3:
	            myIdealModify();
	            break;
	         case 4:
	            return;
	         }
      }
   }
   
   public void userBoard() throws Exception {
		while (true) {
	 		System.out.println("==============================");
	 		System.out.println(" >> 게시판입니다. ");
	 		System.out.println("--------------------------------------------------------");
	 		System.out.println("1. 공지사항");
	 		System.out.println("2. 자유게시판");
	 		System.out.println("3. Q&A ");
	 		System.out.println("4. 뒤로가기");
	 		System.out.println("--------------------------------------------------------");
			System.out.print("번호입력 :");
			int inputNum = scanner.nextInt();
			switch (inputNum) {
					case 1: // 공지사항
						notice.joinNotice(); break;
					case 2: // 자유게시판
						freeboard.joinFree(); break;
					case 3: //Q&A
						qna.joinQnA(); break;
					case 4: // 뒤로가기
						return;
					default:
						System.out.println("--------------------------------------------------------");
						System.out.println("잘못된 입력입니다. 다시 입력해주세요. ");
						break;
			}
		}
	}

   // 자기정보 조회 메소드 ★★★★★★★★★★★★

   public void myLookup() throws Exception {
      MemberDAO dao = MemberDAO.getInstance();
      List<MemberVo> list = dao.MypageLookup(new MemberVo(LoginView.getInstance().loginId.getMemberId()));
      for (MemberVo dto : list) {
         System.out.println(dto.getMemberId() + "||" + dto.getName() + "||" + dto.getIsMarried() + "||"
               + dto.getMemberTel() + "||" + dto.getMemberAge() + "||" + dto.getHight() + "||" + dto.getJob());
      }

   }

   // 나의커플 조회 메소드 ★★★★★★★★★★★

   public void myCouple() throws Exception {
      CoupleDAO dao = CoupleDAO.getInstance();

      String name = dao.selectMemberMAN();

      if (!name.equals("")) {
         System.out.println("\n\t나의 커플은" + name + "입니다.");
      } else {
         System.out.println("\n\t나는 솔로 입니다.♥");
      }

   }

   // 이상형페이지 메인

   // 나의 이상형 등록 ★★★★★★★★★★★★★
   public void myIdealEnrollment() throws Exception {
      MemberDAO dao = MemberDAO.getInstance();
      System.out.println("이상형나이: 1.20대 2. 30대 3.40대: ");
      switch (scanner.nextInt()) {
      case 1:
         idealAge = "20대";
         break;
      case 2:
         idealAge = "30대";
         break;
      case 3:
         idealAge = "40대";
         break;
      }

      System.out.println("이상형 키: 1.150~160cm 2.160~170cm 3.170~180cm ");
      switch (scanner.nextInt()) {
      case 1:
         idealHight = "150~160cm";
         break;
      case 2:
         idealHight = "160~170cm";
         break;
      case 3:
         idealHight = "170~180cm";
         break;
      }

      System.out.println("이상형 직업: 1.사무직 2.전문직 3.자영업");
      switch (scanner.nextInt()) {
      case 1:
         idealJob = "사무직";
         break;
      case 2:
         idealJob = "전문직";
         break;
      case 3:
         idealJob = "자영업";
         break;
      }

      dao.insertidealmemebr(new MemberVo(idealAge, idealHight, idealJob, LoginView.loginId.getMemberId()));

   }

   // 나의 이상형 조회 ★★★★★★★★★★★★
   public void myIdealSelect() throws Exception {
      MemberDAO dao = MemberDAO.getInstance();
      List<MemberVo> list = dao.myIdealSelect(new MemberVo(LoginView.getInstance().loginId.getMemberId()));
      for (MemberVo dto : list) {
         System.out.println(dto.getIdealAge() + "\t" + dto.getIdealHight() + "\t" + dto.getIdealJob());
      }

   }

   // 나의 이상형 수정 ★★★★★★★★★★
   public void myIdealModify() throws Exception {
      MemberDAO dao = MemberDAO.getInstance();
      System.out.println("이상형나이: 1.20대 2. 30대 3.40대: ");
      switch (scanner.nextInt()) {
      case 1:
         idealAge = "20대";
         break;
      case 2:
         idealAge = "30대";
         break;
      case 3:
         idealAge = "40대";
         break;
      }

      System.out.println("이상형 키: 1.150~160cm 2.160~170cm 3.170~180cm ");
      switch (scanner.nextInt()) {
      case 1:
         idealHight = "150~160cm";
         break;
      case 2:
         idealHight = "160~170cm";
         break;
      case 3:
         idealHight = "170~180cm";
         break;
      }

      System.out.println("이상형 직업: 1.사무직 2.전문직 3.자영업");
      switch (scanner.nextInt()) {
      case 1:
         idealJob = "사무직";
         break;
      case 2:
         idealJob = "전문직";
         break;
      case 3:
         idealJob = "자영업";
         break;
      }
      if (dao.insertidealmemebr(new MemberVo(idealAge, idealHight, idealJob, LoginView.loginId.getMemberId())) > 0) {
         System.out.println("이상형 수정이 완료 되었습니다.♥ ");
      } else {
         System.out.println("이상형 수정에 실패하셨습니다.☞☜");
      } 

   }

   // 나의 정보 수정 ★★★★★★★★★★★★★★★
   public void myModify() throws Exception {
      MemberDAO dao = MemberDAO.getInstance();

      System.out.print("변경하실 비밀번호: ");
      String userPassword = scanner.next();
      System.out.print("변경하실 이름: ");
      String userName = scanner.next();
      System.out.print("변경하실 전화번호: ");
      String userTel = scanner.next();
      String userAge = "";
      while (userAge.isEmpty()) {
         System.out.println("============================");
         System.out.println("변경하실 나이가 어떻게 되십니까?");
         System.out.println("(1)20대 (2)30대 (3)40대");
         System.out.print("입력 :");
         int ageNumber = scanner.nextInt();
         switch (ageNumber) {
         case 1:
            userAge = "20대";
            break;
         case 2:
            userAge = "30대";
            break;
         case 3:
            userAge = "40대";
            break;
         default:
            System.out.println("잘못된 입력입니다.");
            System.out.println("다시 입력해주세요.");
         }
      }
      String userTall = "";
      while (userTall.isEmpty()) {
         System.out.println("============================");
         System.out.println("변경하실 키가 어떻게 되십니까?");
         System.out.println("(1)150~160cm (2)160~170cm (3)170~180cm");
         System.out.print("입력 :");
         int tallNumber = scanner.nextInt();
         switch (tallNumber) {
         case 1:
            userTall = "150~160cm";
            break;
         case 2:
            userTall = "160~170cm";
            break;
         case 3:
            userTall = "170~180cm";
            break;
         default:
            System.out.println("잘못된 입력입니다.");
            System.out.println("다시 입력해주세요.");
         }
      }
      String userJob = "";
      while (userJob.isEmpty()) {
         System.out.println("============================");
         System.out.println("변경하실 직업이 어떻게 되십니까?");
         System.out.println("(1)사무직 (2)전문직 (3) 자영업");
         System.out.print("입력:");
         int jobNumber = scanner.nextInt();
         switch (jobNumber) {
         case 1:
            userJob = "사무직";
            break;
         case 2:
            userJob = "전문직";
            break;
         case 3:
            userJob = "자영업";
            break;
         default:
            System.out.println("변경하실 잘못된 입력입니다.");
            System.out.println("변경하실 다시 입력해주세요.");
         }
      }
      String userType = "";
      while (userType.isEmpty()) {
         System.out.println("============================");
         System.out.println("변경하시는 분의 타입을 입력해주세요");
         System.out.println("(1)여성회원 (2)남성회원 (3)매니저 ");
         System.out.print("입력:");
         int typeNumber = scanner.nextInt();
         switch (typeNumber) {
         case 1:
            userType = "여성회원";
            break;
         case 2:
            userType = "남성회원";
            break;
         case 3:
            userType = "매니저";
            break;
         default:
            System.out.println("잘못된 입력입니다.");
            System.out.println("다시 입력해주세요.");
         }
      }

      if (dao.memberInformationChange(new MemberVo(LoginView.loginId.getMemberId(), userPassword, userName, "N",
            userTel, userAge, userTall, userJob, null, null, null, userType)) > 0) {
         System.out.println("회원정보 수정이 완료 되었습니다.♥ ");
      } else {
         System.out.println("회원정보 수정에 실패하셨습니다.☞☜");
      }

   }

   // ★★★★★★★★★★★★★★★★★★★★★★★
   public void ShopMain() throws Exception {

      while (true) {
         System.out.println("1.드레스샵| 2.웨딩홀 | 3.메이크업 | 4.스튜디오 | 5.나가기");
         System.out.print("선택해 주세요 >> ");
         int menuNum = scanner.nextInt();

         switch (menuNum) {
         case 1:
            sh.Dress();
            break;
         case 2:
            sh.Weddinghall();
            break;
         case 3:
            sh.Makeup();
            break;
         case 4:
            sh.Studio();
            break;
         case 5:
            return;
         }
      }
   }

}