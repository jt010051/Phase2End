package com.login;

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

import com.register.RegRequirements;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
 
      
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        RegRequirements login = new RegRequirements();

        
        login.setUserName(userName);
        login.setPassword(password);
  
        	 Configuration con = new Configuration().configure().addAnnotatedClass(RegRequirements.class);
             ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties())
             		.buildServiceRegistry();
             
             SessionFactory sf = con.buildSessionFactory(reg);
             
             Session session =sf.openSession();
             Transaction tx = session.beginTransaction();
           

             
            


                 try  {
                     // start a transaction
                     // get an user object
                     login = (RegRequirements) session.createQuery("FROM RegRequirements WHERE userName = :username").setParameter("username", userName)
                         .uniqueResult();
                     if (login != null && login.getPassword().equals(password)) {
                         tx.commit();

                    	 response.setContentType("text/html");

              	        out.println("<div align=\"center\">");
              	        out.println("Login Successful! Welcome " +userName);
              	        out.println("</div>");                   
                     // commit transaction
                     }
                     else {
                    	 response.setContentType("text/html");

               	        out.println("<div align=\"center\">");
               	        out.println("Login Unccessful. Please Try Again");
               	        out.println("</div>"); 
               	        out.println("<center><b> <a href=\"http://localhost:8080/RegistrationApplication/Login.jsp\">Log-in</a></b></center>");
                    	 
                     }
                 } catch (Exception e) {
                     if (tx != null) {
                    	 tx.rollback();

                     }
                     e.printStackTrace();
                 }
             
           
             
             

                    
        
       
    	          
                   
       
            
            
    

        
              

		doGet(request, response);
	}

}
