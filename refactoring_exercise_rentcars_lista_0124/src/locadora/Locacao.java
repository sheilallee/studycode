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

    // cálculo foi delegado para o automovel
    public double valorDeUmaLocacao() {
        return carro.valorDeUmaLocacao(diasAlugado);
    }

    //cálculo dos pontos foi delegado para o automovel
    public int getPontosDeAlugadorFrequente() {
        return carro.getPontosDeAlugadorFrequente(diasAlugado);
    }

}

