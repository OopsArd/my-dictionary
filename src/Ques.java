import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ques extends JFrame{
    private JPanel panelMain;
    private JLabel labelQues;
    private JButton btnA;
    private JButton btnC;
    private JButton btnB;
    private JButton btnD;
    private JButton btnTypeOne;
    private JButton btnTypeTwo;
    private JButton btnExit;

    int type = 1;
    String[] question;

    public Ques() {
        btnTypeOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = 1;
                Ques();
            }
        });
        btnTypeTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = 2;
                Ques();
            }
        });
        btnA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer(1);
            }
        });

        btnB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer(2);
            }
        });

        btnC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer(3);
            }
        });

        btnD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer(4);
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
    }

    public void Ques() {
        this.setContentPane(this.panelMain);
        this.setTitle("Mini Game");
        this.setSize(700,450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Data d = Data.getInit();
        question = d.quiz(type);
        if(type == 1){
            String q = "Tìm nghĩa của Slang: " + question[0];
            labelQues.setText(q);
        }else{
            String q = "Tìm Slang của: " + question[0];
            labelQues.setText(q);
        }

        btnA.setText("a. " + question[1]);
        btnB.setText("b. " + question[2]);
        btnC.setText("c. " + question[3]);
        btnD.setText("d. " + question[4]);



        this.setVisible(true);
    }

    public void answer(int ans) {
        if (question[ans].equals(question[5])) {
            JOptionPane.showMessageDialog(this, "Chính xác");
            Ques();

        } else {
            JOptionPane.showMessageDialog(this, "Chưa chính xác", null, JOptionPane.ERROR_MESSAGE);
        }

    }
}
