package br.com.cadastro.model.account;

import br.com.cadastro.model.client.ClientModel;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "account")
@Data
public class AccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "O número da conta deve ser pelo menos 0")
    private int number;

    @NotNull(message = "O tipo da conta não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @DecimalMin(value = "0.0", inclusive = true, message = "O saldo da conta deve ser pelo menos 0")
    private double balance;

    @NotNull(message = "O cliente não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel clientModel;

    public AccountModel() {}

    public AccountModel(int number, AccountType type, double balance, ClientModel clientModel) {
        if (number < 0) {
            throw new IllegalArgumentException("O número da conta deve ser pelo menos 0");
        }
        if (type == null) {
            throw new IllegalArgumentException("O tipo de conta não pode ser nulo");
        }
        if(balance < 0) {
            throw new IllegalArgumentException("O saldo da conta não pode ser negativo");
        }
        if(clientModel == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo");
        }
        this.number = number;
        this.type = type;
        this.balance = balance;
        this.clientModel = clientModel;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "number=" + number+
                ", type='" + (type != null ? type : "N/A") + '\'' +
                ", balance=" + balance + '}';
    }
}
