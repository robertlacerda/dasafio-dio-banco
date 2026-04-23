import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;
    private double limite = 100;

    private List<Transacao> transacoes = new ArrayList<>();


    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    // Validação de saque
    @Override
    public void sacar(Double valor) {
        if (valor > saldo) {
            System.out.println("Saldo insuficiente!");
        } else {
            saldo -= valor;
            transacoes.add(new Transacao(TipoTransacao.SAQUE, valor));
        }
    }

    @Override
    public void depositar(Double valor) {
        this.saldo += valor;
        transacoes.add(new Transacao(TipoTransacao.DEPODITO, valor));
    }

    @Override
    public void transferir(Double valor, Conta contaDestino) {
        if (valor > this.saldo) {
            System.out.println("Saldo insuficiente");
        } else {
            this.sacar(valor);
            contaDestino.depositar(valor);
            transacoes.add(new Transacao(TipoTransacao.TRANFERENCIA, valor));
        }

    }

    protected void imprimirInfosComuns() {
        System.out.println("--- INFO COMUNS ---");
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }

    public void imprimirExtratoCompleto() {
        System.out.println("--- EXTRATO ---");
        for (Transacao t : transacoes) {
            System.out.println(t);
        }
        imprimirInfosComuns();
    }
}
