package homeservice;

import dao.MemberDAO;
import managerService.ManagerView;
import userService.UserController;
import util.ScannerBuffer;
import util.View;
import vo.MemberVo;

public class LoginView {
	private static LoginView instance = new LoginView();
	public static LoginView getInstance() {
		return instance;
	}
	private  LoginView() {
	}
	
	private MemberDAO memberdao = MemberDAO.getInstance();
	private ManagerView manager = ManagerView.getInstance();
	private UserController user = UserController.getInstance();
	private ScannerBuffer scanner = ScannerBuffer.getInstance();
	public static MemberVo loginId;
	
	// 로그인 메인화면 
	public int loginview() throws Exception {
		System.out.println("\n\t<로그인 창 입니다>");
		System.out.print("\t아이디 : ");
		String userId = scanner.next();
		
		System.out.print("\t비밀번호 : ");
		String userPassword = scanner.next();
		System.out.println("===========================================================================================================");
		
		if (memberdao.memberLogin(new MemberVo(userId,userPassword))) {
			System.out.println("\n\t로그인에 성공하셨습니다.");
			loginId = memberdao.selectid(new MemberVo(userId));
			if (loginId.getMemberClassification().equals("매니저")) {
				System.out.println("\t"+LoginView.loginId.getName()+" 매니저님 어서오세요.\n");
				manager.managerMain();
				return View.HOME;
			}else {
				System.out.println("\t"+LoginView.loginId.getName()+" 회원님 어서오세요.\n");
				user.userMain();
				return View.HOME;
				} 
		} else {
			System.out.println("\t !! 잘못된 아이디 또는 비밀번호 입니다. ");
		}
		return View.HOME;
	}
		
	// 회원가입 메인화면
	public int join() {
		System.out.println("\n\t<회원가입 창 입니다>");
		String userId = "";
			while (userId.isEmpty()) {
				System.out.print("\n\t아이디: ");
				String inputId = scanner.next();
				if (memberdao.hasId(new MemberVo(inputId))) {
					System.out.println("\n\t이미 존재하는 아이디입니다.");
					System.out.println("\t아이디를 다시 입력해주세요.");
				} else {
					userId = inputId;
					System.out.println("\n\t사용 가능한 아이디입니다.");
				}
			}
		System.out.print("\n\t비밀번호: ");
		String userPassword = scanner.next();
		System.out.print("\t이름: ");
		String userName = scanner.next();
		System.out.print("\t전화번호: ");
		String userTel = scanner.next();
		String userAge ="";
		while (userAge.isEmpty()) {
			System.out.println("\n\t ■ 나이가 어떻게 되십니까?");
			System.out.println("\t(1)20대\t(2)30대\t(3)40대");
			System.out.print("\t입력 :");
			int ageNumber = scanner.nextInt();
			switch (ageNumber) {
				case 1: userAge = "20대"; break;
				case 2: userAge = "30대"; break;
				case 3: userAge = "40대"; break;
				default:
					System.out.println("\n\t잘못된 입력입니다.");
					System.out.println("\n\t다시 입력해주세요.");
			} 
		} 
		String userTall="";
		while (userTall.isEmpty()) {
			System.out.println("\n\t■ 키가 어떻게 되십니까?");
			System.out.println("\t(1)150~160cm (2)160~170cm (3)170~180cm");
			System.out.print("\t입력 :");
			int tallNumber = scanner.nextInt();
			switch (tallNumber) {
				case 1: userTall = "150~160cm"; break;
				case 2: userTall = "160~170cm"; break;
				case 3: userTall = "170~180cm"; break;
				default:
					System.out.println("\n\t 잘못된 입력입니다.");
					System.out.println("\n\t 다시 입력해주세요.");
			}
		}
		String userJob="";
		while (userJob.isEmpty()) {
			System.out.println("\n\t■직업이 어떻게 되십니까?");
			System.out.println("\t(1)사무직 (2)전문직 (3) 자영업");
			System.out.print("\t입력:");
			int jobNumber = scanner.nextInt();
			switch (jobNumber) {
				case 1: userJob = "사무직"; break;
				case 2: userJob = "전문직"; break;
				case 3: userJob = "자영업"; break;
				default:
					System.out.println("\n\t잘못된 입력입니다.");
					System.out.println("\n\t다시 입력해주세요.");
			}
		}
		String userType="";
		while (userType.isEmpty()) {
			System.out.println("\n\t■가입하시는 분의 타입을 입력해주세요");
			System.out.println("\t(1)여성회원 (2)남성회원 (3)매니저 ");
			System.out.print("\t입력:");
			int typeNumber = scanner.nextInt();
			switch (typeNumber) {
				case 1: userType = "여성회원"; break;
				case 2: userType = "남성회원"; break;
				case 3: userType = "매니저"; break;
				default:
					System.out.println("\n\t잘못된 입력입니다.");
					System.out.println("\n\t다시 입력해주세요.");
			}
		}
		if (memberdao.insertUser(new MemberVo(userId, userPassword, userName,"N", userTel, userAge,userTall, userJob,null,null,null, userType))>0) {
			System.out.println("\n\t회원이 되신것을 환영합니다. ");
		} else {
			System.out.println("\n\t회원가입에 실패하셨습니다.");
		}
		return View.HOME;
	}
}
