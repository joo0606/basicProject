package managerService;

import dao.DressDAO;
import dao.MakeupDAO;
import dao.StudioDAO;
import dao.WeddingHallDAO;
import util.JDBCUtil;
import util.ScannerBuffer;
import vo.DressVo;
import vo.MakeupVo;
import vo.StudioVo;
import vo.WeddinghallVo;

public class ManageCompany {
	
	private static ManageCompany instance = new ManageCompany();
	public static ManageCompany getInstance() {
		return instance;
	}
	private ManageCompany() {
	}
	
	private StudioDAO studiodao = StudioDAO.getInstance();
	private MakeupDAO makeupdao = MakeupDAO.getInstance();
	private DressDAO dressdao = DressDAO.getInstance();
	private WeddingHallDAO halldao = WeddingHallDAO.getInstance();
	JDBCUtil jdbc = JDBCUtil.getInstance();
	private ScannerBuffer scanner = ScannerBuffer.getInstance();
	
	// 스튜디오 등록 
	public void studioInsert() {
		System.out.print("\n\t스튜디오샵 이름: ");
		String name = scanner.next();
		System.out.print("\t스튜디오샵 전화번호: ");
		String tel = scanner.next();
		System.out.print("\t스튜디오샵 가격: ");
		int price = scanner.nextInt();
		System.out.print("\t촬영 컨셉: ");
		String concept = scanner.next();
		System.out.print("\t촬영장소: ");
		String place = scanner.next();
		int executeUpdate = studiodao.insertStudio(new StudioVo(name,tel,price,concept,place));
		if(executeUpdate>0) {
			System.out.println("\n\t정상 등록되었습니다.");
		} else {
			System.out.println("\n\t등록되지 않았습니다.");
		}
	}
	
	// 스튜디오샵 수정 
	public void modifyStudio() throws Exception {
		String updateName = "";
		while (updateName.isEmpty()) {
			System.out.print("\n\t스튜디오 이름: ");
			String inputName = scanner.next();
			if (studiodao.hasId(new StudioVo(inputName))) {
				updateName = inputName;
			} else {
				System.out.println("\n\t존재하지 않는 스튜디오 이름입니다.");
				System.out.println("\t다시 입력해주세요.");
			}
		}	
		System.out.println("\n\t수정하고 싶은 메뉴를 입력해주세요.");
		System.out.println("\t1. 스튜디오 이름	 2. 스튜디오 전화번호  3. 스튜디오 가격  4. 촬영컨셉  5. 촬영장소 ");
		System.out.print("\n\t수정하고 싶은 메뉴 : ");
		int updateNumber = scanner.nextInt();
		System.out.print("\t수정하고 싶은 내용 : ");
		String updateContent = scanner.next();
		int updateStudio = studiodao.updateStudio(updateNumber, updateContent, updateName);
		if (updateStudio>0) {
			System.out.println("\n\t수정되었습니다.");
		} else {
			System.out.println("\n\t수정되지 않았습니다.");
		}
	}
	
	//스튜디오샵 삭제 
	public void deleteStudio() throws Exception {
		String deleteName = "";
		while (deleteName.isEmpty()) {
			System.out.print("\n\t삭제할 스튜디오 이름: ");
			String inputName = scanner.next();
			if (studiodao.hasId(new StudioVo(inputName))) {
				deleteName = inputName;
			} else {
				System.out.println("\n\t존재하지 않는 스튜디오 이름입니다.");
				System.out.println("\t다시 입력해주세요.");
			}
		}	
		int deleteStudio = studiodao.deleteStudio(new StudioVo(deleteName));
		if(deleteStudio>0) {
			System.out.println("\n\t삭제되었습니다.");
		} else {
			System.out.println("\n\t삭제되지 않았습니다.");
		}
	}
	
	// 메이크업샵 등록 
		public void makeupInsert() {
			System.out.print("\n\t메이크업샵 이름: ");
			String name = scanner.next();
			System.out.print("\t메이크업샵 전화번호: ");
			String tel = scanner.next();
			System.out.print("\t메이크업샵 가격: ");
			int price = scanner.nextInt();
			System.out.print("\t리허설 메이크업 가능여부: ");
			String rehearsal = scanner.next();
			System.out.print("\t추가 서비스: ");
			String option = scanner.next();
			int executeUpdate = makeupdao.insertMakeup(new MakeupVo(name,tel,price,rehearsal,option));
			if(executeUpdate>0) {
				System.out.println("\n\t정상 등록되었습니다.");
			} else {
				System.out.println("\n\t등록되지 않았습니다.");
			}
		}
	
		// 메이크업샵 수정 
		public void modifymakeup() throws Exception {
			String updateName = "";
			while (updateName.isEmpty()) {
				System.out.print("\n\t메이크업샵 이름: ");
				String inputName = scanner.next();
				if (makeupdao.hasId(new MakeupVo(inputName))) {
					updateName = inputName;
				} else {
					System.out.println("\n\t존재하지 않는 메이크업샵 이름입니다.");
					System.out.println("\t다시 입력해주세요.");
				}
			}	
			System.out.println("\n\t수정하고 싶은 메뉴를 입력해주세요.");
			System.out.println("\t1. 메이크업샵 이름	 2. 메이크업샵 전화번호  3. 메이크업샵 가격  4. 리허설 메이크업 여부 5. 추가서비스 ");
			System.out.print("\n\t수정하고 싶은 메뉴 : ");
			int updateNumber = scanner.nextInt();
			System.out.print("\t수정하고 싶은 내용 : ");
			String updateContent = scanner.next();
			int updateMakeup = makeupdao.updateMaekup(updateNumber, updateContent, updateName);
			if (updateMakeup>0) {
				System.out.println("\n\t수정되었습니다.");
			} else {
				System.out.println("\n\t수정되지 않았습니다.");
			}
		}
		
		//메이크업샵 삭제 
		public void deleteMakeup() throws Exception {
			String deleteName = "";
			while (deleteName.isEmpty()) {
				System.out.print("\n\t삭제할 메이크업샵 이름 : ");
				String inputName = scanner.next();
				if (makeupdao.hasId(new MakeupVo(inputName))) {
					deleteName = inputName;
				} else {
					System.out.println("\n\t존재하지 않는 메이크업샵 이름입니다.");
					System.out.println("\t다시 입력해주세요.");
				}
			}	
			int deleteMakeup = makeupdao.deleteMakeup(new MakeupVo(deleteName));
			if(deleteMakeup>0) {
				System.out.println("\n\t삭제되었습니다.");
			} else {
				System.out.println("\n\t삭제되지 않았습니다.");
			}
		}
		
		// 드레스샵 등록  
		public void dressInsert() {
			System.out.println("\n\t드레스샵 이름: ");
			String name = scanner.next();
			System.out.print("\t드레스샵 전화번호: ");
			String tel = scanner.next();
			System.out.print("\t드레스샵 가격: ");
			int price = scanner.nextInt();
			System.out.print("\t피팅횟수: ");
			String fittingNumber = scanner.next();
			System.out.print("\t비고: ");
			String option = scanner.next();
			int executeUpdate = dressdao.insertDress(new DressVo(name,tel,price,fittingNumber,option));
			if(executeUpdate>0) {
				System.out.println("\n\t정상 등록되었습니다.");
			} else {
				System.out.println("\n\t등록되지 않았습니다.");
			}
		}
		
		// 드레스 수정 
		public void modifyDress() throws Exception {
			String updateName = "";
			while (updateName.isEmpty()) {
				System.out.print("\n\t드레스샵 이름: ");
				String inputName = scanner.next();
				if (dressdao.hasId(new DressVo(inputName))) {
					updateName = inputName;
				} else {
					System.out.println("\n\t존재하지 않는 메이크업샵 이름입니다.");
					System.out.println("\t다시 입력해주세요.");
				}
			}	
			System.out.println("\n\t수정하고 싶은 메뉴를 입력해주세요.");
			System.out.println("\t1. 드레스샵 이름	 2. 드레스샵 전화번호  3. 드레스샵 가격  4. 피팅횟수 5. 비고 ");
			System.out.print("\n\t수정하고 싶은 메뉴 : ");
			int updateNumber = scanner.nextInt();
			System.out.print("\t수정하고 싶은 내용 : ");
			String updateContent = scanner.next();
			int updateDress = dressdao.updateDress(updateNumber, updateContent, updateName);
			if (updateDress>0) {
				System.out.println("\n\t수정되었습니다.");
			} else {
				System.out.println("\n\t수정되지 않았습니다.");
			}
		}
		
		//드레스샵 삭제 
		public void deleteDress() throws Exception {
			String deleteName = "";
			while (deleteName.isEmpty()) {
				System.out.print("\n\t삭제할 드레스샵 이름: ");
				String inputName = scanner.next();
				if (dressdao.hasId(new DressVo(inputName))) {
					deleteName = inputName;
				} else {
					System.out.println("\n\t존재하지 않는 스튜디오 이름입니다.");
					System.out.println("\t다시 입력해주세요.");
				}
			}	
			int deleteDress = dressdao.deleteDress(new DressVo(deleteName));
			if(deleteDress>0) {
				System.out.println("\n\t삭제되었습니다.");
			} else {
				System.out.println("\n\t삭제되지 않았습니다.");
			}
		}
		
		// 웨딩홀 등록  
				public void hallInsert() {
					System.out.println("\n\t웨딩홀 이름: ");
					String name = scanner.next();
					System.out.print("\t웨딩홀 전화번호: ");
					String tel = scanner.next();
					System.out.print("\t웨딩홀 가격: ");
					int price = scanner.nextInt();
					System.out.print("\t수용인원: ");
					String capacity = scanner.next();
					int executeUpdate = halldao.insertHall(new WeddinghallVo(name,tel,price,capacity));
					if(executeUpdate>0) {
						System.out.println("\n\t정상 등록되었습니다.");
					} else {
						System.out.println("\n\t등록되지 않았습니다.");
					}
				}
		
			// 웨딩홀 수정 
				public void modifyHall() throws Exception {
					String updateName = "";
					while (updateName.isEmpty()) {
						System.out.print("\n\t웨딩홀 이름: ");
						String inputName = scanner.next();
						if (halldao.hasId(new WeddinghallVo(inputName))) {
							updateName = inputName;
						} else {
							System.out.println("\n\t존재하지 않는 웨딩홀 이름입니다.");
							System.out.println("\t다시 입력해주세요.");
						}
					}	
					System.out.println("\n\t수정하고 싶은 메뉴를 입력해주세요.");
					System.out.println("\t1. 웨딩홀 이름	 2. 웨딩홀 전화번호  3. 웨딩홀 가격  4. 수용인원 ");
					System.out.print("\n\t수정하고 싶은 메뉴 : ");
					int updateNumber = scanner.nextInt();
					System.out.print("\t수정하고 싶은 내용 : ");
					String updateContent = scanner.next();
					int updateHall = halldao.updateHall(updateNumber, updateContent, updateName);
					if (updateHall>0) {
						System.out.println("\n\t수정되었습니다.");
					} else {
						System.out.println("\n\t수정되지 않았습니다.");
					}
				}				
		
				//웨딩홀 삭제 
				public void deleteHall() throws Exception {
					String deleteName = "";
					while (deleteName.isEmpty()) {
						System.out.print("\n\t삭제할 웨딩홀 이름: ");
						String inputName = scanner.next();
						if (halldao.hasId(new WeddinghallVo(inputName))) {
							deleteName = inputName;
						} else {
							System.out.println("\n\t존재하지 않는 웨딩홀 이름입니다.");
							System.out.println("\t다시 입력해주세요.");
						}
					}	
					int deleteHall = halldao.deleteHall(new WeddinghallVo(deleteName));
					if(deleteHall>0) {
						System.out.println("\n\t삭제되었습니다.");
					} else {
						System.out.println("\n\t삭제되지 않았습니다.");
					}
				}
}	
