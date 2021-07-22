package vo;

import java.util.ArrayList;
import java.util.List;

public class DressVo {
	private String id;
	private String name;
	private String tel;
	private int price;
	private String fittingNumber;
	private String remark;
	
	
	public DressVo(String name, String tel, int price, String fittingNumber, String remark) {
		this.name = name;
		this.tel = tel;
		this.price = price;
		this.fittingNumber = fittingNumber;
		this.remark = remark;
	}
	
	public DressVo(String name) {
		this.name = name;
	}

	public DressVo(String id, String name, String tel, int price, String fittingNumber, String remark) {
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.price = price;
		this.fittingNumber = fittingNumber;
		this.remark = remark;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getFittingNumber() {
		return fittingNumber;
	}
	public void setFittingNumber(String fittingNumber) {
		this.fittingNumber = fittingNumber;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	   public List<String> getColumNames(){
		      List<String> list = new ArrayList<>();
		      list.add("DRESS_ID");
		      list.add("DRESS_NAME");
		      list.add("DRESS_TEL");
		      list.add("DRESS_PRICE");
		      list.add("FITTINGNUMBER");
		      list.add("REMARK");		     
		      return list;

		  
		   }
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("dressVo [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", tel=");
		builder.append(tel);
		builder.append(", price=");
		builder.append(price);
		builder.append(", fittingNumber=");
		builder.append(fittingNumber);
		builder.append(", remark=");
		builder.append(remark);
		builder.append("]");
		return builder.toString();
	}
}
