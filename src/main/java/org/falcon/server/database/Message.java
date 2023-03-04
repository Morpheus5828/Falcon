package org.falcon.server.database;

import java.sql.SQLException;

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
        String sql = "INSERT INTO MESSAGE_USER VALUES(?,?,?)";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, this.user);
        stmt.setInt(2, this.id);
        stmt.setString(3, this.message);

        stmt.executeUpdate();
        System.out.println("Message has been INSERT successfully");
        stmt.close();
        conn.close();
    }
}
