package edu.cmu.dblp.model;

public class Authors {
	
	private int authorId;
	private String authorName;
	private String institution;

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public Authors() {
		super();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Authors) {
			if(this.getAuthorName().equals(((Authors) o).getAuthorName()))
				return true;
		}
		return false;
	}

}
