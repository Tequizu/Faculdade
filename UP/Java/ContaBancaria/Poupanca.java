package ContaBancaria;

public class Poupanca extends Conta {
    private double taxaRendimento;

    public Poupanca(String nome, String cpf, int tp_conta, int nmr_conta, double saldo, String senha,
            double taxaRendimento) {
        super(nome, cpf, tp_conta, nmr_conta, saldo, senha);
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public double calcularRendimento() {
        return getSaldo() * taxaRendimento;
    }

    @Override
    public double simularCDB(int dias) {
        double saldoInicial = getSaldo();
        double rendimentoDiario = taxaRendimento / 36500; // Dividido por 365 dias e por 100 para obter a taxa diária
        double saldoFinal = saldoInicial * Math.pow(1 + rendimentoDiario, dias); // Fórmula do montante final com juros compostos
        return saldoFinal - saldoInicial; // Retorna o rendimento total após o período especificado
    }
    
    // Método adicional para simular o rendimento de uma aplicação em CDB com liquidez diária
    public double simularRendimentoCDBDiario(int dias) {
        double saldoInicial = getSaldo();
        double rendimentoDiario = taxaRendimento / 36500; // Dividido por 365 dias e por 100 para obter a taxa diária
        double saldoFinal = saldoInicial * Math.pow(1 + rendimentoDiario, dias); // Fórmula do montante final com juros compostos
        return saldoFinal - saldoInicial; // Retorna o rendimento total após o período especificado
    }
}