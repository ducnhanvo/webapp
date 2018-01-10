package database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseManagement {

    String urlDatabase;
    String userDatabase;
    String passwordDatabase;
    Connection connectionDatabase = null;

    private boolean initialize() {
        Properties property = new Properties();

        try {

            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream("information.properties");

            property.load(inputStream);

            urlDatabase = property.getProperty("urlDatabase");
            userDatabase = property.getProperty("userDatabase");
            passwordDatabase = property.getProperty("passwordDatabase");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        // Check JDBC driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(
                    "Please include PostgreSQL JDBC Driver library!!!");
            e.printStackTrace();
            return false;
        }

        try {
            connectionDatabase = DriverManager.getConnection(urlDatabase,
                    userDatabase, passwordDatabase);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return false;
        }

        if (connectionDatabase == null) {
            System.out.println("Failed to make connection!");
            return false;
        }
        return true;

    }

    public boolean isAccount(String userName, String password)
            throws SQLException {

        // initialize database connection
        if (!initialize()) {
            return false;
        }

        // statement query in database
        Statement statement = connectionDatabase.createStatement();

        // query statement about userName and password
        String querySql = "Select * from member where username = '" + userName
                + "' and password = '" + password + "'";

        // execute query and get information
        ResultSet rs = statement.executeQuery(querySql);

        // if result set is empty return false
        if (rs.next() == false) {
            return false;
        }
        return true;
    }

    public String getYourName(String userName) throws SQLException {

        // statement to insert data
        Statement statement = connectionDatabase.createStatement();

        // query statement about userName 
        String querySql = "Select * from member where username = '" + userName
                + "'";

        // execute statement
        ResultSet rs = statement.executeQuery(querySql);
        //connectionDatabase.close();
        rs.next();
        return rs.getString("yourname");
        
    }

    public boolean isExistAccount(String userName) throws SQLException {

        // initialize database connection
        if (!initialize()) {
            return false;
        }

        // statement query in database
        Statement statement = connectionDatabase.createStatement();

        // query statement about userName and password
        String querySql = "Select * from member where username = '" + userName
                + "'";

        // execute query and get information
        ResultSet rs = statement.executeQuery(querySql);

        // if result set is empty return false
        if (rs.next() == false) {
            return false;
        }
        connectionDatabase.close();
        return true;
    }
    public void createNewAccount(String yourName, String userName,
            String password) throws SQLException {

        // statement to insert data
        Statement statement = connectionDatabase.createStatement();

        // insert data command
        String insertData = "INSERT INTO member " + "VALUES ('" + yourName
                + "', '" + userName + "', '" + password + "')";

        // execute add new
        statement.executeUpdate(insertData);
        connectionDatabase.close();
    }
}
