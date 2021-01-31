//Autohr: Mulham Alibrahim 170503111

package sample.model;

public class Stage {
    private int idStages;
    private String Name;

    public Stage(int idStages, String name) {
        this.idStages = idStages;
        Name = name;
    }

    public Stage(String name) {
        Name = name;
    }

    public int getIdStages() {
        return idStages;
    }

    public void setIdStages(int idStages) {
        this.idStages = idStages;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
