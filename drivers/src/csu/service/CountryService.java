package csu.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import csu.data.DatabaseHelper;
import csu.domain.Applicant;
import csu.domain.Blood;
import csu.domain.Country;


	public abstract class CountryService {		
		
		public static ArrayList<Country> getCountry(JDBCConnectionPool connectionPool){
			String query = "SELECT code,cname FROM dic_country;";
			ArrayList<Country> country = new ArrayList<Country>();
	        Connection conn = null;
	        try {
	            conn = connectionPool.reserveConnection();
	            CallableStatement proc = conn.prepareCall(query);
	            ResultSet rs = proc.executeQuery();
	            if (rs != null) {
	            	while (rs.next()) {
	            		Country del = new Country();
	            		del.setCode(rs.getInt("code"));
	            		del.setCname(rs.getString("cname"));
	            		country.add(del);
	            	}
	            }
	          conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            connectionPool.releaseConnection(conn);
	        }		
			return country;
			
		}
}
