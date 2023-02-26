package org.falcon.server.database;

import com.sun.jdi.connect.spi.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class User extends DataBaseManagment{
    private int id;
    private String name;
    private String message;

    public User(int id, String name, String message) throws SQLException {
        super();
        this.id = id;
        this.name = name;
        this.message = message;
    }

    public void insertUser() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");


        String sql = "INSERT INTO username VALUES(?,?,?)";

        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, this.id);
        stmt.setString(2, this.name);
        stmt.setString(3, this.message);

        stmt.executeUpdate();

        stmt.close();
        conn.close();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
