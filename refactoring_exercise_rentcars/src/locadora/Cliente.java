package locadora;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cliente {
    private String nome;
    private List<Locacao> carrosAlugados = new ArrayList<Locacao>();

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void adicionaLocacao(Locacao locacao) {
        carrosAlugados.add(locacao);
    }

    // =============== Refatoramento: métodos coesos ===============

    /** Cálculo do valor de uma locação*/
    private double valorDeUmAluguel(Locacao locacao) {
        double valor = 0.0;
        int diasAlugados = locacao.getDiasAlugado();
        int codigoPreco = locacao.getCarro().getCodigoDoPreco();

        switch (codigoPreco) {
            case Automovel.BASICO:   
                valor += diasAlugados * 900.0; 
                break;

            case Automovel.FAMILIA:  
                valor += diasAlugados * 130.0;
                break;

            case Automovel.LUXO:     // R$ 200.00 por dia
                valor += diasAlugados * 200.0;
                if (diasAlugados > 4) {
                    valor *= 0.9;
                }
                break;

            default:
                break;
        }
        return valor;
    }

    /** Pontos de locador frequente de uma locação  */
    private int pontosDeUmAluguel(Locacao locacao) {
        int pontos = 1; //  pontosDeLocadorFrequente++ por locação
        if (locacao.getCarro().getCodigoDoPreco() == Automovel.LUXO &&
            locacao.getDiasAlugado() > 2) {
            pontos += 2; 
        }
        return pontos;
    }

    /** Uma linha do extrato  */
    private String linhaDoExtrato(int sequencia, Locacao locacao, double valor, String fimDeLinha) {
        return String.format(
            "%02d. %-20s       %4d     R$ %8.2f" + fimDeLinha,
            sequencia,
            locacao.getCarro().getDescricao(),
            locacao.getCarro().getAno(),
            valor
        );
    }

    /** Cabeçalho do extrato */
    private String cabecalho(String fimDeLinha) {
        String resultado = "Registro de Loca��es de " + getNome() + fimDeLinha;
        resultado += String.format("Seq Automovel            Ano da Locacao Valor Pago" + fimDeLinha);
        resultado += String.format("=== ==================== ============== ===========" + fimDeLinha);
        return resultado;
    }

    /** Rodapé do extrato  */
    private String rodape(double valorTotal, int pontosDeLocadorFrequente, String fimDeLinha) {
        String resultado = "";
        resultado += "===================================================" + fimDeLinha;
        resultado += String.format("Valor Acumulado em di�rias............: R$ %8.2f" + fimDeLinha, valorTotal);
        resultado += "Voce acumulou " + pontosDeLocadorFrequente + " pontos de locador frequente";
        return resultado;
    }

    // =========================== método extrato ===========================
    public String extrato() {
        final String fimDeLinha = System.getProperty("line.separator");
        double valorTotal = 0.0;
        int pontosDeLocadorFrequente = 0;
        int sequencia = 0;

        Iterator<Locacao> locacoes = carrosAlugados.iterator();

        StringBuilder resultado = new StringBuilder();
        resultado.append(cabecalho(fimDeLinha));

        while (locacoes.hasNext()) {
            Locacao locacao = locacoes.next();

            double valorCorrente = valorDeUmAluguel(locacao);            
            pontosDeLocadorFrequente += pontosDeUmAluguel(locacao);      

            sequencia++;
            resultado.append(linhaDoExtrato(sequencia, locacao, valorCorrente, fimDeLinha)); 
            valorTotal += valorCorrente;
        }

        resultado.append(rodape(valorTotal, pontosDeLocadorFrequente, fimDeLinha));          
        return resultado.toString();
    }
}




/*
package locadora;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cliente {
	private String nome;
	private List<Locacao> carrosAlugados = new ArrayList<Locacao>();

	public Cliente(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void adicionaLocacao(Locacao locacao) {
		carrosAlugados.add(locacao);
	}

	public String extrato() {
		final String fimDeLinha = System.getProperty("line.separator");
		double valorTotal = 0.0;
		int pontosDeLocadorFrequente = 0;
		int sequencia = 0;

		Iterator<Locacao> locacoes = carrosAlugados.iterator();
		String resultado = "Registro de Loca��es de " + getNome() + fimDeLinha;
		resultado += String.format("Seq Automovel            Ano da Locacao Valor Pago"+fimDeLinha);
		resultado += String.format("=== ==================== ============== ==========="+fimDeLinha);


		while(locacoes.hasNext()) {
			double valorCorrente = 0.0;
			Locacao cada = locacoes.next();

			// determina valores para cada linha
			switch(cada.getCarro().getCodigoDoPreco()) {
			case Automovel.BASICO: // R$ 90.00 por dia
				valorCorrente += cada.getDiasAlugado() * 900.0;
				break;

			case Automovel.FAMILIA: // R$ 130.00 por dia
				valorCorrente += cada.getDiasAlugado() * 130.0;
				break;

			case Automovel.LUXO: // R$ 200.00 por dia
				valorCorrente += cada.getDiasAlugado() * 200.0;
				if (cada.getDiasAlugado() > 4)
					valorCorrente *= 0.9;
				break;
			} //switch

			// trata de pontos de locador frequente
			pontosDeLocadorFrequente++;
			// adiciona bonus para locação de um carro de luxo por pelo menos 3 dias
			if(cada.getCarro().getCodigoDoPreco() == Automovel.LUXO &&
				cada.getDiasAlugado() > 2) {
				pontosDeLocadorFrequente+=2;
			}

			// mostra valores para esta locação
			sequencia++;
			resultado += String.format("%02d. %-20s       %4d     R$ %8.2f"+fimDeLinha,sequencia, cada.getCarro().getDescricao(),cada.getCarro().getAno(), valorCorrente );
			valorTotal += valorCorrente;

		} // while

		// adiciona rodapé
		resultado += "===================================================" + fimDeLinha;
		resultado += String.format("Valor Acumulado em di�rias............: R$ %8.2f" + fimDeLinha, valorTotal);
		resultado += "Voce acumulou " + pontosDeLocadorFrequente +
              " pontos de locador frequente";

		return resultado;
	}

}
*/