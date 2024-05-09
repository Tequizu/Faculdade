package ContaBancaria;

public abstract class Conta {
    private String nome;
    private String cpf;
    private int tp_conta;
    private int nmr_conta;
    private double saldo;
    private String senha;

    public Conta(String nome, String cpf, int tp_conta, int nmr_conta, double saldo, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.tp_conta = tp_conta;
        this.nmr_conta = nmr_conta;
        this.saldo = saldo;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getTp_Conta() {
        return tp_conta;
    }

    public int getNmr_conta() {
        return nmr_conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }

    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito de " + valor + " realizado com sucesso.");
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public abstract double calcularRendimento();

    public abstract double simularCDB(int dias);
}