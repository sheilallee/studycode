package locadora;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cliente {
    private String nome;
    private List<Locacao> carrosAlugados = new ArrayList<>();

    public Cliente(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    public void adicionaLocacao(Locacao locacao) { carrosAlugados.add(locacao); }

    // Linha do extrato 
    private String linhaDoExtrato(int seq, Locacao locacao, double valor, String nl) {
        return String.format("%02d. %-20s %4d R$ %8.2f" + nl,
                seq,
                locacao.getCarro().getDescricao(),
                locacao.getCarro().getAno(),
                valor);
    }

    // Cabeçalho do extrato 
    private String cabecalho(String nl) {
        StringBuilder sb = new StringBuilder();
        sb.append("Registro de Locações de ").append(getNome()).append(nl);
        sb.append("Seq Automovel            Ano da Locacao  Valor Pago").append(nl);
        sb.append("=== ==================== ==============  ===========").append(nl);
        return sb.toString();
    }

    // Rodapé do extrato
    private String rodape(double valorTotal, int pontos, String nl) {
        StringBuilder sb = new StringBuilder();
        sb.append("===================================================").append(nl);
        sb.append(String.format("Valor Acumulado em diárias............: R$ %8.2f" + nl, valorTotal));
        sb.append("Você acumulou ").append(pontos).append(" pontos de locador frequente");
        return sb.toString();
    }

    //soma o valor das locações
    public double getValorTotal() {
        double total = 0.0;
        for (Locacao l : carrosAlugados) {
            total += l.valorDeUmaLocacao();
        }
        return total;
    }

    //soma os pontos das locações
    public int getPontosTotaisDeAlugadorFrequente() {
        int total = 0;
        for(Locacao l : carrosAlugados){
            total += l.getPontosDeAlugadorFrequente();
        }
        return total;
    }

    // =========================== extrato texto ===========================
    public String extrato() {
        final String nl = System.getProperty("line.separator");
        StringBuilder out = new StringBuilder();

        out.append(cabecalho(nl));

        int seq = 0;
        Iterator<Locacao> it = carrosAlugados.iterator();
        while(it.hasNext()){
            Locacao loc = it.next();
            seq++;
            double valorLinha = loc.valorDeUmaLocacao(); // delegação
            out.append(linhaDoExtrato(seq, loc, valorLinha, nl));
        }

        out.append(rodape(getValorTotal(), getPontosTotaisDeAlugadorFrequente(), nl));
        return out.toString();
    }

    // ======================== extrato HTML ======================
    public String extratoHTML() {
        final String nl = System.getProperty("line.separator");
        int seq = 0;

        Iterator<Locacao> locacoes = carrosAlugados.iterator();
        String resultado  = "<html><body>" + nl;
        resultado        += String.format("<h2>Registro de Locacoes de <em>%s</em></h2>", getNome()) + nl;
        resultado        += "<table border=\"1\"><tr>"
                         +  "<th>Seq</th><th>Automóvel</th><th>Ano</th><th>Diárias</th><th>Valor</th>"
                         +  "</tr>" + nl;

        while(locacoes.hasNext()){
            Locacao cada = locacoes.next();
            seq++;
            resultado += String.format(
                "<tr><td>%02d.</td><td>%s</td><td>%4d</td><td>%2d</td><td>R$ %8.2f</td></tr>" + nl,
                seq,
                cada.getCarro().getDescricao(),
                cada.getCarro().getAno(),
                cada.getDiasAlugado(),
                cada.valorDeUmaLocacao()
            );
        }

        resultado += String.format(
            "<tfoot><tr><td colspan=\"4\">Valor Acumulado em diárias:</td>"
          + "<td><em>R$ %8.2f</em></td></tr></tfoot></table>" + nl,
            getValorTotal()
        );
        resultado += "<p>Voce acumulou <em>" + getPontosTotaisDeAlugadorFrequente()
                  + "</em> pontos de alugador frequente</p></body></html>";

        return resultado;
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