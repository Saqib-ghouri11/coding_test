package com.smallworld.data;

import java.util.Objects;

public class TransactionItem{
	private int senderAge;
	private String senderFullName;
	private double amount;
	private int issueId;
	private int mtn;
	private boolean issueSolved;
	private String issueMessage;
	private int beneficiaryAge;
	private String beneficiaryFullName;

	public void setSenderAge(int senderAge){
		this.senderAge = senderAge;
	}

	public int getSenderAge(){
		return senderAge;
	}

	public void setSenderFullName(String senderFullName){
		this.senderFullName = senderFullName;
	}

	public String getSenderFullName(){
		return senderFullName;
	}

	public void setAmount(double amount){
		this.amount = amount;
	}

	public double getAmount(){
		return amount;
	}

	public void setIssueId(int issueId){
		this.issueId = issueId;
	}

	public int getIssueId(){
		return issueId;
	}

	public void setMtn(int mtn){
		this.mtn = mtn;
	}

	public int getMtn(){
		return mtn;
	}

	public void setIssueSolved(boolean issueSolved){
		this.issueSolved = issueSolved;
	}

	public boolean isIssueSolved(){
		return issueSolved;
	}

	public void setIssueMessage(String issueMessage){
		this.issueMessage = issueMessage;
	}

	public String getIssueMessage(){
		return issueMessage;
	}

	public void setBeneficiaryAge(int beneficiaryAge){
		this.beneficiaryAge = beneficiaryAge;
	}

	public int getBeneficiaryAge(){
		return beneficiaryAge;
	}

	public void setBeneficiaryFullName(String beneficiaryFullName){
		this.beneficiaryFullName = beneficiaryFullName;
	}

	public String getBeneficiaryFullName(){
		return beneficiaryFullName;
	}

	@Override
 	public String toString(){
		return 
			"TransactionItem{" + 
			"senderAge = '" + senderAge + '\'' + 
			",senderFullName = '" + senderFullName + '\'' + 
			",amount = '" + amount + '\'' + 
			",issueId = '" + issueId + '\'' + 
			",mtn = '" + mtn + '\'' + 
			",issueSolved = '" + issueSolved + '\'' + 
			",issueMessage = '" + issueMessage + '\'' + 
			",beneficiaryAge = '" + beneficiaryAge + '\'' + 
			",beneficiaryFullName = '" + beneficiaryFullName + '\'' + 
			"}";
		}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TransactionItem transactionItem = (TransactionItem) obj;
		return mtn == transactionItem.mtn;
	}
	@Override
	public int hashCode() {
		return Objects.hash(mtn);
	}
}
