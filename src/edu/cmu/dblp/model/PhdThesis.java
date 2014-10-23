package edu.cmu.dblp.model;

//Corresponds to phd-thesis entity in DBLP
public class PhdThesis extends Publication {

	private int schoolId;
	private String department;
	private String advisorId;

	public PhdThesis(){
		super();
	}
	
	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAdvisorId() {
		return advisorId;
	}

	public void setAdvisorId(String advisorId) {
		this.advisorId = advisorId;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof PhdThesis) {
			if(this.getPublicationTitle().equals(((PhdThesis) o).getPublicationTitle()))
				return true;
		}
		return false;
	}
}
