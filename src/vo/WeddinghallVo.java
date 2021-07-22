package vo;

public class WeddinghallVo {
	
	private String hallId;
	private String hallName;
	private String hallTel;
	private int hallPrice;
	private String hallCapacity;
	
	public WeddinghallVo(String hallId, String hallName, String hallTel, int hallPrice, String hallCapacity) {
		this.hallId = hallId;
		this.hallName = hallName;
		this.hallTel = hallTel;
		this.hallPrice = hallPrice;
		this.hallCapacity = hallCapacity;
	}

	public String getHallId() {
		return hallId;
	}

	public WeddinghallVo(String hallName) {
		this.hallName = hallName;
	}

	public WeddinghallVo(String hallName, String hallTel, int hallPrice, String hallCapacity) {
		this.hallName = hallName;
		this.hallTel = hallTel;
		this.hallPrice = hallPrice;
		this.hallCapacity = hallCapacity;
	}

	public void setHallId(String hallId) {
		this.hallId = hallId;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public String getHallTel() {
		return hallTel;
	}

	public void setHallTel(String hallTel) {
		this.hallTel = hallTel;
	}

	public int getHallPrice() {
		return hallPrice;
	}

	public void setHallPrice(int hallPrice) {
		this.hallPrice = hallPrice;
	}

	public String getHallCapacity() {
		return hallCapacity;
	}

	public void setHallCapacity(String hallCapacity) {
		this.hallCapacity = hallCapacity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("weddinghallVo [hallId=");
		builder.append(hallId);
		builder.append(", hallName=");
		builder.append(hallName);
		builder.append(", hallTel=");
		builder.append(hallTel);
		builder.append(", hallPrice=");
		builder.append(hallPrice);
		builder.append(", hallCapacity=");
		builder.append(hallCapacity);
		builder.append("]");
		return builder.toString();
	}

	
	
}
