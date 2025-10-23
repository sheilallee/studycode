package locadora;

public class Automovel implements Alugavel {

	  private String descricao;
	  private String placa;
	  private int ano; // Ano em que foi locado
	  //private int codigoDoPreco;
	  private Classificacao classificacao;
	  
	  public Automovel(String descricao, int ano, String placa, int codigoDoPreco) {
	    this.descricao = descricao;
	    this.placa = placa;
	    this.ano = ano;
	    //this.codigoDoPreco = codigoDoPreco;
		setCodigoDoPreco(codigoDoPreco);
	  }
	 
	 @Override
	  public String getDescricao() {
	    return descricao;
	  }
	  
	  public String getPlaca() {
	    return placa;
	  }
	  
	  @Override
	  public int getAno() {
	    return ano;
	  }
	 
	  public int getCodigoDoPreco() {
	    //return codigoDoPreco;
		return classificacao.getCodigoDoPreco();
	  }
	 
	//troca a classificaçao em tempo de execução
	public void setCodigoDoPreco(int codigoDoPreco) {
	   //this.codigoDoPreco = codigoDoPreco;
	    this.classificacao = ClassificacaoFabrica.criar(codigoDoPreco);
	}

	
	public double valorDeUmaLocacao(int diasAlugado){
		return getValorDaLocacao(diasAlugado); 
    }

	@Override
    public double getValorDaLocacao(int diasAlugado){
		return classificacao.getValorDaLocacao(diasAlugado);
       /* double valor = 0.0;
        switch(codigoDoPreco){
            case BASICO:
                valor += diasAlugado * 900.0;
                break;
            case FAMILIA:
                valor += diasAlugado * 130.0;
                break;
            case LUXO:
                valor += diasAlugado * 200.0;
                if (diasAlugado > 4) valor *= 0.9; 
                break;
            default:
                break;
        }
        return valor; */
    }	 

	@Override
	public int getPontosDeAlugadorFrequente(int diasAlugado){
		return classificacao.getPontosDeAlugadorFrequente(diasAlugado);
		/*int pontos = 1; 
        if(codigoDoPreco == LUXO && diasAlugado > 2){
            pontos += 2;
        }
        return pontos; */
    }	

}
