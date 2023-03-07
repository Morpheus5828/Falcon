package org.falcon.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TestMessageManagment {
    @Test
    public void testCutMessage() throws SQLException, ClassNotFoundException {
        MessageManagment mm = new MessageManagment("PUBLISH author:@Manon Hello I am Morpheus5828");
        Assertions.assertEquals("Hello I am Morpheus5828 ",mm.getMessage());
    }

    @Test
    public void testCutMessageToExtractUsername() throws SQLException, ClassNotFoundException {
        MessageManagment mm = new MessageManagment("PUBLISH author:@Manon Hello I am Morpheus5828");
        mm.cutMessageToExtractUsername(mm.cutMessage()[1]);
        Assertions.assertEquals("Manon", mm.getUsername());
    }

    @Test
    public void testInsertMessageInDb() throws SQLException, ClassNotFoundException {
        new MessageManagment("PUBLISH author:@Manon I love skiing");

    }

    @Test
    public void testRepublishMessage() throws SQLException, ClassNotFoundException {
        new MessageManagment("REPUBLISH author:@morpheus5828 msg_id:146");
    }

    @Test
    public void testReplyMessage() throws SQLException, ClassNotFoundException {
        new MessageManagment("REPLY author:@morpheus5828 reply_to_id:146");
    }

}
