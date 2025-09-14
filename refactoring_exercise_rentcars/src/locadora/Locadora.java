// import para rodar o código original da lista01.2.1-GRASP-refactoring
//import locadora.*;

// import para rodar o código alterado pela lista01.2.2-GRASP-refactoring
// refactoring 1
//import locadora.Locacao;
//import locadora.Carro;
//import refactoring1.*;

// import para executar o código conforme modificações da lista01.2.3-GRASP-refactoring
//import locadora.Carro;
//import refactoring2.Cliente;
//import refactoring2.Locacao;

//import para executar o código conforme modificações da lista01.2.4-GRASP-refactoring
// Tarefa refactoring3
//import refactoring3.*;

// Tarefa refactoring4
//import locadora.Automovel;
//import locadora.Cliente;
//import locadora.Locacao;
package locadora;


//import locadora.Locacao;

public class Locadora {

	public static void main(String[] args) {
		Cliente c1 = new Cliente("Alex Sandro");		 


		c1.adicionaLocacao(new Locacao(new Automovel("Toyota Corolla xEi", 2022, "PLN0525", Automovel.LUXO), 10));
		c1.adicionaLocacao(new Locacao(new Automovel("Fiat Mobi", 2021, "JPA2464", Automovel.BASICO), 2));
		c1.adicionaLocacao(new Locacao(new Automovel("BMW S�rie 7", 2023, "UBA0808", Automovel.LUXO), 30));
		c1.adicionaLocacao(new Locacao(new Automovel("Fiat Siena", 2023, "ABC0001", Automovel.FAMILIA), 4));
		c1.adicionaLocacao(new Locacao(new Automovel("Honda HRV", 2022, "KJD9745", Automovel.FAMILIA), 10));
		c1.adicionaLocacao(new Locacao(new Automovel("Volkswagen Gol", 2021, "JJJ0055", Automovel.BASICO), 3));

		System.out.println(c1.extrato());
	}
}
