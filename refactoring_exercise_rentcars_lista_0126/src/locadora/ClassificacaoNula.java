package locadora;

public class ClassificacaoNula extends Classificacao {

    @Override
    public int getCodigoDoPreco(){
        return BASICO; // Retorna BASICO como padrão
    }

    @Override
    public double getValorDaLocacao(int diasAlugado){
        return 0.0; // Null object não tem custo
    }

    @Override
    public int getPontosDeAlugadorFrequente(int diasAlugado){
        return 0; // Null object não dá pontos
    }

    @Override
    boolean isNull(){
        return true;
    }
}