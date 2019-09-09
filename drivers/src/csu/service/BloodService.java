package csu.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import csu.domain.Blood;


	public abstract class BloodService {		
		
		public static ArrayList<Blood> getBlood(JDBCConnectionPool connectionPool){
			String query = "SELECT code,cname FROM dic_blood;";
			ArrayList<Blood> blood = new ArrayList<Blood>();
	        Connection conn = null;
	        try {
	            conn = connectionPool.reserveConnection();
	            CallableStatement proc = conn.prepareCall(query);
	            ResultSet rs = proc.executeQuery();
	            if (rs != null) {
	            	while (rs.next()) {
	            		Blood del = new Blood();
	            		del.setCode(rs.getInt("code"));
	            		del.setCname(rs.getString("cname"));
	            		blood.add(del);
	            	}
	            }
	          conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            connectionPool.releaseConnection(conn);
	        }		
			return blood;
			
		}

}
