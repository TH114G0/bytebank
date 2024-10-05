package br.com.cadastro.service;

import br.com.cadastro.model.account.AccountModel;
import br.com.cadastro.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public void listAccount(Long id) {
        Optional<AccountModel> accountModelOptional = accountRepository.findById(id);
        AccountModel accountModel = accountModelOptional.orElseThrow(() -> new EntityNotFoundException("Conta não encontrada para o ID: " + id));
        System.out.println(accountModel);
    }

    public void deposit(Long id, double amount) {
        Optional<AccountModel> accountModelOptional = accountRepository.findById(id);
        AccountModel accountModel = accountModelOptional.orElseThrow(() -> new EntityNotFoundException("Conta não encontrada para o ID: " + id));

        if (amount <= 0) {
            System.out.println("Valor inválido para depósito.");
            return;
        }

        accountModel.setBalance(accountModel.getBalance() + amount);

        accountRepository.save(accountModel);

        System.out.println("Depósito realizado com sucesso.");
    }

    public void withdraw(Long id, double amount) {
        Optional<AccountModel> accountModelOptional = accountRepository.findById(id);
        AccountModel accountModel = accountModelOptional.orElseThrow(() -> new EntityNotFoundException("Conta não encontrada para o ID: " + id));

        if (amount <= 0) {
            System.out.println("Valor inválido para saque. O valor deve ser maior que zero.");
            return;
        }

        if (amount > accountModel.getBalance()) {
            System.out.println("Valor inválido para saque! O valor disponível é - " + accountModel.getBalance());
            return;
        }

        accountModel.setBalance(accountModel.getBalance() - amount);
        accountRepository.save(accountModel);
        System.out.println("Saque realizado com sucesso.");
    }

    public void transfer(Long id, double amount, Long idDestiny) {
        Optional<AccountModel> accountModelOptional = accountRepository.findById(id);
        AccountModel accountOrigin  = accountModelOptional.orElseThrow(() -> new EntityNotFoundException("Conta não encontrada para o ID: " + id));

        Optional<AccountModel> accountModelDestinyOptional = accountRepository.findById(idDestiny);
        AccountModel accountDestiny = accountModelDestinyOptional.orElseThrow(() -> new EntityNotFoundException("Conta de destino não encontrada para o ID: " + idDestiny));

        if (amount <= 0) {
            System.out.println("Valor inválido para transferência. O valor deve ser maior que zero.");
            return;
        }

        if (amount > accountOrigin.getBalance()) {
            System.out.println("Saldo insuficiente! O valor disponível na conta de origem é: " + accountOrigin.getBalance());
            return;
        }

        accountOrigin.setBalance(accountOrigin.getBalance() - amount);
        accountDestiny.setBalance(accountDestiny.getBalance() + amount);

        accountRepository.save(accountOrigin);
        accountRepository.save(accountDestiny);

        System.out.println("Transferência realizada com sucesso.");
    }
}
