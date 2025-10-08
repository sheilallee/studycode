package locadora;

public class Automovel implements Alugavel {
	  public static final int BASICO = 0;   // Carros hatch
	  public static final int FAMILIA = 1;  // Carros Sedan ou SUV b�sico
	  public static final int LUXO = 2;     // Carros padr�o luxo

	  private String descricao;
	  private String placa;
	  private int ano; // Ano em que foi locado
	  private int codigoDoPreco;
	  
	  public Automovel(String descricao, int ano, String placa, int codigoDoPreco) {
	    this.descricao = descricao;
	    this.placa = placa;
	    this.ano = ano;
	    this.codigoDoPreco = codigoDoPreco;
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
	    return codigoDoPreco;
	  }
	 
	public void setCodigoDoPreco(int codigoDoPreco) {
	   this.codigoDoPreco = codigoDoPreco;
	}

	
	public double valorDeUmaLocacao(int diasAlugado) {
		return getValorDaLocacao(diasAlugado); 
    }

	@Override
    public double getValorDaLocacao(int diasAlugado) {
        double valor = 0.0;
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
        return valor;
    }	 

	@Override
	public int getPontosDeAlugadorFrequente(int diasAlugado){
        int pontos = 1; 
        if(codigoDoPreco == LUXO && diasAlugado > 2){
            pontos += 2;
        }
        return pontos;
    }	

}
