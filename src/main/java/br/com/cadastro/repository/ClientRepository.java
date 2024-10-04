package br.com.cadastro.repository;

import br.com.cadastro.model.client.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {
    @Query("SELECT c FROM ClientModel c WHERE c.email = :email AND c.password = :password")
    Optional<ClientModel> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}