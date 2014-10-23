package edu.cmu.dblp.model;

// Corresponds to incollection entity in DBLP
public class BookChapter extends Publication{

	private int bookId;
	private String pages;
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public BookChapter(){
		super();
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof BookChapter) {
			if(this.getPublicationTitle().equals(((BookChapter) o).getPublicationTitle()))
				return true;
		}
		return false;
	}
}
