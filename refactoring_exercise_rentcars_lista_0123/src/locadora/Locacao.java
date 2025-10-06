package locadora;

public class Locacao {
	   private Automovel carro;
	   private int diasAlugado;
	 
	   public Locacao(Automovel carro, int diasAlugado) {
	      this.carro = carro;
	      this.diasAlugado = diasAlugado;
	   }
	 
	   public Automovel getCarro() {
	      return carro;
	   }
	 
	   public int getDiasAlugado() {
	      return diasAlugado;
	   }

	   // (refactoring2-1) Regra exptert information 
	   public double valorDeUmaLocacao(){
			double valor = 0.0;

			switch (carro.getCodigoDoPreco()) {
				case Automovel.BASICO:
					valor += diasAlugado * 900.0;
					break;

				case Automovel.FAMILIA:
					valor += diasAlugado * 130.0;
					break;

				case Automovel.LUXO: 
					valor += diasAlugado * 200.0; 
					// Desconto de 10% se alugar mais de 4 dias
					if(diasAlugado > 4){
						valor *= 0.9;
					}
					break;

				default:
					break;
			}
			return valor;
	   }

	   // (refactoring2-3) Pontos de locador frequente de uma locação  
	   public int pontosDeAlugadorFrequente() {
			int pontos = 1;
			if(carro.getCodigoDoPreco() == Automovel.LUXO && diasAlugado > 2){
				pontos += 2;
			}
			return pontos;
		}

}

