package managerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dao.BoardDAO;
import homeservice.LoginView;
import util.ScannerBuffer;
import util.View;
import vo.BoardDTO;

public class ManagerNoticeBoardService {

	private BoardDAO dao = BoardDAO.getInstance();
	private ManagerNoticeBoardService(){}//생성자
	private static ManagerNoticeBoardService instance;//변수생성
	public static ManagerNoticeBoardService getInstance(){
		if(instance == null){
			instance = new ManagerNoticeBoardService();
		}
		return instance;
	}
		private BoardDAO boardDao = BoardDAO.getInstance();
		private static BoardDTO boardId;
		private ScannerBuffer sc = ScannerBuffer.getInstance();

//공지사항 입장
public int joinNotice() throws Exception {
	while (true) {
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
	 	System.out.println(" \t\t\t\t\t\t\t\t\t\t\t\t\t\t♡ 공지사항 ♡\n");
	 	System.out.println("\t\t\t\t1. 전체조회\t\t\t\t 2. 선택조회 \t\t\t\t 3.공지사항게시 \t\t\t\t 4. 글삭제\t\t\t\t 5.돌아가기 ");
 	 	System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("\t♥번호를 입력해주세요  : ");

		int input = sc.nextInt();
		switch (input) {
			case 1: allSelectNotice(); break;
			case 2: selectNotice(); break;
			case 3: insertNotice(); break;
			case 4: deleteNotice(); break;
			case 5: return 0;
			default:
				System.out.println("/n/t다시 입력해주세요");
				break;
		}
	}
}

//공지사항 전체조회
private void allSelectNotice() throws Exception {
	List<BoardDTO> list = dao.allselectNotice(new BoardDTO("공지사항"));
	System.out.println("============================================================================================================");
	System.out.println("\t\t글번호\t\t제목\t\t\t\t\t\t\t\t\t\t\t\t작성일자\t\t\t\t\t\t작성자");
	System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			for (BoardDTO dto : list) {
		System.out.println(String.format("\t\t%s\t\t\t%s\t\t\t\t\t\t\t\t\t\t%s\t\t\t\t\t%s",dto.getBoardId(),dto.getTitle(),dto.getCreationDate(),dto.getMemberId(), dto.getContent(), dto.getCategory()));
	}
	
	
	
	
}
//공지사항 선택조회
private void selectNotice() {
	System.out.print("\t♥선택조회할 글번호를 입력하세요 : ");
	int boardId = sc.nextInt();
	ArrayList<HashMap<String, Object>> list = dao.select(boardId);
    int size = list.size();
    String[] key = {"BOARD_ID","TITLE","CONTENT","CREATIONDATE","MEMBER_ID"};
    for(int i=0; i<size;i++) {
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("\n\t\t글번호 : "+list.get(i).get("BOARD_ID")+"   ");
		System.out.println("\t\t제목 : "+list.get(i).get("TITLE")+"  ");
		System.out.println("\t\t글내용 : "+list.get(i).get("CONTENT")+"          ");
		System.out.println("\n\t\t작성일자 : "+list.get(i).get("CREATIONDATE")+"     ");
		System.out.println("\t\t작성자 : "+list.get(i).get("MEMBER_ID"));
		System.out.println();
	}
}

//공지사항 쓰기
private void insertNotice() throws Exception {
	System.out.print("\n\t제목 :  ");
	String title = sc.next();
	System.out.print("\n\t내용 :  ");
	String content = sc.next();
	Map<String, Object> param = new HashMap<>();

	int executeUpdate = boardDao.insertNoticeBoard(new BoardDTO(title, content, LoginView.loginId.getMemberId()));
	if (executeUpdate > 0) {
		System.out.println("\n\t정상 등록되었습니다.");
	} else {
		System.out.println("\n\t등록되지 않았습니다.");
	}
}

// 지우기
private void deleteNotice() throws Exception {
	System.out.print("\n\t삭제할 글번호: ");
	String deleteBoardId = sc.next();
	int deleteBoard = boardDao.deleteBoard(deleteBoardId);
	if (deleteBoard > 0) {
		System.out.println("\n\t삭제되었습니다.");
	} else {
		System.out.println("\n\t삭제되지 않았습니다.");
	}
	}
}
