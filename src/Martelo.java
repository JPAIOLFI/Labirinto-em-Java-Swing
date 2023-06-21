public class Martelo extends ElementoBasico {
    // construtor
    public Martelo(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, "Martelo.jpeg", linInicial, colInicial, tabuleiro);
    }

    // trasforma o espaço onde o Martelo estava em grama, depois da interação com o
    // personagem
    @Override
    public void acao(ElementoBasico outro) {
        Grama grass = new Grama("Grass", this.getLin(), this.getCol(), getTabuleiro());
        this.getTabuleiro().insereElemento(grass);
    }
}
