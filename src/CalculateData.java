
public class CalculateData extends ElectricityBillingSystem {

    public CalculateData(String t1, long t2, long t3, String t4, String t5, long t6, int t7, int t8, int tU, double t9, double cC, double tCC) {
        super(t1, t2, t3, t4, t5, t6, t7, t8, tU, t9, cC, tCC);
    }

    public void calculateBill() {

        tU = t8 - t7;

        if (tU >= 1 && tU <= 200) {
            cC = tU * 0.218;
        } else if (tU >= 201 && tU <= 300) {
            cC = ((tU - 200) * 0.334) + 43.6;
        } else if (tU >= 301 && tU <= 600) {
            cC = ((tU - 300) * 0.516) + 77;
        } else if (tU >= 601 && tU <= 900) {
            cC = ((tU - 600) * 0.546) + 231.8;
        } else if (tU >= 901) {
            cC = ((tU - 900) * 0.571) + 395.6;
        }

        tCC = t9 + cC;

    }

    public double getTotalcurrentCharge() {
        setTotalcurrentCharge(tCC);
        return tCC;
    }

    public void setTotalcurrentCharge(double tCC) {
        this.tCC = tCC;
    }

    public int getTotalUsage() {
        setTotalUsage(tU);
        return tU;
    }

    public void setTotalUsage(int tU) {
        this.tU = tU;
    }

    public double getCurrentCharge() {
        setCurrentCharge(cC);
        return cC;
    }

    public void setCurrentCharge(double currentCharge) {
        this.cC = currentCharge;
    }
}
