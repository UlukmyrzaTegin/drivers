package csu.service;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

import csu.domain.User;
import csu.domain.UserRole;

public class UserService implements Serializable{
	private static final long serialVersionUID = 1701636480826173119L;
	
	
	public static ArrayList<UserRole> getRoleTypes(JDBCConnectionPool connectionPool){
		String query = "SELECT id, name FROM user_role;";
		ArrayList<UserRole> roleTypes = new ArrayList<UserRole>();
        Connection conn = null;
        try {
            conn = connectionPool.reserveConnection();
            CallableStatement proc = conn.prepareCall(query);
            ResultSet rs = proc.executeQuery();
            if (rs != null) {
            	while (rs.next()) {
            		UserRole roleType = new UserRole();
            		roleType.setId(rs.getInt("id"));
            		roleType.setName(rs.getString("name"));
            		roleTypes.add(roleType);
            	}
            }
          conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionPool.releaseConnection(conn);
        }		
		return roleTypes;
		
	}
	public  static ArrayList<User> getUsers(Map<String, String> map, JDBCConnectionPool connectionPool){
		String query = "" +
				"SELECT " +
				"	u.id, " +
				"	u.username, " +
				"	u.surname, " +
				"	u.first_name, " +
				"	u.department, " +
				"	urole.id as role_id, " +
				"	urole.name as role_name " +
				"FROM " +
				"	\"dic_user\" u " +
				"	inner JOIN user_role urole ON u.user_role = urole.id " +
				"WHERE 1=1 ";
		for (Map.Entry<String, String> entry : map.entrySet()) {
			query += " and "+entry.getKey()+" like ? ";
					entry.getValue();
		}

		ArrayList<User> users = new ArrayList<User>();
        Connection conn = null;
        try {
            conn = connectionPool.reserveConnection();
            
            CallableStatement proc = conn.prepareCall(query);
    		int i = 1;
    		for (Map.Entry<String, String> entry : map.entrySet()) {
    			proc.setString(i++, "%"+entry.getValue()+"%");
    		}
            ResultSet rs = proc.executeQuery();
            if (rs != null) {
            	while (rs.next()) {
            		User u = new User();
            		u.setId(rs.getInt("id"));
            		u.setUserName(rs.getString("username"));
            		u.setSurName(rs.getString("surname"));
            		u.setFirstName(rs.getString("first_name"));
            		u.setDepartment(rs.getString("department"));
            		
            		UserRole roleType = new UserRole();
            		roleType.setId(rs.getInt("role_type_id"));
            		roleType.setName(rs.getString("role_name"));

            		u.setUserRole(roleType);
            		
            		users.add(u);
            	}
            }
          conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionPool.releaseConnection(conn);
        }		
		
		return users;
	}

}
