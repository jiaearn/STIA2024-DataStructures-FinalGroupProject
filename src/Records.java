
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Records extends JFrame implements ActionListener {

    static ArrayList<Customer> customerList = new ArrayList<>();
    static ArrayList<Customer> rowI = new ArrayList<>();

    String[] header = new String[]{"Date Bill", "Account No", "Invoice No", "Name", "Address", "Meter No", "Previos Meter", "Current Meter", "Total Usage", "Tunggakan", "Current Charges", "Total CurrentCharge"};
    JTable output;
    DefaultTableModel dtm;
    JScrollPane jsp;
    JButton search, reset, edit, delete,back;
    JLabel picture,label1,label2;
    JTextField text1,text2;
    JPanel panel1,panel2;

    public Records() {
        ImageIcon icon = new ImageIcon(getClass().getResource("Logo.png"));
        setIconImage(icon.getImage());
        outputTable();
        outputPanel1();
        outputPanel2();
        setTitle("Records");
        setLocation(400, 75);
        setSize(750, 550);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Pay Bill System", dialogButton);
                if (dialogResult == 0) {
                    System.exit(0);
                }
            }
        });

        picture = new JLabel();
        picture.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("Logo.png")).getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH)));
        picture.setBounds(20, 10, 150, 80);
        add(picture);

    }

    public void outputPanel1(){

        panel1 = new JPanel();
        panel1.setBounds(20, 105, 700, 60);
        panel1.setLayout(null);
        panel1.setBorder(BorderFactory.createEtchedBorder());
        add(panel1);

        label1 = new JLabel("Account No:");
        label1.setFont(new Font("Dialog", Font.BOLD, 12));
        label1.setBounds(10, 15, 70, 30);
        panel1.add(label1);

        text1 = new JTextField(20);
        text1.setBounds(90,15,150,30);
        panel1.add(text1);

        label2 = new JLabel("Name:");
        label2.setFont(new Font("Dialog", Font.BOLD, 12));
        label2.setBounds(390, 15, 50, 30);
        panel1.add(label2);

        text2 = new JTextField();
        text2.setBounds(450,15,230,30);
        panel1.add(text2);
    }

    public void outputPanel2(){

        panel2 = new JPanel();
        panel2.setBounds(20, 420, 700, 75);
        panel2.setLayout(null);
        panel2.setBorder(BorderFactory.createEtchedBorder());
        add(panel2);

        search = new JButton("Search");
        search.setBounds(50, 20, 100, 30);
        panel2.add(search);
        search.addActionListener(this);

        reset = new JButton("Reset");
        reset.setBounds(175, 20, 100, 30);
        panel2.add(reset);
        reset.addActionListener(this);

        edit = new JButton("Edit");
        edit.setBounds(300, 20, 100, 30);
        panel2.add(edit);
        edit.addActionListener(this);

        delete = new JButton("Delete");
        delete.setBounds(425, 20, 100, 30);
        panel2.add(delete);
        delete.addActionListener(this);

        back = new JButton("Back");
        back.setBounds(550, 20, 100, 30);
        panel2.add(back);
        back.addActionListener(this);

        search.setFont(new Font("Dialog",Font.BOLD,14));
        reset.setFont(new Font("Dialog",Font.BOLD,14));
        edit.setFont(new Font("Dialog",Font.BOLD,14));
        delete.setFont(new Font("Dialog",Font.BOLD,14));
        back.setFont(new Font("Dialog",Font.BOLD,14));
    }

    public void outputTable() {
        dtm = new DefaultTableModel(header, 0);
        output = new JTable();

        JTableHeader header = output.getTableHeader();
        int headerHeight = 30;
        header.setPreferredSize(new Dimension(50, headerHeight));
        ((DefaultTableCellRenderer) output.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        output.setModel(dtm);

        for (int i = 0; i < 12; i++) {
            output.getColumnModel().getColumn(i).setPreferredWidth(150);
        }

        output.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        output.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        output.setDefaultRenderer(Object.class, centerRenderer);
        output.getTableHeader().setReorderingAllowed(false);
        output.getTableHeader().setResizingAllowed(false);
        output.setDefaultEditor(Object.class, null);
        output.setRowHeight(30);

        insertionSort(ElectricityBillingSystem.customerList);
        dtm.setRowCount(0);
        for (Customer cus : ElectricityBillingSystem.customerList) {
            String t9 = String.format("%.2f", cus.t9);
            String cC = String.format("%.2f", cus.cC);
            String tCC = String.format("%.2f", cus.tCC);
            Object[] objs = {cus.t1, cus.t2, cus.t3, cus.t4, cus.t5, cus.t6, cus.t7, cus.t8, cus.tU, t9, cC, tCC};
            dtm.addRow(objs);
        }
        jsp = new JScrollPane();
        jsp.setViewportView(output);
        jsp.setBounds(20, 180, 700, 230);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(jsp);

    }

    public void insertionSort(ArrayList<Customer> customerList) {

        for (int x = 1; x < customerList.size(); x++) {

            Customer temp = customerList.get(x);
            int y = x - 1;

            while (y >= 0 && customerList.get(y).getT2() > temp.getT2()) {

                customerList.set(x, customerList.get(y));
                customerList.set(y, temp);
                y--;
            }
        }
    }
    public int binarySearch(Long aacNo, ArrayList<Customer> customerList){

        int i=0;
        int j=customerList.size()-1;

        while (i<=j){

            int mid=(i+j)/2;
            if (customerList.get(mid).getT2()==aacNo){
                return mid;
            }else if (customerList.get(mid).getT2()>aacNo){
                j=mid-1;
            }else {
                i=mid+1;
            }
        }
        return -1;
    }

    public int binarySearch2(String name, ArrayList<Customer> customerList){

        int i=0;
        int j=customerList.size()-1;

        while (i<=j){
            int mid= (i+j)/2;
            if (customerList.get(mid).getT4().compareTo(name)<0){
                i = mid+1;
            }
            else if (customerList.get(mid).getT4().compareTo(name)>0){
                j= mid-1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==search){
            if(text1.getText().isEmpty() && text2.getText().isEmpty())
                JOptionPane.showMessageDialog(this,"The field is empty", "Electricity Billing System", JOptionPane.ERROR_MESSAGE);

            else if(!text1.getText().isEmpty() && !text2.getText().isEmpty())
                JOptionPane.showMessageDialog(this,"One time only can search one field", "Electricity Billing System", JOptionPane.ERROR_MESSAGE);

            else if(text2.getText().equals("")) {
                try {
                    Long t1 = Long.parseLong(text1.getText());
                    int index = binarySearch(t1, ElectricityBillingSystem.customerList);
                    if (index >= 0) {
                        dtm.setRowCount(0);
                        Object[] objs = {ElectricityBillingSystem.customerList.get(index).t1, ElectricityBillingSystem.customerList.get(index).t2, ElectricityBillingSystem.customerList.get(index).t3, ElectricityBillingSystem.customerList.get(index).t4, ElectricityBillingSystem.customerList.get(index).t5, ElectricityBillingSystem.customerList.get(index).t6, ElectricityBillingSystem.customerList.get(index).t7, ElectricityBillingSystem.customerList.get(index).t8, ElectricityBillingSystem.customerList.get(index).t9, ElectricityBillingSystem.customerList.get(index).cC, ElectricityBillingSystem.customerList.get(index).tCC};
                        dtm.addRow(objs);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Not found!", "Electricity Billing System", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(this, "Please enter an integer.", "Electricity Billing System", JOptionPane.ERROR_MESSAGE);
                }
            }

            else if(text1.getText().equals("")) {
                String t2 = text2.getText().toUpperCase();
                int index2 = binarySearch2(t2, ElectricityBillingSystem.customerList);
                if (index2 >= 0) {
                    dtm.setRowCount(0);
                    Object[] objs = {ElectricityBillingSystem.customerList.get(index2).t1, ElectricityBillingSystem.customerList.get(index2).t2, ElectricityBillingSystem.customerList.get(index2).t3, ElectricityBillingSystem.customerList.get(index2).t4, ElectricityBillingSystem.customerList.get(index2).t5, ElectricityBillingSystem.customerList.get(index2).t6, ElectricityBillingSystem.customerList.get(index2).t7, ElectricityBillingSystem.customerList.get(index2).t8, ElectricityBillingSystem.customerList.get(index2).t9, ElectricityBillingSystem.customerList.get(index2).cC, ElectricityBillingSystem.customerList.get(index2).tCC};
                    dtm.addRow(objs);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Not found!", "Electricity Billing System", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (e.getSource() == reset) {
            if (ElectricityBillingSystem.customerList.isEmpty()) {
                if (e.getSource() == reset) {
                    JOptionPane.showMessageDialog(this, "List is empty", "Electricity Billing System", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                text1.setText(null);
                text2.setText(null);
                if (dtm.getRowCount() == 1) {
                    dtm.setRowCount(0);
                    for (Customer cus : ElectricityBillingSystem.customerList) {
                        String t9 = String.format("%.2f", cus.t9);
                        String cC = String.format("%.2f", cus.cC);
                        String tCC = String.format("%.2f", cus.tCC);
                        Object[] objs = {cus.t1, cus.t2, cus.t3, cus.t4, cus.t5, cus.t6, cus.t7, cus.t8, cus.tU, t9, cC, tCC};
                        dtm.addRow(objs);
                    }
                }
            }
        }

        if (e.getSource() == back) {
            new MainMenu();
            setVisible(false);
        }

        DefaultTableModel model = (DefaultTableModel) output.getModel();

        if (e.getSource() == delete) {
            if (ElectricityBillingSystem.customerList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "List is empty", "Electricity Billing System", JOptionPane.ERROR_MESSAGE);
            } else {
                String input = JOptionPane.showInputDialog(this, "Search for Name / Acc No. :", "Electricity Billing System", JOptionPane.QUESTION_MESSAGE);
                if (input != null) {
                    if (input.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please enter customer's Name / Acc No.", "Electricity Billing System", JOptionPane.WARNING_MESSAGE);
                    } else {
                        for (int i = 0; i < ElectricityBillingSystem.customerList.size(); i++) {
                            if (ElectricityBillingSystem.customerList.get(i).t4.equalsIgnoreCase(input)) {
                                int dialogButton = JOptionPane.YES_NO_OPTION;
                                int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the data of  " + input.toUpperCase() + "?", "Electricity Billing System", dialogButton);
                                if (dialogResult == 0) {
                                    ElectricityBillingSystem.customerList.remove(i);
                                    model.removeRow(i);
                                    ElectricityBillingSystem ebs = new ElectricityBillingSystem();
                                    ebs.fileWriter();
                                }
                                return;
                            }
                        }
                        for (int i = 0; i < ElectricityBillingSystem.customerList.size(); i++) {
                            if (input.matches("[0-9]+")) {
                                if (ElectricityBillingSystem.customerList.get(i).t2 == Long.parseLong(input)) {
                                    int dialogButton = JOptionPane.YES_NO_OPTION;
                                    int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the data of  " + input + "?", "Electricity Billing System", dialogButton);
                                    if (dialogResult == 0) {
                                        ElectricityBillingSystem.customerList.remove(i);
                                        model.removeRow(i);
                                        ElectricityBillingSystem ebs = new ElectricityBillingSystem();
                                        ebs.fileWriter();
                                    }
                                    return;
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(this, "Not Found", "Electricity Billing System", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        if (e.getSource() == edit) {
            if (ElectricityBillingSystem.customerList.isEmpty()) {
                if (e.getSource() == edit) {
                    JOptionPane.showMessageDialog(this, "List is empty", "Electricity Billing System", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (e.getSource() == edit) {
                    String input = JOptionPane.showInputDialog(this, "Search for Name / Acc No. :", "Electricity Billing System", JOptionPane.QUESTION_MESSAGE);
                    if (input!=null) {
                        if (input.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Please enter customer's Name / Acc No.", "Electricity Billing System", JOptionPane.WARNING_MESSAGE);
                        }
                        else {
                            for (int i = 0; i < ElectricityBillingSystem.customerList.size(); i++) {
                                if (ElectricityBillingSystem.customerList.get(i).t4.equalsIgnoreCase(input)) {
                                    rowI.add(new Customer(i));
                                    JOptionPane.showMessageDialog(this, " Found", "Electricity Billing System", JOptionPane.INFORMATION_MESSAGE);
                                    String t1 = ElectricityBillingSystem.customerList.get(i).t1;
                                    t1 = t1.toUpperCase();
                                    long t2 = ElectricityBillingSystem.customerList.get(i).t2;
                                    long t3 = ElectricityBillingSystem.customerList.get(i).t3;
                                    String t4 = ElectricityBillingSystem.customerList.get(i).t4;
                                    t4 = t4.toUpperCase();
                                    String t5 = ElectricityBillingSystem.customerList.get(i).t5;
                                    t5 = t5.toUpperCase();
                                    long t6 = ElectricityBillingSystem.customerList.get(i).t6;
                                    int t7 = ElectricityBillingSystem.customerList.get(i).t7;
                                    int t8 = ElectricityBillingSystem.customerList.get(i).t8;
                                    double t9 = ElectricityBillingSystem.customerList.get(i).t9;
                                    double cC = 0;
                                    double tCC = 0;
                                    int tU = 0;
                                    customerList.add(new Customer(t1, t2, t3, t4, t5, t6, t7, t8, tU, t9, cC, tCC));
                                    new EditData();
                                    this.dispose();
                                    return;
                                }
                            }
                            for (int i = 0; i < ElectricityBillingSystem.customerList.size(); i++) {
                                if (input.matches("[0-9]+")) {
                                    if (ElectricityBillingSystem.customerList.get(i).t2 == Long.parseLong(input)) {
                                        rowI.add(new Customer(i));
                                        JOptionPane.showMessageDialog(this, "Found", "Electricity Billing System", JOptionPane.INFORMATION_MESSAGE);
                                        String t1 = ElectricityBillingSystem.customerList.get(i).t1;
                                        t1 = t1.toUpperCase();
                                        long t2 = ElectricityBillingSystem.customerList.get(i).t2;
                                        long t3 = ElectricityBillingSystem.customerList.get(i).t3;
                                        String t4 = ElectricityBillingSystem.customerList.get(i).t4;
                                        t4 = t4.toUpperCase();
                                        String t5 = ElectricityBillingSystem.customerList.get(i).t5;
                                        t5 = t5.toUpperCase();
                                        long t6 = ElectricityBillingSystem.customerList.get(i).t6;
                                        int t7 = ElectricityBillingSystem.customerList.get(i).t7;
                                        int t8 = ElectricityBillingSystem.customerList.get(i).t8;
                                        double t9 = ElectricityBillingSystem.customerList.get(i).t9;
                                        double cC = ElectricityBillingSystem.customerList.get(i).cC;
                                        double tCC = ElectricityBillingSystem.customerList.get(i).tCC;
                                        int tU = ElectricityBillingSystem.customerList.get(i).tU;
                                        customerList.add(new Customer(t1, t2, t3, t4, t5, t6, t7, t8, tU, t9, cC, tCC));
                                        new EditData();
                                        this.dispose();
                                        return;
                                    }
                                } else
                                    return;
                            }
                            JOptionPane.showMessageDialog(this, "Not Found", "Electricity Billing System", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }
}