package csu.domain;

public class Raion {

	private static final long serialVersionUID = -3856518279921410532L;
	private Integer code;
	private String cname;
	private Oblast oblastId;
	
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Oblast getOblastId() {
		return oblastId;
	}
	public void setOblastId(Oblast oblastId) {
		this.oblastId = oblastId;
	}
	
	

}
