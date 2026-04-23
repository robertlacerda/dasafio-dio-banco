import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Banco banco = new Banco();
        banco.setNome("Banco Digital");
        banco.setContas(new ArrayList<>());

        Cliente marcos = new Cliente();
        marcos.setNome("Marcos");

        Conta cc = new ContaCorrente(marcos);
        Conta poupanca = new ContaPoupanca(marcos);

        banco.getContas().add(cc);
        banco.getContas().add(poupanca);


        Scanner scanner = new Scanner(System.in);

        int opcao;
        boolean continuar = true;

        while (continuar) {

            System.out.println("--- MENU ---");
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Transferir");
            System.out.println("4 - Extrato");
            System.out.println("5 - Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o valor a ser depositado: ");
                    double valor = scanner.nextDouble();
                    cc.depositar(valor);
                    break;

                case 2:
                    System.out.println("Digite o valor a ser sacado: ");
                    double valorSacado = scanner.nextDouble();
                    cc.sacar(valorSacado);
                    break;

                case 3:
                    System.out.println("Digite o valor a ser transferido: ");
                    double valorTransferido = scanner.nextDouble();
                    cc.transferir(valorTransferido, poupanca);
                    break;

                case 4:
                    cc.imprimirExtratoCompleto();
                    break;

                case 5:
                    continuar = false;
                    System.out.println("Encerrando");
                    break;

                default:
                    System.out.println("Opção invalida");
            }
        }
        scanner.close();

        cc.imprimirExtratoCompleto();

    }
}