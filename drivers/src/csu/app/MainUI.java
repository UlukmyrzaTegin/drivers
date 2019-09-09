package csu.app;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import csu.data.DatabaseHelper;
import csu.view.LoginView;
import csu.view.oper.OperView;


@SuppressWarnings("serial")
public class MainUI extends UI {

	private Navigator navigator;
	private DatabaseHelper dbHelper;
	
	public static final String MAINVIEW = "";
	public static final String OPERATORVIEW = "operator";
	public static final String USERADMINVIEW = "userAdmin";
	public static final String KURATORCTVIEW = "kurator-ct";
	public static final String HEADOFORUBA = "oruba-head";

	@Override
	protected void init(VaadinRequest request) {
		dbHelper = new DatabaseHelper();
		navigator = new Navigator(this, this);
		navigator.addView(MAINVIEW, new LoginView(this));
		navigator.addView(OPERATORVIEW, new OperView(this));
		navigator.setErrorView(new LoginView(this));
		navigator.navigateTo(MAINVIEW);
	}

	public Navigator getNavigator() {
		return navigator;
	}

	public DatabaseHelper getDbHelper() {
		return dbHelper;
	}

	public void setDbHelper(DatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

}