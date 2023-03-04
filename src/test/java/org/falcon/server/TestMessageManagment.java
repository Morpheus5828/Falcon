package org.falcon.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMessageManagment {
    @Test
    public void testCutMessage() {
        MessageManagment mm = new MessageManagment("PUBLISH Hello I am Morpheus5828");
        mm.commandAnalyse(mm.cutMessage());
        Assertions.assertEquals(mm.getMessage(), "Hello I am Morpheus5828 ");
    }

}
