package vo;

public class CoupleVo {
	
	private String coupleId;
	private String matchingday;
	private String memberMen;
	private String memberWomen;
	private String studioId;
	private String dressId;
	private String makeupId;
	private String hallId;
	
	public CoupleVo(String coupleId, String matchingday, String memberMen, String memberWomen,
			String studioId, String dressId, String makeupId, String hallId) {
		this.coupleId = coupleId;
		this.matchingday = matchingday;
		this.memberMen = memberMen;
		this.memberWomen = memberWomen;
		this.studioId = studioId;
		this.dressId = dressId;
		this.makeupId = makeupId;
		this.hallId = hallId;
	}

	public String getCoupleId() {
		return coupleId;
	}

	public void setCoupleId(String coupleId) {
		this.coupleId = coupleId;
	}

	public String getMatchingday() {
		return matchingday;
	}

	public void setMatchingday(String matchingday) {
		this.matchingday = matchingday;
	}


	public String getMemberMen() {
		return memberMen;
	}

	public void setMemberMen(String memberMen) {
		this.memberMen = memberMen;
	}

	public String getMemberWomen() {
		return memberWomen;
	}

	public void setMemberWomen(String memberWomen) {
		this.memberWomen = memberWomen;
	}

	public String getStudioId() {
		return studioId;
	}

	public void setStudioId(String studioId) {
		this.studioId = studioId;
	}

	public String getDressId() {
		return dressId;
	}

	public void setDressId(String dressId) {
		this.dressId = dressId;
	}

	public String getMakeupId() {
		return makeupId;
	}

	public void setMakeupId(String makeupId) {
		this.makeupId = makeupId;
	}

	public String getHallId() {
		return hallId;
	}

	public void setHallId(String hallId) {
		this.hallId = hallId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CoupleVo [coupleId=");
		builder.append(coupleId);
		builder.append(", matchingday=");
		builder.append(matchingday);
		builder.append(", currentlove=");
		builder.append(", memberMen=");
		builder.append(memberMen);
		builder.append(", memberWomen=");
		builder.append(memberWomen);
		builder.append(", manager=");
		builder.append(", studioId=");
		builder.append(studioId);
		builder.append(", dressId=");
		builder.append(dressId);
		builder.append(", makeupId=");
		builder.append(makeupId);
		builder.append(", hallId=");
		builder.append(hallId);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
