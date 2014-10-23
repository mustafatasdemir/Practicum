package edu.cmu.dblp.model;

// Corresponds to article entity in DBLP
public class JournalArticle extends Publication {

	private int journalId;
	private String pages;
	private String volume;
	private String column;
	private String month;
	private String url;
	
	public int getJournalId() {
		return journalId;
	}

	public void setJournalId(int journalId) {
		this.journalId = journalId;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof JournalArticle) {
			if(this.getPublicationTitle().equals(((JournalArticle) o).getPublicationTitle()))
				return true;
		}
		return false;
	}
}
