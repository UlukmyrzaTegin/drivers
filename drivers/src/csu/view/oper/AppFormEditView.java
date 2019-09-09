package csu.view.oper;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.cxf.transport.jms.wsdl.DeliveryModeType;

import com.sun.enterprise.security.integration.AppServSecurityContext;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component.Listener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import csu.app.MainUI;
import csu.service.BloodService;
import csu.service.DeliveryService;
import csu.service.CountryService;
import csu.service.OblastService;
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
import csu.service.AppFormService;
import csu.service.AdressService;
import csu.util.CommandButtonsLayout;
import csu.util.EnhancedFieldGroupFieldFactory;

public class AppFormEditView extends CustomComponent implements Listener{
	private static final long serialVersionUID = 6256540909821472692L;
	private AppForm appForm;
	private Button submit, cancel;
	private FieldGroup binder;
	private Window subWindow;
	private MainUI app;
	private ComboBox cBoxDelivery, cBoxBlood, cBoxBornCountry, cBoxOblast, cBoxCountry, cBoxRaion, cBoxSettl;
	private CheckBox chekGlasses;
	private ArrayList<Delivery> delivery;
	private ArrayList<Blood> blood;
	private ArrayList<Country> country;
	private ArrayList<Oblast> oblast;
	private ArrayList<Raion> raion;
	private ArrayList<Settlement> settlement;
	public AppFormEditView(Integer id, Window sWindow, MainUI app) {
		this.app = app;
		subWindow = sWindow;
		delivery = DeliveryService.getDelivery(app.getDbHelper().getConnectionPool());
		blood = BloodService.getBlood(app.getDbHelper().getConnectionPool());
		country = CountryService.getCountry(app.getDbHelper().getConnectionPool());
		oblast = OblastService.getOblast(app.getDbHelper().getConnectionPool());
	//	raion = AdressService.getRaionById(id,app.getDbHelper().getConnectionPool());
		Integer num = id;
		if (id == null)
			appForm = new AppForm();
		else {
		appForm = AppFormService.getAppFormById(id, app.getDbHelper()) ;
		raion = AdressService.getRaionById(appForm.getStatment().getOblastId().getCode(),app.getDbHelper().getConnectionPool());
		settlement = AdressService.getSettlementById(appForm.getStatment().getSettlementId().getCode(),app.getDbHelper().getConnectionPool());
		}
    	BeanItem<AppForm>  agrItem = new BeanItem<AppForm> (appForm);
        agrItem.addItemProperty("doc_no", new MethodProperty<Statment>(appForm.getStatment(), "doc_no"));
        agrItem.addItemProperty("doc_date", new MethodProperty<Statment>(appForm.getStatment(), "doc_date"));
        agrItem.addItemProperty("pin", new MethodProperty<Statment>(appForm.getStatment(), "pin"));
        agrItem.addItemProperty("deliveryId", new MethodProperty<Statment>(appForm.getStatment(), "deliveryId"));
        agrItem.addItemProperty("pass_no", new MethodProperty<Statment>(appForm.getStatment(), "pass_no"));
        agrItem.addItemProperty("pass_date", new MethodProperty<Statment>(appForm.getStatment(), "pass_date"));
        agrItem.addItemProperty("med_date", new MethodProperty<Statment>(appForm.getStatment(), "med_date"));
        agrItem.addItemProperty("stage", new MethodProperty<Statment>(appForm.getStatment(), "stage"));
        agrItem.addItemProperty("glasses", new MethodProperty<Statment>(appForm.getStatment(), "glasses"));
        agrItem.addItemProperty("mod_date", new MethodProperty<Statment>(appForm.getStatment(), "mod_date"));
        agrItem.addItemProperty("pass_organ", new MethodProperty<Statment>(appForm.getStatment(), "pass_organ"));
        agrItem.addItemProperty("old_fio", new MethodProperty<Statment>(appForm.getStatment(), "old_fio"));
        agrItem.addItemProperty("old_bu_no", new MethodProperty<Statment>(appForm.getStatment(), "old_bu_no"));
        agrItem.addItemProperty("old_bu_date", new MethodProperty<Statment>(appForm.getStatment(), "old_bu_date"));
        agrItem.addItemProperty("old_category", new MethodProperty<Statment>(appForm.getStatment(), "old_category"));
        agrItem.addItemProperty("old_mreo", new MethodProperty<Statment>(appForm.getStatment(), "old_mreo"));
        agrItem.addItemProperty("additional_data", new MethodProperty<Statment>(appForm.getStatment(), "additional_data"));
        agrItem.addItemProperty("med_no", new MethodProperty<Statment>(appForm.getStatment(), "med_no"));
        agrItem.addItemProperty("med_organ", new MethodProperty<Statment>(appForm.getStatment(), "med_organ"));
        agrItem.addItemProperty("adress_lat", new MethodProperty<Statment>(appForm.getStatment(), "adress_lat"));
        agrItem.addItemProperty("crip_organ", new MethodProperty<Statment>(appForm.getStatment(), "crip_organ"));
        agrItem.addItemProperty("descr", new MethodProperty<Statment>(appForm.getStatment(), "descr"));
        agrItem.addItemProperty("oblastId", new MethodProperty<Statment>(appForm.getStatment(), "oblastId"));
        agrItem.addItemProperty("countryId", new MethodProperty<Statment>(appForm.getStatment(), "countryId"));
        agrItem.addItemProperty("raionId", new MethodProperty<Statment>(appForm.getStatment(), "raionId"));
        
       // agrItem.addItemProperty("streetId", new MethodProperty<Statment>(appForm.getStatment(), "streetId"));
        agrItem.addItemProperty("settlementId", new MethodProperty<Statment>(appForm.getStatment(), "settlementId"));
      
    
        agrItem.addItemProperty("glasses", new MethodProperty<Applicant>(appForm.getApplicant(), "pin"));
        agrItem.addItemProperty("fname", new MethodProperty<Applicant>(appForm.getApplicant(), "fname"));
        agrItem.addItemProperty("iname", new MethodProperty<Applicant>(appForm.getApplicant(), "iname"));
        agrItem.addItemProperty("oname", new MethodProperty<Applicant>(appForm.getApplicant(), "oname"));
        agrItem.addItemProperty("fnameLat", new MethodProperty<Applicant>(appForm.getApplicant(), "fnameLat"));
        agrItem.addItemProperty("inameLat", new MethodProperty<Applicant>(appForm.getApplicant(), "inameLat"));
        agrItem.addItemProperty("onameLat", new MethodProperty<Applicant>(appForm.getApplicant(), "onameLat"));
        agrItem.addItemProperty("born_date", new MethodProperty<Applicant>(appForm.getApplicant(), "born_date"));
        agrItem.addItemProperty("born_countryId", new MethodProperty<Applicant>(appForm.getApplicant(), "born_countryId"));
        agrItem.addItemProperty("blood_groupId", new MethodProperty<Applicant>(appForm.getApplicant(), "blood_groupId"));
        binder = new FieldGroup(agrItem);
        binder.setBuffered(false);
        binder.setFieldFactory(new EnhancedFieldGroupFieldFactory());
        setSizeFull();
		setCompositionRoot(buildMainLayout());

	}

	@Override
	public void componentEvent(Event event) {
		if (event.getClass() == Button.ClickEvent.class) {
			if (event.getSource() == submit){
			}
			else if (event.getSource() == cancel) 
				this.getUI().removeWindow(subWindow);
		}
	}

	private AbsoluteLayout buildMainLayout() {
		AbsoluteLayout mainLayout = new AbsoluteLayout();
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setHeight("545px");
		vLayout.setWidth("800px");
		
		TabSheet tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		       
        tab.addTab(createTab1Layout(), "Заявление");
        tab.addTab(createTab2Layout(), "Медсправка и адрес");
        tab.addTab(createTab3Layout(), "Категории");
        tab.addTab(createTab4Layout(), "Старый ВУ");
        vLayout.addComponent(tab);
        vLayout.setExpandRatio(tab, 1.0f);
       CommandButtonsLayout hLayoutButtons = new CommandButtonsLayout();     
    	cancel = hLayoutButtons.getCancel();
    	cancel.addListener(this);
    	submit = hLayoutButtons.getSubmit();
    	submit.addListener(this);
    	vLayout.addComponent(hLayoutButtons);
    	hLayoutButtons.setWidth("-1px");
    	vLayout.setComponentAlignment(hLayoutButtons, Alignment.BOTTOM_RIGHT);
    	mainLayout.addComponent(vLayout, "top:5.0px;left:5.0px;");
        return mainLayout;
	}
	
	@SuppressWarnings("rawtypes")
	private void buildAndBind(Layout layout, String caption, Object propertyId,  Class  fieldType){
		if (fieldType == DateField.class){ 
			@SuppressWarnings("unchecked")
			DateField field = binder.buildAndBind(caption, propertyId, fieldType);
			field.setDateFormat("dd-MM-yyyy");
			layout.addComponent(field);
		}
		else if (fieldType == CheckBox.class)
		{
			CheckBox field = (CheckBox) binder.buildAndBind(caption, propertyId);
			layout.addComponent(field);
		}
		else 
		{
			AbstractTextField field = (AbstractTextField) binder.buildAndBind(caption, propertyId);
			field.setWidth("150px");
			field.setNullRepresentation("");
			layout.addComponent(field);
		}
		
	}
	
	private FormLayout createTab1Layout(){
		FormLayout tabLayout = new FormLayout();
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setWidth("-1px");
		hLayout.setHeight("-1px");
		hLayout.setSpacing(true);
		buildAndBind(hLayout, "№ заявление:", "doc_no", String.class);
		buildAndBind(hLayout, "Дата заявление:", "doc_date", DateField.class);
			
		cBoxDelivery = new ComboBox("Основание выдачи:");
		cBoxDelivery.setWidth("300px");
		cBoxDelivery.setImmediate(true);
		cBoxDelivery.setNullSelectionAllowed(false);
		cBoxDelivery.setInvalidAllowed(false);
		Iterator<Delivery> del = delivery.iterator();    
		while (del.hasNext()) {
			Delivery rt = del.next();
			cBoxDelivery.addItem(rt);
			cBoxDelivery.setItemCaption(rt, rt.getCname());
			if ( appForm.getId() !=null){
				if (rt.getCode().equals(appForm.getStatment().getDeliveryId().getCode())) {
					appForm.getStatment().setDeliveryId(rt);
				} }
		}
		hLayout.addComponent(cBoxDelivery);
		binder.bind(cBoxDelivery, "deliveryId");
		
		tabLayout.addComponent(hLayout);			
		buildAndBind(tabLayout, "ПИН:", "pin", String.class);
		HorizontalLayout hLayout1 = new HorizontalLayout();
		hLayout1.setWidth("-1px");
		hLayout1.setHeight("-1px");
		hLayout1.setSpacing(true);
		buildAndBind(hLayout1, "Фамилия", "fname", String.class);
		buildAndBind(hLayout1, "На латинице", "fnameLat", String.class);
		tabLayout.addComponent(hLayout1);
		HorizontalLayout hLayout2 = new HorizontalLayout();
		hLayout2.setWidth("-1px");
		hLayout2.setHeight("-1px");
		hLayout2.setSpacing(true);
		buildAndBind(hLayout2, "Имя", "iname", String.class);
		buildAndBind(hLayout2, "", "inameLat", String.class);
		tabLayout.addComponent(hLayout2);
		HorizontalLayout hLayout3 = new HorizontalLayout();
		hLayout3.setWidth("-1px");
		hLayout3.setHeight("-1px");
		hLayout3.setSpacing(true);
		buildAndBind(hLayout3, "Отчество", "oname", String.class);
		buildAndBind(hLayout3, "", "onameLat", String.class);
		tabLayout.addComponent(hLayout3);
		buildAndBind(tabLayout, "Дата рождения:", "born_date", DateField.class);
		cBoxBornCountry = new ComboBox("Место рождения:");
		cBoxBornCountry.setWidth("150px");
		cBoxBornCountry.setImmediate(true);
		cBoxBornCountry.setNullSelectionAllowed(false);
		cBoxBornCountry.setInvalidAllowed(false);
		Iterator<Country> it = country.iterator();    
		while (it.hasNext()) {
			Country rt = it.next();
			cBoxBornCountry.addItem(rt);
			cBoxBornCountry.setItemCaption(rt, rt.getCname());
			if ( appForm.getId() !=null){
				if (rt.getCode().equals(appForm.getApplicant().getBorn_countryId().getCode())) {
					appForm.getApplicant().setBorn_countryId(rt);
				} }
		
		
		}
		tabLayout.addComponent(cBoxBornCountry);
		binder.bind(cBoxBornCountry, "born_countryId");		
		buildAndBind(tabLayout, "Номер и серия:", "pass_no", String.class);
		buildAndBind(tabLayout, "Дата выдачи:", "pass_date", DateField.class);
		buildAndBind(tabLayout, "Орган:", "pass_organ", String.class);
		cBoxCountry = new ComboBox("Гражданство:");
		cBoxCountry.setWidth("150px");
		cBoxCountry.setImmediate(true);
		cBoxCountry.setNullSelectionAllowed(false);
		cBoxCountry.setInvalidAllowed(false);
		Iterator<Country> co = country.iterator();    
		while (co.hasNext()) {
			Country rt = co.next();
			cBoxCountry.addItem(rt);
			cBoxCountry.setItemCaption(rt, rt.getCname());
			if ( appForm.getId() !=null){
				if (rt.getCode().equals(appForm.getStatment().getCountryId().getCode())) { 
					appForm.getStatment().setCountryId(rt);
				} }
		}
		tabLayout.addComponent(cBoxCountry);
		binder.bind(cBoxCountry, "countryId");		
		
	
 		return tabLayout;		
	}


	private FormLayout createTab2Layout(){
		FormLayout tabLayout = new FormLayout();
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setWidth("-1px");
		hLayout.setHeight("-1px");
		hLayout.setSpacing(true);
		buildAndBind(tabLayout, "Номер справки:", "med_no", String.class);
		buildAndBind(tabLayout, "Дата выдачи:", "med_date", DateField.class);
		buildAndBind(tabLayout, "Мед.учреждение:", "med_organ", String.class);
		chekGlasses= new CheckBox("Ношение очков:");
		chekGlasses.setWidth("300px");
		chekGlasses.setImmediate(true);		
		tabLayout.addComponent(chekGlasses);
		binder.bind(chekGlasses, "glasses");
		cBoxBlood = new ComboBox("Группа крови:");
		cBoxBlood.setWidth("150px");
		cBoxBlood.setImmediate(true);
		cBoxBlood.setNullSelectionAllowed(false);
		cBoxBlood.setInvalidAllowed(false);
		Iterator<Blood> bl = blood.iterator();    
		while (bl.hasNext()) {
			Blood rt = bl.next();
			cBoxBlood.addItem(rt);
			cBoxBlood.setItemCaption(rt, rt.getCname());
			if ( appForm.getId() !=null){
				if (rt.getCode().equals(appForm.getApplicant().getBlood_groupId().getCode())) {
					appForm.getApplicant().setBlood_groupId(rt);
				} }
		}
		tabLayout.addComponent(cBoxBlood);
		binder.bind(cBoxBlood, "blood_groupId");		
		
		cBoxOblast = new ComboBox("Област:");
		cBoxOblast.setWidth("150px");
		cBoxOblast.setImmediate(true);
		cBoxOblast.setNullSelectionAllowed(false);
		cBoxOblast.setInvalidAllowed(false);
		Iterator<Oblast> ob = oblast.iterator();    
		while (ob.hasNext()) {
			Oblast rt = ob.next();
			cBoxOblast.addItem(rt);
			cBoxOblast.setItemCaption(rt, rt.getCname());
			if ( appForm.getId() !=null){
				if (rt.getCode().equals(appForm.getStatment().getOblastId().getCode())) {
					appForm.getStatment().setOblastId(rt);
				} }
		}
		tabLayout.addComponent(cBoxOblast);
		binder.bind(cBoxOblast, "oblastId");		
		
		cBoxRaion = new ComboBox("Район:");
		cBoxRaion.setWidth("150px");
		cBoxRaion.setImmediate(true);
		cBoxRaion.setNullSelectionAllowed(false);
		cBoxRaion.setInvalidAllowed(false);
		Iterator<Raion> rai = raion.iterator();    
		while (rai.hasNext()) {
			Raion rt = rai.next();
			cBoxRaion.addItem(rt);
			cBoxRaion.setItemCaption(rt, rt.getCname());
			if ( appForm.getId() !=null){
				if (rt.getCode().equals(appForm.getStatment().getRaionId().getCode())) {
					appForm.getStatment().setRaionId(rt);
				} }
		}
		tabLayout.addComponent(cBoxRaion);
		binder.bind(cBoxRaion, "raionId");
		
		cBoxSettl = new ComboBox("Населенный пункт:");
		cBoxSettl.setWidth("150px");
		cBoxSettl.setImmediate(true);
		cBoxSettl.setNullSelectionAllowed(false);
		cBoxSettl.setInvalidAllowed(false);
		Iterator<Settlement> sett = settlement.iterator();    
		while (sett.hasNext()) {
			Settlement rt = sett.next();
			cBoxSettl.addItem(rt);
			cBoxSettl.setItemCaption(rt, rt.getCname());
			if ( appForm.getId() !=null){
				if (rt.getCode().equals(appForm.getStatment().getSettlementId().getCode())) {
					appForm.getStatment().setSettlementId(rt);
				} }
		}
		tabLayout.addComponent(cBoxSettl);
		binder.bind(cBoxSettl, "settlementId");		
		/*officialAddressLayout.setCaption("Официальный адрес регистрации:");
		officialAddressLayout.getAddressLabel().setWidth("300px");
		tabLayout.addComponent(officialAddressLayout);
		actualAddressLayout.setCaption("Фактический адрес нахождения:");
		actualAddressLayout.getAddressLabel().setWidth("300px");
		tabLayout.addComponent(actualAddressLayout);
		buildAndBind(tabLayout, "Почтовый индекс", "postalCode", TextField.class);
    	tabLayout.addComponent(new Label("<hr/>",ContentMode.HTML));*/
		return tabLayout;				
	}

	private FormLayout createTab3Layout(){
		FormLayout tabLayout = new FormLayout();
		/*tabLayout.addComponent(new Label("<hr/>",ContentMode.HTML));
		buildAndBind(tabLayout, "Количество штатных единиц, всего (чел.)", "staff", TextField.class);
		tabLayout.addComponent(new Label("в том числе:"));
		buildAndBind(tabLayout, "Центральный аппарат (чел)", "apparatStaff", TextField.class);
		buildAndBind(tabLayout, "ТОП (чел)", "technicalStaff", TextField.class);
		buildAndBind(tabLayout, "МОП (чел)", "juniorStaff", TextField.class);
		tabLayout.addComponent(new Label("<hr/>",ContentMode.HTML));*/
		return tabLayout;
	}
	
	private FormLayout createTab4Layout(){
		FormLayout tabLayout = new FormLayout();
		tabLayout.addComponent(new Label("Сведения о старом водительском удостоверении",ContentMode.HTML));
		buildAndBind(tabLayout, "Серия и номер ВУ", "old_bu_no", TextField.class);
 		buildAndBind(tabLayout, "Дата выдачи:", "old_bu_date", DateField.class);
		buildAndBind(tabLayout, "Кем выдан:", "old_mreo", TextField.class);
		buildAndBind(tabLayout, "Категории:", "old_category", TextField.class);
//		tabLayout.addComponent(new Label("<hr/>",ContentMode.HTML));
   	
       /* PersonSelectTextField accountant = new PersonSelectTextField(applicationForm.getBudgetInstitution().getAccountant(),app);
        accountant.setCaption("Бухгалтер:");
        accountant.getFio().setWidth("300px");
        tabLayout.addComponent(accountant);
        binder.bind(accountant.getFio(), "accountantFio");
        binder.getField("accountantFio").setEnabled(false);
		buildAndBind(tabLayout, "Серия и номер паспорта", "accountantPassportNumber", TextField.class);
		buildAndBind(tabLayout, "Действителен до:", "accountantPassportValidTo", DateField.class);
		buildAndBind(tabLayout, "Кем выдан:", "accountantPassportIssuedBy", TextField.class);
		buildAndBind(tabLayout, "Номер телефона:", "accountantPhoneNumber", TextField.class);
		buildAndBind(tabLayout, "Электр. почта:", "accountantEmail", TextField.class);
		tabLayout.addComponent(new Label("<hr/>",ContentMode.HTML));
		buildAndBind(tabLayout, "Слово-пароль:", "keyWord", TextField.class);*/
		return tabLayout;
	}

	
	
}
