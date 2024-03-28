package com.stb.money_transfer_app.entities;

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
    User user;
    double balance;
    boolean isActive;
}
