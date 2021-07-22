package userService;

import java.util.ArrayList;
import java.util.HashMap;

import dao.DressDAO;
import dao.MakeupDAO;
import dao.StudioDAO;
import dao.WeddingHallDAO;
import util.ScannerBuffer;

public class Shopcontroller {
   private static Shopcontroller instance = new Shopcontroller();
   public static Shopcontroller getInstance() {
      return instance;
   }
   private DressDAO dressdao = DressDAO.getInstance();
   private MakeupDAO makeupdao = MakeupDAO.getInstance();
   private StudioDAO studiodao = StudioDAO.getInstance();
   private WeddingHallDAO halldao = WeddingHallDAO.getInstance();
  
   private ScannerBuffer sc = ScannerBuffer.getInstance();

   // 드레스
   public void Dress() {
	   System.out.println("----------------------------------------------------------");
	   System.out.print("예산을 입력해 주세요: ");
	   int budget = sc.nextInt();
	   System.out.println("-----------------------------------------------------------");
	   ArrayList<HashMap<String, Object>> list = dressdao.selectDress(budget);
		int size = list.size();
		String[] key = {"DRESS_NAME","DRESS_TEL","DRESS_PRICE","FITTINGNUMBER","REMARK"};
		System.out.println("샵이름\t전화번호\t가격\t피팅횟수\t설명");
		for(int i=0; i<size;i++) {
			for(int j=0; j<key.length; j++) {
				System.out.print(list.get(i).get(key[j])+"\t");
			}
			System.out.println();
		}
   }
   
   
   public void Weddinghall() {
	   System.out.println("----------------------------------------------------------");
	   System.out.print("예산을 입력해 주세요: ");
	   int budget = sc.nextInt();
	   System.out.println("-----------------------------------------------------------");
	   ArrayList<HashMap<String, Object>> list = halldao.selectHall(budget);
		int size = list.size();
		String[] key = {"HALL_NAME","HALL_TEL","HALL_PRICE","CAPACITY"};
		System.out.println("샵 이름\t전화번호 \t가격\t수용인원");
		for(int i=0; i<size;i++) {
			for(int j=0; j<key.length; j++) {
				System.out.print(list.get(i).get(key[j])+"\t");
			}
			System.out.println();
		}
   }

   public void Makeup() {
	   System.out.println("----------------------------------------------------------");
	   System.out.print("예산을 입력해 주세요: ");
	   int budget = sc.nextInt();
	   System.out.println("-----------------------------------------------------------");
	   ArrayList<HashMap<String, Object>> list = makeupdao.selectMakeup(budget);
		int size = list.size();
		String[] key = {"MAKEUP_NAME","MAKEUP_TEL","MAKEUP_PRICE","REHEARSAL_MAKEUP","OPTION_SERVICE"};
		System.out.println(" 샵 이름\t 전화번호 \t가격\t리허설 메이크업\t추가  서비스");
		for(int i=0; i<size;i++) {
			for(int j=0; j<key.length; j++) {
				System.out.print(list.get(i).get(key[j])+"\t");
			}
			System.out.println();
		}
   }

   public void Studio() {
	   System.out.println("----------------------------------------------------------");
	   System.out.print("예산을 입력해 주세요: ");
	   int budget = sc.nextInt();
	   System.out.println("-----------------------------------------------------------");
	   ArrayList<HashMap<String, Object>> list = studiodao.selectStudio(budget);
		int size = list.size();
		String[] key = {"STUDIO_NAME","STUDIO_TEL","STUDIO_PRICE","STUDIO_CONCEPT","STUDIO_PLACE"};
		System.out.println(" 이름\t전화 번호 \t가격\t촬영 컨셉\t촬영 장소");
		for(int i=0; i<size;i++) {
			for(int j=0; j<key.length; j++) {
				System.out.print(list.get(i).get(key[j])+"\t");
			}
			System.out.println();
		}

   }

}