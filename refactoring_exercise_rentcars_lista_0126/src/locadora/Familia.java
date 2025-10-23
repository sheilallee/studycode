package locadora;

public class Familia extends Classificacao{

    @Override
    public int getCodigoDoPreco(){
        return Classificacao.FAMILIA;
    }

    @Override
    public double getValorDaLocacao(int diasAlugado){
        return diasAlugado * 130.0;
    }
}
