package org.falcon.server.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public void getRecentMessage() throws SQLException {
        initDataBase();
        String sql = "SELECT * FROM MESSAGE_USER ORDER BY POST_DATE ";

        stmt = conn.prepareStatement(sql);
        ResultSet dbResult = stmt.executeQuery();

        List<String> messages = new ArrayList<>();

        while(dbResult.next()) {
            String message = dbResult.getString("MESSAGE") + " from: " +
                    dbResult.getString("NAME") + " posted: " +
                    dbResult.getTimestamp("POST_DATE");
            messages.add(message);
        }

        for(String s : messages) System.out.println(s);

        stmt.close();
        conn.close();
    }




}
