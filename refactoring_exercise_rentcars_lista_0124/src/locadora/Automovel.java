package locadora;

public class Automovel {
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
	 
	  public String getDescricao() {
	    return descricao;
	  }
	  
	  public String getPlaca() {
	    return placa;
	  }
	  
	  public int getAno() {
	    return ano;
	  }
	 
	  public int getCodigoDoPreco() {
	    return codigoDoPreco;
	  }
	 
	public void setCodigoDoPreco(int codigoDoPreco) {
	   this.codigoDoPreco = codigoDoPreco;
	}

	// refactoring3
public double valorDeUmaLocacao(int diasAlugado) {
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
                if (diasAlugado > 4) {
                    valor *= 0.9; 
                }
                break;
            default:
                break;
        }
        return valor;
    }

	public int getPontosDeAlugadorFrequente(int diasAlugado){
        int pontos = 1; 
        if(codigoDoPreco == LUXO && diasAlugado > 2){
            pontos += 2;
        }
        return pontos;
    }	

}
