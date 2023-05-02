package com.anpilov.onlinebank.models;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;


//CREATE TABLE Transaction (
//        id int (15) NOT NULL AUTO_INCREMENT,
//        id_payer int (15),
//        id_payee int (15),
//        data date,
//        type varchar(255),
//        money int (100) NOT NULL,
//        details varchar(255) NOT NULL,
//        PRIMARY KEY (ID),
//        FOREIGN KEY (id_payer) REFERENCES Person (id),
//        FOREIGN KEY (id_payee) REFERENCES Person (id)
//        ) ENGINE=InnODB DEFAULT CHARSET=UTF8


@Entity
@Table(name="Transaction", schema="OnlineBank")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "payee_id")
    private Long id_payee;

    @Column(name = "payer_id")
    private Long id_payer;

    private String type;
    private Integer money;


    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data")
    private String data;

    private String details;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_payee() {
        return id_payee;
    }

    public void setId_payee(Long id_payee) {
        this.id_payee = id_payee;
    }

    public Long getId_payer() {
        return id_payer;
    }

    public void setId_payer(Long id_payer) {
        this.id_payer = id_payer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
