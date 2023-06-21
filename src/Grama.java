public class Grama extends ElementoBasico {
    // construtor
    public Grama(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, "grass.jpg", linInicial, colInicial, tabuleiro);

    }

    @Override
    public void acao(ElementoBasico outro) {
        throw new UnsupportedOperationException("Unimplemented method 'acao'");
    }
}
