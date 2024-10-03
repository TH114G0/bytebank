package br.com.cadastro.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private int number;

    private String city;

    private String states;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Address() {
    }

    public Address(String street, int number, String city, String states, Client client) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.states = states;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", city='" + city + '\'' +
                ", states='" + states + '\'' +
                ", client=" + client +
                '}';
    }
}
