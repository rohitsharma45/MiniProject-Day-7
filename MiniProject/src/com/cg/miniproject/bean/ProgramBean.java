package com.cg.miniproject.bean;

import java.sql.Date;

public class ProgramBean {

	private String schedulePgmId,programName,location,university;
	private Date startDate,endDate;
	private int sessionsPerWeek;
	
	public ProgramBean(){
		
	}
	


	@Override
	public String toString() {
		return "ProgramBean [schedulePgmId=" + schedulePgmId + ", programName="
				+ programName + ", location=" + location + ", startDate="
				+ startDate + ", endDate=" + endDate + ", sessionsPerWeek="
				+ sessionsPerWeek + "]";
	}



	public ProgramBean(String schedulePgmId, String programName,
			String location, Date startDate, Date endDate, int sessionsPerWeek, String university) {
		super();
		this.schedulePgmId = schedulePgmId;
		this.programName = programName;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.sessionsPerWeek = sessionsPerWeek;
		this.university=university;
	}



	public String getUniversity() {
		return university;
	}



	public void setUniversity(String university) {
		this.university = university;
	}



	public String getSchedulePgmId() {
		return schedulePgmId;
	}
	public void setSchedulePgmId(String schedulePgmId) {
		this.schedulePgmId = schedulePgmId;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getSessionsPerWeek() {
		return sessionsPerWeek;
	}
	public void setSessionsPerWeek(int sessionsPerWeek) {
		this.sessionsPerWeek = sessionsPerWeek;
	}
	
}


