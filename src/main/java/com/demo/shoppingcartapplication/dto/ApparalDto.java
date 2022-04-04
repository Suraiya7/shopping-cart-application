package com.demo.shoppingcartapplication.dto;

public class ApparalDto {

	private int productId;

	private String productname;

	private int productQuant;

	private float price;

	private String type;

	private String brand;

	private String design;

	public ApparalDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApparalDto(int productId, String productname, int productQuant, float price, String type, String brand,
			String design) {
		super();
		this.productId = productId;
		this.productname = productname;
		this.productQuant = productQuant;
		this.price = price;
		this.type = type;
		this.brand = brand;
		this.design = design;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getProductQuant() {
		return productQuant;
	}

	public void setProductQuant(int productQuant) {
		this.productQuant = productQuant;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	@Override
	public String toString() {
		return "ApparalDto [productId=" + productId + ", productname=" + productname + ", productQuant=" + productQuant
				+ ", price=" + price + ", type=" + type + ", brand=" + brand + ", design=" + design + "]";
	}

}
