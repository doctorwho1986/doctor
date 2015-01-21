package com.doctor.manager;

import com.doctor.entity.BankCard;

public interface BankCardManager {
	public boolean insertBankCard(BankCard bankCard);
	public BankCard queryBankCard(Long id);
	public BankCard queryBankCard2(Long id);
}
