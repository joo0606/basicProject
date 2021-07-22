package homeservice;

import util.ScannerBuffer;
import util.View;

public class HomeView {
	private static HomeView instance = new HomeView();
	public static HomeView getInstance() {
		return instance;
	}
	private  HomeView() {
	}

	   private ScannerBuffer scanner = ScannerBuffer.getInstance();

	public int home() {
	
		System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t♥ 자 기 야 ♥\n");
		System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println(" 						1. 로그인							2. 회원가입								0. 종료");
		System.out.println("==========================================================================================================");
		System.out.print("\t♥번호를 입력해주세요  :	");
		try {
			int inputNumber = scanner.nextInt();
			switch(inputNumber) {
			case 1: return View.LOGIN;
			case 2: return View.JOIN;
			case 0: 
				System.out.println("\n\t종료되었습니다.");
				System.out.println("\t다음에 또 만나요♥");
				System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				System.exit(0);	
			default:
				System.out.println("\n\t!!잘못된 입력입니다. 다시 입력해주세요");
				break;
			}
		} catch (Exception e) {
			System.out.println("\n\t!!잘못 입력 하셨습니다.");
			scanner.next();
		}

		return View.HOME;

	}


}
