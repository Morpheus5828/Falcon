package org.falcon.server.database;

import com.sun.jdi.connect.spi.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class User extends DataBaseManagment{
    private String name;

    public User(String name) throws SQLException {
        super();
        this.name = name;
    }

    public void insertUser() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");


        String sql = "INSERT INTO username VALUES(?)";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, this.name);

        stmt.executeUpdate();

        stmt.close();
        conn.close();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
