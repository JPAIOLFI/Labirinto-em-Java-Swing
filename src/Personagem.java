import javax.swing.JOptionPane;

public class Personagem extends ElementoBasico {
    private ElementoBasico anterior;
    private App app;

    private boolean temMartelo = false;
    private int vidas = 3;
    private int vidaMartelo;

    // construtor
    public Personagem(String id, String iconPath, int linInicial, int colInicial, Tabuleiro tabuleiro, App app) {
        super(id, iconPath, linInicial, colInicial, tabuleiro);
        this.app = app;
        this.vidaMartelo = 3;
    }

    public void setAnterior(ElementoBasico anterior) {
        this.anterior = anterior;
    }

    public ElementoBasico getAnterior() {
        return anterior;
    }

    public void moveDireita() {
        // Remove o Personagem da posicao atual e avança
        getTabuleiro().insereElemento(anterior);
        this.incCol();
        // Verifica se tem algum elemento de interesse na nova posicao
        // e interage de acordo
        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());
        if (!(elemento instanceof Grama)) {
            acaoElemento(elemento);
            this.decCol();
            this.anterior = getTabuleiro().insereElemento(this);
        } else {
            this.anterior = getTabuleiro().insereElemento(this);
        }
    }

    public void moveEsquerda() {
        // Remove o Personagem da posicao atual e avança
        getTabuleiro().insereElemento(anterior);
        this.decCol();
        // Verifica se tem algum elemento de interesse na nova posicao
        // e interage de acordo
        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());
        if (!(elemento instanceof Grama)) {
            acaoElemento(elemento);
            this.incCol();
            this.anterior = getTabuleiro().insereElemento(this);
        } else {
            this.anterior = getTabuleiro().insereElemento(this);
        }
    }

    public void moveCima() {
        // Remove o Personagem da posicao atual e avança
        getTabuleiro().insereElemento(anterior);
        this.decLin();
        // Verifica se tem algum elemento de interesse na nova posicao
        // e interage de acordo
        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());
        if (!(elemento instanceof Grama)) {
            acaoElemento(elemento);
            this.incLin();
            this.anterior = getTabuleiro().insereElemento(this);
        } else {
            this.anterior = getTabuleiro().insereElemento(this);
        }
    }

    public void moveBaixo() {
        // Remove o Personagem da posicao atual e avança
        getTabuleiro().insereElemento(anterior);
        this.incLin();
        // Verifica se tem algum elemento de interesse na nova posicao
        // e interage de acordo
        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());
        if (!(elemento instanceof Grama)) {
            acaoElemento(elemento);
            this.decLin();
            this.anterior = getTabuleiro().insereElemento(this);
        } else {
            this.anterior = getTabuleiro().insereElemento(this);
        }
    }

    @Override
    public void acao(ElementoBasico outro) {
        throw new UnsupportedOperationException("Unimplemented method 'acao'");
    }

    // metodo que atualiza a posição do boneco, para a posição inicial, assim que
    // ele interage com uma armadilha
    public void atualizaPos(int lin, int col) {
        super.setLin(lin);
        super.setCol(col);
    }

    // Essa função chama as ações dos elementos dependendo de qual for o tal
    private void acaoElemento(ElementoBasico elemento) {

        // PAREDE
        if ((elemento instanceof Parede) && this.temMartelo && vidaMartelo > 0) {// se for parede testa se tem picareta
                                                                                 // ou n

            elemento.acao(elemento);
            vidaMartelo--;
        }

        // Armadilha
        if ((elemento instanceof Armadilha)) {
            if (this.vidas != 0) {
                this.vidas--;
                this.app.atualizarlayout();
                elemento.acao(elemento);
                this.atualizaPos(app.getTabuleiro().getLinInicial(), app.getTabuleiro().getConInicial());

            } else {
                JOptionPane.showMessageDialog(getRootPane(), "Você Morreu!");
                System.exit(0);
            }
        }
        // Bau
        if (elemento instanceof Bau) {
            JOptionPane.showMessageDialog(getRootPane(), "Parabens, voce ganhou!!");
            System.exit(0);
        }

        // Martelo
        if ((elemento instanceof Martelo)) {

            this.temMartelo = true;
            elemento.acao(elemento);
            this.app.atualizarlayout();
        }
        if ((elemento instanceof Vida)) {
            if (vidas > 2) {

            } else
                this.vidas++;
            this.app.atualizarlayout();
            elemento.acao(elemento);

        }
    }

    // Metodo usado para atualizar se esta com a martelo ou não nos itens
    public String getTemMartelo() {
        String martelo = "Não tem";
        if (temMartelo) {
            martelo = "Tem";
        }
        return martelo;

    }

    public int getvidas() {
        return vidas;
    }

    public int getMartas() {
        return vidaMartelo;
    }

    public boolean temPicaEreta() {
        return temMartelo;
    }
}
