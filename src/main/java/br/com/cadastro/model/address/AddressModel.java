package br.com.cadastro.model.address;

import br.com.cadastro.model.client.ClientModel;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private int number;

    private String city;

    private String states;

    @OneToOne
    @JoinColumn(name = "client_id")
    private ClientModel clientModel;

    public AddressModel() {
    }

    public AddressModel(String street, int number, String city, String states, ClientModel clientModel) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.states = states;
        this.clientModel = clientModel;
    }

    @Override
    public String toString() {
        return "AddressModel{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", city='" + city + '\'' +
                ", states='" + states + '\'' +
                ", clientModel=" + clientModel +
                '}';
    }
}
