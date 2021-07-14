package jdbc;


/*
 *  DTO(Data Transfer Object)
 *  VO(Value Object)  VO로 쓰는것이 좋음 *****
 *  Model ==> Data
 *  Item ==> StudentItem
 *  Bean ==> 콩,  자바에서 객체를 가리킬 때 사용 
 */

public class studentDTO {
	private String id;
	private String name;
	private String email;
	private String mobileNumber;
	
	
	
	public studentDTO(String id, String email, String mobileNumber) {
		this.id = id;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}
	
	public studentDTO(String id, String name, String email, String mobileNumber) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("studentDTO [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", email=");
		builder.append(email);
		builder.append(", mobileNumber=");
		builder.append(mobileNumber);
		builder.append("]");
		return builder.toString();
	}
	
}
