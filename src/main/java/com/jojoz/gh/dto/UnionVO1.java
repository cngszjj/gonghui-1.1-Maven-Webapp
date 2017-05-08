package com.jojoz.gh.dto;

import java.util.Date;

public class UnionVO1 {
//	 {"num","unionName","registerAddress","legal","legalCredCode",
// 		"legalPhone","firstSetupTime","thisSetupTime","issueDate",
// 		"workersCount","unionCardCode","unitNature","unitType"}
	
	private String id;
	private int state;
	private String unionName;
	private String registerAddress;
	private String legal;
	private String legalCredCode;
	private String legalPhone;
	private Date firstSetupTime;
	private Date thisSetupTime;
	private Date issueDate;
	private int workersCount;
	private String unionCardCode;
	private String unitNature;
	private String unitType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnionName() {
		return unionName;
	}
	public void setUnionName(String unionName) {
		this.unionName = unionName;
	}
	public String getRegisterAddress() {
		return registerAddress;
	}
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}
	public String getLegal() {
		return legal;
	}
	public void setLegal(String legal) {
		this.legal = legal;
	}
	public String getLegalCredCode() {
		return legalCredCode;
	}
	public void setLegalCredCode(String legalCredCode) {
		this.legalCredCode = legalCredCode;
	}
	public String getLegalPhone() {
		return legalPhone;
	}
	public void setLegalPhone(String legalPhone) {
		this.legalPhone = legalPhone;
	}
	public Date getFirstSetupTime() {
		return firstSetupTime;
	}
	public void setFirstSetupTime(Date firstSetupTime) {
		this.firstSetupTime = firstSetupTime;
	}
	public Date getThisSetupTime() {
		return thisSetupTime;
	}
	public void setThisSetupTime(Date thisSetupTime) {
		this.thisSetupTime = thisSetupTime;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public int getWorkersCount() {
		return workersCount;
	}
	public void setWorkersCount(int workersCount) {
		this.workersCount = workersCount;
	}
	public String getUnionCardCode() {
		return unionCardCode;
	}
	public void setUnionCardCode(String unionCardCode) {
		this.unionCardCode = unionCardCode;
	}
	public String getUnitNature() {
		return unitNature;
	}
	public void setUnitNature(String unitNature) {
		this.unitNature = unitNature;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
	
	

}
