import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JFrame{
    private JPanel panelMain;
    private JLabel labelTittle;

    private JButton btnMiniGame;

    private JButton btnListWord;
    private JPanel panelNav;


    Data d = Data.getInit();;
     public Form() {
         btnListWord.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 dispose();
                 ListWords ls = new ListWords();
                 ls.ListWords();
             }
         });
         btnMiniGame.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                dispose();
                Game g = new Game();
                g.Game();
             }
         });
     }

    public void Form() {
        this.setContentPane(this.panelMain);

        btnListWord.setFocusable(false);
        btnMiniGame.setFocusable(false);

        this.setTitle("Dictionary App");
        this.setSize(700,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
