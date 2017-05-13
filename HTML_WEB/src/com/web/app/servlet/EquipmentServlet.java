package com.web.app.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejb.app.jms.SenderMessageLocal;
import com.ejb.app.logger.LoggerOut;
import com.ejb.app.session.EquipmentBeanLocal;

/**
 * Servlet implementation class EquipmentServlet
 */
@WebServlet("/EquipmentServlet")
public class EquipmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	private EquipmentBeanLocal equip;
	
	/**
	 * Inject SenderMessageLocal session bean local interface.
	 */
	@Inject
	private SenderMessageLocal sender;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EquipmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Logger.
				LoggerOut log = new LoggerOut(this.getClass().getSimpleName(), new Exception().getStackTrace()[0].getMethodName());
								
				response.getWriter().print("\nTest application using Wildfly 8.\n\n");
				
				// Get all list.
				//System.out.println("Query data");
				//equip.getAllList();
				//System.out.println("Save million data.");
				//test.addData("CAT45", "IU78", "Travelling", 12, "Production", "logimine");
				
				// Confirm doGet request received.
				response.getWriter().print(log.print("Get servlet request",true));
								
				log.reset();
				
				try {
					equip.hello();
					response.getWriter().print(log.print("Call local EJB",true));
				
					// Add a row in Simple entity.
					try{
						equip.persistData();
						response.getWriter().print(log.print("Append a row in database",true));
					}catch(Exception e){
						response.getWriter().print(log.print("Append a row in database",false));
					}
					
					// Test of initialization by web application context listener.
					if(equip.isInit()) response.getWriter().print(log.print("Web context listener", true));
					else response.getWriter().print(log.print("Web context listener", false));
					
					// JMS simple test.
					try {
						sender.sendMessage("Test JMSContext(JMS 2.0)");
						response.getWriter().print(log.print("JMS 2.0 Topic Sent",true));
					} catch (Exception e) {
						response.getWriter().print(log.print("JMS 2.0 Topic Sent",false));
					}			
				} catch (Exception e) {			
					response.getWriter().print(log.print("Call local EJB",false));
				}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
