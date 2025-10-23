package locadora;

public class ClassificacaoFabrica {
    //simple factory
    public static Classificacao criar(int classificacao){
        switch(classificacao ){
            case Classificacao.BASICO:
                return new Basica();
            case Classificacao.FAMILIA:
                return new Familia();
            case Classificacao.LUXO:
                return new Luxo();
            default: 
                return new ClassificacaoNula(); //NULL OBJECT
           // throw new IllegalArgumentException("Código de preço incorreto");
        }
    }
}