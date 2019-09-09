package csu.view;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import csu.app.MainUI;
import csu.service.UserService;
import csu.util.Crypto;
import csu.domain.UserRole;;


public class LoginView extends VerticalLayout implements View {
	private static final long serialVersionUID = -6888545467042277381L;
	private TextField login = new TextField();
	private PasswordField paswd = new PasswordField();
	private Button loginButton;
	private ComboBox rolesCbx;
	private ArrayList<UserRole> roleTypes;
	
	public LoginView(final MainUI app) {
        setSizeFull();
		VerticalLayout vLayout = new VerticalLayout();
		
        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setHeight("25px");
        hLayout.setWidth("200px");
        hLayout.addComponent(new Label("Пользователь"));
        hLayout.addComponent(login);
        login.setWidth("200px");
        
        vLayout.addComponent(hLayout);
        vLayout.setComponentAlignment(hLayout,Alignment.MIDDLE_CENTER);
        HorizontalLayout hPasswordLayout = new HorizontalLayout();
        hPasswordLayout.addComponent(new Label("Пароль"));
        paswd.setWidth("200px");
        
      
        HorizontalLayout roleLayout = new HorizontalLayout();
        roleLayout.setWidth("200px");
        roleLayout.addComponent(new Label("Роль"));
        rolesCbx= new ComboBox();
        rolesCbx.setNullSelectionAllowed(false);
        rolesCbx.setInvalidAllowed(false);
        rolesCbx.setWidth("200px");
        roleTypes= UserService.getRoleTypes(app.getDbHelper().getConnectionPool());        
        
		Iterator<UserRole> it = roleTypes.iterator();    
	    while (it.hasNext()) {
	    	UserRole rt = it.next();
	    	rolesCbx.addItem(rt);
	    	rolesCbx.setItemCaption(rt, rt.getName());
	    }
	    roleLayout.addComponent(rolesCbx);
        
        login.setValue("nasmus");
        paswd.setValue("sumsan");
        hPasswordLayout.addComponent(paswd);
        vLayout.addComponent(hPasswordLayout);
        
        vLayout.addComponent(roleLayout);
        vLayout.setComponentAlignment(roleLayout,Alignment.MIDDLE_CENTER);
        hPasswordLayout.setHeight("25px");
        hPasswordLayout.setWidth("200px");
        vLayout.setComponentAlignment(hPasswordLayout,Alignment.MIDDLE_CENTER);
        loginButton = new Button("Войти", new Button.ClickListener() {
			private static final long serialVersionUID = -5514448801678437295L;

			@Override
            public void buttonClick(ClickEvent event) {
				JDBCConnectionPool connectionPool = app.getDbHelper().getConnectionPool();
		        Connection conn = null;
		        try {
		            conn = connectionPool.reserveConnection();
		           
		            conn.setAutoCommit(false);
		            
		            CallableStatement proc = conn.prepareCall(
		            		"SELECT " +
		            		"	usr.* " +
		            		"FROM " +
		            		"	dic_user usr" +
		            		"	INNER JOIN user_role urole ON usr.user_role = urole.id " +
		            		"WHERE " +
		            		"	usr.username = ? and usr.password = ? AND usr.user_role=?");
		            proc.setString(1, login.getValue());
		            proc.setString(2, Crypto.getEncodedStringAlg1(login.getValue(), paswd.getValue()));
		            proc.setInt(3, ((UserRole) rolesCbx.getValue()).getId());
		            ResultSet rs = proc.executeQuery();
		            if (rs != null && rs.next()) {
						UI.getCurrent().getSession().setAttribute("user_id", rs.getInt("id"));
						UI.getCurrent().getSession().setAttribute("user_first_name", rs.getString("first_name"));
						UI.getCurrent().getSession().setAttribute("user_surname", rs.getString("surname"));
						UI.getCurrent().getSession().setAttribute("department", rs.getString("department"));
						UI.getCurrent().getSession().setAttribute("user_role", rs.getInt("user_role"));
		            	if (rs.getInt("user_role")==1)
		            		app.getNavigator().navigateTo(MainUI.OPERATORVIEW);
		            	  else
								Notification.show("Ошибка:", " Не достаточно прав для входа в систему", Notification.Type.ERROR_MESSAGE);
			              login.setValue("");
			              paswd.setValue("");
			            }
			            else
							Notification.show("Ошибка:", " имя пользователя или пароль не верно", Notification.Type.ERROR_MESSAGE);
		          conn.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            connectionPool.releaseConnection(conn);
		        }		
             }
        });
		
        
        vLayout.addComponent(loginButton);
        vLayout.setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);
        addComponent(vLayout);
        setComponentAlignment(vLayout,Alignment.MIDDLE_CENTER);
    }        

	@Override
	public void enter(ViewChangeEvent event) {
	}

}
