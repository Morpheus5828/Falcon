package org.falcon.server.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TestMessage {
    @Test
    public void testInsertMessage() throws SQLException, ClassNotFoundException {
        Message message = new Message("Manon", "Ceci est un très long text");
        message.initDataBase();
        message.insertMessage();
    }

    @Test public void testGetRecentMessage() throws SQLException {
        Message message = new Message("Manon", "Ceci est un très long text");
        message.initDataBase();
        message.getRecentMessage();
    }

    @Test public void testGetMessageFromUser() throws SQLException {
        Message message = new Message("Manon");
        message.initDataBase();
        System.out.println(message.getMessageFromUser().toString());
    }
}
