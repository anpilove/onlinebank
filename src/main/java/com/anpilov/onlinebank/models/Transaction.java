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

/**
 The Transaction class represents a transaction made between two people.
 Each transaction has an id, payer id, payee id, type, amount of money, date, and details.
 The Transaction class maps to the "Transaction" table in the "OnlineBank" schema.
 @author Anpilov Kirill
 @version 1.0
 */
@Entity
@Table(name="Transaction", schema="OnlineBank")
public class Transaction {

    /**
     * The unique identifier of the transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The id of the person who received the money.
     */
    @Column(name = "payee_id")
    private Long id_payee;

    /**
     * The id of the person who paid the money.
     */
    @Column(name = "payer_id")
    private Long id_payer;

    /**
     * The type of the transaction, such as "transfer" or "withdrawal".
     */
    @Column(name = "type")
    private String type;

    /**
     * The amount of money involved in the transaction.
     */
    @Column(name = "money")
    private Double money;

    /**
     * The date and time of the transaction.
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data")
    private String data;

    /**
     * Details about the transaction, such as a description or reference number.
     */
    @Column(name = "details")
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", id_payee=" + id_payee +
                ", id_payer=" + id_payer +
                ", type='" + type + '\'' +
                ", money=" + money +
                ", data='" + data + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
