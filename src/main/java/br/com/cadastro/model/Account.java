package br.com.cadastro.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private double balance;

    @JoinColumn(name = "client_id")
    private Client client;

    public Account() {
    }

    public Account(int number, AccountType type, double balance, Client client) {
        this.number = number;
        this.type = type;
        this.balance = balance;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number=" + number +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                ", client=" + client +
                '}';
    }
}
