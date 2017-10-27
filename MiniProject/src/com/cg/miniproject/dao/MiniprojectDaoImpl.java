package com.cg.miniproject.dao;

import java.io.IOException;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cg.miniproject.bean.MacBean;
import com.cg.miniproject.bean.MiniprojectBean;
import com.cg.miniproject.bean.ProgramBean;
import com.cg.miniproject.bean.ProgramsOfferedBean;
import com.cg.miniproject.dbutil.DbUtil;

public class MiniprojectDaoImpl implements IMiniprojectDao {

	ProgramsOfferedBean pgmbean=new ProgramsOfferedBean();
	Connection conn=null;
	int result=0;
	public int insertData(MiniprojectBean bean) {
		try {
			conn=DbUtil.getConnection();
			
		String insertQuery="Insert into Application values(Application_id_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=conn.prepareStatement(insertQuery);
		ps.setString(1,bean.getFullName());
		ps.setDate(2,bean.getDateOfBirth());
		ps.setString(3, bean.getQualification());
		ps.setInt(4,bean.getMarks());
		ps.setString(5,bean.getGoals());
		ps.setString(6,bean.getEmail());
		ps.setString(7,bean.getProgramId());
		ps.setString(8,bean.getStatus());
		ps.setDate(9,bean.getInterviewDate());
		ps.setString(10,bean.getUniversity());
		
		int res=ps.executeUpdate();
		System.out.println(res);
		
		String sql="select Application_id_seq.currval from Application";
		Statement pst=conn.createStatement();
		
		ResultSet rs=pst.executeQuery(sql);
		
		while(rs.next())
		{
			result=rs.getInt(1);
		}
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
	}
	

	@Override
	public ArrayList<MacBean> allLogin(MacBean mac) {
		String login=null;
		String password=null;
		ArrayList<MacBean> list = new ArrayList<MacBean>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select login_id,password from users where role IN ('MAC')";
			Statement pst=conn.createStatement();
			
			ResultSet rs=pst.executeQuery(sql);
			
			
			while(rs.next())
			{
				
				login=rs.getString(1);
				//System.out.println(login);
				password=rs.getString(2);
				//System.out.println(password);
				list.add(new MacBean(login,password));
				
			}

		} catch (IOException | SQLException e) {
			
			e.printStackTrace();
		}

		return list;
	}
	@Override
	public ArrayList<MacBean> adminLogin(MacBean mac) {
		String login=null;
		String password=null;
		ArrayList<MacBean> list = new ArrayList<MacBean>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select login_id,password from users where role IN ('Admin')";
			Statement pst=conn.createStatement();
			
			ResultSet rs=pst.executeQuery(sql);
			
			
			while(rs.next())
			{
				
				login=rs.getString(1);
				
				password=rs.getString(2);
				
				list.add(new MacBean(login,password));
			
			}

			
		} catch (IOException | SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	@Override
	public String getId(String id) {
		String programId=null;
		ArrayList list = new ArrayList();
		try {
			conn=DbUtil.getConnection();

			String sql="select Scheduled_program_id from Programs_Scheduled where ProgramName IN (?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				programId=rs.getString(1);
				
			}
		
	}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}

		return programId;
}
	
	public ArrayList<String> retrieveDetails(String university) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select * from Programs_Offered where University_name= ? ";
			PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setString(1,university);
			
			ResultSet rs=pst.executeQuery();
			
			
			while(rs.next())
			{
				String programName=rs.getString(1);
				String description=rs.getString(2);
				String eligibility=rs.getString(3);
				Long duration=rs.getLong(4);
				String courseDuration=(duration).toString();
				String degreeCertificate=rs.getString(5);
				list.add("Program Name: "+programName);
				list.add("Description of course: "+description);
				list.add("Eligibility Criteria: "+eligibility);
				list.add("Duration of course(in years): "+courseDuration);
				list.add("Degree certificate: "+degreeCertificate);
				list.add(" ");
			}
			System.out.println(" ");
		
	}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}

		return list;
	}
	@Override
	public ArrayList<MiniprojectBean> getApplicantList(String programName) {
		ArrayList<MiniprojectBean> list = new ArrayList<MiniprojectBean>();

		try {
			conn=DbUtil.getConnection();
			
			String sql="select * from Application where status  LIKE 'Applied' AND Scheduled_program_id=(select Scheduled_program_id from Programs_Scheduled where ProgramName IN (?))";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,programName);
			
			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				
				int id=rs.getInt(1);
				String fullName=rs.getString(2);
				Date dob=rs.getDate(3);
				String qualification=rs.getString(4);
				int marksObt=rs.getInt(5);
				String goals=rs.getString(6);
				String emailId=rs.getString(7);
				String pgmId=rs.getString(8);
				String status=rs.getString(9);
				Date dateOfInterview=rs.getDate(10);
				
				list.add(new MiniprojectBean(id,fullName,dob,qualification,marksObt,goals,emailId,pgmId,status,dateOfInterview));
			}
		
		}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int updateStatus(MiniprojectBean bean) {
		int  result=0;
		
	       try
	       {
			conn= DbUtil.getConnection();
			String insertQuery= "Update application set status=?,date_of_interview=? where application_id=? ";
			PreparedStatement ps= conn.prepareStatement(insertQuery);
			ps.setString(1,bean.getStatus());
			ps.setDate(2,bean.getInterviewDate());
			System.out.println(bean.getInterviewDate());
			ps.setInt(3,bean.getApplicationId());
			
			result=ps.executeUpdate();
			System.out.println(result);
		}
	       catch(IOException | SQLException e) {
				
				e.printStackTrace();
			}
	       return result;
		
	}
	@Override
	public ArrayList<MiniprojectBean> retrieveStatus(String programName) {
		ArrayList<MiniprojectBean> list = new ArrayList<MiniprojectBean>();
		int id=0;
		String fullName=null;
		String status=null;
		
		try {
			conn=DbUtil.getConnection();
			
			String sql="select Application_id,full_name,status from Application where status LIKE 'ACCEPTED' "; 
			
			Statement pst=conn.createStatement();
			
			ResultSet rs=pst.executeQuery(sql);
			
		
			while(rs.next())
			{
				id=rs.getInt(1);
			
				fullName=rs.getString(2);
				
				status=rs.getString(3);
				
				list.add(new MiniprojectBean(id,fullName,status));
			}
		
		}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}

		return list;
	}
	@Override
	public int updateCnfStatus(MiniprojectBean bean) {
		int  result=0;
		
	       try
	       {
			conn= DbUtil.getConnection();
			String insertQuery= "Update application set status=? where application_id=? ";
			PreparedStatement ps= conn.prepareStatement(insertQuery);
			ps.setString(1,bean.getStatus());
			ps.setInt(2,bean.getApplicationId());
			
			result=ps.executeUpdate();
			System.out.println(result);
		}
	       catch(IOException | SQLException e) {
				
				e.printStackTrace();
			}
	       return result;

	}
	@Override
	public ArrayList<MiniprojectBean> getCnfApplicantList(String programName) {
		ArrayList<MiniprojectBean> list = new ArrayList<MiniprojectBean>();
		int id=0;
		String fullName=null;
		String status=null;
		
		try {
			conn=DbUtil.getConnection();
			
			String sql="select Application_id,full_name,status from Application where status NOT LIKE 'Applied' AND Scheduled_program_id=(select Scheduled_program_id from Programs_Scheduled where ProgramName IN (?))";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,programName);
			
			ResultSet rs=ps.executeQuery();
			
		
			while(rs.next())
			{
				id=rs.getInt(1);
				fullName=rs.getString(2);
				status=rs.getString(3);
				
				list.add(new MiniprojectBean(id,fullName,status));
			}
		}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public ArrayList<ProgramBean> retrievePgms() {
		ArrayList<ProgramBean> list = new ArrayList<ProgramBean>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select * from Programs_Scheduled ";
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				String programID=rs.getString(1);
				String description=rs.getString(2);
				String location=rs.getString(3);
				Date startDate=rs.getDate(4);
				Date endDate=rs.getDate(5);
				int sessions=rs.getInt(6);
				String university=rs.getString(7);
				list.add(new ProgramBean(programID,description,location,startDate,endDate,sessions,university));

			}

	}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public ArrayList<MiniprojectBean> getApplicantStatus(int applicationId) {
		ArrayList<MiniprojectBean> list = new ArrayList<MiniprojectBean>();
		int id=0;
		String fullName=null;
		String status=null;
		
		try {
			conn=DbUtil.getConnection();
	

			String sql="select Application_id,full_name,status,Date_Of_Interview from Application where Application_id=?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1,applicationId);
			
			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				id=rs.getInt(1);
				fullName=rs.getString(2);
				status=rs.getString(3);
				Date interviewDate=rs.getDate(4);
				
				list.add(new MiniprojectBean(id,fullName,status,interviewDate));
			}
		}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public ArrayList<String> retrievePrograms() {
		ArrayList<String> list = new ArrayList<String>();
		
		String programName=null;
		
		
		try {
			conn=DbUtil.getConnection();
			
			String sql="select ProgramName from Programs_Offered";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				programName=rs.getString(1);

				list.add(programName);
			}
		}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public ArrayList<MiniprojectBean> retrieveAllDetails() {
	ArrayList<MiniprojectBean> list = new ArrayList<MiniprojectBean>();

		try {
			conn=DbUtil.getConnection();
			
			String sql="select * from Application where status  LIKE 'Applied' ";
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				
				int id=rs.getInt(1);
				String fullName=rs.getString(2);
				Date dob=rs.getDate(3);
				String qualification=rs.getString(4);
				int marksObt=rs.getInt(5);
				String goals=rs.getString(6);
				String emailId=rs.getString(7);
				String pgmId=rs.getString(8);
				String status=rs.getString(9);
				Date dateOfInterview=rs.getDate(10);
				
				list.add(new MiniprojectBean(id,fullName,dob,qualification,marksObt,goals,emailId,pgmId,status,dateOfInterview));
			}
		
		}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int addProgramOffered(ProgramsOfferedBean pgmbean) {
		
		int result=0;
	       try
	       {
			conn= DbUtil.getConnection();
			String insertQuery= "INSERT INTO Programs_Offered values(?,?,?,?,?)";
			PreparedStatement ps= conn.prepareStatement(insertQuery);
			ps.setString(1, pgmbean.getProgramName());
			ps.setString(2, pgmbean.getDesc());
			ps.setString(3, pgmbean.getEligibility());
			ps.setInt(4, pgmbean.getDuration());
			ps.setString(5, pgmbean.getCertificate());
	
			result=ps.executeUpdate();
		
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
return result;
}
	@Override
	public int deleteProgramOffered(String programName) {
		Connection conn= null;
		try{
			
			conn= DbUtil.getConnection();
			String sql = "delete from Participant where Scheduled_program_id=(select Scheduled_program_id from Programs_Scheduled where ProgramName=?)";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1,programName);
			int res=ps.executeUpdate();
			String sql1 = "delete from Application where Scheduled_program_id=(select Scheduled_program_id from Programs_Scheduled where ProgramName=?)";
			PreparedStatement pst= conn.prepareStatement(sql1);
			pst.setString(1,programName);
			int res1=pst.executeUpdate();
			String sql2 = "delete from Programs_Scheduled where ProgramName=? ";
			PreparedStatement pstr= conn.prepareStatement(sql2);
			pstr.setString(1,programName);
			int res2=pstr.executeUpdate();
			String sql3="delete from Programs_Offered where ProgramName =?";
			PreparedStatement pstrq= conn.prepareStatement(sql3);
			pstrq.setString(1,programName);
			result=pstrq.executeUpdate();
			System.out.println(result);
		}
		catch(IOException | SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return result;
		
	}
	@Override
	public int deleteProgramOffered(ProgramsOfferedBean pgmbean) {
		int  result=0;
		
	       try
	       {
			conn= DbUtil.getConnection();
			String insertQuery= "UPDATE Programs_Offered SET description=?,applicant_eligibility  =?,duration = ?,degree_certificate_offered =? where ProgramName=?";
			PreparedStatement ps= conn.prepareStatement(insertQuery);
			
			ps.setString(1, pgmbean.getDesc());
			ps.setString(2, pgmbean.getEligibility());
			ps.setInt(3, pgmbean.getDuration());
			ps.setString(4, pgmbean.getCertificate());
			ps.setString(5, pgmbean.getProgramName());
			result=ps.executeUpdate();
		
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return result;
	}
	@Override
	public int addScheduleProgram(ProgramBean progmbean) {
		int  result=0;
		
	       try
	       {
			conn= DbUtil.getConnection();
			String insertQuery= "insert into Programs_Scheduled values(?,?,?,?,?,?)";
			PreparedStatement ps= conn.prepareStatement(insertQuery);
			ps.setString(1, progmbean.getSchedulePgmId());
			ps.setString(2, progmbean.getProgramName());
			ps.setString(3, progmbean.getLocation());
			ps.setDate(4, progmbean.getStartDate());
			ps.setDate(5, progmbean.getEndDate());
			ps.setInt(6, progmbean.getSessionsPerWeek());
			result=ps.executeUpdate();
		
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
			return result; 
	}
	@Override
	public int deleteProgramSchedule(String programSchId) {
		Connection conn= null;
		int result=0;
		try{
			conn= DbUtil.getConnection();
			String sql = "delete from Participant where Scheduled_program_id=?";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1,programSchId);
			int res=ps.executeUpdate();
			String sql1 = "delete from Application where Scheduled_program_id=?";
			PreparedStatement pst= conn.prepareStatement(sql1);
			pst.setString(1,programSchId);
			int res1=pst.executeUpdate();
			String sql2 = "delete from Programs_Scheduled where Scheduled_program_id  = ? ";
			PreparedStatement pstr= conn.prepareStatement(sql2);
			pstr.setString(1,programSchId);
			result=pstr.executeUpdate();
		}
		catch(IOException|SQLException e)
		{
			
			System.out.println(e.getMessage());
		}
		return result;

		
	}
	
	@Override
	public ArrayList<MiniprojectBean> getApplicantStatusList(String programName) {
		ArrayList<MiniprojectBean> list = new ArrayList<MiniprojectBean>();

		try {
			conn=DbUtil.getConnection();
			
			String sql="select Application_id,status  from Application WHERE Scheduled_program_id =(select Scheduled_program_id from Programs_Scheduled where ProgramName = ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, programName);
			
			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				
				int applicationId=rs.getInt(1);
				String status=rs.getString(2);
				
				list.add(new MiniprojectBean(applicationId,status));
			
			}
		
		}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public String getProgramId() {
	
		return null;
	}


	@Override
	public ArrayList<String> retrievePgmId() {
		ArrayList<String> list = new ArrayList<String>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select Scheduled_program_id  from Programs_Scheduled ";
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				String programID=rs.getString(1);
			
				list.add(programID);

			}

	}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public ArrayList<String> retrievePgmName() {
		ArrayList<String> list = new ArrayList<String>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select ProgramName from Programs_Offered ";
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				String programName=rs.getString(1);
			
				list.add(programName);

			}

	}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public ArrayList<MiniprojectBean> getApplicantStatList(String programSchId) {
		ArrayList<MiniprojectBean> list = new ArrayList<MiniprojectBean>();

		try {
			conn=DbUtil.getConnection();
			
			String sql="select Application_id,status  from Application WHERE Scheduled_program_id =?";
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, programSchId);
			
			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				
				int applicationId=rs.getInt(1);
				String status=rs.getString(2);
				
				list.add(new MiniprojectBean(applicationId,status));
			
			}
		
		}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
}
