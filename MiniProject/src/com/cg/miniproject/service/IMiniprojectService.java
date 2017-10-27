package com.cg.miniproject.service;

import java.sql.Date;
import java.util.ArrayList;

import com.cg.miniproject.bean.MacBean;
import com.cg.miniproject.bean.MiniprojectBean;
import com.cg.miniproject.bean.ProgramBean;
import com.cg.miniproject.bean.ProgramsOfferedBean;

public interface IMiniprojectService {

	int insertData(MiniprojectBean bean);

	String getProgramId();

	boolean checkLogin(MacBean mac);

	boolean checkAdminLogin(MacBean mac);

	String getId(String string);

	ArrayList<String> retrieveDetails(String university);

	ArrayList<MiniprojectBean> getApplicantList(String programName);

	int updateStatus(MiniprojectBean bean);

	ArrayList<MiniprojectBean> retrieveStatus(String pgmName);

	int updateCnfStatus(MiniprojectBean bean);

	ArrayList<MiniprojectBean> getCnfApplicantList(String programName);

	ArrayList<ProgramBean> retrievePgms();

	ArrayList<MiniprojectBean> getApplicantStatus(int applicationId);

	ArrayList<String> retrievePrograms();

	ArrayList<MiniprojectBean> retrieveAllDetails();

	boolean validateForm(String qualification);

	int addProgramOffered(ProgramsOfferedBean pgmbean);

	int deleteProgramOffered(String programName1);

	int updateDetail(ProgramsOfferedBean pgmbean);

	int addScheduleProgram(ProgramBean progmbean);

	int deleteProgramSchedule(String programSchId);

	boolean checkPattern(String firstName);

	boolean validateStatusCheck(ArrayList<MiniprojectBean> statusCheck);

	ArrayList<String> retrievePgmId();

	ArrayList<String> retrievePgmName();

	boolean checkRetrieveStatus(ArrayList<MiniprojectBean> list3);

	



	//boolean validateUser(ArrayList<MacBean> list);

}
