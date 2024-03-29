package com.stb.money_transfer_app.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "OPERATIONS")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private long senderID;
    @Column
    private long recipientID;
    @Column
    private double amount;

    @Column
    @CreationTimestamp
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timestamp;

    @Column
    private boolean status;

    public Operation() {

    }

    public Operation(long senderID, long recipientID, double amount) {
        this.senderID = senderID;
        this.recipientID = recipientID;
        this.amount = amount;
        this.status = false;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public long getOperationID() {
        return id;
    }

    public long getRecipientID() {
        return recipientID;
    }

    public long getSenderID() {
        return senderID;
    }
}

