package locadora;

public class Basica extends Classificacao{

    @Override
    public int getCodigoDoPreco(){
        return Classificacao.BASICO;
    }

    @Override
    public double getValorDaLocacao(int diasAlugado){
        return diasAlugado * 900.0;
    }
    
}
