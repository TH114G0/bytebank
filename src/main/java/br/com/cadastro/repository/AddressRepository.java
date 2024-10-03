package br.com.cadastro.repository;

import br.com.cadastro.model.address.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressModel, Long> {}