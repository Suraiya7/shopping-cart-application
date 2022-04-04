package com.demo.shoppingcartapplication.dto;

public class BookDto {

	private int productId;

	private String productname;

	private int productQuant;

	private float price;

	private String genre;

	private String author;

	private String publication;

	public BookDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookDto(int productId, String productname, int productQuant, float price, String genre, String author,
			String publication) {
		super();
		this.productId = productId;
		this.productname = productname;
		this.productQuant = productQuant;
		this.price = price;
		this.genre = genre;
		this.author = author;
		this.publication = publication;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	@Override
	public String toString() {
		return "BookDto [productId=" + productId + ", productname=" + productname + ", productQuant=" + productQuant
				+ ", price=" + price + ", genre=" + genre + ", author=" + author + ", publication=" + publication + "]";
	}

}
