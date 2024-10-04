package br.com.cadastro.service;


import br.com.cadastro.model.address.AddressModel;
import br.com.cadastro.repository.AddressRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public void listAddress(Long id) {
        Optional<AddressModel> optionalAddress  = addressRepository.findById(id);
        AddressModel addressModel = optionalAddress.orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado para o ID: " + id));
        System.out.println(addressModel);
    }
}
