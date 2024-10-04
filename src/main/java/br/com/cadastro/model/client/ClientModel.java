package br.com.cadastro.model.client;

import br.com.cadastro.model.account.AccountModel;
import br.com.cadastro.model.address.AddressModel;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Data
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome não pode ser nulo")
    @NotEmpty(message = "O nome não pode ser vazio")
    private String name;

    @NotNull(message = "O e-mail não pode ser nulo.")
    @NotEmpty(message = "O e-mail não pode ser vazio.")
    @Email(message = "O e-mail deve ser válido.")
    private String email;

    @NotNull(message = "A senha não pode ser nula")
    @NotEmpty(message = "A senha não pode ser vazia")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
    private String password;

    @NotNull(message = "O endereço não pode ser nulo")
    @OneToOne(mappedBy = "clientModel", cascade = CascadeType.ALL)
    private AddressModel addressModel;

    @NotNull(message = "A conta não pode ser nula")
    @OneToMany(mappedBy = "clientModel", cascade = CascadeType.ALL)
    private List<AccountModel> accountModel = new ArrayList<>();

    public ClientModel() {}

    public ClientModel(String name, String email, String password) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia.");
        }
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "ClientModel{" +
                "id= '" + id + '\'' +
                ", name='" + (name != null ? name : "N/A") + '\'' +
                ", email='" + (email != null ? email : "N/A") + '\'' + '}';
    }
}
