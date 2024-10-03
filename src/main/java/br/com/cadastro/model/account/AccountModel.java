package br.com.cadastro.model.account;

import br.com.cadastro.model.client.ClientModel;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class AccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private double balance;

    @JoinColumn(name = "client_id")
    private ClientModel clientModel;

    public AccountModel() {
    }

    public AccountModel(int number, AccountType type, double balance, ClientModel clientModel) {
        this.number = number;
        this.type = type;
        this.balance = balance;
        this.clientModel = clientModel;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "id=" + id +
                ", number=" + number +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                ", clientModel=" + clientModel +
                '}';
    }
}
