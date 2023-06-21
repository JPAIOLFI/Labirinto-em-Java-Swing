

public class Parede extends ElementoBasico {

    // construtor
    public Parede(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, "Parede.png", linInicial, colInicial, tabuleiro);
    }

    // Metodo que trasnforma a parede em grama depois da interação com o personagem, com a posse da marreta
    @Override
    public void acao(ElementoBasico outro) {
        Grama grass = new Grama("Grass",this.getLin(),this.getCol(),getTabuleiro()); 
        this.getTabuleiro().insereElemento(grass);
    }
}
