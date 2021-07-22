package vo;

public class BoardDTO {
	private int boardId;
	private String title;
	private String content;
	private String creationDate;
	private String category;
	private String memberId;
	
	
	public BoardDTO(int boardId, String title, String content, String creationDate, String category,
			String memberId) {
		super();
		this.boardId = boardId;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
		this.category = category;
		this.memberId = memberId;
	}
	


	public BoardDTO(int boardId, String title, String creationDate, String memberId) {
		super();
		this.boardId = boardId;
		this.title = title;
		this.creationDate = creationDate;
		this.memberId = memberId;
	}


	public BoardDTO(String category) {
		this.category = category;
	}


	public BoardDTO(String title, String content, String memberId) {
		this.title = title;
		this.content = content;
		this.memberId = memberId;
	}


	public BoardDTO(int boardId, String title, String content, String creationDate, String memberId) {
		this.boardId = boardId;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
		this.memberId = memberId;
		
	}


	public int getBoardId() {
		return boardId;
	}


	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardDTO [boardId=");
		builder.append(boardId);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", category=");
		builder.append(category);
		builder.append(", memberId=");
		builder.append(memberId);
		builder.append("]");
		return builder.toString();
	}
	
//	public List<String> getColunmNames() {
//		List<String> colunmNames = new ArrayList<>();
//		JDBCUtil.getInstance().selectOne("SELECT COLUMN_NAME FROM COLS WHERE TABLE_NAME = 'BOARD'");
//
//		
//		
//		
//		
//		return colunmNames;
//	}
	
//	   public List<String> getColumnNames(){
//		      List<String> list = new ArrayList<>();
//		      list.add("BOARD_ID");
//		      list.add("TITLE");
//		      list.add("CONTENT");
//		      list.add("CREATIONDATE");
//		      list.add("CATEGORY");
//		      list.add("MEMBER_ID");
//		      return list;
//		   }

//
//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("BoardDTO [boardId=");
//		builder.append(boardId);
//		builder.append(", title=");
//		builder.append(title);
//		builder.append(", content=");
//		builder.append(content);
//		builder.append(", creationDate=");
//		builder.append(creationDate);
//		builder.append(", category=");
//		builder.append(category);
//		builder.append(", memberId=");
//		builder.append(memberId);
//		builder.append("]");
//		return builder.toString();
//	}

	
	
	
	
}
