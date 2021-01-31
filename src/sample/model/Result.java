//Autohr: Mulham Alibrahim 170503111

package sample.model;

public class Result {
    private int idResults;
    private int Bericht;
    private String weld;
    private String welding_process;
    private int length;
    private int thickness;
    private String diameter;
    private String defect_type;
    private String defect_loc;
    private String result;

    public Result(int bericht, String weld, String welding_process, int length, int thickness, String diameter, String defect_type, String defect_loc, String result) {
        Bericht = bericht;
        this.weld = weld;
        this.welding_process = welding_process;
        this.length = length;
        this.thickness = thickness;
        this.diameter = diameter;
        this.defect_type = defect_type;
        this.defect_loc = defect_loc;
        this.result = result;
    }

    public Result(int idResults, int bericht, String weld, String welding_process, int length, int thickness, String diameter, String defect_type, String defect_loc, String result) {
        this.idResults = idResults;
        Bericht = bericht;
        this.weld = weld;
        this.welding_process = welding_process;
        this.length = length;
        this.thickness = thickness;
        this.diameter = diameter;
        this.defect_type = defect_type;
        this.defect_loc = defect_loc;
        this.result = result;
    }

    public int getIdResults() {
        return idResults;
    }

    public void setIdResults(int idResults) {
        this.idResults = idResults;
    }

    public int getBericht() {
        return Bericht;
    }

    public void setBericht(int bericht) {
        Bericht = bericht;
    }

    public String getWeld() {
        return weld;
    }

    public void setWeld(String weld) {
        this.weld = weld;
    }

    public String getWelding_process() {
        return welding_process;
    }

    public void setWelding_process(String welding_process) {
        this.welding_process = welding_process;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getDefect_type() {
        return defect_type;
    }

    public void setDefect_type(String defect_type) {
        this.defect_type = defect_type;
    }

    public String getDefect_loc() {
        return defect_loc;
    }

    public void setDefect_loc(String defect_loc) {
        this.defect_loc = defect_loc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
