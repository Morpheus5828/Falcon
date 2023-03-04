package org.falcon.server.database;


import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TestUser {
    @Test
    public void testInsertUser() throws SQLException, ClassNotFoundException {
        //Add name which not in database
        /*User user = new User("Manon");
        user.initDataBase();
        user.insertUser();*/
    }
    @Test
    public void testAlreadyInDb() throws SQLException, ClassNotFoundException {
        User user = new User("Manon");
        user.initDataBase();
        System.out.println(user.alreadyInDb());
    }

}
