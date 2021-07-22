package vo;


public class ReplyDTO {
	
	
	private String replyId;
	private String replyTitle;
	private String replyContent;
	private String replyCreationDate;
	private int boardId;
	private String memberId;
	
	
	public ReplyDTO(String replyId, String replyContent, String memberId, int boardId) {
		super();
		this.replyId = replyId;
		this.replyContent = replyContent;
		this.boardId = boardId;
		this.memberId = memberId;
	}



	public ReplyDTO(String replyId, String replycontent, String replycreationdate, String memberId, int boardId, String replyContent, String replyCreationDate) {
		this.replyId = replyId;
		this.replyContent = replyContent;
		this.replyCreationDate = replyCreationDate;
		this.memberId = memberId;
		this.boardId = boardId;

		
	}


	public ReplyDTO(String replyContent, String memberId,int boardId) {
		this.replyContent = replyContent;
		this.memberId = memberId;
		this.boardId = boardId;
	}


	public ReplyDTO(int boardId) {
		this.boardId = boardId;
	}


	public ReplyDTO(String replyContent, String replyId) {
		this.replyContent = replyContent;
		this.replyId = replyId;
		
	}


	public ReplyDTO(String replyContent) {
		this.replyContent = replyContent;
	}



	public String getReplyId() {
		return replyId;
	}


	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public String getReplyContent() {
		return replyContent;
	}


	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}


	public String getReplyCreationDate() {
		return replyCreationDate;
	}


	public void setReplyCreationDate(String replyCreationDate) {
		this.replyCreationDate = replyCreationDate;
	}


	public int getBoardId() {
		return boardId;
	}


	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	@Override
	public String toString() {
		return "ReplyDTO [replyId=" + replyId + ", replyTitle=" + replyTitle + ", replyContent=" + replyContent
				+ ", replyCreationDate=" + replyCreationDate + ", boardId=" + boardId + ", memberId=" + memberId + "]";
	}
}
