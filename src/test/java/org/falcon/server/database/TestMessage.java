package org.falcon.server.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TestMessage {
    @Test
    public void testInsertMessage() throws SQLException, ClassNotFoundException {
        Message message = new Message("Manon", 12, "Ceci est un très long text");
        message.initDataBase();
        message.insertMessage();

    }
}
