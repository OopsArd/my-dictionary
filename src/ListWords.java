import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListWords extends JFrame{
    private JPanel panelMain;
    private JPanel pn1;
    private JPanel pn2;
    private JPanel pn3;
    private JPanel pn4;
    private JTable tb1;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnHistory;
    private JButton btnExit;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnReset;

    Data d = Data.getInit();

    public ListWords() {
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Form f = new Form();
                f.Form();
            }
        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = txtSearch.getText();
                System.out.println("key: " + key);
                String[][] temp1 = d.getValues(key);
                String[][] temp2 = d.getKeys(key);

                String[][] result;
                if(temp1 == null){
                    result = temp2;
                }else{
                    result = temp1;
                }

                String checkKey = key.trim();
                if(!checkKey.isEmpty()){
                    try {
                        for(int i = 0; i < result.length; i++){
                            String k = result[i][1];
                            String v = result[i][2];
                            d.saveHistory(k,v);
                            System.out.println("save ok roi");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                DefaultTableModel model = (DefaultTableModel) tb1.getModel();
                model.setRowCount(0);
                //model.addRow(result);
                for (int i = 0; i < result.length; i++)
                {
                    String value[] = result[i];
                    model.addRow(value);
                }



            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddWords a = new AddWords();
                a.AddWords();
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = tb1.getSelectedRow();
                System.out.println("sl: " + select);
                if(select >= 0){
                    //String stt = tb1.getValueAt(select, 0).toString();
                    String key = tb1.getValueAt(select, 1).toString();
                    String value = tb1.getValueAt(select, 2).toString();

                    String[] options = {"No", "Yes"};
                    int choice = JOptionPane.showOptionDialog(null, "Chắc muốn xóa không", null, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (choice == 1) {
                        d.delete(key,value);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "chưa có cái gì cần xóa. Hãy chọn lại");
                }
                //dispose();
                //ListWords l = new ListWords();
                ListWords();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"No", "Yes"};
                int choice = JOptionPane.showOptionDialog(null, "Chắc muốn thiết lập lại không", null, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (choice == 1) {
                    d.readFileAgain();
                }
                ListWords();
            }
        });
        btnHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tb1.getModel();
                model.setRowCount(0);

                String[][] listSearch = d.readHistory();
                for(int i =0; i < listSearch.length; i++){
                    String[] row = listSearch[i];
                    model.addRow(row);
                }
            }
        });
    }

    public void ListWords() {

        this.setContentPane(this.panelMain);
        this.setTitle("List Data");
        this.setSize(850,650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        String[][] data = d.getData();

        tb1.setModel(new DefaultTableModel(
                data,
                new String[] {"Stt", "Key", "Value"}
        ));
        tb1.setRowHeight(30);

        //tb1.getColumnModel().getColumn(1).setCellEditor(new NameTableCellEditor());
        tb1.getColumnModel().getColumn(2).setCellEditor(new NameTableCellEditor(tb1));


        this.setVisible(true);
    }

    static class NameTableCellEditor extends AbstractCellEditor implements TableCellEditor {
        private JTextField textField;
        private Object oldValue;
        private JTable table;

        Data d = Data.getInit();

        public NameTableCellEditor(JTable table) {
            this.table = table;
            textField = new JTextField();
            textField.addActionListener(e -> stopCellEditing());
        }

        @Override
        public Object getCellEditorValue() {
            return textField.getText();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            oldValue = value;
            textField.setText(value != null ? value.toString() : "");
            return textField;
        }

        @Override
        public boolean stopCellEditing() {
            String newValue = textField.getText();

            int selectedRow = table.getSelectedRow();
            Object numberValue = table.getValueAt(selectedRow, 1);

            String key = numberValue.toString();
            String value = oldValue.toString();

            d.edit(key,value,newValue);

            JOptionPane.showMessageDialog(null, "Đã thay đổi value của " + numberValue + " từ " + oldValue + " sang " + newValue);
            return super.stopCellEditing();
        }
    }

}
