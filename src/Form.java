import javax.swing.*;

public class Form extends JFrame{
    private JPanel panelMain;
    private JLabel labelTittle;
    private JLabel jLabelKey;
    private JTextField jTxtKeyInput;
    private JList jListKey;
    private JLabel jLabelValue;
    private JTextArea jAreaValue;
    private JButton jBtnSearch;

    public void displayForm() {
        Form ui = new Form();
        ui.setContentPane(ui.panelMain);
        ui.setTitle("Dictionary App");
        ui.setSize(1000,1000);
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
