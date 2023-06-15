import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JFrame{
    private JPanel panelMain;
    private JLabel labelTittle;
    private JButton btnRandom;
    private JButton btnDelete;
    private JButton btnMiniGame;
    private JButton btnReset;
    private JButton btnEdit;
    private JButton btnListWord;
    private JButton btnFind;
    private JButton btnAdd;

    Data data;
     public Form() {
         btnListWord.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 ListWords ls = new ListWords();
                 ls.ListWords();
             }
         });
     }

    public void Form() {


        data = Data.getInit();

        this.setContentPane(this.panelMain);

        btnListWord.setFocusable(false);


        btnFind.setFocusable(false);

        btnAdd.setFocusable(false);

        btnDelete.setFocusable(false);

        btnEdit.setFocusable(false);

        btnRandom.setFocusable(false);

        btnReset.setFocusable(false);

        btnMiniGame.setFocusable(false);

        this.setTitle("Dictionary App");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
