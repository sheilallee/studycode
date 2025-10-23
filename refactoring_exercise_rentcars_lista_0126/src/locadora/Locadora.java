package locadora;


//import locadora.Locacao;

public class Locadora {

	public static void main(String[] args) {
		Cliente c1 = new Cliente("Alex Sandro");		 


		c1.adicionaLocacao(new Locacao(new Automovel("Toyota Corolla xEi", 2022, "PLN0525", Classificacao.LUXO), 10));
		c1.adicionaLocacao(new Locacao(new Automovel("Fiat Mobi", 2021, "JPA2464", Classificacao.BASICO), 2));
		c1.adicionaLocacao(new Locacao(new Automovel("BMW S�rie 7", 2023, "UBA0808", Classificacao.LUXO), 30));
		c1.adicionaLocacao(new Locacao(new Automovel("Fiat Siena", 2023, "ABC0001", Classificacao.FAMILIA), 4));
		c1.adicionaLocacao(new Locacao(new Automovel("Honda HRV", 2022, "KJD9745", Classificacao.FAMILIA), 10));
		c1.adicionaLocacao(new Locacao(new Automovel("Volkswagen Gol", 2021, "JJJ0055", Classificacao.BASICO), 3));

		System.out.println(c1.extrato());
		// System.out.println("\n=== HTML ===\n" + c1.extratoHTML());
	}
}
