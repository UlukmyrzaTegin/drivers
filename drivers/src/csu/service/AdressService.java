package csu.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import csu.domain.Country;
import csu.domain.Raion;
import csu.domain.Settlement;
import csu.domain.UserRole;


	public abstract class AdressService {		
		
		public static ArrayList<Raion> getRaion(JDBCConnectionPool connectionPool){
			String query = "SELECT code,cname FROM dic_raion;";
			ArrayList<Raion> raion = new ArrayList<Raion>();
	        Connection conn = null;
	        try {
	            conn = connectionPool.reserveConnection();
	            CallableStatement proc = conn.prepareCall(query);
	            ResultSet rs = proc.executeQuery();
	            if (rs != null) {
	            	while (rs.next()) {
	            		Raion del = new Raion();
	            		del.setCode(rs.getInt("code"));
	            		del.setCname(rs.getString("cname"));
	            		raion.add(del);
	            	}
	            }
	          conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            connectionPool.releaseConnection(conn);
	        }		
			return raion;
		}

		public static ArrayList<Raion> getRaionById(Integer id , JDBCConnectionPool connectionPool){
			String query = "SELECT code,cname FROM dic_raion where oblast_code='"+id+"';";
			ArrayList<Raion> rai = new ArrayList<Raion>();
	        Connection conn = null;
	        try {
	            conn = connectionPool.reserveConnection();
	            CallableStatement proc = conn.prepareCall(query);
	            ResultSet rs = proc.executeQuery();
	            if (rs != null) {
	            	while (rs.next()) {
	            		Raion del = new Raion();
	            		del.setCode(rs.getInt("code"));
	            		del.setCname(rs.getString("cname"));
	            		rai.add(del);
	            	}
	            }
	          conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            connectionPool.releaseConnection(conn);
	        }		
			return rai;
			
		}
		
		public static ArrayList<Settlement> getSettlement(JDBCConnectionPool connectionPool){
			String query = "SELECT code,cname FROM dic_settlement;";
			ArrayList<Settlement> sett = new ArrayList<Settlement>();
	        Connection conn = null;
	        try {
	            conn = connectionPool.reserveConnection();
	            CallableStatement proc = conn.prepareCall(query);
	            ResultSet rs = proc.executeQuery();
	            if (rs != null) {
	            	while (rs.next()) {
	            		Settlement del = new Settlement();
	            		del.setCode(rs.getInt("code"));
	            		del.setCname(rs.getString("cname"));
	            		sett.add(del);
	            	}
	            }
	          conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            connectionPool.releaseConnection(conn);
	        }		
			return sett;
		}

		public static ArrayList<Settlement> getSettlementById(Integer id , JDBCConnectionPool connectionPool){
			String query = "SELECT code,cname FROM dic_settlement where raion_code='"+id+"';";
			ArrayList<Settlement> sett = new ArrayList<Settlement>();
			Connection conn = null;
			try {
				conn = connectionPool.reserveConnection();
				CallableStatement proc = conn.prepareCall(query);
				ResultSet rs = proc.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						Settlement del = new Settlement();
						del.setCode(rs.getInt("code"));
						del.setCname(rs.getString("cname"));
						sett.add(del);
					}
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connectionPool.releaseConnection(conn);
			}		
			return sett;
			
		}
		
}
