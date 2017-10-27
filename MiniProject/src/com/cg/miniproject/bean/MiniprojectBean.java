package com.cg.miniproject.bean;

import java.sql.Date;

public class MiniprojectBean {
	private int applicationId;
	
	private	String fullName;
	private Date dateOfBirth;
	private String qualification;
	private int marks;
	private String goals;
	private String email;
	private String programId;
	private String status;
	private Date interviewDate;
	private String university;
	

	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public MiniprojectBean(int applicationId, String fullName, String status,Date interviewDate) {
		super();
		this.applicationId = applicationId;
		this.fullName = fullName;
		this.status = status;
		this.interviewDate=interviewDate;
	}
	


	public MiniprojectBean(int id, String fullName2, Date dob,
			String qualification2, int marksObt, String goals2,
			String emailId, String pgmId, String status2, Date dateOfInterview) {
		super();
		this.applicationId = id;
		this.fullName = fullName2;
		this.dateOfBirth=dob;
		this.qualification=qualification2;
		this.marks=marksObt;
		this.goals=goals2;
		this.email=emailId;
		this.programId=pgmId;
		this.status = status2;
		this.interviewDate=dateOfInterview;
		
	}
	public MiniprojectBean() {
		// TODO Auto-generated constructor stub
	}
	public MiniprojectBean(int id, String fullName2, String status2) {
		this.applicationId = id;
		this.fullName = fullName2;
		this.status = status2;
	}
	
	public MiniprojectBean(int applicationId, String status) {
		super();
		this.applicationId = applicationId;
		this.status = status;
	}
	public String getFullName() {
		return fullName;
	}
	@Override
	public String toString() {
		return "MiniprojectBean [applicationId=" + applicationId
				+ ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth
				+ ", qualification=" + qualification + ", marks=" + marks
				+ ", goals=" + goals + ", email=" + email + ", programId="
				+ programId + ", status=" + status + ", interviewDate="
				+ interviewDate + "]";
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public java.sql.Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date date) {
		this.dateOfBirth = date;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getGoals() {
		return goals;
	}
	public void setGoals(String goals) {
		this.goals = goals;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public java.sql.Date getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}
	

	
	
}
