package csu.service;

import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.activation.DataHandler;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.attachment.ByteDataSource;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;



import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;

import csu.data.DatabaseHelper;
import csu.domain.AppForm;
import csu.domain.Applicant;
import csu.domain.Blood;
import csu.domain.Country;
import csu.domain.Delivery;
import csu.domain.Oblast;
import csu.domain.Raion;
import csu.domain.Settlement;
import csu.domain.Statment;
import csu.domain.Street;

import csu.enums.GenderType;

@SuppressWarnings("serial")
public class AppFormService implements Serializable{
	
	
	public static void getApplicationForms(Table table, Map<String, Object> map, int limit, int offset, JDBCConnectionPool connectionPool){
		table.removeAllItems();
		//String department = (String) UI.getCurrent().getSession().getAttribute("department");
		Integer user_id = (Integer) UI.getCurrent().getSession().getAttribute("user_id");
		String query = "" +
				"SELECT                                                              "
				+"				st.doc_no as doc_no,                                           "
				+"				st.doc_date,                                         "
				+"				st.pin as inn,                                              "
				+"				app.f_name as surName,                                          "
				+"				app.i_name as name,                                          "
				+"				card.cardid,                                          "
				+"				st.id                                          "
				+"				FROM                                                 "
				+"				     statment AS st                                    "
				+"				left Join card AS card  ON st.id = card.statmentid  "
				+"				Inner Join applicant AS app ON st.pin = app.pin      "
				+"				WHERE st.user_code = "+user_id.toString()+" ";
		
		Connection conn = null;
		for (Map.Entry<String, Object> entry : map.entrySet()) 
			query += " and "+entry.getKey()+" ? ";
		query += " LIMIT ? OFFSET ? ";
		try {
			conn = connectionPool.reserveConnection();

			CallableStatement proc = conn.prepareCall(query);
			int i = 1;
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if  (entry.getValue().getClass() == Integer.class)
					proc.setInt(i++, (Integer) entry.getValue());
				else if (entry.getValue().getClass() == String.class) 
					proc.setString(i++, (String) entry.getValue());
				else if (entry.getValue().getClass() == java.sql.Date.class) 
					proc.setDate(i++, (java.sql.Date) entry.getValue());
			}
			proc.setInt(i++, limit);
			proc.setInt(i++, offset);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					table.addItem(new Object[] { rs.getString("doc_no"),
							rs.getDate("doc_date"), rs.getString("inn"),
							rs.getString("surName"), rs.getString("name"),
							rs.getString("cardid")},rs.getInt("id"));
					/*table.addItem(new Object[] { 1,
							null, "123",
							"123", "123",
							"123"

					},1);*/
			//	table.setHeight("100");
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
	}
	
	public static Integer getTotalNumberOfApplicationForms(Map<String, Object> map, JDBCConnectionPool connectionPool){
		Integer result = 0;
		//String department = (String) UI.getCurrent().getSession().getAttribute("department");
		Integer user_id = (Integer) UI.getCurrent().getSession().getAttribute("user_id");
		String query ="" +
				"SELECT                                                              "
				+"		Count(st.id) as cnt	                                         "
				+"				FROM                                                 "
				+"				     statment AS st                                  "
				+"				WHERE st.user_code = "+user_id.toString()+" ";
		
		Connection conn = null;
		for (Map.Entry<String, Object> entry : map.entrySet()) 
			query += " and "+entry.getKey()+" ? ";
		try {
			conn = connectionPool.reserveConnection();

			CallableStatement proc = conn.prepareCall(query);
			int i = 1;
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				query += " and "+entry.getKey()+" ? ";
				if  (entry.getValue().getClass() == Integer.class)
					proc.setInt(i++, (Integer) entry.getValue());
				else if (entry.getValue().getClass() == String.class) 
					proc.setString(i++, (String) entry.getValue());
				else if (entry.getValue().getClass() == java.sql.Date.class) 
					proc.setDate(i++, (java.sql.Date) entry.getValue());
			}
			ResultSet rs = proc.executeQuery();
			if (rs != null && rs.next()) {
				result = rs.getInt("cnt");
				}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return result;
	}	
	
	public static AppForm getAppFormById(Integer id, DatabaseHelper dbHelper) {
		AppForm appForm = new AppForm();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        try {
            conn = dbHelper.getConnectionPool().reserveConnection();
            proc = conn.prepareCall("SELECT * FROM statment as st WHERE id = "+id+";");
            rs = proc.executeQuery();
            if (rs != null) {
                rs.next();
                appForm.setApplicant(getApplicantById(rs.getString("pin"), dbHelper));
                appForm.setId(id);
                Statment  st = new Statment();
                st.setDoc_no(rs.getString("doc_no"));
                st.setDoc_date(rs.getDate("doc_date"));
                st.setPin(rs.getString("pin"));
                Delivery delivery = new Delivery();
                delivery.setCode(rs.getInt("delivery_code"));
                st.setDeliveryId(delivery);
                st.setPass_no(rs.getString("pass_no"));
				st.setPass_date(rs.getDate("pass_date"));
                st.setMed_date(rs.getDate("med_date"));
                st.setStage(rs.getDate("stage"));
                st.setMod_date(rs.getDate("mod_date"));
                st.setPass_organ(rs.getString("pass_organ"));
                st.setOld_fio(rs.getString("old_fio"));
                st.setOld_bu_no(rs.getString("old_bu_no"));
                st.setOld_bu_date(rs.getDate("old_bu_date"));
                st.setOld_category(rs.getString("old_category"));
                st.setOld_mreo(rs.getString("old_mreo"));
                st.setAdditional_data(rs.getString("additional_data"));
                st.setMed_no(rs.getString("med_no"));
                st.setMed_organ(rs.getString("med_organ"));
                st.setAdress_lat(rs.getString("adress_lat"));
                st.setCrip_organ(rs.getString("crip_organ"));
                st.setDescr(rs.getString("descr"));
                st.setGlasses(rs.getBoolean("glasses"));
                Oblast oblast = new Oblast();
                oblast.setCode(rs.getInt("oblast_code"));
                st.setOblastId(oblast);
                Country co = new Country();
                co.setCode(rs.getInt("country_code"));
                st.setCountryId(co);
                Raion rai = new Raion();
                rai.setCode(rs.getInt("raion_code"));
                st.setRaionId(rai);
                Settlement sett = new Settlement();
                sett.setCode(rs.getInt("settlement_code"));
                st.setSettlementId(sett);
               /*
                (rs.getInteger("settlement_code"));*/
				appForm.setStatment(st);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	dbHelper.getConnectionPool().releaseConnection(conn);
        }		
		
		return appForm;
	}
	
	public static Applicant getApplicantById(String pin, DatabaseHelper dbHelper) {
		Applicant applicant = new Applicant();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        try {
            conn = dbHelper.getConnectionPool().reserveConnection();
            proc = conn.prepareCall("SELECT * FROM applicant as app WHERE pin = '"+pin+"';");
            rs = proc.executeQuery();
            if (rs != null) {
                rs.next();
                //appForm.setId(id);
               /* private GenderType genderId ;
            	private Country born_countryId ;*/
            	
              
                //ap.setGenderId(rs.getString("doc_no"));
                applicant.setFname(rs.getString("f_name"));
                applicant.setIname(rs.getString("i_name"));
                applicant.setOname(rs.getString("o_name"));
                applicant.setFnameLat(rs.getString("f_name_lat"));
                applicant.setInameLat(rs.getString("i_name_lat"));
                applicant.setOnameLat(rs.getString("o_name_lat"));
                applicant.setPin(rs.getString("pin"));
                applicant.setBorn_date(rs.getDate("born_date"));
                Blood blood = new Blood();
                blood.setCode(rs.getInt("blood_code"));
                applicant.setBlood_groupId(blood);
                Country country = new Country();
                country.setCode(rs.getInt("country_code"));
                applicant.setBorn_countryId(country);
              
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	dbHelper.getConnectionPool().releaseConnection(conn);
        }		
		
		return applicant;
	}
	
}
