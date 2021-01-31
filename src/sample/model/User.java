//Autohr: Mulham Alibrahim 170503111

package sample.model;

import java.time.LocalDate;

public class User {
    private Integer idUser;
    private String Name;
    private String Nachname;
    private String Level;
    private String password;
    private LocalDate Zertifikat_datum;

    public User(){

    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public User(String name, String nachname, String level, String password, LocalDate zertifikat_datum) {
        Name = name;
        Nachname = nachname;
        Level = level;
        this.password = password;
        Zertifikat_datum = zertifikat_datum;
    }
    public User(Integer id_User, String name, String nachname, String level, LocalDate zertifikat_datum, String password) {
        idUser = id_User;
        Name = name;
        Nachname = nachname;
        Level = level;
        this.password = password;
        Zertifikat_datum = zertifikat_datum;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNachname() {
        return Nachname;
    }
    public void setId(Integer id){
        idUser = id;
    }
    public Integer getId(){
        return idUser;
    }
    public void setNachname(String nachname) {
        Nachname = nachname;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getZertifikat_datum() {
        return Zertifikat_datum;
    }

    public void setZertifikat_datum(LocalDate zertifikat_datum) {
        Zertifikat_datum = zertifikat_datum;
    }
}
