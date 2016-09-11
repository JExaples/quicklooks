package com.sarvika.jdbcdesignpattern;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Glob {
	
	private static final Logger logger = Logger.getLogger(Glob.class);

	/*
	 * ====================================================
	 * 			APPLICATION SETTINGS VARIABLES
	 * ====================================================
	 */
	private static Connection connection 	= null;
	
	private static String dbuser 			= "";
	private static String dbpass			= "";
	private static String dburl				= "";
	
	/*
	 * ====================================================
	 * 			GLOB VARIABLES
	 * ====================================================
	 */
	private static boolean loadedYet = false;
	
	/*
	 * =======================================================
	 * 			GLOB METHODS TO GET STATIC CONTENT
	 * =======================================================
	 */
	public static Connection getConnection() {
		checkLoadedYet();
		return connection;
	}
	
	/*
	 * ======================================================
	 * 			GLOB UTILITY MEHTODS
	 * ======================================================
	 */	
	private static void checkLoadedYet() {
		if (!loadedYet) {
			readApplicationProperties();
			loadedYet = true;
		}
	}	
	
	private static void readApplicationProperties() {
		
		InputStream inputStream = null;
		
		try {
			
			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("DesignPatternsExample.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			
			readApplicationProperties(properties);
			
			inputStream.close();
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dburl, dbuser, dbpass);
			
		} catch (Exception ex) {
			logger.error("DP203: Cannot read application properties: " + ex.getMessage(), ex);
		}
	}
	
	private static void readApplicationProperties(Properties props) {
		logger.info("Start reading application properties...");
		
		dburl			= props.getProperty("dburl", dburl);
		dbuser			= props.getProperty("dbuser", dbuser);
		dbpass			= props.getProperty("dbpass", dbpass);
		
		logger.info("Application properties read.");
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException ex) {
			logger.error("Error while closing connection: " + ex.getMessage(), ex);
		}
	}
	
}
