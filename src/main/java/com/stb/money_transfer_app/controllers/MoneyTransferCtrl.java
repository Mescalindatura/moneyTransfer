package com.stb.money_transfer_app.controllers;

import com.stb.money_transfer_app.dto.TransferData;
import com.stb.money_transfer_app.service.MoneyTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

public class MoneyTransferCtrl {
    final MoneyTransfer service;

    @GetMapping("/balance/{id}")
    public double getBalance(@PathVariable long id){
        return service.getBalance(id);
    }

    @PostMapping("/send")
    public boolean sendMoney(@RequestBody TransferData data){
        return service.sendMoney(data);
    }

    @PostMapping("/withdraw/{id}")
    public double withdraw(@PathVariable long id, @RequestBody double amount){
        return service.withdraw(id, amount);
    }
}
