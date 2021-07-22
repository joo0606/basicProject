package managerService;

import homeservice.LoginView;
import util.ScannerBuffer;
import util.View;

public class ManagerView {
	private static ManagerView instance = new ManagerView();			//싱글톤 
	public static  ManagerView getInstance() {
		return instance;
	}
	private ManagerView() {
	}
	
	private MachingController matching = MachingController.getInstnce();
	private ManageCompany manageCompany = ManageCompany.getInstance();
	private ManagerNoticeBoardService notice = ManagerNoticeBoardService.getInstance();
	private ManagerQnaBoardService qna = ManagerQnaBoardService.getInstance();
	private ManagerFreeBoardService freeboard = ManagerFreeBoardService.getInstance();
	private ScannerBuffer scanner = ScannerBuffer.getInstance();
 
	 public int managerMain() throws Exception {
		while(true) {
			System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println(" \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t♡ 매니저 메뉴입니다 ♡\n");
			System.out.println("\t\t\t\t\t1.매칭하기\t\t\t\t\t2. 스드메관리\t\t\t\t\t3. 게시판관리\t\t\t\t\t4.로그아웃");
			System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.print("\t♥번호를 입력해주세요  : ");
				int input = scanner.nextInt();
				switch (input) {
					case 1: managerMatching(); break;
					case 2: manageCompany(); break;
					case 3: manageBoard();break;
					case 4:  
						System.out.println("로그아웃되었습니다");
						System.out.println("다음에 또 만나요♥");
			            LoginView.loginId = null;
			            return View.HOME;
					default: System.out.println("잘못 입력하였습니다.");
									System.out.println("============================");
				}
		 }
	 }
	 
	 public void managerMatching() {
		 System.out.println("===========================================================================================================");
			System.out.println("\n\t ** 매칭가능한 여성회원 목록입니다.\n");
			System.out.println("\t\t<이름>\t\t\t\t\t<아이디>\n");
			matching.serchingsolo();
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.print("\t♥매칭할 회원 아이디를 입력해주세요 : ");
			String soloId = scanner.next();
			matching.serchingmyIdeal(soloId);
			System.out.println("===========================================================================================================");
			System.out.println("\n\t ** 매칭가능한 남성회원 목록입니다.\n");
			System.out.println("\t\t<이름>\t\t\t\t\t<아이디>\n");
			matching.serchingPartner();
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.print("\t♥매칭해 줄 남성회원 아이디를 입력해주세요 : ");
			String partnerId = scanner.next();
			matching.matchingCouple(partnerId);
			matching.coupleInsert();
			System.out.println("\n\t♥ 매칭이 완료되었습니다 ♥");
	 }
	 
	 	public void manageCompany() throws Exception {
	 		while (true) {
		 		System.out.println("===========================================================================================================");
		 		System.out.println(" \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t♡ 업체 관리창입니다 ♡\n");
		 		System.out.println("\t\t\t1. 스튜디오샵 관리\t\t\t2.메이크업샵 관리\t\t\t3.드레스샵 관리\t\t\t4.웨딩홀 관리\t\t\t5.뒤로가기");
		 		System.out.println("===========================================================================================================");
				System.out.print("\t♥번호를 입력해주세요  : ");
				int inputNum = scanner.nextInt();
				switch (inputNum) {
					case 1: manageStudio(); break;
					case 2: manageMakeup(); break;
					case 3: manageDress(); break;
					case 4: manageHall(); break;
					case 5: return;
					default:
						System.out.println(" \n\t !! 잘못된 입력입니다. 다시 입력해주세요. ");
						break;
				} 
			}
	 	}
	 	
	 	public void manageStudio() throws Exception {
	 		 while (true) {
	 	 		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		 		System.out.println(" \t\t\t\t\t\t\t\t\t\t\t\t\t\t♡ 스튜디오샵 관리창입니다 ♡\n");
		 		System.out.println("\t\t\t\t1. 새로운 스튜디오샵 등록\t\t\t\t 2. 스튜디오샵 수정 \t\t\t\t 3. 스튜디오샵 삭제 \t\t\t\t 4. 뒤로가기");
	 	 		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.print("\t♥번호를 입력해주세요  : ");
				int inputNum = scanner.nextInt();
				switch (inputNum) {
					case 1: // 스튜디오샵 등록 
						manageCompany.studioInsert(); break;
					case 2: // 스튜디오샵 수정 
						manageCompany.modifyStudio(); break; 
					case 3: // 스튜디오샵 삭제 
						manageCompany.deleteStudio(); break; 
					case 4: // 뒤로가기
						return;
					default:
						System.out.println(" \n\t !! 잘못된 입력입니다. 다시 입력해주세요. ");
						break;
				}
			}
	 	}
	 	
		public void manageMakeup() throws Exception {
	 		while (true) {
	 	 		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			 	System.out.println(" \t\t\t\t\t\t\t\t\t\t\t\t\t\t♡ 메이크업샵 관리창입니다 ♡\n");
		 		System.out.println("\t\t\t\t1. 새로운 메이크업샵 등록\t\t\t\t 2. 메이크업샵 수정 \t\t\t\t 3. 메이크업샵 삭제 \t\t\t\t 4. 뒤로가기");
	 	 		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.print("\t♥번호를 입력해주세요  : ");
				int inputNum = scanner.nextInt();
				switch (inputNum) {
					case 1: // 메이크업샵 등록 
						manageCompany.makeupInsert(); break;
					case 2: // 메이크업샵 수정 
						manageCompany.modifymakeup(); break;
					case 3: // 메이크업샵 삭제 
						manageCompany.deleteMakeup(); break;
					case 4: // 뒤로가기
						return;
					default:
						System.out.println(" \n\t !! 잘못된 입력입니다. 다시 입력해주세요. ");
						break;
				}
			}
	 	}
	 	
		public void manageDress() throws Exception {
	 		while (true) {
	 	 		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			 	System.out.println(" \t\t\t\t\t\t\t\t\t\t\t\t\t\t♡ 메이크업샵 관리창입니다 ♡\n");
		 		System.out.println("\t\t\t\t1. 새로운 드레스샵 등록\t\t\t\t 2. 드레스샵 수정 \t\t\t\t 3. 드레스샵 삭제 \t\t\t\t 4. 뒤로가기");
	 	 		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.print("\t♥번호를 입력해주세요  : ");
				int inputNum = scanner.nextInt();
				switch (inputNum) {
					case 1: // 드레스샵 등록 
						manageCompany.dressInsert(); break;
					case 2: // 드레스샵 수정 
						manageCompany.modifyDress(); break;
					case 3: // 드레스샵 삭제 
						manageCompany.deleteDress(); break;
					case 4: // 뒤로가기
						return;
					default:
						System.out.println(" \n\t !! 잘못된 입력입니다. 다시 입력해주세요. ");
						break;
				}
			}
	 	}
	 	
		public void manageHall() throws Exception {
	 		while (true) {
	 	 		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			 	System.out.println(" \t\t\t\t\t\t\t\t\t\t\t\t\t\t♡ 메이크업샵 관리창입니다 ♡\n");
		 		System.out.println("\t\t\t\t1. 새로운 웨딩홀 등록\t\t\t\t 2. 웨딩홀 수정 \t\t\t\t 3. 웨딩홀 삭제 \t\t\t\t 4. 뒤로가기");
	 	 		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.print("\t♥번호를 입력해주세요  : ");
				int inputNum = scanner.nextInt();
				switch (inputNum) {
					case 1: // 웨딩홀 등록 
						manageCompany.hallInsert(); break;
					case 2: // 웨딩홀 수정 
						manageCompany.modifyHall(); break;
					case 3: // 웨딩홀 삭제 
						manageCompany.deleteHall(); break;
					case 4: // 뒤로가기
						return;
					default:
						System.out.println(" \n\t !! 잘못된 입력입니다. 다시 입력해주세요. ");
						break;
				}
			}
	 	}
		
		public void manageBoard() throws Exception {
			while (true) {
	 	 		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			 	System.out.println(" \t\t\t\t\t\t\t\t\t\t\t\t\t\t♡ 게시판 관리창입니다 ♡\n");
		 		System.out.println("\t\t\t\t\t1. 공지사항\t\t\t\t\t 2. 자유게시판 \t\t\t\t\t 3. Q&A \t\t\t\t\t\t\t 4. 뒤로가기");
	 	 		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.print("\t♥번호를 입력해주세요  : ");
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
						System.out.println(" \n\t !! 잘못된 입력입니다. 다시 입력해주세요. ");
						break;
				}
			}
		}
}