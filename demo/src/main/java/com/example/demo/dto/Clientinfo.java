package com.example.demo.dto;


public class Clientinfo {
	private long companyId;

	private String staffId;

	private long branchNo;

	private String clientId;

	private String clientCard;

	private String payee;

	private String securitiesSaleInstruction;

	private String ccassInvestorAccount;

	private String status;

	private String riskLevel;

	private String staffContent;

	private String organFlag;
	
	private long activeDate;

	private long openDate;
	
	private long cancelDate;
	
	private String remark;
	
    private String developSource;

    private String ensurePwdValid;  

	private String bcanAgree;  
	
	private String tfaModes;//新增tfa认证模式 char32
	
	private String langType;//新增语言类别 char
	
	/** 所属TTEP参与者的编号 */
    private String ttepFirmId;
    
    private String productCode;
    
    /** 修改流程修改客户信息版本号 */
    private long modiVersion;
    
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getEnsurePwdValid() {
		return ensurePwdValid;
	}

	public void setEnsurePwdValid(String ensurePwdValid) {
		this.ensurePwdValid = ensurePwdValid;
	}

	public String getDevelopSource() {
		return developSource;
	}

	public void setDevelopSource(String developSource) {
		this.developSource = developSource;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(long activeDate) {
		this.activeDate = activeDate;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public long getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(long branchNo) {
		this.branchNo = branchNo;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientCard() {
		return clientCard;
	}

	public void setClientCard(String clientCard) {
		this.clientCard = clientCard;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getSecuritiesSaleInstruction() {
		return securitiesSaleInstruction;
	}

	public void setSecuritiesSaleInstruction(String securitiesSaleInstruction) {
		this.securitiesSaleInstruction = securitiesSaleInstruction;
	}

	public String getCcassInvestorAccount() {
		return ccassInvestorAccount;
	}

	public void setCcassInvestorAccount(String ccassInvestorAccount) {
		this.ccassInvestorAccount = ccassInvestorAccount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getStaffContent() {
		return staffContent;
	}

	public void setStaffContent(String staffContent) {
		this.staffContent = staffContent;
	}

	public String getOrganFlag() {
		return organFlag;
	}

	public void setOrganFlag(String organFlag) {
		this.organFlag = organFlag;
	}

	public long getOpenDate() {
		return openDate;
	}

	public void setOpenDate(long openDate) {
		this.openDate = openDate;
	}

	public long getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(long cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getBcanAgree() {
		return bcanAgree;
	}

	public void setBcanAgree(String bcanAgree) {
		this.bcanAgree = bcanAgree;
	}

	public String getTfaModes() {
		return tfaModes;
	}

	public void setTfaModes(String tfaModes) {
		this.tfaModes = tfaModes;
	}

	public String getLangType() {
		return langType;
	}

	public void setLangType(String langType) {
		this.langType = langType;
	}

    public String getTtepFirmId() {
        return ttepFirmId;
    }

    public void setTtepFirmId(String ttepFirmId) {
        this.ttepFirmId = ttepFirmId;
    }
    
    public long getModiVersion() {
		return modiVersion;
	}

	public void setModiVersion(long modiVersion) {
		this.modiVersion = modiVersion;
	}
}