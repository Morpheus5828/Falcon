package org.falcon.server.database;

import com.sun.jdi.connect.spi.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class User extends DataBaseManagment{
    private String name;

    public User(String name) {
        super();
        this.name = name;
    }

    public void insertUser() throws SQLException, ClassNotFoundException {
        initDataBase();
        String sql = "INSERT INTO username VALUES(?)";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, this.name);

        stmt.executeUpdate();

        stmt.close();
        conn.close();

    }

    public boolean alreadyInDb() throws SQLException, ClassNotFoundException {
        initDataBase();
        String sql = "SELECT * FROM USERNAME";

        stmt = conn.prepareStatement(sql);
        ResultSet dbResult = stmt.executeQuery();

        while(dbResult.next()) {
            if(dbResult.getString("NAME").equals(this.name)) {
                stmt.close();
                conn.close();
                return true;
            }
        }

        stmt.close();
        conn.close();
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
