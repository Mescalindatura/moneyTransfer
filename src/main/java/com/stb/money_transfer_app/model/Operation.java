package com.stb.money_transfer_app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="OPERATIONS")
@NoArgsConstructor
@Getter
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long operationID;
    private long senderID;
    private long recipientID;
    private double amount;
    private LocalDateTime timestamp;
    private boolean status = false;

public Operation(long senderID, long recipientID, double amount){
    this.senderID = senderID;
    this.recipientID = recipientID;
    this.amount = amount;
    this.timestamp = LocalDateTime.now();
}

    public void setStatus(boolean status) {
        this.status = status;
    }
}
