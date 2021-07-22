package vo;

public class MakeupVo {
	
	private String makeupId;
	private String makeupName;
	private String makeupTel;
	private int makeupPrice;
	private String RehersalMakeup;
	private String optionService;

	public MakeupVo(String makeupId, String makeupName, String makeupTel, int makeupPrice, String rehersalMakeup,
			String optionService) {
		this.makeupId = makeupId;
		this.makeupName = makeupName;
		this.makeupTel = makeupTel;
		this.makeupPrice = makeupPrice;
		RehersalMakeup = rehersalMakeup;
		this.optionService = optionService;
	}

	public MakeupVo(String makeupName, String makeupTel, int makeupPrice, String rehersalMakeup,
			String optionService) {
		this.makeupName = makeupName;
		this.makeupTel = makeupTel;
		this.makeupPrice = makeupPrice;
		RehersalMakeup = rehersalMakeup;
		this.optionService = optionService;
	}

	public MakeupVo(String makeupName) {
		this.makeupName = makeupName;
	}

	public String getMakeupId() {
		return makeupId;
	}

	public void setMakeupId(String makeupId) {
		this.makeupId = makeupId;
	}

	public String getMakeupName() {
		return makeupName;
	}

	public void setMakeupName(String makeupName) {
		this.makeupName = makeupName;
	}

	public String getMakeupTel() {
		return makeupTel;
	}

	public void setMakeupTel(String makeupTel) {
		this.makeupTel = makeupTel;
	}

	public int getMakeupPrice() {
		return makeupPrice;
	}

	public void setMakeupPrice(int makeupPrice) {
		this.makeupPrice = makeupPrice;
	}

	public String getRehersalMakeup() {
		return RehersalMakeup;
	}

	public void setRehersalMakeup(String rehersalMakeup) {
		RehersalMakeup = rehersalMakeup;
	}

	public String getOptionService() {
		return optionService;
	}

	public void setOptionService(String optionService) {
		this.optionService = optionService;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("makeupVo [makeupId=");
		builder.append(makeupId);
		builder.append(", makeupName=");
		builder.append(makeupName);
		builder.append(", makeupTel=");
		builder.append(makeupTel);
		builder.append(", makeupPrice=");
		builder.append(makeupPrice);
		builder.append(", RehersalMakeup=");
		builder.append(RehersalMakeup);
		builder.append(", optionService=");
		builder.append(optionService);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
