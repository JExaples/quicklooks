package com.sarvika.jdbcdesignpattern;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class SQLUtils {
	
	public static List<Map<String, Object>> resultSetToCollection(ResultSet resultSet) throws SQLException, ClassNotFoundException {
		
		ResultSetMetaData metaData = resultSet.getMetaData();
		List<Map<String, Object>> collection = new ArrayList<Map<String, Object>>();
		
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 1 ; i <= metaData.getColumnCount() ; i++) {
				String colName = metaData.getColumnName(i);
				String dataType = metaData.getColumnClassName(i);
				Object object = resultSet.getObject(i);
				
				map.put(colName, Class.forName(dataType).cast(object));
			}
			collection.add(map);
		}
		
		return collection;		
	}
	
	public static String getInsertQuery(String tableName, Map data) {
		
		Map<String, String> sqlMap = new HashMap<String, String>();
		
		for (Object key : data.keySet()) {
			Object val = data.get(key);
			String insertData = (val instanceof Number ? val.toString() : 
								"\"" + StringEscapeUtils.escapeJava(val.toString()) + "\"");
			
			sqlMap.put(key.toString(), insertData);
		}
		
		String query = "INSERT INTO " + tableName + "(" 
						+ StringUtils.join(sqlMap.keySet(), ",") + ") "
						+ "VALUES (" + StringUtils.join(sqlMap.values(), ",") + ");";
		
		return query;
	}
	
	public static String getPrimaryKeyName(Connection connection, String tableName) throws SQLException {
		DatabaseMetaData metaData = connection.getMetaData();
		ResultSet resultSet = metaData.getExportedKeys("", "", tableName);
		String key = "";
		while (resultSet.next()) {
			key = resultSet.getString("PKCOLUMN_NAME");
		}
		return key;
	}

}
