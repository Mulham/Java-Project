
//Autohr: Mulham Alibrahim 170503111
package sample.model;

public class Job {

    private int idJobOrder;
    private double No;
    private int Firma;



    public Job(double no) {
        No = no;
    }

    public int getIdJobOrder() {
        return idJobOrder;
    }

    public void setIdJobOrder(int idJobOrder) {
        this.idJobOrder = idJobOrder;
    }

    public double getNo() {
        return No;
    }

    public void setNo(double no) {
        No = no;
    }

    public int getFirma() {
        return Firma;
    }

    public void setFirma(int firma) {
        Firma = firma;
    }

    public Job(int idJobOrder, double no, int firma) {
        this.idJobOrder = idJobOrder;
        No = no;
        Firma = firma;
    }
}
