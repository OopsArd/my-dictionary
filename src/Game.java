import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame{
    private JPanel panelMain;
    private JLabel labelContent;
    private JButton btnRandom;
    private JButton btnExit;
    private JButton btnQuiz;

    Data d = Data.getInit();
    public Game() {
        btnRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] r = d.randomWord();
                String rs =r[0] + "          " + r[1];
                labelContent.setText(rs);
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Form f = new Form();
                f.Form();
            }
        });
        btnQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Ques q = new Ques();
                q.Ques();
            }
        });
    }

    public void Game() {
        this.setContentPane(panelMain);
        this.setTitle("Game show");
        this.setSize(700,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
