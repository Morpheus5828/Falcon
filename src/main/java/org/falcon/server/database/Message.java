package org.falcon.server.database;

import java.sql.SQLException;

public class Message extends DataBaseManagment{
    private int id;
    private String message;
    private String user;

    public Message(String user, int id, String message) {
        this.user = user;
        this.id = id;
        this.message = message;
    }

    public void insertMessage() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");


        String sql = "INSERT INTO MESSAGE_USER VALUES(?,?,?)";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, this.user);
        stmt.setInt(2, this.id);
        stmt.setString(3, this.message);

        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
}
