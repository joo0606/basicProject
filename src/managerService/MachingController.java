package managerService;

import java.util.ArrayList;
import java.util.HashMap;

import dao.CoupleDAO;
import dao.MemberDAO;

public class MachingController {
	private static MachingController instance = new MachingController();
	public static MachingController getInstnce() {
		return instance;
	}
	private MachingController() {
	}
	private MemberDAO dao = MemberDAO.getInstance();
	private CoupleDAO coupledao = CoupleDAO.getInstance();
	
	private String age, hight, job ; 
	private String coupleMan, coupleWoman;
	
	// 매칭하기1. 솔로조회
	public void serchingsolo() {
		ArrayList<HashMap<String, Object>> list = dao.searchsolo();
		int size = list.size();
		String[] key = {"MEMBER_NAME","MEMBER_ID"};
		for(int i=0; i<size;i++) {
			for(int j=0; j<key.length; j++) {
				System.out.print("\t\t"+list.get(i).get(key[j])+"\t\t\t");
			}
			System.out.println();
		} 
	}	
	
	//매칭하기 2. 선택된 회원의 이상형정보 호출하기
	public void serchingmyIdeal(String soloId) {
		coupleWoman = soloId;
		ArrayList<HashMap<String, Object>> list = dao.searchmyIdeal(soloId);
		age = (String) list.get(0).get("IDEAL_AGE");
		hight = (String) list.get(0).get("IDEAL_HIGHT");
		job = (String) list.get(0).get("IDEAL_JOB");
	}
	
	// 매칭하기 3. 조건에 맞는 이상형 조회하기 
	public void serchingPartner() {
		ArrayList<HashMap<String,Object>> list2 = dao.searchIdealPartner(age, hight, job);
		int size = list2.size();
			if (size>0) {
				String[] key = {"MEMBER_NAME","MEMBER_ID"};
				for(int i=0; i<size;i++) {
					for(int j=0; j<key.length; j++) {
						System.out.print("\t\t"+list2.get(i).get(key[j])+"\t\t\t");
					}
					System.out.println();
				} 
			} else {
				System.out.println("조건에 부합하는 남성이 존재하지 않습니다.");
			}
	}
	
	// 매칭하기 4. 멤버테이블의 커플여부 "Y"로 변경하기 
	public void matchingCouple(String partnerId) {
		coupleMan = partnerId;
		dao.updateCouple(coupleMan);
		dao.updateCouple(coupleWoman);
	}
	
	//매칭하기 5. 커플테이블에 정보 자동 등록
	public void coupleInsert() {
		coupledao.insertCouple(coupleMan, coupleWoman);
	}

}
