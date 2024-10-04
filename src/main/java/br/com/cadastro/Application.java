package br.com.cadastro;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Application {
    public static void main(String[] args) {}
import br.com.cadastro.model.account.AccountModel;
import br.com.cadastro.model.account.AccountType;
import br.com.cadastro.model.address.AddressModel;
import br.com.cadastro.model.client.ClientModel;
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
                System.out.println("Sucesso!");
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
}