package com.web.app.listener;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ejb.app.session.EquipmentBeanLocal;

/**
 * Application Lifecycle Listener implementation class Init
 *
 */
@WebListener
public class Init implements ServletContextListener {

	@Inject
	private EquipmentBeanLocal equip;
	
    /**
     * Default constructor. 
     */
    public Init() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	
    	int a;
    	
    	for(int i=1; i<=10; i++) {
    		a = i + 1;
    		for(int b=a; a<=i; a++) {
    			System.out.println(i + " " + b);
    		}    		
    	}
    	
    	try {
			equip.init();		
		} catch (Exception e) {			
			e.printStackTrace();
		}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
