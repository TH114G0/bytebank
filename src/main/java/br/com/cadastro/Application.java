package br.com.cadastro;

import br.com.cadastro.model.account.AccountModel;
import br.com.cadastro.model.account.AccountType;
import br.com.cadastro.model.address.AddressModel;
import br.com.cadastro.model.client.ClientModel;
import br.com.cadastro.service.AccountService;
import br.com.cadastro.service.AddressService;
import br.com.cadastro.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private ClientService clientService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Você possui cadastro (s/n) ? ");
            String result = scanner.nextLine().trim().toUpperCase();
            if (result.equals("N")) {
                register(scanner);
                break;
            } else if (result.equals("S")) {
                login(scanner);
                break;
            } else {
                System.out.println("Informe apenas as LETRAS informadas.");
            }
        }
    }

    public void login(Scanner scanner) {
        while(true) {
            System.out.println(">>>>>>>>>>>>>>>>LOGIN<<<<<<<<<<<<<<<<");
            System.out.print("Informe o seu e-mail: ");
            String email = scanner.nextLine();
            System.out.print("Informe sua senha segura: ");
            String password = scanner.nextLine();
            if(clientService.validateCredentials(email, password)) {
                System.out.println("Login realizado com sucesso!");
                menu(scanner);
                break;
            }else {
                System.out.println("Email ou Senha inválidos!");
            }
        }
    }

    public void register(Scanner scanner) {
        AccountType accountType = null;

        System.out.println(">>>>>>>>>>>>>>>>REGISTER<<<<<<<<<<<<<<<<");
        System.out.print("Informe o seu nome: ");
        String name = scanner.nextLine();
        System.out.print("Informe o seu e-mail: ");
        String email = scanner.nextLine();
        System.out.print("Informe uma senha segura: ");
        String password = scanner.nextLine();
        System.out.println(">>>>>>>>>>>>>>>>ADDRESS<<<<<<<<<<<<<<<<");
        System.out.print("Informe a sua cidade: ");
        String city = scanner.nextLine();
        System.out.print("Informe o seu estado: ");
        String state = scanner.nextLine();
        System.out.print("Informe a sua rua: ");
        String street = scanner.nextLine();
        System.out.print("Informe o número de sua residência: ");
        int number = scanner.nextInt();
        scanner.nextLine();
        System.out.println(">>>>>>>>>>>>>>>>ACCOUNT<<<<<<<<<<<<<<<<");
        while (true) {
            System.out.println("Informe o tipo de conta: ");
            System.out.println("1 - SAVINGS_ACCOUNT");
            System.out.println("2 - CURRENT_ACCOUNT");

            System.out.print("Digite a opção: ");
            int result = 0;

            try {
                result = scanner.nextInt();
                scanner.nextLine();

                String accountTypeInput = "";

                if (result == 1) {
                    accountTypeInput = "SAVINGS_ACCOUNT";
                } else if (result == 2) {
                    accountTypeInput = "CURRENT_ACCOUNT";
                } else {
                    System.out.println("Opção inválida! Tente novamente...");
                    continue;
                }

                try {
                    accountType = AccountType.valueOf(accountTypeInput);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Tipo de conta inválido. Tente novamente.");
                }
            } catch (IllegalArgumentException ex) {
                System.err.println("Opção inválida - " + ex.getMessage());
                scanner.nextLine();
            }
        }
        System.out.print("Informe o número da conta: ");
        int numberAccount = scanner.nextInt();

        ClientModel clientModel = new ClientModel(name, email, password);
        AddressModel addressModel = new AddressModel(street, number, city, state, clientModel);
        AccountModel accountModel = new AccountModel(numberAccount, accountType, 0.0, clientModel);

        List<AccountModel> accountModelList = new ArrayList<>();
        accountModelList.add(accountModel);

        clientModel.setAccountModel(accountModelList);
        clientModel.setAddressModel(addressModel);
        clientService.createClient(clientModel, accountModelList, addressModel);
    }

    public void menu(Scanner scanner) {
        while (true) {
            System.out.println("ESCOLHA ALGUMA OPÇÃO");
            System.out.println("0. Sair");
            System.out.println("1. Cliente");
            System.out.println("2. Endereço");
            System.out.println("3. Conta");
            int result = 0;
            try {
                result = scanner.nextInt();
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.err.println("Opção inválida - " + e);
                continue;
            }
            switch (result) {
                case 0:
                    System.out.println("Finalizando aplicação.....");
                    return;
                case 1:
                    optionClient(scanner);
                    break;
                case 2:
                    optionAddress(scanner);
                    break;
                case 3:
                    optionAccount(scanner);
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA");
            }
        }
    }

    public void optionClient(Scanner scanner) {
        Long id;
        while (true) {
            System.out.println(">>>>>>>>>>>>>>>>CLIENT<<<<<<<<<<<<<<<<");
            System.out.println("ESCOLHA ALGUMA OPÇÃO");
            System.out.println("0. Sair");
            System.out.println("1. Visualizar informações pessoais");
            System.out.println("2. Excluir conta");
            int result = 0;
            try {
                result = scanner.nextInt();
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.err.println("Opção inválida - " + e);
                continue;
            }
            switch (result) {
                case 0:
                    return;
                case 1:
                    System.out.print("Informe seu ID: ");
                    id = scanner.nextLong();
                    clientService.listClient(id);
                    break;
                case 2:
                    System.out.print("Informe seu ID: ");
                    id = scanner.nextLong();
                    clientService.deleteClient(id);
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA");
            }
        }
    }

    public void optionAddress(Scanner scanner) {
        Long id;
        while (true) {
            System.out.println(">>>>>>>>>>>>>>>>ADDRESS<<<<<<<<<<<<<<<<");
            System.out.println("ESCOLHA ALGUMA OPÇÃO");
            System.out.println("0. Sair");
            System.out.println("1. Visualizar informações pessoais");
            int result = 0;
            try {
                result = scanner.nextInt();
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.err.println("Opção inválida - " + e);
                continue;
            }
            switch (result) {
                case 0:
                    return;
                case 1:
                    System.out.print("Informe seu ID: ");
                    id = scanner.nextLong();
                    addressService.listAddress(id);
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA");
            }
        }
    }

    public void optionAccount(Scanner scanner) {
        Long id;
        double amount;
        while (true) {
            System.out.println(">>>>>>>>>>>>>>>>ACCOUNT<<<<<<<<<<<<<<<<");
            System.out.println("ESCOLHA ALGUMA OPÇÃO");
            System.out.println("0. Sair");
            System.out.println("1. Visualizar informações pessoais");
            System.out.println("2. Realizar depósito");
            System.out.println("3. Realizar saque");
            System.out.println("4. Realizar transferência");
            int result = 0;
            try {
                result = scanner.nextInt();
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.err.println("Opção inválida - " + e);
                continue;
            }
            switch (result) {
                case 0:
                    return;
                case 1:
                    System.out.print("Informe o seu ID: ");
                    id = scanner.nextLong();
                    accountService.listAccount(id);
                    break;
                case 2:
                    System.out.print("Informe o seu id: ");
                    id = scanner.nextLong();
                    System.out.print("Informe o valor do depósito: ");
                    amount = scanner.nextDouble();
                    accountService.deposit(id, amount);
                    break;
                case 3:
                    System.out.print("Informe o seu id: ");
                    id = scanner.nextLong();
                    System.out.print("Informe o valor do saque: ");
                    amount = scanner.nextDouble();
                    accountService.withdraw(id, amount);
                    break;
                case 4:
                    System.out.print("Informe o seu id: ");
                    id = scanner.nextLong();
                    System.out.print("Informe o id da conta de destino: ");
                    Long idDestiny = scanner.nextLong();
                    System.out.print("Informe o valor da transferência: ");
                    amount = scanner.nextDouble();
                    accountService.transfer(id, amount, idDestiny);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}