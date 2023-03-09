package org.falcon.server.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Follower extends DataBaseManagment{
    //TODO test this class with getFollower
    private String name;

    public Follower(String name) {
        super();
        this.name = name;
    }

    public List<String> getFollowers() throws SQLException {
        List<String> follower = new ArrayList<>();
        initDataBase();
        String sql = "SELECT * FROM USERNAME WHERE NAME = (?);";


        stmt = conn.prepareStatement(sql);
        stmt.setString(1, this.name);
        ResultSet dbResult = stmt.executeQuery();

        while(dbResult.next()) {
            if(dbResult.getString("NAME").equals(this.name)) {
                String message = dbResult.getString("FOLLOWER");
                follower.add(message);
            }
        }

        stmt.close();
        conn.close();
        return follower;
    }
}
