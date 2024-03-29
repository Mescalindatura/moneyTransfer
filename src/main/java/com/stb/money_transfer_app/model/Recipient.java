package com.stb.money_transfer_app.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "RECIPIENTS")
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String residency;
    //in real-life app it is better to create separated table for recipients accounts for write operations and to use the balance field for read operations
    @Column
    private double balance;

    public Recipient(String name, String email, String residency){
        this.email = email;
        this.name = name;
        this.residency = residency;
        this.balance = 0;
    }

    public Recipient() {

    }

    public double getBalance() {
        return balance;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getResidency() {
        return residency;
    }

    public void setBalance(double operation) {
        this.balance += operation;
    }

}
