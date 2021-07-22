package userService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dao.*;
import util.ScannerBuffer;
import util.View;
import vo.*;
public class QnABoardService {
	private BoardDAO dao = BoardDAO.getInstance();
	private QnABoardService(){}//생성자
	private static QnABoardService instance;//변수생성
	public static QnABoardService getInstance(){
		if(instance == null){
			instance = new QnABoardService();
		}
		return instance;
	}
		private BoardDAO boardDao = BoardDAO.getInstance();
		private ReplyDAO replyDao = ReplyDAO.getInstance();
		private ScannerBuffer sc = ScannerBuffer.getInstance();


//QnA 입장
public void joinQnA() throws Exception  {
	while (true) {
		System.out.println("===================================QnA==================================");
		System.out.println("1.전체조회\t\t 2.선택조회\t 3.글쓰기\t\t 4.글삭제\t\t 5.돌아가기 ");
		System.out.println("번호를 입력해주세요>");
		int input = sc.nextInt();
		switch (input) {
			case 1:
				allSelectQnA();
				break;
			case 2:
				selectQnA();
				break;
			case 3:
				writeQnA();
				break;
			case 4:
				deleteQnA();
				break;
			case 5:
				return;
			default:
				System.out.println("다시 입력해주세요");
				break;
		}
	}
}

//전체조회
private void allSelectQnA() throws Exception {
	
	List<BoardDTO> list = dao.allselectQnA(new BoardDTO("QnA"));
	System.out.println("============================================================================================");
	System.out.println("글번호\t\t제목\t\t\t\t\t작성일자\t\t작성자");
	System.out.println("============================================================================================");
	for (BoardDTO dto : list) {
		System.out.println(String.format("%s\t\t%s\t\t\t\t%s\t%s",dto.getBoardId(),dto.getTitle(),dto.getCreationDate(),dto.getMemberId(), dto.getContent(), dto.getCategory()));
	}
}

//QnA 선택조회
private void selectQnA() {
	System.out.println("조회할 글번호를 입력하세요 ");
	int boardId = sc.nextInt();
	
	System.out.println("============================================================================================");
	System.out.println("글번호\t\t제목\t\t내용\t\t\t작성일자\t\t\t작성자");
	System.out.println("============================================================================================");
	ArrayList<HashMap<String, Object>> list = dao.select(boardId);
    int size = list.size();
    String[] key = {"BOARD_ID","TITLE","CONTENT","CREATIONDATE","MEMBER_ID"};
    for(int i=0; i<size;i++) {
       for(int j=0; j<key.length; j++) {
          System.out.print(list.get(i).get(key[j])+"\t\t");
       }
       System.out.println();	
       
       //18:47수정
       System.out.println("==========================  댓글  ==========================================================");
       System.out.println("댓글번호\t\t\t내용\t\t\t\t작성일자\t\t작성자");
       System.out.println("==========================================================================================");
       ArrayList<HashMap<String, Object>> list1 = replyDao.replyselect(boardId);
       if(list1 == null) {
       System.out.println("댓글이 없습니다.");
       return;
       }
       for (int k = 0; k < list1.size(); k++) {   
          System.out.print(list1.get(k).get("REPLY_ID"));
       System.out.print("\t\t");
       System.out.print(list1.get(k).get("REPLY_CONTENT"));
       System.out.print("\t\t\t\t");
       System.out.print(list1.get(k).get("REPLY_CREATIONDATE"));
       System.out.print("\t");
       System.out.print(list1.get(k).get("MEMBER_ID"));
       System.out.println("     ");
       }
       //18:47수정
    }	
}	

//글쓰기
private void writeQnA() throws Exception {
	System.out.print("제목 :  ");
	String title = sc.next();
	System.out.print("내용 :  ");
	String content = sc.next();
	System.out.print("작성자:  ");
	String memberId = sc.next();
	Map<String, Object> param = new HashMap<>();

	
	int executeUpdate = dao.insertQnABoard(new BoardDTO(title, content, memberId));
	if (executeUpdate>0) {
		System.out.println("정상 등록되었습니다.");
	} else {
		System.out.println("등록되지 않았습니다.");
	}

}

//지우기
private void deleteQnA() throws Exception {
	System.out.print("삭제할 글번호: ");
	String deleteBoardId = sc.next();
	int deleteBoard = dao.deleteBoard(deleteBoardId);
	if (deleteBoard>0) {
		System.out.println("삭제되었습니다.");
	} else {
		System.out.println("삭제되지 않았습니다.");
	}
}
}