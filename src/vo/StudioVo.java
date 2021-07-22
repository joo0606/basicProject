package vo;

public class StudioVo {
	
	private String studioId;
	private String studioName;
	private String studioTel;
	private int studioPrice;
	private String studioConcept;
	private String studioPlace;
	
	public StudioVo(String studioId, String studioName, String studioTel, int studioPrice, String studioConcept,
			String studioPlace) {
		this.studioId = studioId;
		this.studioName = studioName;
		this.studioTel = studioTel;
		this.studioPrice = studioPrice;
		this.studioConcept = studioConcept;
		this.studioPlace = studioPlace;
	}

	public StudioVo(String studioName, String studioTel, int studioPrice, String studioConcept, String studioPlace) {
		this.studioName = studioName;
		this.studioTel = studioTel;
		this.studioPrice = studioPrice;
		this.studioConcept = studioConcept;
		this.studioPlace = studioPlace;
	}

	public StudioVo(String studioName) {
		this.studioName = studioName;
	}

	public String getStudioId() {
		return studioId;
	}

	public void setStudioId(String studioId) {
		this.studioId = studioId;
	}

	public String getStudioName() {
		return studioName;
	}

	public void setStudioName(String studioName) {
		this.studioName = studioName;
	}

	public String getStudioTel() {
		return studioTel;
	}

	public void setStudioTel(String studioTel) {
		this.studioTel = studioTel;
	}

	public int getStudioPrice() {
		return studioPrice;
	}

	public void setStudioPrice(int studioPrice) {
		this.studioPrice = studioPrice;
	}

	public String getStudioConcept() {
		return studioConcept;
	}

	public void setStudioConcept(String studioConcept) {
		this.studioConcept = studioConcept;
	}

	public String getStudioPlace() {
		return studioPlace;
	}

	public void setStudioPlace(String studioPlace) {
		this.studioPlace = studioPlace;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("studioVo [studioId=");
		builder.append(studioId);
		builder.append(", studioName=");
		builder.append(studioName);
		builder.append(", studioTel=");
		builder.append(studioTel);
		builder.append(", studioPrice=");
		builder.append(studioPrice);
		builder.append(", studioConcept=");
		builder.append(studioConcept);
		builder.append(", studioPlace=");
		builder.append(studioPlace);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
