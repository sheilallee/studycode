package locadora;

public class Luxo extends Classificacao{

    @Override
    public int getCodigoDoPreco(){
        return Automovel.LUXO;
    }

    @Override
    public double getValorDaLocacao(int diasAlugado){
        double valor = diasAlugado * 200.0;
        if(diasAlugado > 4){
            valor *= 0.9; 
        }
        return valor;
    }

    @Override
    public int getPontosDeAlugadorFrequente(int diasAlugado){
        // padrão = 1; bônus de +2 se Luxo por mais de 2 dias
        return (diasAlugado > 2) ? 3 : 1;
    }
}
