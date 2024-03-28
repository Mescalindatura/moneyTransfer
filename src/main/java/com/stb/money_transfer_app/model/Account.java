package com.stb.money_transfer_app.model;

import jakarta.persistence.*;

@Entity
@Table(name="ACCOUNTS")
public class Account {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    long id;
    long userID;
    @ManyToOne
    @JoinColumn(name="userID", nullable=false)
    Recipient user;
    double balance;
    boolean isActive;
}
