package com.stb.money_transfer_app.service;

import com.stb.money_transfer_app.dto.TransferData;
import com.stb.money_transfer_app.model.*;
import com.stb.money_transfer_app.exceptions.*;
import com.stb.money_transfer_app.repo.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class MoneyTransfer implements IMoneyTransfer {

    final RecipientsRepo recipients;
    final SendersRepo senders;
    final OperationsRepo operations;

    @Override
    public double getBalance(long userID) {
        Recipient recipient = recipients.findById(userID).orElse(null);
        if (recipient != null) {
            return recipient.getBalance();
        } else {
            throw new UserNotFoundException("User not exists");
        }
    }

    @Override
    @Transactional
    public long sendMoney(TransferData data) {
        long senderID = data.senderID(), recipientID = data.recipientID();
        double amount = data.amount();
        if (amount > 1000)
            throw new NotEnoughMoneyException("The operation amount should not be bigger than 1000 USD");
        Operation o = new Operation(senderID, recipientID, amount);
        operations.save(o); //will put the operation with false status so even in case of error we will have the log
        Sender sender = senders.findById(senderID).orElse(null);
        Recipient recipient = recipients.findById(recipientID).orElse(null);
        if (sender == null) {
            throw new UserNotFoundException("Sender not found");
        } else if (recipient == null)
            throw new UserNotFoundException("Recipient not found");
        recipient.setBalance(amount);
        o.setStatus(true);
        senders.save(sender);
        recipients.save(recipient);
        operations.save(o); //updates operations status after successfull transfer
        return o.getOperationID();
    }

    @Override
    @Transactional
    public double withdraw(long userID, double amount) {
        Recipient recipient = recipients.findById(userID).orElse(null);
        if (recipient == null)
            throw new UserNotFoundException("User not exists");
        else if (recipient.getBalance() < amount)
            throw new NotEnoughMoneyException("User's balance is lower than operation amount");
        else {
            recipient.setBalance(-amount);
            recipients.save(recipient);
        }
        return recipient.getBalance();
    }
}
