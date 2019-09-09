package csu.domain;

import java.math.BigInteger;

public class Settlement {
	private static final long serialVersionUID = -3856518279921410532L;
	private Integer code;
	private String cname;
	private Raion raionId;
	

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
	public Raion getRaionId() {
		return raionId;
	}
	public void setRaionId(Raion raionId) {
		this.raionId = raionId;
	}

}
