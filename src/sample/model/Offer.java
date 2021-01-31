//Autohr: Mulham Alibrahim 170503111

package sample.model;

public class Offer {
    private int idOffer;
    private double No;
    private int Firma;


    public Offer(double no) {
        No = no;
    }

    public int getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(int idOffer) {
        this.idOffer = idOffer;
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

    public Offer(int idOffer, double no, int firma) {
        this.idOffer = idOffer;
        No = no;
        Firma = firma;
    }
}
