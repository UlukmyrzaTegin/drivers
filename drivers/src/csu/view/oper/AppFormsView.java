package csu.view.oper;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import csu.app.MainUI;
import csu.service.AppFormService;
import csu.util.TableView;


import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component.Listener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;


public class AppFormsView extends TableView implements Listener {
	private static final long serialVersionUID = -2339856381309773979L;
	private Button findButton, newButton, editButton, deleteButton;
	private PropertysetItem item;
	private FieldGroup binder;
	private DateField dateFrom, dateTo;
	private Window subWindow;
	private MainUI app;
	private Map<String, Object> filterMap = new HashMap<String, Object>();
	
	
	public AppFormsView(MainUI app){
		this.app = app;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		item = new PropertysetItem();
		item.addItemProperty("dateFrom", new ObjectProperty<Date>(calendar.getTime()));
		item.addItemProperty("dateTo", new ObjectProperty<Date>(new Date()));
	
		item.addItemProperty("inn", new ObjectProperty<String>(""));
		item.addItemProperty("name", new ObjectProperty<String>(""));
		item.addItemProperty("surName", new ObjectProperty<String>(""));
		binder = new FieldGroup(item);
		
		propClass = new Object[] {String.class, Date.class, String.class, String.class, String.class, String.class};
		visibleColumns = new String[] {"doc_no", "doc_date", "inn", "name", "surName", "cardid"};
		columnHeaders  = new String[] {"№", "Дата", "ПИН",  "Имя", "Фамилия", "№ ВУ"};
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setImmediate(false);
		vLayout.setWidth("100.0%");
		vLayout.setHeight("100.0%");
		vLayout.setMargin(false);
		VerticalLayout tableLayout = createTableView(); 
		vLayout.addComponent(buildFilterPanel());
		vLayout.addComponent(buildComandPanel());
		vLayout.addComponent(tableLayout);
		vLayout.setExpandRatio(tableLayout, 1.0f);
		setCompositionRoot(vLayout);
	/*	table.addItemClickListener(new ItemClickListener() {
			private static final long serialVersionUID = 2623975941049751312L;

			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick())
					showSubWindow("editApp");
			}
		});  */
	}


	private Panel buildFilterPanel(){
		Panel panel = new Panel();
		panel.setWidth("100%");
		panel.setHeight("45px");
		HorizontalLayout hLayout = new HorizontalLayout();
		dateFrom = new DateField("Дата с");
		dateFrom.setDateFormat("dd-MM-yyyy");
		dateTo = new DateField("Дата по");
		dateTo.setDateFormat("dd-MM-yyyy");
		
        String nullItem = "-- все --";    
	    
	    binder.bind(dateFrom, "dateFrom");
		binder.bind(dateTo, "dateTo");
			
		hLayout.setWidth("-1px");
		hLayout.setHeight("-1px");
		hLayout.setSpacing(true);
		hLayout.addComponent(dateFrom);
		hLayout.addComponent(dateTo);
		
		TextField pinTF = (TextField) binder.buildAndBind("ПИН", "inn", TextField.class);
		pinTF.setWidth("100px");
		TextField nameTF = (TextField) binder.buildAndBind("Имя", "name", TextField.class);
		nameTF.setWidth("100px");
		TextField surnameTF = (TextField) binder.buildAndBind("Фамилия", "surName", TextField.class);
		surnameTF.setWidth("200px");
		hLayout.addComponent(pinTF);
		hLayout.addComponent(nameTF);
		hLayout.addComponent(surnameTF);
		
		findButton = new Button("Поиск");
		findButton.addListener(this);
		hLayout.addComponent(findButton);
        hLayout.setComponentAlignment(findButton, Alignment.BOTTOM_LEFT);
		panel.setContent(hLayout);
		
		return panel;
	}
	
	private Button buildButton(HorizontalLayout hLayout, String caption){
		Button button = new Button(caption);
		button.addListener(this);
		hLayout.addComponent(button);
        hLayout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
        return button;
	}
	
	
	private Panel buildComandPanel(){
		Panel panel = new Panel();
		panel.setWidth("100%");
		panel.setHeight("-1px");
		final HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setWidth("-1px");
		hLayout.setHeight("30px");
		hLayout.setSpacing(true);
		 
				newButton = buildButton(hLayout,"Добавить");
				editButton = buildButton(hLayout,"Редактировать");
				deleteButton = buildButton(hLayout,"Удалить");
		
		panel.setContent(hLayout);
		return panel;		
	}

	@Override
	public void componentEvent(Event event) {
		if (event.getClass() == Button.ClickEvent.class) {
			if (event.getSource() == findButton){
				filterMap.clear();
				filterMap.put("st.doc_date >=", new java.sql.Date(((Date) binder.getField("dateFrom").getValue()).getTime()));
				filterMap.put("st.doc_date <=", new java.sql.Date(((Date) binder.getField("dateTo").getValue()).getTime()));
				/*if (binder.getField("budgetType").getValue() != null && ((BudgetType)binder.getField("budgetType").getValue()).getCode()!=null)
					filterMap.put("bt.code =",(String)((BudgetType) binder.getField("budgetType").getValue()).getCode());
				if (binder.getField("rok").getValue() != null && ((Rok)binder.getField("rok").getValue()).getId()!=null)
					filterMap.put("bi.rok =",(Integer)((Rok) binder.getField("rok").getValue()).getId());
				if (binder.getField("status").getValue() != null && ((CardDocStatusType)binder.getField("status").getValue()).getId()!=null)
					filterMap.put("cdoc.status = ",(Integer)((CardDocStatusType) binder.getField("status").getValue()).getId());*/
				changePage();
			}
			else if (event.getSource() == newButton) 
				showSubWindow("newApp");
			else if (event.getSource() == editButton) 
				showSubWindow("editApp");
		}
		
	}
	
	@Override
	protected void changePage(){
		setNumberOfPages(AppFormService.getTotalNumberOfApplicationForms(filterMap, app.getDbHelper().getConnectionPool()));
		AppFormService.getApplicationForms(table, filterMap, 
				(Integer) navigatorItem.getItemProperty("pageSize").getValue(), 
				(Integer) navigatorItem.getItemProperty("pageSize").getValue()
				*(Integer) navigatorItem.getItemProperty("curPage").getValue()
				-(Integer) navigatorItem.getItemProperty("pageSize").getValue(),
				app.getDbHelper().getConnectionPool());
	}
	
	private void showSubWindow(String type){
		subWindow =  new Window("Информация о водителе");
		if (type == "newApp")
			subWindow.setContent(new AppFormEditView(null,subWindow,app));
		else {
			Object rowId = table.getValue();
			if (rowId == null)
				return;
			
			subWindow.setContent(new AppFormEditView((Integer)rowId,subWindow,app));
		}
		subWindow.setHeight("595px");
		subWindow.setWidth("805px");
		subWindow.setResizable(false);
		subWindow.setModal(true);        
		getUI().addWindow(subWindow);				
	}
	
	
}

  
   
