
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMenu extends JFrame implements ActionListener {

    JButton addData, records, exit;
    JLabel picture;

    public MainMenu() {
        ImageIcon icon = new ImageIcon(getClass().getResource("Logo.png"));
        setIconImage(icon.getImage());
        setLayout(null);
        setTitle("Main Menu");
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

        picture = new JLabel();
        picture.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("Logo.png")).getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH)));
        picture.setBounds(20, 10, 180, 100);
        add(picture);

        addData = new JButton("Add Data");
        records = new JButton("Records");
        exit = new JButton("Exit");

        addData.setFont(new Font("Dialog",Font.BOLD,14));
        records.setFont(new Font("Dialog",Font.BOLD,14));
        exit.setFont(new Font("Dialog",Font.BOLD,14));

        addData.setBounds(40,140,120,40);
        records.setBounds(185,140,120,40);
        exit.setBounds(330,140,120,40);

        add(addData);
        add(records);
        add(exit);

        addData.addActionListener(this);
        records.addActionListener(this);
        exit.addActionListener(this);

        fileReader();
        setResizable(false);
        setVisible(true);
        setLocation(400, 150);
        setSize(500, 250);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== addData){
            new AddData();
            this.dispose();

        }
        if (e.getSource() == records) {
            new Records();
            this.dispose();

        }
        if (e.getSource() == exit) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Electricity Billing System", dialogButton);
            if (dialogResult == 0) {
                System.exit(0);
            }
        }
    }

    public void fileReader() {
        try {
            boolean create;
            File newFile = new File("Electricity Billing System.txt");
            create = newFile.createNewFile();

            if (create) {
                System.out.println("\nCreate Electricity Billing System.txt");
            }

            ElectricityBillingSystem.customerList.clear();
            FileReader cus = new FileReader("Electricity Billing System.txt");

            StringBuffer sb = new StringBuffer();
            while (cus.ready()) {
                char c = (char) cus.read();
                if (c == '\n') {
                    String[] dataArr = sb.toString().split(",");
                    ElectricityBillingSystem.customerList.add(new Customer(dataArr[0], Long.parseLong(dataArr[1]), Long.parseLong(dataArr[2]), dataArr[3], dataArr[4], Long.parseLong(dataArr[5]), Integer.parseInt(dataArr[6]), Integer.parseInt(dataArr[7]), Integer.parseInt(dataArr[8]), Double.parseDouble(dataArr[9]), Double.parseDouble(dataArr[10]), Double.parseDouble(dataArr[11])));
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(() -> new MainMenu().setVisible(true));
    }
}
