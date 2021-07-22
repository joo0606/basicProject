package userService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dao.*;
import homeservice.LoginView;
import util.ScannerBuffer;
import util.View;
import vo.*;

public class FreeBoardService {
	private FreeBoardService() {
	}// 생성자

	private static FreeBoardService instance;// 변수생성

	public static FreeBoardService getInstance() {
		if (instance == null) {
			instance = new FreeBoardService();
		}
		return instance;
	}

	private ReplyDAO replyDao = ReplyDAO.getInstance();
	private BoardDAO boardDao = BoardDAO.getInstance();
	private ScannerBuffer sc = ScannerBuffer.getInstance();

	// 자유게시판 입장
	public void joinFree() throws Exception {
		while (true) {
			System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		 	System.out.println(" \t\t\t\t\t\t\t\t\t\t\t\t\t\t♡ 자유게시판 ♡\n");
		 	System.out.println("\t\t\t\t\t1. 전체조회\t\t\t\t\t 2. 선택조회 \t\t\t\t\t 3.글쓰기\t\t\t4.글삭제\t\t\t5.돌아가기 ");
	 	 	System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.print("\t♥번호를 입력해주세요  : ");
			int input = sc.nextInt();
				switch (input) {
				case 1: allSelectFree(); break;
				case 2: selectfree(); break;
				case 3: writefree(); break;
				case 4: deletefree(); break;
				case 5: return;
				default:
					System.out.println("/t다시 입력해주세요");
					break;
			}
		}
	}

//조회
	private void allSelectFree() throws Exception {

		List<BoardDTO> list = boardDao.allselectFree(new BoardDTO("자유게시판"));
		System.out.println("============================================================================================================");
		System.out.println("\t\t글번호\t\t제목\t\t\t\t\t\t\t\t\t\t\t\t작성일자\t\t\t\t\t\t작성자");
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		for (BoardDTO dto : list) {
			System.out.println(String.format("\t\t%s\t\t\t%s\t\t\t\t\t\t\t\t\t\t%s\t\t\t\t\t%s", dto.getBoardId(), dto.getTitle(), dto.getCreationDate(),
					dto.getMemberId(), dto.getContent(), dto.getCategory()));
		}

	}

	// 자유게시판 선택조회
	private void selectfree() throws Exception {
		System.err.print("\t♥선택조회할 글번호를 입력하세요 : ");
		int boardId = sc.nextInt();
		ArrayList<HashMap<String, Object>> list = boardDao.select(boardId);
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
		System.out.println("\t\t댓글번호\t\t\t\t\t내용\t\t\t\t\t\t작성일자\t\t\t\t작성자");
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		ArrayList<HashMap<String, Object>> list1 = replyDao.replyselect(boardId);
		int replysize = list1.size();
		if(replysize==0) {
			System.out.println("\t\t댓글이 없습니다.");
		}
		for (int i = 0; i < list1.size(); i++) {
			  System.out.print(list1.get(i).get("REPLY_ID"));
		       System.out.print("\t\t");
		       System.out.print(list1.get(i).get("REPLY_CONTENT"));
		       System.out.print("\t\t\t\t");
		       System.out.print(list1.get(i).get("REPLY_CREATIONDATE"));
		       System.out.print("\t");
		       System.out.print(list1.get(i).get("MEMBER_ID"));
		       System.out.println("     ");
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
						updateReply(boardId);
						break;
					case 3:
						deleteReply();
						break;
					case 4:
						return;
					default:
						System.out.println("\t다시 입력해주세요");
						break;
					}
			}
	}

	// 댓글쓰기
	private void insertReply(int boardId) throws Exception {
		System.out.print("\n\t내용 :  ");
		String replycontent = sc.next();
		int executeUpdate = replyDao.insertReply(new ReplyDTO(replycontent,LoginView.loginId.getMemberId(),boardId));
		if (executeUpdate > 0) {
			System.out.println("\n\t정상 등록되었습니다.");
		} else {
			System.out.println("\n\t등록되지 않았습니다.");
		}
	}

	// 댓글 수정
	private void updateReply(int boardId) throws Exception {
		System.out.print("\n\t수정할 댓글번호 :  ");
		String replyId = sc.next();

		System.out.print("\n\t내용 :  ");
		String replycontent = sc.next();
		int updateReply = replyDao.updateReply(new ReplyDTO(replyId, replycontent, null, boardId));
		if (updateReply > 0) {
			System.out.println("\n\t수정되었습니다.");
		} else {
			System.out.println("\n\t수정되지 않았습니다.");
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

	// 글쓰기
	private void writefree() throws Exception {
		System.out.print("\n\t제목 :  ");
		String title = sc.next();
		System.out.print("\n\t내용 :  ");
		String content = sc.next();
		Map<String, Object> param = new HashMap<>();
		int executeUpdate = boardDao.insertFreeBoard(new BoardDTO(title, content,LoginView.loginId.getMemberId()));
		if (executeUpdate > 0) {
			System.out.println("\n\t정상 등록되었습니다.");
		} else {
			System.out.println("\n\t등록되지 않았습니다.");
		}

	}

	// 지우기
	private void deletefree() throws Exception {
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