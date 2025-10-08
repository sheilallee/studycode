package locadora;

public class Locacao {
	   private Alugavel carro;
	   private int diasAlugado;
	 
	   public Locacao(Alugavel carro, int diasAlugado) {
	      this.carro = carro;
	      this.diasAlugado = diasAlugado;
	   }
	 
	   public Alugavel getCarro() {
	      return carro;
	   }
	 
	   public int getDiasAlugado() {
	      return diasAlugado;
	   }

    // cálculo foi delegado para o automovel
    public double valorDeUmaLocacao() {
        return carro.getValorDaLocacao(diasAlugado);
    }

    //cálculo dos pontos foi delegado para o automovel
    public int getPontosDeAlugadorFrequente() {
        return carro.getPontosDeAlugadorFrequente(diasAlugado);
    }

}

