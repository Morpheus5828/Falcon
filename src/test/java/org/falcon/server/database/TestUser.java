package org.falcon.server.database;


import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TestUser {
    @Test
    public void testInsertUser() throws SQLException, ClassNotFoundException {
        User user = new User("Manon");
        user.initDataBase();
        user.insertUser();
    }

}
