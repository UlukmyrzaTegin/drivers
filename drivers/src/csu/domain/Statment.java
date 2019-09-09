package csu.domain;

import java.util.Date;


public class Statment {
	
	private static final long serialVersionUID = -3856518279921410532L;
	private Integer id;
	private Date doc_date;
	private String doc_no;
	private String pin;
	private Delivery deliveryId;
	private String pass_no;
	private Date pass_date , med_date , stage ,mod_date;
	private String pass_organ;
	private String old_fio;
	private String old_bu_no;
	private Date old_bu_date;
	private String old_category;
	private String old_mreo;
	private String additional_data,med_no, med_organ, adress_lat ,crip_organ ,descr;
	//private GlassesType glasses;
	private Boolean glasses;
	private Oblast oblastId;
	private Raion raionId;
	private Country countryId;
	private Street streetId;
	private Settlement settlementId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDoc_date() {
		return doc_date;
	}
	public void setDoc_date(Date doc_date) {
		this.doc_date = doc_date;
	}
	public String getDoc_no() {
		return doc_no;
	}
	public void setDoc_no(String doc_no) {
		this.doc_no = doc_no;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public Delivery getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(Delivery deliveryId) {
		this.deliveryId = deliveryId;
	}
	public String getPass_no() {
		return pass_no;
	}
	public void setPass_no(String pass_no) {
		this.pass_no = pass_no;
	}
	public Date getPass_date() {
		return pass_date;
	}
	public void setPass_date(Date pass_date) {
		this.pass_date = pass_date;
	}
	public Date getMed_date() {
		return med_date;
	}
	public void setMed_date(Date med_date) {
		this.med_date = med_date;
	}
	public Date getStage() {
		return stage;
	}
	public void setStage(Date stage) {
		this.stage = stage;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}
	public String getPass_organ() {
		return pass_organ;
	}
	public void setPass_organ(String pass_organ) {
		this.pass_organ = pass_organ;
	}
	public String getOld_fio() {
		return old_fio;
	}
	public void setOld_fio(String old_fio) {
		this.old_fio = old_fio;
	}
	public String getOld_bu_no() {
		return old_bu_no;
	}
	public void setOld_bu_no(String old_bu_no) {
		this.old_bu_no = old_bu_no;
	}
	public Date getOld_bu_date() {
		return old_bu_date;
	}
	public void setOld_bu_date(Date old_bu_date) {
		this.old_bu_date = old_bu_date;
	}
	public String getOld_category() {
		return old_category;
	}
	public void setOld_category(String old_category) {
		this.old_category = old_category;
	}
	public String getOld_mreo() {
		return old_mreo;
	}
	public void setOld_mreo(String old_mreo) {
		this.old_mreo = old_mreo;
	}
	public String getAdditional_data() {
		return additional_data;
	}
	public void setAdditional_data(String additional_data) {
		this.additional_data = additional_data;
	}
	public String getMed_no() {
		return med_no;
	}
	public void setMed_no(String med_no) {
		this.med_no = med_no;
	}
	public String getMed_organ() {
		return med_organ;
	}
	public void setMed_organ(String med_organ) {
		this.med_organ = med_organ;
	}
	public String getAdress_lat() {
		return adress_lat;
	}
	public void setAdress_lat(String adress_lat) {
		this.adress_lat = adress_lat;
	}
	public String getCrip_organ() {
		return crip_organ;
	}
	public void setCrip_organ(String crip_organ) {
		this.crip_organ = crip_organ;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Boolean getGlasses() {
		return glasses;
	}
	public void setGlasses(Boolean glasses) {
		this.glasses = glasses;
	}
	public Oblast getOblastId() {
		return oblastId;
	}
	public void setOblastId(Oblast oblastId) {
		this.oblastId = oblastId;
	}
	public Raion getRaionId() {
		return raionId;
	}
	public void setRaionId(Raion raionId) {
		this.raionId = raionId;
	}
	public Country getCountryId() {
		return countryId;
	}
	public void setCountryId(Country countryId) {
		this.countryId = countryId;
	}
	public Street getStreetId() {
		return streetId;
	}
	public void setStreetId(Street streetId) {
		this.streetId = streetId;
	}
	public Settlement getSettlementId() {
		return settlementId;
	}
	public void setSettlementId(Settlement settlementId) {
		this.settlementId = settlementId;
	}
	
}
