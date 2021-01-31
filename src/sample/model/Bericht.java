//Autohr: Mulham Alibrahim 170503111


package sample.model;

import java.time.LocalDate;

public class Bericht {
    private int idBericht;
    private int customer;
    private int project;
    private String inspection_place;
    private String inspection_standart;
    private String evaluation_standart;
    private String inspection_procedure;
    private int inspection_scope;
    private String drawing_no;
    private int surface_condiotion;
    private int stage;
    private String page;
    private String report_no;
    private LocalDate report_date;
    private String job;
    private String offer;
    private String pole_distance;
    private int equipment;
    private String mp;
    private String uv;
    private String mag;
    private String distance_of_light;
    private String examination_area;
    private String current_type;
    private String luxmeter;
    private String test_medium;
    private String demagnetization;
    private String heat_treatment;
    private String surface_temp;
    private String gauss;
    private String surface_condition2;
    private String id_light;
    private String lifting_test;
    private int grafik;
    private String standard_deviations;
    private String inspection_dates;
    private String description_and_attachments;
    private int operator;
    private int confirmator;
    private int evaluator;
    private String customerName;
    private String projectName;
    private String equipmentName;



    public Bericht(int idBericht, int customer, int project, String inspection_place, String inspection_standart, String evaluation_standart, String inspection_procedure, int inspection_scope, String drawing_no, int surface_condiotion, int stage, String page, String report_no, LocalDate report_date, String job, String offer, String pole_distance, int equipment, String mp, String uv, String mag, String distance_of_light, String examination_area, String current_type, String luxmeter, String test_medium, String demagnetization, String heat_treatment, String surface_temp, String gauss, String surface_condition2, String id_light, String lifting_test, int grafik, String standard_deviations, String inspection_dates, String description_and_attachments, int operator, int confirmator, int evaluator) {
        this.idBericht = idBericht;
        this.customer = customer;
        this.project = project;
        this.inspection_place = inspection_place;
        this.inspection_standart = inspection_standart;
        this.evaluation_standart = evaluation_standart;
        this.inspection_procedure = inspection_procedure;
        this.inspection_scope = inspection_scope;
        this.drawing_no = drawing_no;
        this.surface_condiotion = surface_condiotion;
        this.stage = stage;
        this.page = page;
        this.report_no = report_no;
        this.report_date = report_date;
        this.job = job;
        this.offer = offer;
        this.pole_distance = pole_distance;
        this.equipment = equipment;
        this.mp = mp;
        this.uv = uv;
        this.mag = mag;
        this.distance_of_light = distance_of_light;
        this.examination_area = examination_area;
        this.current_type = current_type;
        this.luxmeter = luxmeter;
        this.test_medium = test_medium;
        this.demagnetization = demagnetization;
        this.heat_treatment = heat_treatment;
        this.surface_temp = surface_temp;
        this.gauss = gauss;
        this.surface_condition2 = surface_condition2;
        this.id_light = id_light;
        this.lifting_test = lifting_test;
        this.grafik = grafik;
        this.standard_deviations = standard_deviations;
        this.inspection_dates = inspection_dates;
        this.description_and_attachments = description_and_attachments;
        this.operator = operator;
        this.confirmator = confirmator;
        this.evaluator = evaluator;
    }

    public Bericht(int customer, int project, String inspection_place, String inspection_standart, String evaluation_standart, String inspection_procedure, int inspection_scope, String drawing_no, int surface_condiotion, int stage, String page, String report_no, LocalDate report_date, String job, String offer, String pole_distance, int equipment, String mp, String uv, String mag, String distance_of_light, String examination_area, String current_type, String luxmeter, String test_medium, String demagnetization, String heat_treatment, String surface_temp, String gauss, String surface_condition2, String id_light, String lifting_test, int grafik, String standard_deviations, String inspection_dates, String description_and_attachments, int operator, int confirmator, int evaluator) {
        this.customer = customer;
        this.project = project;
        this.inspection_place = inspection_place;
        this.inspection_standart = inspection_standart;
        this.evaluation_standart = evaluation_standart;
        this.inspection_procedure = inspection_procedure;
        this.inspection_scope = inspection_scope;
        this.drawing_no = drawing_no;
        this.surface_condiotion = surface_condiotion;
        this.stage = stage;
        this.page = page;
        this.report_no = report_no;
        this.report_date = report_date;
        this.job = job;
        this.offer = offer;
        this.pole_distance = pole_distance;
        this.equipment = equipment;
        this.mp = mp;
        this.uv = uv;
        this.mag = mag;
        this.distance_of_light = distance_of_light;
        this.examination_area = examination_area;
        this.current_type = current_type;
        this.luxmeter = luxmeter;
        this.test_medium = test_medium;
        this.demagnetization = demagnetization;
        this.heat_treatment = heat_treatment;
        this.surface_temp = surface_temp;
        this.gauss = gauss;
        this.surface_condition2 = surface_condition2;
        this.id_light = id_light;
        this.lifting_test = lifting_test;
        this.grafik = grafik;
        this.standard_deviations = standard_deviations;
        this.inspection_dates = inspection_dates;
        this.description_and_attachments = description_and_attachments;
        this.operator = operator;
        this.confirmator = confirmator;
        this.evaluator = evaluator;
    }



    public Bericht(int idBericht, int customer, int project, String inspection_place, String inspection_standart, String evaluation_standart, String inspection_procedure, int inspection_scope, String drawing_no, int surface_condiotion, int stage, String page, String report_no, LocalDate report_date, String job, String offer) {
        this.idBericht = idBericht;
        this.customer = customer;
        this.project = project;
        this.inspection_place = inspection_place;
        this.inspection_standart = inspection_standart;
        this.evaluation_standart = evaluation_standart;
        this.inspection_procedure = inspection_procedure;
        this.inspection_scope = inspection_scope;
        this.drawing_no = drawing_no;
        this.surface_condiotion = surface_condiotion;
        this.stage = stage;
        this.page = page;
        this.report_no = report_no;
        this.report_date = report_date;
        this.job = job;
        this.offer = offer;
    }

    public Bericht(int idBericht, int customer, int project, int equipment) {
        this.idBericht = idBericht;
        this.customer = customer;
        this.project = project;
        this.equipment = equipment;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Bericht(int idBerichten, String customer, String project, String equipment) {
        idBericht = idBerichten;
        customerName = customer;
        projectName = project;
        equipmentName = equipment;
    }

    public int getIdBericht() {
        return idBericht;
    }

    public void setIdBericht(int idBericht) {
        this.idBericht = idBericht;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public String getInspection_place() {
        return inspection_place;
    }

    public void setInspection_place(String inspection_place) {
        this.inspection_place = inspection_place;
    }

    public String getInspection_standart() {
        return inspection_standart;
    }

    public void setInspection_standart(String inspection_standart) {
        this.inspection_standart = inspection_standart;
    }

    public String getEvaluation_standart() {
        return evaluation_standart;
    }

    public void setEvaluation_standart(String evaluation_standart) {
        this.evaluation_standart = evaluation_standart;
    }

    public String getInspection_procedure() {
        return inspection_procedure;
    }

    public void setInspection_procedure(String inspection_procedure) {
        this.inspection_procedure = inspection_procedure;
    }

    public int getInspection_scope() {
        return inspection_scope;
    }

    public void setInspection_scope(int inspection_scope) {
        this.inspection_scope = inspection_scope;
    }

    public String getDrawing_no() {
        return drawing_no;
    }

    public void setDrawing_no(String drawing_no) {
        this.drawing_no = drawing_no;
    }

    public int getSurface_condiotion() {
        return surface_condiotion;
    }

    public void setSurface_condiotion(int surface_condiotion) {
        this.surface_condiotion = surface_condiotion;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
    }

    public LocalDate getReport_date() {
        return report_date;
    }

    public void setReport_date(LocalDate report_date) {
        this.report_date = report_date;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getPole_distance() {
        return pole_distance;
    }

    public void setPole_distance(String pole_distance) {
        this.pole_distance = pole_distance;
    }

    public int getEquipment() {
        return equipment;
    }

    public void setEquipment(int equipment) {
        this.equipment = equipment;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public String getDistance_of_light() {
        return distance_of_light;
    }

    public void setDistance_of_light(String distance_of_light) {
        this.distance_of_light = distance_of_light;
    }

    public String getExamination_area() {
        return examination_area;
    }

    public void setExamination_area(String examination_area) {
        this.examination_area = examination_area;
    }

    public String getCurrent_type() {
        return current_type;
    }

    public void setCurrent_type(String current_type) {
        this.current_type = current_type;
    }

    public String getLuxmeter() {
        return luxmeter;
    }

    public void setLuxmeter(String luxmeter) {
        this.luxmeter = luxmeter;
    }

    public String getTest_medium() {
        return test_medium;
    }

    public void setTest_medium(String test_medium) {
        this.test_medium = test_medium;
    }

    public String getDemagnetization() {
        return demagnetization;
    }

    public void setDemagnetization(String demagnetization) {
        this.demagnetization = demagnetization;
    }

    public String getHeat_treatment() {
        return heat_treatment;
    }

    public void setHeat_treatment(String heat_treatment) {
        this.heat_treatment = heat_treatment;
    }

    public String getSurface_temp() {
        return surface_temp;
    }

    public void setSurface_temp(String surface_temp) {
        this.surface_temp = surface_temp;
    }

    public String getGauss() {
        return gauss;
    }

    public void setGauss(String gauss) {
        this.gauss = gauss;
    }

    public String getSurface_condition2() {
        return surface_condition2;
    }

    public void setSurface_condition2(String surface_condition2) {
        this.surface_condition2 = surface_condition2;
    }

    public String getId_light() {
        return id_light;
    }

    public void setId_light(String id_light) {
        this.id_light = id_light;
    }

    public String getLifting_test() {
        return lifting_test;
    }

    public void setLifting_test(String lifting_test) {
        this.lifting_test = lifting_test;
    }

    public int getGrafik() {
        return grafik;
    }

    public void setGrafik(int grafik) {
        this.grafik = grafik;
    }

    public String getStandard_deviations() {
        return standard_deviations;
    }

    public void setStandard_deviations(String standard_deviations) {
        this.standard_deviations = standard_deviations;
    }

    public String getInspection_dates() {
        return inspection_dates;
    }

    public void setInspection_dates(String inspection_dates) {
        this.inspection_dates = inspection_dates;
    }

    public String getDescription_and_attachments() {
        return description_and_attachments;
    }

    public void setDescription_and_attachments(String description_and_attachments) {
        this.description_and_attachments = description_and_attachments;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public int getConfirmator() {
        return confirmator;
    }

    public void setConfirmator(int confirmator) {
        this.confirmator = confirmator;
    }

    public int getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(int evaluator) {
        this.evaluator = evaluator;
    }
}
