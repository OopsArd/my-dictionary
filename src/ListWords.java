import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListWords extends JFrame{
    private JPanel panelMain;
    private JScrollPane pnSr;
    private JTable tableList;

    public void ListWords() {

        this.setContentPane(this.panelMain);
        this.setTitle("List Data");
        this.setSize(1000,1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);







        this.setVisible(true);
    }

    void createTable(){
        Data d;
        d = Data.getInit();

        String data[][] = d.getData();
        String header[] = { "Stt", "Key", "Value" };

        String[][] dt = {
                {"1", "John", "Doe"},
                {"2", "Jane", "Smith"},
        };

        String[] columnNames = {"ID", "First Name", "Last Name"};

        // Tạo JTable với dữ liệu và tiêu đề cột
         this.tableList = new JTable(data, columnNames);


        //tableList.setModel(new DefaultTableModel(null,header));
    }
}
