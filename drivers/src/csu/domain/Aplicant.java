package csu.domain;

import csu.enums.GenderType;

public class Aplicant {
	private static final long serialVersionUID = -3856518279921410532L;
	private GenderType genderId ;
	private Blood blood_groupId ;
	private Country born_countryId ;
	private String pin, fname, iname, oname ,fnameLat ,inameLat ,onameLat ;
	public GenderType getGenderId() {
		return genderId;
	}
	public void setGenderId(GenderType genderId) {
		this.genderId = genderId;
	}
	public Blood getBlood_groupId() {
		return blood_groupId;
	}
	public void setBlood_groupId(Blood blood_groupId) {
		this.blood_groupId = blood_groupId;
	}
	public Country getBorn_countryId() {
		return born_countryId;
	}
	public void setBorn_countryId(Country born_countryId) {
		this.born_countryId = born_countryId;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getFnameLat() {
		return fnameLat;
	}
	public void setFnameLat(String fnameLat) {
		this.fnameLat = fnameLat;
	}
	public String getInameLat() {
		return inameLat;
	}
	public void setInameLat(String inameLat) {
		this.inameLat = inameLat;
	}
	public String getOnameLat() {
		return onameLat;
	}
	public void setOnameLat(String onameLat) {
		this.onameLat = onameLat;
	}

	  
}
