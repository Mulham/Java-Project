//Autohr: Mulham Alibrahim 170503111


package sample.Database;

import sample.model.*;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/"
                + dbName + "?autoReconnect=true&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
        //write

        public boolean addUser(User user) {
            String insert = "INSERT INTO Users " + " (Name, Nachname, Level, Zertifikat_datum, password) VALUES (?,?,?,?,?);";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getNachname());
                preparedStatement.setString(3, user.getLevel());
                preparedStatement.setDate(4, Date.valueOf(user.getZertifikat_datum()));
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }

    public boolean addFirm(Firm firm) {
        String insert = "INSERT INTO mydb.Firmen " + " (Name, Stadt) VALUES (?,?);";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, firm.getName());
            preparedStatement.setString(2, firm.getStadt());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFirm(Firm firm) {
        String delete = "DELETE FROM mydb.Firmen " + " Where idFirmen" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setInt(1, firm.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean editFirm(Firm firm) {
        String edit = "update mydb.Firmen " + " set Name = ?, Stadt = ?"
                +" Where id" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(edit);
            preparedStatement.setString(1, firm.getName());
            preparedStatement.setString(2, firm.getStadt());
            preparedStatement.setInt(3, firm.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addJob(Job job, int id) {
        String insert = "INSERT INTO mydb.JobOrder (No, Firma) VALUES (?,?);";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setDouble(1, job.getNo());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addOffer(Offer offer, int id) {
        String insert = "INSERT INTO mydb.Offer (No, Firma) VALUES (?,?);";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setDouble(1, offer.getNo());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addProject(Project project) {
        String insert = "INSERT INTO mydb.Projects (Name) VALUES (?);";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, project.getName());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addSurface(Surface surface) {
        String insert = "INSERT INTO mydb.SurfaceCondition (Name) VALUES (?);";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, surface.getName());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addStage(Stage stage) {
        String insert = "INSERT INTO mydb.Stages (Name) VALUES (?);";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, stage.getName());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addEquipment(Equipment equipment) {
        String insert = "INSERT INTO mydb.Geraeten (Name, pole_distance, mp_carrier_medium, mag_tech, uv_light_intensity" +
                ", distance_of_light, produktleistungen, Kolabirasyon_Datum) VALUES (?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, equipment.getName());
            preparedStatement.setFloat(2, equipment.getPole_distance());
            preparedStatement.setString(3, equipment.getMp_carrier_medium());
            preparedStatement.setString(4, equipment.getMag_tech());
            preparedStatement.setString(5, equipment.getUv_light_intensity());
            preparedStatement.setString(6, equipment.getDistance_of_light());
            preparedStatement.setString(7, equipment.getProduktleistungen());
            preparedStatement.setDate(8, Date.valueOf(equipment.getKolabirasyon_Datum()));

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addResult(Result result) {
        String insert = "INSERT INTO mydb.Results (Bericht, weld, length, thickness, diameter" +
                ", defect_type, defect_loc, result, welding_process) VALUES (?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1, result.getBericht());
            preparedStatement.setString(2, result.getWeld());
            preparedStatement.setInt(3, result.getLength());
            preparedStatement.setInt(4, result.getThickness());
            preparedStatement.setString(5, result.getDiameter());
            preparedStatement.setString(6, result.getDefect_type());
            preparedStatement.setString(7, result.getDefect_loc());
            preparedStatement.setString(8, result.getResult());
            preparedStatement.setString(9, result.getWelding_process());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addBericht(Bericht bericht) {
        String insert = "INSERT INTO mydb.Berichten (datum, operator, surface_tempreture, firma, geraet," +
                " project, surfaceCondition, stage, confirmator, evaluator, inspection_place, inspection_standart," +
                " evaluation_standart," +
                " inspection_procedure, inspection_scope, drawing_no, page, report_no, job, offer, pole_distance," +
                " mp_carrier_medium," +
                " uv_light_intensity, mag_tech, distance_of_light, examination_area, current_type, luxmeter, test_medium," +
                " demagnetization, heat_treatment, gauss, surface_condition2, id_light, lifting_test, grafik," +
                " standard_deviations," +
                " inspection_dates, description_and_attachments) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";



        try {
            System.out.println("equipment = " + bericht.getEquipment());
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setDate(1, Date.valueOf(bericht.getReport_date()));
            preparedStatement.setInt(2, bericht.getOperator());
            preparedStatement.setString(3, bericht.getSurface_temp());
            preparedStatement.setInt(4, bericht.getCustomer());
            preparedStatement.setInt(5, bericht.getEquipment());
            preparedStatement.setInt(6, bericht.getProject());
            preparedStatement.setInt(7, bericht.getSurface_condiotion());
            preparedStatement.setInt(8, bericht.getStage());
            preparedStatement.setInt(9, bericht.getConfirmator());
            preparedStatement.setInt(10, bericht.getEvaluator());
            preparedStatement.setString(11, bericht.getInspection_place());
            preparedStatement.setString(12, bericht.getInspection_standart());
            preparedStatement.setString(13, bericht.getEvaluation_standart());
            preparedStatement.setString(14, bericht.getInspection_procedure());
            preparedStatement.setInt(15, bericht.getInspection_scope());
            preparedStatement.setString(16, bericht.getDrawing_no());
            preparedStatement.setString(17, bericht.getPage());
            preparedStatement.setString(18, bericht.getReport_no());
            preparedStatement.setString(19, bericht.getJob());
            preparedStatement.setString(20, bericht.getOffer());
            preparedStatement.setString(21, bericht.getPole_distance());
            preparedStatement.setString(22, bericht.getMp());
            preparedStatement.setString(23, bericht.getUv());
            preparedStatement.setString(24, bericht.getMag());
            preparedStatement.setString(25, bericht.getDistance_of_light());
            preparedStatement.setString(26, bericht.getExamination_area());
            preparedStatement.setString(27, bericht.getCurrent_type());
            preparedStatement.setString(28, bericht.getLuxmeter());
            preparedStatement.setString(29, bericht.getTest_medium());
            preparedStatement.setString(30, bericht.getDemagnetization());
            preparedStatement.setString(31, bericht.getHeat_treatment());
            preparedStatement.setString(32, bericht.getGauss());
            preparedStatement.setString(33, bericht.getSurface_condition2());
            preparedStatement.setString(34, bericht.getId_light());
            preparedStatement.setString(35, bericht.getLifting_test());
            preparedStatement.setInt(36, bericht.getGrafik());
            preparedStatement.setString(37, bericht.getStandard_deviations());
            preparedStatement.setString(38, bericht.getInspection_dates());
            preparedStatement.setString(39, bericht.getDescription_and_attachments());


            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean editResult(Result result) {
        String edit = "update mydb.Results set Bericht = ?, weld = ?, length = ?, thickness = ?, diameter = ?" +
                ", defect_type = ?, defect_loc = ?, result = ?, welding_process = ? Where idResults = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(edit);
            preparedStatement.setInt(1, result.getBericht());
            preparedStatement.setString(2, result.getWeld());
            preparedStatement.setInt(3, result.getLength());
            preparedStatement.setInt(4, result.getThickness());
            preparedStatement.setString(5, result.getDiameter());
            preparedStatement.setString(6, result.getDefect_type());
            preparedStatement.setString(7, result.getDefect_loc());
            preparedStatement.setString(8, result.getResult());
            preparedStatement.setString(9, result.getWelding_process());
            preparedStatement.setInt(10, result.getIdResults());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean editEquipment(Equipment equipment) {
        String edit = "update mydb.Geraeten " + " set Name = ?, pole_distance = ?, mp_carrier_medium = ?," +
                " mag_tech = ?, uv_light_intensity = ?, distance_of_light = ?, produktleistungen = ?, Kolabirasyon_Datum = ?"
                +" Where idGeraeten" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(edit);
            preparedStatement.setString(1, equipment.getName());
            preparedStatement.setFloat(2, equipment.getPole_distance());
            preparedStatement.setString(3, equipment.getMp_carrier_medium());
            preparedStatement.setString(4, equipment.getMag_tech());
            preparedStatement.setString(5, equipment.getUv_light_intensity());
            preparedStatement.setString(6, equipment.getDistance_of_light());
            preparedStatement.setString(7, equipment.getProduktleistungen());
            preparedStatement.setDate(8, Date.valueOf(equipment.getKolabirasyon_Datum()));
            preparedStatement.setInt(9, equipment.getIdGeraeten());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteResult(Result result) {
        String delete = "DELETE FROM mydb.Results " + " Where idResults" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setInt(1, result.getIdResults());
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteEquipment(Equipment equipment) {
        String delete = "DELETE FROM mydb.Geraeten " + " Where idGeraeten" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setInt(1, equipment.getIdGeraeten());
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean editOffer(Offer offer) {
        String edit = "update mydb.Offer " + " set No = ?"
                +" Where idOffer" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(edit);
            preparedStatement.setDouble(1, offer.getNo());
            preparedStatement.setInt(2, offer.getIdOffer());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean editJob(Job job) {
        String edit = "update mydb.JobOrder " + " set No = ?"
                +" Where idJobOrder" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(edit);
            preparedStatement.setDouble(1, job.getNo());
            preparedStatement.setInt(2, job.getIdJobOrder());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteUser(User user) {
        String delete = "DELETE FROM Users " + " Where idUser" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteBericht(Bericht bericht) {
        String delete = "DELETE FROM Berichten " + " Where idBerichten" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setInt(1, bericht.getIdBericht());
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteProject(Project project) {
        String delete = "DELETE FROM mydb.Projects " + " Where idProjects" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setInt(1, project.getIdProjects());
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteJob(Job job) {
        String delete = "DELETE FROM mydb.JobOrder Where idJobOrder = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setInt(1, job.getIdJobOrder());
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteOffer(Offer offer) {
        String delete = "DELETE FROM mydb.Offer Where idOffer = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setInt(1, offer.getIdOffer());
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean editUser(User user) {
        String edit = "update Users " + " set Name = ?, Nachname = ?, Level = ?, Zertifikat_datum = ?, password = ?"
                +" Where idUser" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(edit);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getNachname());
            preparedStatement.setString(3, user.getLevel());
            preparedStatement.setString(4, String.valueOf(user.getZertifikat_datum()));
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editProject(Project project) {
        String edit = "update mydb.Projects " + " set Name = ?"
                +" Where idProjects" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(edit);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setInt(2, project.getIdProjects());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean editSurface(Surface surface) {
        String edit = "update mydb.SurfaceCondition " + " set Name = ?"
                +" Where idSurfaceCondition" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(edit);
            preparedStatement.setString(1, surface.getName());
            preparedStatement.setInt(2, surface.getIdSurfaceCondition());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteSurface(Surface surface) {
        String delete = "DELETE FROM mydb.SurfaceCondition " + " Where idSurfaceCondition" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setInt(1, surface.getIdSurfaceCondition());
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean editStage(Stage stage) {
        String edit = "update mydb.Stages " + " set Name = ?"
                +" Where idStages" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(edit);
            preparedStatement.setString(1, stage.getName());
            preparedStatement.setInt(2, stage.getIdStages());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteStage(Stage stage) {
        String delete = "DELETE FROM mydb.Stages " + " Where idStages" + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setInt(1, stage.getIdStages());
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
        public ResultSet getUser(String username, String password){
                ResultSet resultset = null;
                String query = "SELECT * FROM mydb.Users where Name=? and password=?";
                try {
                    PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString( 2, password);
                    resultset = preparedStatement.executeQuery();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return  resultset;
            }
    public ResultSet getTable(String table){
        ResultSet resultset = null;
        String query = "SELECT * FROM mydb." + table;
        try {
            resultset = getDbConnection().createStatement().executeQuery(query);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  resultset;
    }
    public ResultSet getSelect(String table , int id, String fk){
        ResultSet resultset = null;
        String query = "SELECT * FROM mydb." + table + " where " + fk + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultset = preparedStatement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  resultset;
    }
    public int getId(String table, String column, String value) throws SQLException {
        ResultSet resultset = null;
        String select = "select *" + " from mydb." + table + " where " + column + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, value);
            resultset = preparedStatement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        int o = 0;
        while(resultset.next()){
            o =   resultset.getInt(1);}
        return o;
    }
    public Date getDate(String table, String column, String id,int value) throws SQLException {
        ResultSet resultset = null;
        String select = "select " + column+ " from mydb." + table + " where "+id  + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setInt(1, value);
            resultset = preparedStatement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Date o = null;
        while(resultset.next()){
            o =   resultset.getDate(1);}
        return o;
    }
    public int getLastId(String table) throws SQLException {
        ResultSet resultset = null;
        String query = "SELECT *" + " FROM mydb."+table +" order by id" +table +" DESC limit 1;";
        try {
            resultset = getDbConnection().createStatement().executeQuery(query);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }int o = 0;
        while(resultset.next()){
        System.out.printf(String.valueOf(resultset.getInt("idBerichten")));
        o =   resultset.getInt(1);}
        return o;
    }
           public String getName() throws SQLException, ClassNotFoundException {
                String select = "SELECT Name FROM Kunden";
                 PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
                return String.valueOf(preparedStatement.execute());
             }
    public String getName1(String table , int id, String fk) throws SQLException {
        ResultSet resultset = null;
        String name = null;
        String query = "SELECT Name FROM mydb." + table + " where " + fk + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
             resultset = preparedStatement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        while(resultset.next()){
            name = resultset.getString(1);
        }
        return  name;
    }

    }

