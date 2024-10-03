package br.com.cadastro.model.client;

import br.com.cadastro.model.account.AccountModel;
import br.com.cadastro.model.address.AddressModel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "client")
@Data
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private AddressModel addressModel;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<AccountModel> accountModel;
    public ClientModel() {
    }

    public ClientModel(String name, String email, String password, AddressModel addressModel) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.addressModel = addressModel;
    }

    @Override
    public String toString() {
        return "ClientModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", addressModel=" + addressModel +
                '}';
    }
}
