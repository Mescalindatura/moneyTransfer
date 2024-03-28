package com.stb.money_transfer_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String residency;
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
    //in real life we should create at least one account for data consistency, but as this app exists for demonstration purposes only i will put the balance to the user entity
    private double balance;

    public void setBalance(double operation) {
        this.balance += operation;
    }

}
