//Autohr: Mulham Alibrahim 170503111

package sample.model;

public class Surface {
   private int idSurfaceCondition;
   private String Name;

    public int getIdSurfaceCondition() {
        return idSurfaceCondition;
    }

    public void setIdSurfaceCondition(int idSurfaceCondition) {
        this.idSurfaceCondition = idSurfaceCondition;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Surface(String name) {
        Name = name;
    }

    public Surface(int idSurfaceCondition, String name) {
        this.idSurfaceCondition = idSurfaceCondition;
        Name = name;
    }
}
