package com.orkut;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.orkut.interf.dao.ILoginDao;
import com.orkut.pojo.Student;
import com.valid.Validation;

@Controller
public class LogiController {

	@Autowired
	private ILoginDao loginDao; 

	@Autowired
	private Stud student1;
	
	@Autowired
	private Stud student2;
	
	@Autowired
	private Validation orkutVal;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("studForm") Student stud, Model model1) throws ClassNotFoundException, SQLException{
		
		System.out.println("Saving into Database");
//		Validation orkutVal = new Validation();
//		OrkutValidation orkutVal = new OrkutValidation();
		boolean valideName = orkutVal.validateName(stud.getName());
		System.out.println(valideName + "validat name result");
		
		boolean validMobile = orkutVal.mobileNumberValidation(stud.getMail());
		
		if(!valideName && !validMobile) {
			System.out.println("Invalid Mobile/Name");
			model1.addAttribute("message", "Invalid Mobile/Name");
			return "result";
			
		}
//		ILoginDao loginDao = new LoginHibernateDao();
		
		String response = loginDao.loginUser(stud, model1);
		return response;
	}
	
	@RequestMapping(value="/getAllStud", method=RequestMethod.GET)
	public String getAllStud() throws SQLException{
		String name = "srik";
		student1.setName("ravi");
		student1.setAge(23);
		student1.setMail("ravi@gmail.com");
		
		loginDao.getAllStud(name);
		
//		student2.setName(null);
		System.out.println("testing!!!!!!!");
		return "result";
	}
	
		
	@RequestMapping(value="/getAllStudDetails", method=RequestMethod.GET)
	public String getAllStudents(Model model) throws SQLException{
		
		System.out.println("getAllSttudents method execution started");
		
		System.out.println("*********************************************");
		
		System.out.println(student1.getName() + "-----------------" + student1.getCollegeName());
		System.out.println(student2.getName() + "-----------------" + student2.getCollegeName());
		System.out.println(student1.getPassport().getPptNumber() + "&&&&&&&&" + student2.getPassport().getPptNumber());
		
		student1.setName("Venu");
		student1.setCollegeName("Aurora");
		student1.getPassport().setPptNumber("12345");
		
		student2.setName("Gopal");
		student2.setCollegeName("Aurora");
		student2.getPassport().setPptNumber("98765");
		
		System.out.println(student1.getName() + "-----------------" + student1.getCollegeName());
		System.out.println(student2.getName() + "-----------------" + student2.getCollegeName());
		System.out.println(student1.getPassport().getPptNumber() + "&&&&&&&&" + student2.getPassport().getPptNumber());
		
		System.out.println("*********************************************");
		
		
		
		
		
		Connection con=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring", "root", "admin");
			PreparedStatement stmt = con.prepareStatement("select *from student");
			
			ResultSet rs = stmt.executeQuery();	
			List<Student> list = new ArrayList<Student>();
			
			while(rs.next()){
				Student stud  = new Student();
				
				stud.setName(rs.getString("name"));
				stud.setPassword(rs.getString("password"));
				stud.setMail(rs.getString("email"));
				
				list.add(stud);
				
			}
			model.addAttribute("isFromRegister", true);
			model.addAttribute("studList", list);			
			
		} catch(MySQLIntegrityConstraintViolationException e) {
			System.out.println(e);
			return "result";
		} catch(Exception e) {
			return "result";
		} finally {
			con.close();
		}
		return "result";
	}
	
	@RequestMapping(value = "/getEditStud", method = RequestMethod.GET)
	public String getEditStudent(@RequestParam("email") String mail, Model model) throws ClassNotFoundException, SQLException{
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring", "root", "admin");
		
		PreparedStatement stmt = con.prepareStatement("select *from student where email=?");
		stmt.setString(1, mail);
		
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs);
		
		Student stud = new Student();
		
		while (rs.next()) {
			stud.setName(rs.getString("name"));
			stud.setPassword(rs.getString("password"));
			stud.setMail(rs.getString("email"));
		}
		
		
		model.addAttribute("student", stud);
		
		return "editStudent";
	}

	public void reg(){
		System.out.println("Bug fixed!!");
		System.out.println("tet");
	}
	public void testingGit(){
		System.out.println("Testing Git In progress!!");
	}

	public void loginImplementation(){
		System.out.println("Login Controller");

		System.out.println("Hi");

		System.out.println("Helo im");
	}
	
	public void method2(){
		System.out.println("Method2");
		System.out.println("Helo");
		System.out.println("Hi");
	}
}
