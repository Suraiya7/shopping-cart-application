package com.demo.shoppingcartapplication.model;

public class ProductModel {

	private int productId;

	private String productName;

	private float price;

	private int productQuant;

	private String genreOrType;
	private String publicationOrBrand;
	private String authorOrDesign;
	private String pictureURL;

	private boolean isProductAddedInCart;

	public ProductModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductModel(int productId, String productName, float price, int productQuant, String genreOrType,
			String publicationOrBrand, String authorOrDesign, String pictureURL, boolean isProductAddedInCart) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.productQuant = productQuant;
		this.genreOrType = genreOrType;
		this.publicationOrBrand = publicationOrBrand;
		this.authorOrDesign = authorOrDesign;
		this.pictureURL = pictureURL;
		this.isProductAddedInCart = isProductAddedInCart;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getProductQuant() {
		return productQuant;
	}

	public void setProductQuant(int productQuant) {
		this.productQuant = productQuant;
	}

	public String getGenreOrType() {
		return genreOrType;
	}

	public void setGenreOrType(String genreOrType) {
		this.genreOrType = genreOrType;
	}

	public String getPublicationOrBrand() {
		return publicationOrBrand;
	}

	public void setPublicationOrBrand(String publicationOrBrand) {
		this.publicationOrBrand = publicationOrBrand;
	}

	public String getAuthorOrDesign() {
		return authorOrDesign;
	}

	public void setAuthorOrDesign(String authorOrDesign) {
		this.authorOrDesign = authorOrDesign;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public boolean isProductAddedInCart() {
		return isProductAddedInCart;
	}

	public void setProductAddedInCart(boolean isProductAddedInCart) {
		this.isProductAddedInCart = isProductAddedInCart;
	}

	@Override
	public String toString() {
		return "ProductModel [productId=" + productId + ", productName=" + productName + ", price=" + price
				+ ", productQuant=" + productQuant + ", genreOrType=" + genreOrType + ", publicationOrBrand="
				+ publicationOrBrand + ", authorOrDesign=" + authorOrDesign + ", pictureURL=" + pictureURL
				+ ", isProductAddedInCart=" + isProductAddedInCart + "]";
	}

}
