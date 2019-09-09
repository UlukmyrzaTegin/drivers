package csu.view.oper;


import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

import csu.app.MainUI;
import csu.tiles.FooterUI;
import csu.tiles.HeaderUI;
import csu.tiles.MainTiles;

public class OperView extends VerticalLayout implements View {
	private static final long serialVersionUID = -8361384254676931917L;
	private MainUI app; 
	public OperView(MainUI app){
		this.app = app;

	}

	@Override
	public void enter(ViewChangeEvent event) {
		if (getUI().getSession().getAttribute("user_id") == null) {
			app.getNavigator().navigateTo("");
		}
		else {
			setSizeFull();
	
			MainTiles mainUI = new MainTiles();
			removeAllComponents();
			addComponent(mainUI);
			mainUI.getHeaderLayout().addComponent(new HeaderUI(app.getNavigator())); 
			mainUI.getMenuLayout().addComponent(new OperMenu(mainUI,app)); 
			mainUI.getFooterLayout().addComponent(new FooterUI()); 
		//	mainUI.getBodyLayout().addComponent(new Listening());
		}
	}

}
