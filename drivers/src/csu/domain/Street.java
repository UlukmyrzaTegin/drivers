package csu.domain;

import java.math.BigInteger;

public class Street {
	private static final long serialVersionUID = -3856518279921410532L;
	private Integer id;
	private String cname;
	private Settlement settlId;
	private Str_type str_typeId;
	
	

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Settlement getSettlId() {
		return settlId;
	}
	public void setSettlId(Settlement settlId) {
		this.settlId = settlId;
	}
	public Str_type getStr_typeId() {
		return str_typeId;
	}
	public void setStr_typeId(Str_type str_typeId) {
		this.str_typeId = str_typeId;
	}
	

}
