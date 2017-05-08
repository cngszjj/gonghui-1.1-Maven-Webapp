package com.jojoz.gh.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jojoz.gh.util.CustomDateSerializer;


public class Union {

	private String id;
	private int state;//1已审核 0：未审核 2 不通过
	private String unionName;//工会名称
	private String unionPhone;//联系电话
	private String unionEmail;//电子邮箱
	private String registerAddress;//住所地址
	private String registerZipcode;//住所地址邮政编码
	private String produceAddress;//办公地址
	/**
	 * 办公地址行政区划
	 */
	private String produceDivision;//办公地址行政区划
	private String qvregisterZipcode;//办公地址邮政编码
	private Date setupTime;//该工会成立日期
	private Date approvalTime;//核准日期
	
	private String workUnit;//审批单位
	private String workUnitCode;//审批文号
	private int workersCount;//现职工人数
	private int vipCount;//会员人数
	private int unionWorkersCount;//专职工会干部数
	private String unitNature;//所在单位性质
	private String unitType;//所在单位行业属性
	
	private Date firstSetupTime;//第一届委员会成立日期
	private Date thisSetupTime;//本届委员会成立日期
	
	private String photoUrl;
	
	private String lastYearSurplus;//上年结余累计（万元）
	private String lastYearVIPSurplus;//年会员缴纳会费收入（万元）
	private String unionFunds;//拨交工会经费本级留成收入（万元）
	private String lastYearOtherSurplus;//其他收入（万元）
	
	private String fixedFunds;//固定资金（万元）
	private String flowFunds;//流动资金
	private String otherFunds;//其他资金
	private String countFunds;//资金合计
	
	private String workPlace;//办公场所
	private String sportPlace;//活动场所
	private String otherPlace;//其他场所
	private String countPlace;//场所合计
	
	private String unionCardCode;//机构代码证号码
	private String legalCardCode;//工会法人资格证书号码
	private String civilLiability;//承担民事责任能力状况
	
	private String businessScope;//业务范围
	
	private String legal;//法定代表人
	private String legalSex;//性别
	private String legalNation;//民族
	private Date legalTime;//出生年月
	private String culture;//文化程度
	private String politics;//政治面貌
	private String unionPost;//现任工会职务
	private String concurrentPost;//属兼职或者专职
	private String legalOtherPost;//现任其他职务
	private String legalCredCode;//证件号码
	private String legalPhone;//手机号码
	private Date operationBegin;//本届任起始时间
	private Date operationTime;//何时加入工会组织
	
	private Date issueDate;//发证日期
	
	private String operatorName;//经办人姓名
	private String operatorPost;//职务
	private String operatorCode;//证件号码
	private String operatorPhone;//手机号码
	
	private Date addTime;
	
	private String info;
	
	private String user_id;

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

	public String getUnionPhone() {
		return unionPhone;
	}

	public void setUnionPhone(String unionPhone) {
		this.unionPhone = unionPhone;
	}

	public String getUnionEmail() {
		return unionEmail;
	}

	public void setUnionEmail(String unionEmail) {
		this.unionEmail = unionEmail;
	}

	public String getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	public String getRegisterZipcode() {
		return registerZipcode;
	}

	public void setRegisterZipcode(String registerZipcode) {
		this.registerZipcode = registerZipcode;
	}

	public String getProduceAddress() {
		return produceAddress;
	}

	public void setProduceAddress(String produceAddress) {
		this.produceAddress = produceAddress;
	}

	public String getProduceDivision() {
		return produceDivision;
	}

	public void setProduceDivision(String produceDivision) {
		this.produceDivision = produceDivision;
	}

	

	public String getQvregisterZipcode() {
		return qvregisterZipcode;
	}

	public void setQvregisterZipcode(String qvregisterZipcode) {
		this.qvregisterZipcode = qvregisterZipcode;
	}
	
    @JsonSerialize(using=CustomDateSerializer.class)
	public Date getSetupTime() {
		return setupTime;
	}

	public void setSetupTime(Date setupTime) {
		this.setupTime = setupTime;
	}

	public Date getApprovalTime() {
		return approvalTime;
	}
    @JsonSerialize(using=CustomDateSerializer.class)
	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getWorkUnitCode() {
		return workUnitCode;
	}

	public void setWorkUnitCode(String workUnitCode) {
		this.workUnitCode = workUnitCode;
	}

	public int getWorkersCount() {
		return workersCount;
	}

	public void setWorkersCount(int workersCount) {
		this.workersCount = workersCount;
	}

	public int getVipCount() {
		return vipCount;
	}

	public void setVipCount(int vipCount) {
		this.vipCount = vipCount;
	}

	public int getUnionWorkersCount() {
		return unionWorkersCount;
	}

	public void setUnionWorkersCount(int unionWorkersCount) {
		this.unionWorkersCount = unionWorkersCount;
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

	public String getLastYearSurplus() {
		return lastYearSurplus;
	}

	public void setLastYearSurplus(String lastYearSurplus) {
		this.lastYearSurplus = lastYearSurplus;
	}

	public String getLastYearVIPSurplus() {
		return lastYearVIPSurplus;
	}

	public void setLastYearVIPSurplus(String lastYearVIPSurplus) {
		this.lastYearVIPSurplus = lastYearVIPSurplus;
	}

	public String getUnionFunds() {
		return unionFunds;
	}

	public void setUnionFunds(String unionFunds) {
		this.unionFunds = unionFunds;
	}

	public String getLastYearOtherSurplus() {
		return lastYearOtherSurplus;
	}

	public void setLastYearOtherSurplus(String lastYearOtherSurplus) {
		this.lastYearOtherSurplus = lastYearOtherSurplus;
	}

	public String getFixedFunds() {
		return fixedFunds;
	}

	public void setFixedFunds(String fixedFunds) {
		this.fixedFunds = fixedFunds;
	}

	public String getFlowFunds() {
		return flowFunds;
	}

	public void setFlowFunds(String flowFunds) {
		this.flowFunds = flowFunds;
	}

	public String getOtherFunds() {
		return otherFunds;
	}

	public void setOtherFunds(String otherFunds) {
		this.otherFunds = otherFunds;
	}

	public String getCountFunds() {
		return countFunds;
	}

	public void setCountFunds(String countFunds) {
		this.countFunds = countFunds;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getSportPlace() {
		return sportPlace;
	}

	public void setSportPlace(String sportPlace) {
		this.sportPlace = sportPlace;
	}

	public String getOtherPlace() {
		return otherPlace;
	}

	public void setOtherPlace(String otherPlace) {
		this.otherPlace = otherPlace;
	}

	public String getCountPlace() {
		return countPlace;
	}

	public void setCountPlace(String countPlace) {
		this.countPlace = countPlace;
	}

	public String getUnionCardCode() {
		return unionCardCode;
	}

	public void setUnionCardCode(String unionCardCode) {
		this.unionCardCode = unionCardCode;
	}

	public String getLegalCardCode() {
		return legalCardCode;
	}

	public void setLegalCardCode(String legalCardCode) {
		this.legalCardCode = legalCardCode;
	}

	public String getCivilLiability() {
		return civilLiability;
	}

	public void setCivilLiability(String civilLiability) {
		this.civilLiability = civilLiability;
	}

	

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getLegalSex() {
		return legalSex;
	}

	public void setLegalSex(String legalSex) {
		this.legalSex = legalSex;
	}

	public String getLegalNation() {
		return legalNation;
	}

	public void setLegalNation(String legalNation) {
		this.legalNation = legalNation;
	}
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getLegalTime() {
		return legalTime;
	}

	public void setLegalTime(Date legalTime) {
		this.legalTime = legalTime;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	public String getUnionPost() {
		return unionPost;
	}

	public void setUnionPost(String unionPost) {
		this.unionPost = unionPost;
	}

	public String getConcurrentPost() {
		return concurrentPost;
	}

	public void setConcurrentPost(String concurrentPost) {
		this.concurrentPost = concurrentPost;
	}

	public String getLegalOtherPost() {
		return legalOtherPost;
	}

	public void setLegalOtherPost(String legalOtherPost) {
		this.legalOtherPost = legalOtherPost;
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
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getOperationBegin() {
		return operationBegin;
	}

	public void setOperationBegin(Date operationBegin) {
		this.operationBegin = operationBegin;
	}
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorPost() {
		return operatorPost;
	}

	public void setOperatorPost(String operatorPost) {
		this.operatorPost = operatorPost;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getOperatorPhone() {
		return operatorPhone;
	}

	public void setOperatorPhone(String operatorPhone) {
		this.operatorPhone = operatorPhone;
	}
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getFirstSetupTime() {
		return firstSetupTime;
	}

	public void setFirstSetupTime(Date firstSetupTime) {
		this.firstSetupTime = firstSetupTime;
	}
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getThisSetupTime() {
		return thisSetupTime;
	}

	public void setThisSetupTime(Date thisSetupTime) {
		this.thisSetupTime = thisSetupTime;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
	
	
	
	
	

	
	
	
}
