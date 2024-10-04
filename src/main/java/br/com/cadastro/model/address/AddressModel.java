package br.com.cadastro.model.address;

import br.com.cadastro.model.client.ClientModel;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
@Data
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A rua não pode ser nula")
    @NotEmpty(message = "A rua não pode ser vazia")
    private String street;

    @Min(value = 0, message = "O número da residência deve ser pelo menos 0")
    private int number;

    @NotNull(message = "A cidade não pode ser nula")
    @NotEmpty(message = "A cidade não pode ser vazia")
    private String city;

    @NotNull(message = "O estado não pode ser nulo")
    @NotEmpty(message = "O estado não pode ser vazio")
    private String states;

    @OneToOne
    @NotNull(message = "O cliente não pode ser nulo")
    @JoinColumn(name = "client_id")
    private ClientModel clientModel;

    public AddressModel() {
    }

    public AddressModel(String street, int number, String city, String states, ClientModel clientModel) {
        if (street == null || street.trim().isEmpty()) {
            throw new IllegalArgumentException("A rua não pode ser nula e nem ser vazia.");
        }
        if (number < 0) {
            throw new IllegalArgumentException("O número da residência deve ser pelo menos 0.");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("A cidade não pode ser nula e nem ser vazia.");
        }
        if (states == null || states.trim().isEmpty()) {
            throw new IllegalArgumentException("O estado não pode ser nulo e nem ser vazio.");
        }
        if (clientModel == null) {
            throw new IllegalArgumentException("A cliente não pode ser nulo.");
        }
        this.street = street;
        this.number = number;
        this.city = city;
        this.states = states;
        this.clientModel = clientModel;
    }

    @Override
    public String toString() {
        return "AddressModel{" +
                "street='" + (street != null ? street : "N/A") + '\'' +
                ", number=" + number +
                ", city='" + (city != null ? city : "N/A") + '\'' +
                ", states='" + (states != null ? states : "N/A") + '\'' + '}';
    }
}
