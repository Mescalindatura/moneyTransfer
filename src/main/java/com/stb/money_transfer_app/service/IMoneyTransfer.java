package com.stb.money_transfer_app.service;


import com.stb.money_transfer_app.dto.TransferData;

public interface IMoneyTransfer {

    public double getBalance(long userID);

    public boolean sendMoney(TransferData data);

    public double withdraw(long userID, double amount);
}
