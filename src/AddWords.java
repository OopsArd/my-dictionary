import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWords extends JFrame{
    private JTextField txtKey;
    private JLabel labelKey;
    private JLabel labelValue;
    private JTextField txtValue;
    private JButton btnExit;
    private JButton btnAdd;
    private JPanel pannelMain;

    Data d = Data.getInit();

    public AddWords() {
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ListWords l = new ListWords();
                l.ListWords();
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = txtKey.getText();
                String value = txtValue.getText();
                if(key.trim().isEmpty() || value.trim().isEmpty()){
                    JOptionPane.showMessageDialog(null,"key và value không được để trống");
                    return;
                }

                if(d.check(key) == true){
                    String[] options = {"Overwrite", "Duplicate"};
                    int choice = JOptionPane.showOptionDialog(null, "key đã tồn tại", null, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                    if (choice == 0) {
                        // Người dùng chọn "Overwrite"
                        d.addOverwrite(key,value);
                        JOptionPane.showMessageDialog(null,"Thêm thành công");
                    } else if (choice == 1) {
                        // Người dùng chọn "Duplicate"
                        d.addDuplicate(key,value);
                        JOptionPane.showMessageDialog(null,"Thêm thành công");

                    }
                }else{
                    d.add(key,value);
                    JOptionPane.showMessageDialog(null,"Thêm thành công");
                }
                txtKey.setText("");
                txtValue.setText("");
            }
        });
    }

    public void AddWords() {
        this.setContentPane(this.pannelMain);
        this.setTitle("List Data");
        this.setSize(500,150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        this.setVisible(true);
    }

}
