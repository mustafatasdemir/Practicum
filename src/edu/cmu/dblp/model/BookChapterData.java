package edu.cmu.dblp.model;

public class BookChapterData {
	
	private int bookId;
	private String bookChapterName;
	private double relevance;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookChapterName() {
		return bookChapterName;
	}

	public void setBookChapterName(String bookChapterName) {
		this.bookChapterName = bookChapterName;
	}

	public double getRelevance() {
		return relevance;
	}

	public void setRelevance(double relevance) {
		this.relevance = relevance;
	}

	public BookChapterData() {
		super();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof BookChapterData) {
			if(this.getBookChapterName().equals(((BookChapterData) o).getBookChapterName()))
				return true;
		}
		return false;
	}

}
