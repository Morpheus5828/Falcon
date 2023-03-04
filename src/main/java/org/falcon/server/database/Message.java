package org.falcon.server.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Message extends DataBaseManagment{
    private int id;
    private String message;
    private String user;

    public Message(String user, String message) {
        super();
        this.user = user;
        this.id = (int) (Math.random() * 150);
        this.message = message;
    }

    public void insertMessage() throws SQLException, ClassNotFoundException {
        //Class.forName("org.h2.Driver");
        initDataBase();
        String sql = "INSERT INTO MESSAGE_USER VALUES(?,?,?,?)";

        Date utilDate = new java.util.Date();

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, this.user);
        stmt.setTimestamp(2, new java.sql.Timestamp(utilDate.getTime()));
        stmt.setInt(3, this.id);
        stmt.setString(4, this.message);

        stmt.executeUpdate();
        System.out.println("Message has been INSERT successfully");
        stmt.close();
        conn.close();
    }

   /* public List<String> getRecentMessage() throws SQLException {
        initDataBase();
        String sql = "SELECT * FROM USERNAME";

        stmt = conn.prepareStatement(sql);
        ResultSet dbResult = stmt.executeQuery();

        while(dbResult.next()) {
            if(dbResult.getString("NAME").equals(this.name)) {
                stmt.close();
                conn.close();
            }
        }

        stmt.close();
        conn.close();
    }*/




}
