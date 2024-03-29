package edu.cmu.dblp.model;

//Corresponds to inproceedings entity in DBLP
public class ConferencePaper extends Publication {

	private int conferenceId;
	private String pages;
	private String url;
	
	public int getConferenceId() {
		return conferenceId;
	}

	public void setConferenceId(int conferenceId) {
		this.conferenceId = conferenceId;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ConferencePaper(){
		super();
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof ConferencePaper) {
			if(this.getPublicationTitle().equals(((ConferencePaper) o).getPublicationTitle()))
				return true;
		}
		return false;
	}
}
