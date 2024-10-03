package br.com.cadastro.repository;

import br.com.cadastro.model.account.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountModel, Long> { }