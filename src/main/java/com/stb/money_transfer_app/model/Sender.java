package com.stb.money_transfer_app.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "SENDERS")
public class Sender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String residency;

    public Sender(String name, String email, String residency){
        this.email = email;
        this.name = name;
        this.residency = residency;
    }

    public Sender() {

    }

    public long getId() {
        return id;
    }

    public String getResidency() {
        return residency;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
