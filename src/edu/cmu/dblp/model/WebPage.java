package edu.cmu.dblp.model;

//Corresponds to www entity in DBLP
public class WebPage extends Publication {

	private String url;
	private String accessDate;
	
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(String accessDate) {
		this.accessDate = accessDate;
	}

	public WebPage(){
		super();
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof WebPage) {
			if(this.getPublicationTitle().equals(((WebPage) o).getPublicationTitle()))
				return true;
		}
		return false;
	}
}
