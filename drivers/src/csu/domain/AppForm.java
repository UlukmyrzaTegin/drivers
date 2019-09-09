package csu.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import csu.enums.GenderType;



public class AppForm implements Serializable {
	private static final long serialVersionUID = 8561191071142952885L;
	
	private Integer id;
	private Applicant applicant;
	private Statment statment;
	
	public AppForm() {
		applicant = new Applicant();
		statment = new Statment();
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Statment getStatment() {
		return statment;
	}

	public void setStatment(Statment statment) {
		this.statment = statment;
	}
}
