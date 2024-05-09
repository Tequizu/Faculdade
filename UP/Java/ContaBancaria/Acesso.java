package ContaBancaria;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Acesso {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Conta contaCorrente = new Corrente("Jose", "123456789", 1, 1, 1000, "1", 1.2); 
        Conta contaPoupanca = new Poupanca("Marcos", "987654321", 2, 2, 2000, "2", 0.8);

        System.out.print("Digite o número da conta: ");
        int numeroConta = scanner.nextInt();
        scanner.nextLine();

        Conta contaSelecionada = null;

        if (numeroConta == contaCorrente.getNmr_conta()) {
            contaSelecionada = contaCorrente;
        } else if (numeroConta == contaPoupanca.getNmr_conta()) {
            contaSelecionada = contaPoupanca;
        } else {
            System.out.println("Conta não encontrada.");
            scanner.close();
            return;
        }

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        if (!contaSelecionada.verificarSenha(senha)) {
            System.out.println("Senha incorreta.");
            scanner.close();
            return;
        }

        System.out.println("\nBem-vindo, " + contaSelecionada.getNome() + "!");

        DecimalFormat df = new DecimalFormat("#.##");

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Realizar saque");
            System.out.println("2. Realizar depósito");
            System.out.println("3. Mudar senha");
            System.out.println("4. Ver rendimentos ");
            System.out.println("5. Simular rendimento em CDB ");
            System.out.println("6. Sair");

            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.print("Digite o valor do saque: ");
                double valorSaque = scanner.nextDouble();
                scanner.nextLine();
                contaSelecionada.sacar(valorSaque);
            } else if (opcao == 2) {
                System.out.print("Digite o valor do depósito: ");
                double valorDeposito = scanner.nextDouble();
                scanner.nextLine();
                contaSelecionada.depositar(valorDeposito);
            } else if (opcao == 3) {
                System.out.print("Digite a nova senha: ");
                String novaSenha = scanner.nextLine();
                contaSelecionada.setSenha(novaSenha);
                System.out.println("Senha alterada com sucesso.");
            } else if (opcao == 4) {
                System.out.println("Rendimento: " + df.format(contaSelecionada.calcularRendimento()));
            } else if (opcao == 5) {
        
                    Poupanca poupanca = (Poupanca) contaSelecionada;
                    double rendimentoDia = poupanca.simularRendimentoCDBDiario(1);
                    double rendimentoSemana = poupanca.simularRendimentoCDBDiario(7);
                    double rendimentoMes = poupanca.simularRendimentoCDBDiario(30);
                    double rendimentoAno = poupanca.simularRendimentoCDBDiario(365);
                    
                    System.out.println("Simulação de rendimento em CDB:");
                    System.out.println("Rendimento em 1 dia: " + df.format(rendimentoDia));
                    System.out.println("Rendimento em 1 semana: " + df.format(rendimentoSemana));
                    System.out.println("Rendimento em 1 mês: " + df.format(rendimentoMes));
                    System.out.println("Rendimento em 1 ano: " + df.format(rendimentoAno));

            } else if (opcao == 6) {
                System.out.println("Você finalizou a sessão.");
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}