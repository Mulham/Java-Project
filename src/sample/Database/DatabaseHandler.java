package sample.Database;


import sample.model.User;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/"
                + dbName + "?autoReconnect=true&useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
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
    public ResultSet getUser(String username, String password){
        ResultSet resultset = null;
        String query = "SELECT * FROM " + dbName + "." + Const.USERS_TABLE + " where Name=? and password=?";
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
}
