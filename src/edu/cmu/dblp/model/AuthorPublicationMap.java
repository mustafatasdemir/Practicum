package edu.cmu.dblp.model;

public class AuthorPublicationMap {
	
	private int mapId;
	private int publicationId;
	private int authorId;

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public int getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public AuthorPublicationMap() {
		super();
	}

}
