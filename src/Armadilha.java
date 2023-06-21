public class Armadilha extends ElementoBasico {

    private boolean fechada;

    // Construtor
    public Armadilha(String id, int linInicial, int colInicial, Tabuleiro tabuleiro, int tentantivas) {
        super(id, "grass.jpg", linInicial, colInicial, tabuleiro);
        this.fechada = false;

    }

    // metodo responsável por transformar a grama em armadilha assim que personagem pisa
    @Override
    public void acao(ElementoBasico outro) {
        if (fechada) {

            setImage(Tabuleiro.createImageIcon("grass.jpg"));
        } else {

            fechada = true;
            setImage(Tabuleiro.createImageIcon("armadilha.png"));

        }
    }
}
