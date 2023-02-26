package org.falcon.server.database;

import java.sql.*;

public abstract class DataBaseManagment {
    private final String DB_URL = "jdbc:h2:C:/Users/thorr/IdeaProjects/Falcon/src/main/java/org/falcon/server/database";
    private final String JDBC_DRIVER = "org.h2.Driver";
    protected Connection conn;
    protected PreparedStatement stmt;


    protected void sendData(String command) throws SQLException {

    }

    public void initDataBase() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
           // stmt = conn.createStatement();
            System.out.println("Successfully Connected to Falcon DB ");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("ERROR - Unsuccessfully Connected to Falcon DB ");
        }
    }


}

