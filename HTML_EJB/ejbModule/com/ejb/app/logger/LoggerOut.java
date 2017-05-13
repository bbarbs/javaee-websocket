package com.ejb.app.logger;

import java.util.Date;

public class LoggerOut {

	private String classname;
	private String methodname;
	private Date start;
	
	public LoggerOut(String classname, String methodname) {
		this.classname = classname;
		this.methodname = methodname;
		reset();
	}
	
	/**
	 * Reset chronometer.
	 */
	public void reset(){
		start = new Date();
	}
	
	/**
	 * Print log.
	 * @param log text to display
	 * @param ok test result
	 * @return log + line break
	 */
	public String print(String log, boolean ok){
		
		// Prefix.
		String prefix='['+classname+"]["+methodname+"] ";
		
		// Add dashes.
		log+=' ';
		for(int i=0;i+log.length()<80;i++) log+='-';
		
		// Suffix.
		log+=(ok?" Okay":"Failed")+" ["
				+(new Date().getTime()-start.getTime())+" ms]";
		
		// Log.
		System.out.println(prefix+log);
		
		return log+'\n';
	}
}
