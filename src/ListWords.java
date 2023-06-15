import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListWords extends JFrame{
    private JPanel panelMain;
    private JPanel pn1;
    private JPanel pn2;
    private JPanel pn3;
    private JPanel pn4;
    private JTable tb1;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnEdit;
    private JButton btnExit;
    private JTextField txtSearch;
    private JButton btnSearch;

    public void ListWords() {

        this.setContentPane(this.panelMain);
        this.setTitle("List Data");
        this.setSize(850,650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Data d;
        d = Data.getInit();
        String[][] data = d.getData();

        tb1.setModel(new DefaultTableModel(
                data,
                new String[] {"Id", "Key", "Value"}
        ));
        tb1.setRowHeight(30);

        this.setVisible(true);
    }
}
