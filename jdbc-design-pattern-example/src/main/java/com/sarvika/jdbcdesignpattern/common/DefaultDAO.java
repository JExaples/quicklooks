package com.sarvika.jdbcdesignpattern.common;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sarvika.jdbcdesignpattern.Glob;
import com.sarvika.jdbcdesignpattern.SQLUtils;

public class DefaultDAO {
	
	private static final Logger logger = Logger.getLogger(DefaultDAO.class);
	
	protected List<Map<String, Object>> listAll(String tableName) {
		ResultSet resultSet = null;
		List<Map<String, Object>> result = null;
		try {
			Connection connection = Glob.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName);
			resultSet = preparedStatement.executeQuery();
			result = SQLUtils.resultSetToCollection(resultSet);
		} catch (Exception ex) {
			logger.error("Error while listing data from table " + tableName + ": " + ex.getMessage(), ex);
		}
		return result;
	}
	
	protected Map<String, Object> get(String tableName, Serializable id) {
		ResultSet resultSet = null;
		Map<String, Object> result = null;
		try {
			Connection connection = Glob.getConnection();
			
			String query = "SELECT * FROM " + tableName + " WHERE " + SQLUtils.getPrimaryKeyName(connection, tableName)
							+ " = " + (id instanceof Number ? id : "\"" + id + "\"") + ";";
			
			PreparedStatement statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			
			List<Map<String, Object>> results = SQLUtils.resultSetToCollection(resultSet);
			if (results.size() > 0)
				result = results.get(0);
		} catch (Exception ex) {
			logger.error("Error while listing data from table " + tableName + ": " + ex.getMessage(), ex);
		}
		
		return result;
	}
	
	protected void save(String tableName, Map<String, Object> data) {
		try {
			Connection connection = Glob.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQLUtils.getInsertQuery(tableName, data));
			statement.executeUpdate();
			logger.info("Record inserted into table " + tableName);
		} catch (SQLException ex) {
			logger.error("Error while saving data into database: " + ex.getMessage(), ex);
		}
	}
	
	protected void delete(String tableName, Serializable id) {
		try {
			Connection connection = Glob.getConnection();
			
			String query = "DELETE FROM " + tableName + " WHERE " + SQLUtils.getPrimaryKeyName(connection, tableName)
							+ " = " + (id instanceof Number ? id : "\"" + id + "\"") + ";";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeUpdate();
			logger.info("Record deleted from table " + tableName);
		} catch (SQLException ex) {
			logger.error("Error while deleting data form table " + tableName + ": " + ex.getMessage(), ex);
		}
	}
	
	protected List<Map<String, Object>> evictGet(String query) {
		ResultSet resultSet = null;
		List<Map<String, Object>> result = null;
		try {
			Connection connection = Glob.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			result = (List<Map<String, Object>>) SQLUtils.resultSetToCollection(resultSet);
		} catch (Exception ex) {
			logger.error("Error while executing query " + query + ": " + ex.getMessage(), ex);
		}
		return result;
	}
	
}
