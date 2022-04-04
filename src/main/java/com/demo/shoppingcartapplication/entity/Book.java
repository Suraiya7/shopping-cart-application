package com.demo.shoppingcartapplication.entity;

import javax.persistence.Entity;

@Entity
public class Book extends Product {

	private String genre;

	private String author;

	private String publication;

	private String bookPictureURL;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int productId, String productName, float price, int productQuant, String genre, String author,
			String publication, String bookPictureURL) {
		super(productId, productName, price, productQuant);
		this.genre = genre;
		this.author = author;
		this.publication = publication;
		this.bookPictureURL = bookPictureURL;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bookPictureURL == null) ? 0 : bookPictureURL.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((publication == null) ? 0 : publication.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookPictureURL == null) {
			if (other.bookPictureURL != null)
				return false;
		} else if (!bookPictureURL.equals(other.bookPictureURL))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (publication == null) {
			if (other.publication != null)
				return false;
		} else if (!publication.equals(other.publication))
			return false;
		return true;
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

	public String getBookPictureURL() {
		return bookPictureURL;
	}

	public void setBookPictureURL(String bookPictureURL) {
		this.bookPictureURL = bookPictureURL;
	}

	@Override
	public String toString() {
		return "Book [genre=" + genre + ", author=" + author + ", publication=" + publication + ", bookPictureURL="
				+ bookPictureURL + "]";
	}

}
