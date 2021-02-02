package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
 
		//Copying all the input parameters in to local variables
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        RegRequirements register = new RegRequirements();

        register.setFullName(fullName);
        
        register.setUserName(userName);
        register.setPassword(password);
        register.setEmail(email);
        if (null != email) {
            String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                response.setContentType("text/html");
            	out.println("Invalid Email: Try Again");


                response.sendRedirect("InvalidEmail.jsp");
            }
            else {
                Configuration con = new Configuration().configure().addAnnotatedClass(RegRequirements.class);
                ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties())
                		.buildServiceRegistry();
                
                SessionFactory sf = con.buildSessionFactory(reg);
                
                Session session =sf.openSession();
                Transaction tx = session.beginTransaction();
                session.save(register);
                tx.commit(); 

               
                   
                
                response.sendRedirect("Home.jsp");
            }
            }
    

        
       //The core Logic of the Registration application is present here. We are going to insert user data in to the database.
              

		doGet(request, response);
	}

}
