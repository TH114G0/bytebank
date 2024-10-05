package br.com.cadastro.service;

import br.com.cadastro.model.account.AccountModel;
import br.com.cadastro.model.address.AddressModel;
import br.com.cadastro.model.client.ClientModel;
import br.com.cadastro.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    private final Scanner scanner = new Scanner(System.in);

    @Transactional
    public void createClient(ClientModel clientModel, List<AccountModel> accountModel, AddressModel addressModel) {
        clientModel.setAddressModel(addressModel);

        List<AccountModel> updatedAccountModels = new ArrayList<>();

        for (AccountModel model : accountModel) {
            model.setClientModel(clientModel);
            updatedAccountModels.add(model);
        }

        clientModel.setAccountModel(updatedAccountModels);
        clientRepository.save(clientModel);
    }

    public void listClient(Long id) {
        Optional<ClientModel> client = clientRepository.findById(id);
        ClientModel clientModel = client.orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado para o ID:" + id));
        System.out.println(clientModel);
    }

    public boolean validateCredentials(String email, String password) {
        Optional<ClientModel> client = clientRepository.findByEmailAndPassword(email, password);
        return client.isPresent();
    }

    public void deleteClient(Long id) {
        while (true) {
            System.out.println("você tem certeza que deseja deletar seu usuário (s/n) ?");
            String resp = scanner.nextLine().trim().toUpperCase();
            if (resp.equals("N")) {
                break;
            } else if (resp.equals("S")) {
                clientRepository.deleteById(id);
                break;
            }
            System.out.println("Resposta inválida!");
        }
    }
}
