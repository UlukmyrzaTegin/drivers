package csu.domain;

public class Country {
	private static final long serialVersionUID = -3856518279921410532L;
	private Integer code;
	private String cname,cname_lat,abbr;
	
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getCname() {
		return cname;
	}
	public String getCname_lat() {
		return cname_lat;
	}
	public void setCname_lat(String cname_lat) {
		this.cname_lat = cname_lat;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	

}
