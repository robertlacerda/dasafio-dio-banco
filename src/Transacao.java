import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transacao {

    private TipoTransacao tipo;
    private double valor;
    private LocalDateTime data;

    public Transacao(TipoTransacao tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = LocalDateTime.now();
    }

    // Retorna o valor e a data da transação
    @Override
    public String toString() {
        return tipo +  "- R$ " + valor +  " - " + data;
    }
}
