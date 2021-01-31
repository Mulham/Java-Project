//Autohr: Mulham Alibrahim 170503111


package sample.model;

import java.time.LocalDate;

public class Equipment {
    private int idGeraeten;
    private String Name;
    private float pole_distance;
    private String mp_carrier_medium;
    private String mag_tech;
    private String uv_light_intensity;

    private String distance_of_light;

    private String produktleistungen;

    private LocalDate Kolabirasyon_Datum;


    public Equipment(String name, float pole_distance, String mp_carrier_medium, String mag_tech, String uv_light_intensity, String distance_of_light, String produktleistungen, LocalDate kolabirasyon_Datum) {
        Name = name;
        this.pole_distance = pole_distance;
        this.mp_carrier_medium = mp_carrier_medium;
        this.mag_tech = mag_tech;
        this.uv_light_intensity = uv_light_intensity;
        this.distance_of_light = distance_of_light;
        this.produktleistungen = produktleistungen;
        Kolabirasyon_Datum = kolabirasyon_Datum;
    }

    public Equipment(int idGeraeten, String name, float pole_distance, String mp_carrier_medium, String mag_tech, String uv_light_intensity, String distance_of_light, String produktleistungen, LocalDate kolabirasyon_Datum) {
        this.idGeraeten = idGeraeten;
        Name = name;
        this.pole_distance = pole_distance;
        this.mp_carrier_medium = mp_carrier_medium;
        this.mag_tech = mag_tech;
        this.uv_light_intensity = uv_light_intensity;
        this.distance_of_light = distance_of_light;
        this.produktleistungen = produktleistungen;
        Kolabirasyon_Datum = kolabirasyon_Datum;
    }

    public Equipment(String equipment) {
        Name = equipment;
    }

    public int getIdGeraeten() {
        return idGeraeten;
    }

    public void setIdGeraeten(int idGeraeten) {
        this.idGeraeten = idGeraeten;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getPole_distance() {
        return pole_distance;
    }

    public void setPole_distance(float pole_distance) {
        this.pole_distance = pole_distance;
    }

    public String getMp_carrier_medium() {
        return mp_carrier_medium;
    }

    public void setMp_carrier_medium(String mp_carrier_medium) {
        this.mp_carrier_medium = mp_carrier_medium;
    }

    public String getMag_tech() {
        return mag_tech;
    }

    public void setMag_tech(String mag_tech) {
        this.mag_tech = mag_tech;
    }

    public String getUv_light_intensity() {
        return uv_light_intensity;
    }

    public void setUv_light_intensity(String uv_light_intensity) {
        this.uv_light_intensity = uv_light_intensity;
    }

    public String getDistance_of_light() {
        return distance_of_light;
    }

    public void setDistance_of_light(String distance_of_light) {
        this.distance_of_light = distance_of_light;
    }

    public String getProduktleistungen() {
        return produktleistungen;
    }

    public void setProduktleistungen(String produktleistungen) {
        this.produktleistungen = produktleistungen;
    }

    public LocalDate getKolabirasyon_Datum() {
        return Kolabirasyon_Datum;
    }

    public void setKolabirasyon_Datum(LocalDate kolabirasyon_Datum) {
        Kolabirasyon_Datum = kolabirasyon_Datum;
    }
}
