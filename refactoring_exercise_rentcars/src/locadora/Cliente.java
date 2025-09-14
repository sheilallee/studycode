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
		String resultado = "Registro de LocaÁıes de " + getNome() + fimDeLinha;
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
			// adiciona bonus para loca√ß√£o de um carro de luxo por pelo menos 3 dias
			if(cada.getCarro().getCodigoDoPreco() == Automovel.LUXO &&
				cada.getDiasAlugado() > 2) {
				pontosDeLocadorFrequente+=2;
			}

			// mostra valores para esta loca√ß√£o
			sequencia++;
			resultado += String.format("%02d. %-20s       %4d     R$ %8.2f"+fimDeLinha,sequencia, cada.getCarro().getDescricao(),cada.getCarro().getAno(), valorCorrente );
			valorTotal += valorCorrente;

		} // while

		// adiciona rodap√©
		resultado += "===================================================" + fimDeLinha;
		resultado += String.format("Valor Acumulado em di·rias............: R$ %8.2f" + fimDeLinha, valorTotal);
		resultado += "Voce acumulou " + pontosDeLocadorFrequente +
              " pontos de locador frequente";

		return resultado;
	}

}