package locadora;

public abstract class Classificacao {

    public static final int BASICO = 0;   // Carros hatch
    public static final int FAMILIA = 1;  // Carros Sedan ou SUV básico
    public static final int LUXO = 2;     // Carros padrão luxo

    public abstract int getCodigoDoPreco();
    public abstract double getValorDaLocacao(int diasAlugado);

    public int getPontosDeAlugadorFrequente(int diasAlugado) {
        return 1;
    }

    boolean isNull() {       
        return false;
    }
    
}
