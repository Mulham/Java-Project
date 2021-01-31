//Autohr: Mulham Alibrahim 170503111

package sample.model;

public class Project {
    private int idProjects;
    private String Name;

    public Project(int idProjects, String name) {
        this.idProjects = idProjects;
        Name = name;
    }

    public Project(String name) {
        Name = name;
    }

    public int getIdProjects() {
        return idProjects;
    }

    public void setIdProjects(int idProjects) {
        this.idProjects = idProjects;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }



}
