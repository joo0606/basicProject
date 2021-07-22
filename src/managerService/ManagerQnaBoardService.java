package managerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dao.BoardDAO;
import dao.ReplyDAO;
import homeservice.LoginView;
import util.ScannerBuffer;
import util.View;
import vo.BoardDTO;
import vo.ReplyDTO;

public class ManagerQnaBoardService {
	private BoardDAO dao = BoardDAO.getInstance();
	private ManagerQnaBoardService() {
	}// 생성자
	private static ManagerQnaBoardService instance;// 변수생성
	public static ManagerQnaBoardService getInstance() {
		if (instance == null) {
			instance = new ManagerQnaBoardService();
		}
		return instance;
	}

	private ReplyDAO replyDao = ReplyDAO.getInstance();
	private BoardDAO boardDao = BoardDAO.getInstance();
	private ScannerBuffer sc = ScannerBuffer.getInstance();

	//QnA 입장
	public int joinQnA() throws Exception {
		while (true) {
			System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		 	System.out.println(" \t\t\t\t\t\t\t\t\t\t\t\t\t\t♡ Q&A ♡\n");
		 	System.out.println("\t\t\t\t\t\t\t1. 전체조회\t\t\t\t\t\t\t 2. 선택조회 \t\t\t\t\t\t\t 3.돌아가기 ");
	 	 	System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.print("\t♥번호를 입력해주세요  : ");

			int input = sc.nextInt();
				switch (input) {
				case 1: allSelectQnA(); break;
				case 2: selectQnA(); break;
				case 3: return 0;
				default:
					System.out.println("\n\t다시 입력해주세요");
					break;
				}
		}
	}

	//전체조회
	private void allSelectQnA() throws Exception {

		List<BoardDTO> list = dao.allselectQnA(new BoardDTO("QnA"));
		System.out.println("============================================================================================================");
		System.out.println("\t\t글번호\t\t제목\t\t\t\t\t\t\t\t\t\t\t\t작성일자\t\t\t\t\t\t작성자");
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		for (BoardDTO dto : list) {
			System.out.println(String.format("\t\t%s\t\t\t%s\t\t\t\t\t\t\t\t\t\t%s\t\t\t\t\t%s", dto.getBoardId(), dto.getTitle(), dto.getCreationDate(),
					dto.getMemberId(), dto.getContent(), dto.getCategory()));
		}

	}

	//QnA 선택조회
	private void selectQnA() throws Exception {
		System.err.print("\t♥선택조회할 글번호를 입력하세요 : ");
		int boardId = sc.nextInt();
		ArrayList<HashMap<String, Object>> list = dao.select(boardId);
		int size = list.size();
		String[] key = { "BOARD_ID", "TITLE", "CONTENT", "CREATIONDATE", "MEMBER_ID" };
		for (int i = 0; i < size; i++) {
			System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("\n\t\t글번호 : "+list.get(i).get("BOARD_ID")+"   ");
			System.out.println("\t\t제목 : "+list.get(i).get("TITLE")+"  ");
			System.out.println("\t\t글내용 : "+list.get(i).get("CONTENT")+"          ");
			System.out.println("\n\t\t작성일자 : "+list.get(i).get("CREATIONDATE")+"     ");
			System.out.println("\t\t작성자 : "+list.get(i).get("MEMBER_ID"));
			System.out.println();
		}
		// 댓글 조회
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("\t\t댓글번호\t\t\t\t\t\t\t내용\t\t\t\t\t\t작성일자\t\t\t\t작성자");
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		ArrayList<HashMap<String, Object>> list1 = replyDao.replyselect(boardId);
		int listSize = list1.size();
		if (listSize == 0) {
			System.out.println("\t\t댓글이 없습니다.");
		}
		
		
		//수정구역18:45
		for (int i = 0; i < list1.size(); i++) {   
	       System.out.print("\t\t"+list1.get(i).get("REPLY_ID"));
	       System.out.print("\t\t\t\t\t"+list1.get(i).get("REPLY_CONTENT"));
	       System.out.print("\t\t\t\t"+list1.get(i).get("REPLY_CREATIONDATE"));
	       System.out.println("\t\t\t"+list1.get(i).get("MEMBER_ID"));
	       System.out.print("     ");
		}
	       while (true) {
			System.out.println();
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t1.댓글쓰기\t 2.댓글삭제\t 3.돌아가기");
			System.out.print("\t번호를 입력해주세요>");
			int input = sc.nextInt();
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			switch (input) {
				case 1:
					insertReply(boardId);
					break;
				case 2:
					deleteReply();
					break;
				case 3:
					return;
				default:
					System.out.println("\n\t다시 입력해주세요");
					break;
			}
		}
		
	}

	// 댓글쓰기
	private void insertReply(int boardId) throws Exception {
		System.out.print("\n\t내용 :  ");
		String replycontent = sc.next();
		String replyId = null;
		Map<String, Object> param = new HashMap<>();
		int executeUpdate = replyDao.insertReply(new ReplyDTO(replyId, replycontent,LoginView.loginId.getMemberId(), boardId));
		if (executeUpdate > 0) {
			System.out.println("\n\t정상 등록되었습니다.");
		} else {
			System.out.println("\n\t등록되지 않았습니다.");
		}
	}

	// 댓글삭제
	private void deleteReply() throws Exception {
		System.out.print("\n\t삭제할 글제목: ");
		String deleteReplyId = sc.next();
		int deleteBoard = ReplyDAO.deleteReply(deleteReplyId);
		if (deleteBoard > 0) {
			System.out.println("\n\t삭제되었습니다.");
		} else {
			System.out.println("\n\t삭제되지 않았습니다.");
		}
	}
}