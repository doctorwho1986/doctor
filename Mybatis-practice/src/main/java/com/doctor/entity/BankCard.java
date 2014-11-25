package com.doctor.entity;

import org.apache.ibatis.type.Alias;


@Alias("BankCard")
public class BankCard {
	private Long cardId;
	 
	private String identityId;
	private String bankAccountNo;
	private String accountName;
	private String certNo;
	private String phoneNo;
	
	public BankCard(){}
	
	public BankCard(String identityId, String bankAccountNo, String accountName,String certNo, String phoneNo ){
		this.identityId = identityId;
		this.bankAccountNo = bankAccountNo;
		this.accountName = accountName;
		this.certNo = certNo;
		this.phoneNo = phoneNo;
	}
	
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	public String getIdentityId() {
		return identityId;
	}
	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
}
