package com.cg.miniproject.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.cg.miniproject.bean.MacBean;
import com.cg.miniproject.bean.MiniprojectBean;
import com.cg.miniproject.bean.ProgramBean;
import com.cg.miniproject.bean.ProgramsOfferedBean;
import com.cg.miniproject.dao.IMiniprojectDao;
import com.cg.miniproject.dao.MiniprojectDaoImpl;

public class MiniprojectServiceImpl implements IMiniprojectService {

	IMiniprojectDao dao=null;
	public int insertData(MiniprojectBean bean) {
		 dao=new MiniprojectDaoImpl();
		return dao.insertData(bean);
	}
	public String getProgramId() {
		dao=new MiniprojectDaoImpl();
			return dao.getProgramId();
	}
	@Override
	public boolean checkLogin(MacBean mac) {
		dao=new MiniprojectDaoImpl();
		ArrayList<MacBean> list = null;
		list = dao.allLogin(mac);
		
		int flag = 0;
		for(MacBean b:list)
		{
		
			if((b.getLoginId().equals(mac.getLoginId())) && (b.getPassword().equals(mac.getPassword()))){
				flag = 1;
				break;
			}
				
		}
		
		if(flag == 1){
			return true;
		}
		else return false;
		
	}
	@Override
	public boolean checkAdminLogin(MacBean mac) {
		dao=new MiniprojectDaoImpl();
		ArrayList<MacBean> list = null;
		list = dao.adminLogin(mac);
		
		int flag = 0;
		for(MacBean b:list)
		{
			//System.out.println(b);
			
			if((b.getLoginId().equals(mac.getLoginId())) && (b.getPassword().equals(mac.getPassword()))){
				flag = 1;
				break;
			}
				
		}
		
		if(flag == 1){
			return true;
		}
		else return false;
		
	
	}
	@Override
	public String getId(String id) {
		
		dao=new MiniprojectDaoImpl();
		return dao.getId(id);
	}
	
	public ArrayList<String> retrieveDetails(String university) {
		dao=new MiniprojectDaoImpl();
		return dao.retrieveDetails(university);
	}
	@Override
	public ArrayList<MiniprojectBean> getApplicantList(String programName) {
		
		dao=new MiniprojectDaoImpl();
		return dao.getApplicantList(programName);
	}
	@Override
	public int updateStatus(MiniprojectBean bean) {
		dao=new MiniprojectDaoImpl();
		return dao.updateStatus(bean);
	}
	@Override
	public ArrayList<MiniprojectBean> retrieveStatus(String pgmName) {
		dao=new MiniprojectDaoImpl();
		return dao.retrieveStatus(pgmName);
	}
	@Override
	public int updateCnfStatus(MiniprojectBean bean) {
		dao=new MiniprojectDaoImpl();
		return dao.updateCnfStatus(bean);
	}
	@Override
	public ArrayList<MiniprojectBean> getCnfApplicantList(String programName) {
		dao=new MiniprojectDaoImpl();
		return dao.getCnfApplicantList(programName);
	}

	@Override
	public ArrayList<MiniprojectBean> getApplicantStatus(int applicationId) {
		dao=new MiniprojectDaoImpl();
		return dao.getApplicantStatus(applicationId);
	}
	@Override
	public ArrayList<String> retrievePrograms() {
		dao=new MiniprojectDaoImpl();
		return dao.retrievePrograms();
	}
	@Override
	public ArrayList<MiniprojectBean> retrieveAllDetails() {
		dao=new MiniprojectDaoImpl();
		return dao.retrieveAllDetails();
	}
	@Override
	public ArrayList<ProgramBean> retrievePgms() {
		dao=new MiniprojectDaoImpl();
		return dao.retrievePgms();
	}
	@Override
	public boolean validateForm(String qualification) {
		if(qualification.equalsIgnoreCase("HSC")){
			return true;
		}
		else{
			System.out.println("Your qualification should be HSC");
			System.exit(0);
			return false;
		}
		
	}
	@Override
	public int addProgramOffered(
			ProgramsOfferedBean pgmbean) {
		dao=new MiniprojectDaoImpl();
		return dao.addProgramOffered(pgmbean);
	}
	@Override
	public int deleteProgramOffered(String programName) {
		dao=new MiniprojectDaoImpl();
		int flag=0;
		ArrayList<MiniprojectBean> list1=dao.getApplicantStatusList(programName);
		for(MiniprojectBean l:list1)
		{
			if(l.getStatus().equalsIgnoreCase("ACCEPTED")||l.getStatus().equalsIgnoreCase("CONFIRMED")||l.getStatus().equalsIgnoreCase("APPLIED"))
			{ 
				
				flag=0;
				break;
			}
			else if(l.getApplicationId()==0 || (l.getApplicationId()!=0 && l.getStatus().equalsIgnoreCase("REJECTED")))
			{
				
				flag=1;
			
				return dao.deleteProgramOffered(programName);
			}
		}
		return flag;
		
		
	}
	@Override
	public int updateDetail(ProgramsOfferedBean pgmbean) {
		dao=new MiniprojectDaoImpl();
		return dao.deleteProgramOffered(pgmbean);
	}
	@Override
	public int addScheduleProgram(ProgramBean progmbean) {
		
		return dao.addScheduleProgram(progmbean);
	}
	@Override
	public int deleteProgramSchedule(String programSchId) {
		dao=new MiniprojectDaoImpl();
		int flag=0;
		ArrayList<MiniprojectBean> list1=dao.getApplicantStatList(programSchId);
		for(MiniprojectBean l:list1)
		{
			if(l.getStatus().equalsIgnoreCase("ACCEPTED")||l.getStatus().equalsIgnoreCase("CONFIRMED")||l.getStatus().equalsIgnoreCase("APPLIED"))
			{ 
				
				flag=0;
				break;
			}
			else if(l.getApplicationId()==0 || (l.getApplicationId()!=0 && l.getStatus().equalsIgnoreCase("REJECTED")))
			{
				
				flag=1;
				
				return dao.deleteProgramOffered(programSchId);
			}
		}
		return flag;
		
	}
	@Override
	public boolean checkPattern(String firstName) {
		String ptn="[A-Z]{1}[A-Za-z]{2,20}";
		if(Pattern.matches(ptn,firstName))
		{
			return true;
		}
		else
		{
			System.out.println("Please enter valid name with first letter capital and minimum of 3 characters");
			System.exit(0);
			return false;
		}
	}
	@Override
	public boolean validateStatusCheck(ArrayList<MiniprojectBean> statusCheck) {
		if(statusCheck.isEmpty()){
			System.out.println("Application Id not found");
			return false;}
		else{
		return true;
	}
	}
	@Override
	public ArrayList<String> retrievePgmId() {
		dao=new MiniprojectDaoImpl();
		return dao.retrievePgmId();
	}
	@Override
	public ArrayList<String> retrievePgmName() {
		dao=new MiniprojectDaoImpl();
		return dao.retrievePgmName();
	}
	@Override
	public boolean checkRetrieveStatus(ArrayList<MiniprojectBean> list3) {
		if(list3.isEmpty()){
			System.out.println("No application is  available for confirmation");
			System.exit(0);
			return false;
			}
		else{
		return true;}
	}



}

