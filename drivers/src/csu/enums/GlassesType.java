package csu.enums;

public enum GlassesType {
	NOGLASSES(1,"Нет"),
	GLASSES(2,"Да");
	
	private Integer code;
	private String cname;
	
	private GlassesType(Integer id, String cname){
		this.cname = cname;
		this.code = code;
	}

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

	
	

}
