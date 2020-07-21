
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ElectricityBillingSystem {
    String t1, t4, t5;
    double t9, cC, tCC;
    int tU, t7, t8;
    long t2, t3, t6;
    static ArrayList<Customer> customerList = new ArrayList<>();

    public ElectricityBillingSystem() {
    }

    public ElectricityBillingSystem(String t1, long t2, long t3, String t4, String t5, long t6, int t7, int t8, int tU, double t9, double cC, double tCC) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.t6 = t6;
        this.t7 = t7;
        this.t8 = t8;
        this.tU = tU;
        this.t9 = t9;
        this.cC = cC;
        this.tCC = tCC;
    }

    public String toString() {
        String data;
        CalculateData c = new CalculateData(t1, t2, t3, t4, t5, t6, t7, t8, tU, t9, cC, tCC);
        c.calculateBill();
        tU = c.getTotalUsage();
        cC = c.getCurrentCharge();
        tCC = c.getTotalcurrentCharge();
        customerList.add(new Customer(t1, t2, t3, t4, t5, t6, t7, t8, tU, t9, cC, tCC));
        data = "\nDate Bill  : " + t1
                + "\n" + "Account No  :" + t2
                + "\n" + "Invoice No  :" + t3
                + "\n" + "Name  : " + t4
                + "\n" + "Address  :" + t5
                + "\n" + "Meter No  :" + t6
                + "\n" + "Previous Meter  :" + t7
                + "\n" + "Current Meter  :" + t8
                + "\n" + "Total Usage  :" + c.getTotalUsage()
                + "\n" + "Tunggakan  :" + String.format("%.2f", t9)
                + "\n" + "Current Charge  :" + String.format("%.2f", cC)
                + "\n" + "Total CurrentCharge  : RM" + String.format("%.2f", tCC);
        return data;
    }

    public void fileWriter() {
        try (FileWriter ebs = new FileWriter("Electricity Billing System.txt")) {
            StringBuilder sb = new StringBuilder();
            for (Customer customer : ElectricityBillingSystem.customerList) {
                String t9 = String.format("%.2f", customer.t9);
                String cC = String.format("%.2f", customer.cC);
                String tCC = String.format("%.2f", customer.tCC);
                sb
                        .append(customer.t1).append(",")
                        .append(customer.t2).append(",")
                        .append(customer.t3).append(",")
                        .append(customer.t4).append(",")
                        .append(customer.t5).append(",")
                        .append(customer.t6).append(",")
                        .append(customer.t7).append(",")
                        .append(customer.t8).append(",")
                        .append(customer.tU).append(",")
                        .append(t9).append(",")
                        .append(cC).append(",")
                        .append(tCC)
                        .append("\r\n");
            }
            ebs.write(sb.toString());
            ebs.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}