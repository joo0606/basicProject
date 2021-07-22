package userService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import dao.*;
import util.View;
import vo.*;

public class NoticeBoardService {

	private BoardDAO dao = BoardDAO.getInstance();
	private NoticeBoardService(){}//생성자
	private static NoticeBoardService instance;//변수생성
	public static NoticeBoardService getInstance(){
		if(instance == null){
			instance = new NoticeBoardService();
		}
		return instance;
	}
		private BoardDAO boardDao = BoardDAO.getInstance();
		private static BoardDTO boardId;


//공지사항 입장
public void joinNotice() throws Exception {
	while (true) {
		Scanner sc = new Scanner(System.in);
		System.out.println("====================공지사항===================");
		System.out.println("1.전체조회\t\t 2.선택조회\t 3.돌아가기");
		System.out.println("번호를 입력해주세요>");
		System.out.println("==============================================");
		int input = sc.nextInt();
		switch (input) {
			case 1:
				allSelectNotice();
				break;
			case 2:
				selectNotice();
				break;
			case 3:
				return;
			default:
				System.out.println("다시 입력해주세요");
				break;
			}
	}

	
	
}

//공지사항 전체조회
private void allSelectNotice() throws Exception {
	
	List<BoardDTO> list = dao.allselectNotice(new BoardDTO("공지사항"));
	System.out.println("=====================================================================");
	System.out.println("글번호\t제목\t\t\t작성일자\t\t\t작성자");
	System.out.println("======================================================================");
	for (BoardDTO dto : list) {
		System.out.println(String.format("%s\t%s\t\t%s\t%s",dto.getBoardId(),dto.getTitle(),dto.getCreationDate(),dto.getMemberId(), dto.getContent(), dto.getCategory()));
	}
	
	
	
	
}
//공지사항 선택조회
private void selectNotice() {
	Scanner sc = new Scanner(System.in);
	System.out.println("조회할 글번호를 입력하세요 ");
	int boardId = sc.nextInt();
	
	
	ArrayList<HashMap<String, Object>> list = dao.select(boardId);
    int size = list.size();
    String[] key = {"BOARD_ID","TITLE","CONTENT","CREATIONDATE","MEMBER_ID"};
    System.out.println("===========================================================================================");
    System.out.println("글번호\t제목\t\t내용\t\t\t\t작성일자\t\t\t작성자");
    System.out.println("===========================================================================================");
    for(int i=0; i<size;i++) {
    	System.out.print(list.get(i).get("BOARD_ID"));
    	System.out.print("   ");
    	System.out.print(list.get(i).get("TITLE"));
    	System.out.print("       ");
    	System.out.print(list.get(i).get("CONTENT"));
    	System.out.print("             ");
    	System.out.print(list.get(i).get("CREATIONDATE"));
    	System.out.print("     ");
    	System.out.println(list.get(i).get("MEMBER_ID"));
       }
       System.out.println();	

       
    }

}
