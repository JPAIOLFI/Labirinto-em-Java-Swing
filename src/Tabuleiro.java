import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tabuleiro extends JPanel {
    private static final int MAXLIN = 15;
    private static final int MAXCOL = 20;
    private ElementoBasico[][] celulas;
    private App app;
    private Personagem principal;
    private int linIn;
    private int conIn;

    public Tabuleiro(App app) {
        super();
        // Cria o conjunto de células vazia e as insere no layout
        celulas = new ElementoBasico[MAXLIN][MAXCOL];
        this.setLayout(new GridLayout(MAXLIN, MAXCOL));
        for (int i = 0; i < MAXLIN; i++) {
            for (int j = 0; j < MAXCOL; j++) {

                celulas[i][j] = new Grama("Fundo[" + i + "][" + j + "]", i, j, this);
                this.add(celulas[i][j]);
            }
        }
        this.app = app;
    }

    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = App.class.getResource("imagens/" + path);

        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            System.exit(0);
            return null;
        }
    }

    public static int getMaxlin() {
        return MAXLIN;
    }

    public static int getMaxcol() {
        return MAXCOL;
    }

    public boolean posicaoValida(int lin, int col) {
        if ((lin < 0) || (col < 0) ||
                (lin >= MAXLIN) || (col >= MAXCOL)) {
            return false;
        } else {
            return true;
        }
    }

    // Retorna referencia em determinada posição
    public ElementoBasico getElementoNaPosicao(int lin, int col) {
        if (!posicaoValida(lin, col)) {
            return null;
        }
        return celulas[lin][col];
    }

    public ElementoBasico posicaoInicial(ElementoBasico posicaoInicial) {
        // int lin = elemento.getLin();
        // int col = elemento.getCol();
        posicaoInicial = celulas[2][9];
        return posicaoInicial;

    }

    public ElementoBasico insereElemento(ElementoBasico elemento) {
        int lin = elemento.getLin();
        int col = elemento.getCol();
        if (!posicaoValida(lin, col)) {
            throw new IllegalArgumentException("Posicao invalida:" + lin + " ," + col);
        }
        ElementoBasico elementoAnterior = celulas[lin][col];
        celulas[lin][col] = elemento;
        return elementoAnterior;
    }

    public void atualizaVisualizacao() {
        // Atualiza o conteúdo do JPanel (ver algo melhor)
        this.removeAll(); // erase everything from your JPanel
        this.revalidate();
        this.repaint();// I always do these steps after I modify my JPanel
        for (int i = 0; i < MAXLIN; i++) {
            for (int j = 0; j < MAXCOL; j++) {
                this.add(celulas[i][j]);
            }
        }
    }

    public void loadLevel(int nivel) {
        Path path1 = Paths.get(String.format("nivel%d.txt", nivel));
        try (BufferedReader reader = Files.newBufferedReader(path1)) {
            String line = null;
            int lin = 0;
            while ((line = reader.readLine()) != null) {

                for (int col = 0; col < MAXCOL; col++) {
                    if (line.charAt(col) != ' ') {
                        ElementoBasico elem = this.getElem(line.charAt(col), lin, col);
                        this.insereElemento(elem);
                    }
                }
                lin++;

            }
        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }
    }

    // define os sinais referentes aos objetos do jogo, para serem inseridos no mapa
    public ElementoBasico getElem(char elem, int lin, int col) {

        switch (elem) {
            case ' ':
                return new Grama("Fundo", lin, col, this);

            case '-':
                return new Parede("Parede", lin, col, this);
            case '=':
                return new Bau("Fim", lin, col, this);
            case '>':
                return new Armadilha("armadilha", lin, col, this, col);
            case '+':
                return new Vida("Vida", lin, col, this);
            case '@':
                return new Martelo("Martelo", lin, col, this);
            case '*': {
                ElementoBasico anterior = new Grama("Fundo", lin, col, this);
                principal = new Personagem("Veio", "grassVeio.jpg", lin, col, this, app);
                principal.setAnterior(anterior);
                this.linIn = lin;
                this.conIn = col;
                return principal;
            }

            default:
                throw new IllegalArgumentException("Personagem invalido: " + elem);
        }

    }

    // retorna o personagem principal
    public Personagem getPrincipal() {
        return principal;
    }

    // retorna linha inicial
    public int getLinInicial() {
        return linIn;
    }

    // retorna a coluna inicial
    public int getConInicial() {
        return conIn;
    }
}
