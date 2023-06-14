import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JFrame implements ActionListener {
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



    public void displayForm() {

        Form ui = new Form();
        ui.setContentPane(ui.panelMain);
        btnListWord.setFocusable(false);
        btnFind.setFocusable(false);
        btnAdd.setFocusable(false);
        btnDelete.setFocusable(false);
        btnEdit.setFocusable(false);
        btnRandom.setFocusable(false);
        btnReset.setFocusable(false);
        btnMiniGame.setFocusable(false);
        ui.setTitle("Dictionary App");
        ui.setSize(500,500);
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        // Xử lý sự kiện ở đây
        if(e.getSource() == btnListWord){
            
        } else if (e.getSource() == btnFind) {
            
        } else if (e.getSource() == btnAdd) {
            
        } else if (e.getSource() == btnDelete) {
            
        } else if (e.getSource() == btnEdit) {
            
        } else if (e.getSource() == btnRandom) {
            
        } else if (e.getSource() == btnMiniGame) {
            
        } else if (e.getSource() == btnReset) {
            
        }

    }


}
