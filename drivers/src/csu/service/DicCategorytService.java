package csu.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import csu.domain.DicCategory;


	public abstract class DicCategorytService {		
		
		public static ArrayList<DicCategory> getOblast(JDBCConnectionPool connectionPool){
			String query = "SELECT code,cname,years_old FROM dic_category;";
			ArrayList<DicCategory> dicCategory = new ArrayList<DicCategory>();
	        Connection conn = null;
	        try {
	            conn = connectionPool.reserveConnection();
	            CallableStatement proc = conn.prepareCall(query);
	            ResultSet rs = proc.executeQuery();
	            if (rs != null) {
	            	while (rs.next()) {
	            		DicCategory del = new DicCategory();
	            		del.setCode(rs.getInt("code"));
	            		del.setCname(rs.getString("cname"));
	            		del.setYears_old(rs.getInt("years_old"));
	            		dicCategory.add(del);
	            	}
	            }
	          conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            connectionPool.releaseConnection(conn);
	        }		
			return dicCategory;
			
		}

}
