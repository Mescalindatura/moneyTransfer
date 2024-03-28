package com.stb.money_transfer_app.service;

import com.stb.money_transfer_app.dto.TransferData;
import com.stb.money_transfer_app.entities.Operation;
import com.stb.money_transfer_app.entities.User;
import com.stb.money_transfer_app.exceptions.NotEnoughMoneyException;
import com.stb.money_transfer_app.exceptions.UserNotFoundException;
import com.stb.money_transfer_app.repo.OperationsRepo;
import com.stb.money_transfer_app.repo.UsersRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class MoneyTransferImpl implements MoneyTransfer {

    final UsersRepo users;
    final OperationsRepo operations;

    @Override
    public double getBalance(long userID) {
        User user = users.findById(userID).orElse(null);
        if (user != null) {
            return user.getBalance();
        } else {
            throw new UserNotFoundException("User not exists");
        }
    }

    @Override
    public boolean sendMoney(TransferData data) {
        long senderID = data.senderID(), recipientID = data.recipientID();
        double amount = data.amount();
        Operation o = new Operation(senderID, recipientID, amount);
        operations.save(o);
        User sender = users.findById(senderID).orElse(null);
        User recipient = users.findById(recipientID).orElse(null);
        if (sender == null) {
            throw new UserNotFoundException("Sender not found");
        } else if (recipient == null)
            throw new UserNotFoundException("Recipient not found");
        else if (sender.getBalance() < amount)
            throw new NotEnoughMoneyException("Sender's balance is lower than operation amount");
        sender.setBalance(-amount);
        recipient.setBalance(amount);
        o.setStatus(true);
        operations.save(o);
        users.save(sender);
        users.save(recipient);
        return true;
    }

    @Override
    public double withdraw(long userID, double amount) {
        User user = users.findById(userID).orElse(null);
        if (user == null)
            throw new UserNotFoundException("User not exists");
        else if (user.getBalance() < amount)
            throw new NotEnoughMoneyException("User's balance is lower than operation amount");
        else {
            user.setBalance(amount);
            users.save(user);
        }
        return user.getBalance();
    }
}
