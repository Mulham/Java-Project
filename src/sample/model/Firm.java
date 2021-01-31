//Autohr: Mulham Alibrahim 170503111

package sample.model;

public class Firm {
    private Integer idFirmen;
    private String Name;
    private String Stadt;


    public Firm(){

    }

    public Firm(String customer) {
        Name = customer;
    }

    public Integer getId() {
        return idFirmen;
    }

    public void setId(Integer id) {
        this.idFirmen = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStadt() {
        return Stadt;
    }

    public void setStadt(String stadt) {
        Stadt = stadt;
    }

    public Firm(Integer id, String name, String stadt) {
        this.idFirmen = id;
        Name = name;
        Stadt = stadt;
    }

    public Firm(String name, String stadt) {
        Name = name;
        Stadt = stadt;
    }
}
