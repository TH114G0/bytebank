package br.com.cadastro.repository;

import br.com.cadastro.model.client.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {}