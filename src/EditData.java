
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditData extends JFrame implements ActionListener {

    JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, label10;
    JTextField text1, text2, text3, text4, text5, text6, text7, text8, text9;
    JButton button1, button2, button3;
    DefaultTableModel dtm;
    JTable table;
    JScrollPane jsp1;
    JPanel input, button;

    public EditData() {
        ImageIcon icon = new ImageIcon(getClass().getResource("Logo.png"));
        setIconImage(icon.getImage());

        table();
        setLayout(null);
        setTitle("Edit Data");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Electricity Billing System", dialogButton);
                if (dialogResult == 0) {
                    System.exit(0);
                }
            }
        });

        input = new JPanel();
        input.setBounds(20, 306, 700, 250);
        input.setLayout(null);
        input.setBorder(BorderFactory.createEtchedBorder());
        add(input);

        label10 = new JLabel();
        label10.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("Logo.png")).getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH)));
        label10.setBounds(20, 10, 150, 80);
        add(label10);

        label1 = new JLabel("Date Bill:");
        label1.setFont(new Font("Dialog",Font.BOLD,12));
        label2 = new JLabel("Account No:");
        label2.setFont(new Font("Dialog",Font.BOLD,12));
        label3 = new JLabel("Invoice No:");
        label3.setFont(new Font("Dialog",Font.BOLD,12));
        label4 = new JLabel("Name:");
        label4.setFont(new Font("Dialog",Font.BOLD,12));
        label5 = new JLabel("Address:");
        label5.setFont(new Font("Dialog",Font.BOLD,12));
        label6 = new JLabel("Meter No:");
        label6.setFont(new Font("Dialog",Font.BOLD,12));
        label7 = new JLabel("Previos Meter:");
        label7.setFont(new Font("Dialog",Font.BOLD,12));
        label8 = new JLabel("Current Meter:");
        label8.setFont(new Font("Dialog",Font.BOLD,12));
        label9 = new JLabel("Tunggakan:");
        label9.setFont(new Font("Dialog",Font.BOLD,12));

        text1 = new JTextField(10);
        text2 = new JTextField(10);
        text3 = new JTextField(10);
        text4 = new JTextField(50);
        text5 = new JTextField(60);
        text6 = new JTextField(10);
        text7 = new JTextField(10);
        text8 = new JTextField(10);
        text9 = new JTextField(10);

        button1 = new JButton("Edit");
        button2 = new JButton("Reset");
        button3 = new JButton("Back");

        for (Customer cus : Records.customerList) {
            text1.setText(cus.t1);
            text2.setText(String.valueOf(cus.t2));
            text3.setText(String.valueOf(cus.t3));
            text4.setText(cus.t4);
            text5.setText(cus.t5);
            text6.setText(String.valueOf(cus.t6));
            text7.setText(String.valueOf(cus.t7));
            text8.setText(String.valueOf(cus.t8));
            text9.setText(String.format("%.2f",cus.t9));
        }

        button1.setFont(new Font("Dialog",Font.BOLD,14));
        button2.setFont(new Font("Dialog",Font.BOLD,14));
        button3.setFont(new Font("Dialog",Font.BOLD,14));

        label1.setBounds(20, 5, 150, 50);
        label2.setBounds(20, 45, 150, 50);
        label3.setBounds(20, 85, 150, 50);
        label4.setBounds(20, 125, 150, 50);
        label5.setBounds(20, 165, 150, 50);
        label6.setBounds(370, 5, 150, 50);
        label7.setBounds(370, 45, 150, 50);
        label8.setBounds(370, 85, 150, 50);
        label9.setBounds(370, 125, 150, 50);

        text1.setBounds(170, 20, 150, 25);
        text2.setBounds(170, 60, 150, 25);
        text3.setBounds(170, 100, 150, 25);
        text4.setBounds(170, 140, 150, 25);
        text5.setBounds(170, 180, 150, 55);
        text6.setBounds(520, 20, 150, 25);
        text7.setBounds(520, 60, 150, 25);
        text8.setBounds(520, 100, 150, 25);
        text9.setBounds(520, 140, 150, 25);

        button = new JPanel();
        button.setBounds(20, 571, 700, 75);
        button.setLayout(null);
        button.setBorder(BorderFactory.createEtchedBorder());
        add(button);

        button1.setBounds(100, 20, 100, 30);
        button2.setBounds(300, 20, 100, 30);
        button3.setBounds(500, 20, 100, 30);

        input.add(label1);
        input.add(label2);
        input.add(label3);
        input.add(label4);
        input.add(label5);
        input.add(label6);
        input.add(label7);
        input.add(label8);
        input.add(label9);

        input.add(text1);
        input.add(text2);
        input.add(text3);
        input.add(text4);
        input.add(text5);
        input.add(text6);
        input.add(text7);
        input.add(text8);
        input.add(text9);

        button.add(button1);
        button.add(button2);
        button.add(button3);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        setResizable(false);
        setVisible(true);
        setLocation(400, 50);
        setSize(750, 700);

    }

    public void table() {
        String[] header = new String[]{"Penggunaan elektrik(kWj)", "Kadar tariff (RM)", "Unit penggunaan elektrik (kWj)"};
        dtm = new DefaultTableModel(header, 0);
        dtm.addRow(new Object[]{"1-200", "0.218", "200"});
        dtm.addRow(new Object[]{"201-300", "0.334", "100"});
        dtm.addRow(new Object[]{"301-600", "0.516", "300"});
        dtm.addRow(new Object[]{"601-900", "0.546", "300"});
        dtm.addRow(new Object[]{"901 dan ke atas", "0.571", "Apa-apa nilai penggunaan dalam julat ini"});
        table = new JTable();

        JTableHeader header1 = table.getTableHeader();
        int headerHeight = 30;
        header1.setPreferredSize(new Dimension(50, headerHeight));

        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        table.setModel(dtm);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setEnabled(false);
        table.setRowHeight(30);

        jsp1 = new JScrollPane();
        jsp1.setViewportView(table);
        jsp1.setBounds(20, 105, 700, 186);
        jsp1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(jsp1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == button1) {
            try {
                if (text1.getText().isEmpty() || text2.getText().isEmpty() || text3.getText().isEmpty() || text4.getText().isEmpty() || text5.getText().isEmpty() || text6.getText().isEmpty() || text7.getText().isEmpty() || text8.getText().isEmpty() || text9.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Some fields have not been filled.", "Electricity Billing System", JOptionPane.ERROR_MESSAGE);
                }else{
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to edit the data?", "Electricity Billing System", dialogButton);
                    if (dialogResult == 0) {

                            String t1 = text1.getText();
                            t1 = t1.toUpperCase();
                            long t2 = Long.parseLong(text2.getText());
                            long t3 = Long.parseLong(text3.getText());
                            String t4 = text4.getText();
                            t4 = t4.toUpperCase();
                            String t5 = text5.getText();
                            t5 = t5.toUpperCase();
                            long t6 = Long.parseLong(text6.getText());
                            int t7 = Integer.parseInt(text7.getText());
                            int t8 = Integer.parseInt(text8.getText());
                            double t9 = Double.parseDouble(text9.getText());
                            double cC = 0, tCC = 0;
                            int tU = 0;

                            if (t7 > 0) {
                                if (t8 > t7) {
                                    ElectricityBillingSystem electricityBillingSystem = new ElectricityBillingSystem(t1, t2, t3, t4, t5, t6, t7, t8, tU, t9, cC, tCC);
                                    for (Customer cus : Records.rowI) {
                                        int row = cus.row;
                                        ElectricityBillingSystem.customerList.remove(row);
                                    }
                                    JOptionPane.showMessageDialog(this, "Data has been edit.\n" + electricityBillingSystem.toString(), "Electricity Billing System", JOptionPane.INFORMATION_MESSAGE);
                                } else
                                    JOptionPane.showMessageDialog(this, "Current Meter must bigger than Previous Meter.", "Electricity Billing System", JOptionPane.ERROR_MESSAGE);
                            } else
                                JOptionPane.showMessageDialog(this, "Please enter positive number", "Electricity Billing System", JOptionPane.ERROR_MESSAGE);
                        }

                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter an integer.", "Electricity Billing System", JOptionPane.ERROR_MESSAGE);
            }
            ElectricityBillingSystem ebs = new ElectricityBillingSystem();
            ebs.fileWriter();

        }
        if (e.getSource() == button2) {
            clearField();
        }
        if (e.getSource() == button3) {
            new Records();
            this.dispose();
        }
    }

    public void clearField() {
        text1.setText(null);
        text2.setText(null);
        text3.setText(null);
        text4.setText(null);
        text5.setText(null);
        text6.setText(null);
        text7.setText(null);
        text8.setText(null);
        text9.setText(null);
    }

}
