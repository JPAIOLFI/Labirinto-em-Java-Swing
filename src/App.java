import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener {
    private Tabuleiro tabuleiro;
    private Personagem personagem;

    private JLabel labelVidas;
    private JLabel labelMartelo;

    public App() {
        super();
        // Define o tabuleiro e os outros componentes da tela
        tabuleiro = new Tabuleiro(this);
        personagem = new Personagem("Personagem", "grassVeio.jpg", 1, 9, tabuleiro, this);

        // Cria os botões de direção
        JPanel botoesDirecao = new JPanel(new FlowLayout());
        JButton butDir = new JButton("Direita");
        butDir.addActionListener(this);
        JButton butEsq = new JButton("Esquerda");
        butEsq.addActionListener(this);
        JButton butCima = new JButton("Acima");
        butCima.addActionListener(this);
        JButton butBaixo = new JButton("Abaixo");
        butBaixo.addActionListener(this);
        botoesDirecao.add(butEsq);
        botoesDirecao.add(butDir);
        botoesDirecao.add(butCima);
        botoesDirecao.add(butBaixo);

        // Adiciona os componentes no painel geral
        JPanel painelGeral = new JPanel();
        painelGeral.setLayout(new BoxLayout(painelGeral, BoxLayout.PAGE_AXIS));
        painelGeral.add(tabuleiro);
        painelGeral.add(botoesDirecao);

        // Cria um painel para exibir informações de vidas e "vidas" do martelo
        JPanel itens = new JPanel(new FlowLayout());
        labelVidas = new JLabel("Vidas: " + personagem.getvidas());
        labelMartelo = new JLabel("");
        itens.add(labelVidas);
        itens.add(labelMartelo);
        painelGeral.add(itens);

        this.add(painelGeral);

        tabuleiro.loadLevel(1);

        personagem = tabuleiro.getPrincipal();
        personagem.setAnterior(personagem.getAnterior());

        // Exibe a janela
        this.add(painelGeral);

        this.setSize(1100, 1100);
        this.setTitle("Jogo Demo");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        tabuleiro.atualizaVisualizacao();
        JOptionPane.showMessageDialog(getRootPane(), "Pegue a marreta para quebrar os muros necessarios para conquistar o bau!!");
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // Manipula os eventos de movimentação do personagem
        JButton but = (JButton) arg0.getSource();
        if (but.getText().equals("Direita")) {
            personagem.moveDireita();
        }
        if (but.getText().equals("Esquerda")) {
            personagem.moveEsquerda();
        }
        if (but.getText().equals("Acima")) {
            personagem.moveCima();
        }
        if (but.getText().equals("Abaixo")) {
            personagem.moveBaixo();
        }
        tabuleiro.atualizaVisualizacao();
    }

    // Atualiza o layout exibindo as informações atualizadas de vidas e martelo
    public void atualizarlayout() {
        labelVidas.setText("Vidas: " + personagem.getvidas());
        if (tabuleiro.getPrincipal().temPicaEreta()) {
            labelMartelo.setText("Martelo: " + personagem.getMartas());
        } else {
            labelMartelo.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }

    // Retorna o tabuleiro
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}
