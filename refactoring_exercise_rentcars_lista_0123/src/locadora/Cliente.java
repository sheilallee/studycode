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

    // Uma linha do extrato  
    private String linhaDoExtrato(int sequencia, Locacao locacao, double valor, String fimDeLinha) {
        return String.format(
            "%02d. %-20s       %4d     R$ %8.2f" + fimDeLinha,
            sequencia,
            locacao.getCarro().getDescricao(),
            locacao.getCarro().getAno(),
            valor
        );
    }

    // Cabeçalho do extrato 
    private String cabecalho(String fimDeLinha) {
        String resultado = "Registro de Loca��es de " + getNome() + fimDeLinha;
        resultado += String.format("Seq Automovel            Ano da Locacao Valor Pago" + fimDeLinha);
        resultado += String.format("=== ==================== ============== ===========" + fimDeLinha);
        return resultado;
    }

    // Rodapé do extrato  
    private String rodape(double valorTotal, int pontosDeLocadorFrequente, String fimDeLinha) {
        String resultado = "";
        resultado += "===================================================" + fimDeLinha;
        resultado += String.format("Valor Acumulado em di�rias............: R$ %8.2f" + fimDeLinha, valorTotal);
        resultado += "Voce acumulou " + pontosDeLocadorFrequente + " pontos de locador frequente";
        return resultado;
    }

    // (refactoring2-4) calcula o total somando o valor de cada Locacao
    public double getValorTotal() {
        double total = 0.0;
        for(Locacao l : carrosAlugados){
            total += l.valorDeUmaLocacao();
        }
        return total;
    }

    //(refactoring2-4) soma os pontos de todas as locações
    public int getPontosTotaisDeAlugadorFrequente() {
        int total = 0;
        for(Locacao l : carrosAlugados){
            total += l.pontosDeAlugadorFrequente();
        }
        return total;
    }

    // =========================== método extrato ===========================
    public String extrato() {
        final String fimDeLinha = System.getProperty("line.separator");
        StringBuilder resultado = new StringBuilder();

        // Cabeçalho
        resultado.append(cabecalho(fimDeLinha));

        // (refactoring2-2) sem variáveis temporárias desnecessárias
        int sequencia = 0;
        Iterator<Locacao> it = carrosAlugados.iterator();
        while (it.hasNext()) {
            Locacao locacao = it.next();
            sequencia++;
            double valorDaLinha = locacao.valorDeUmaLocacao(); // cálculo delegado a Locacao
            resultado.append(linhaDoExtrato(sequencia, locacao, valorDaLinha, fimDeLinha));
        }

        // (refactoring2-4) pega os agregados prontos
        double valorTotal = getValorTotal();
        int pontos = getPontosTotaisDeAlugadorFrequente();

        resultado.append(rodape(valorTotal, pontos, fimDeLinha));
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