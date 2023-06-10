package com.smallworld.data;

import java.util.List;

public class Transaction{
	public Transaction(){}
	public Transaction(List<TransactionItem> transaction){
		this.transaction=transaction;
	}
	private List<TransactionItem> transaction;

	public void setTransaction(List<TransactionItem> transaction){
		this.transaction = transaction;
	}

	public List<TransactionItem> getTransaction(){
		return transaction;
	}

	@Override
 	public String toString(){
		return 
			"Transaction{" + 
			"transaction = '" + transaction + '\'' + 
			"}";
		}
}