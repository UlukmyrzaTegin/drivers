package csu.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import csu.domain.Oblast;


	public abstract class OblastService {		
		
		public static ArrayList<Oblast> getOblast(JDBCConnectionPool connectionPool){
			String query = "SELECT code,cname FROM dic_oblast;";
			ArrayList<Oblast> oblast = new ArrayList<Oblast>();
	        Connection conn = null;
	        try {
	            conn = connectionPool.reserveConnection();
	            CallableStatement proc = conn.prepareCall(query);
	            ResultSet rs = proc.executeQuery();
	            if (rs != null) {
	            	while (rs.next()) {
	            		Oblast del = new Oblast();
	            		del.setCode(rs.getInt("code"));
	            		del.setCname(rs.getString("cname"));
	            		oblast.add(del);
	            	}
	            }
	          conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            connectionPool.releaseConnection(conn);
	        }		
			return oblast;
			
		}

}
