package org.falcon.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMessageManagment {
    @Test
    public void testCutMessage() {
        new MessageManagment("PUBLISH author@Manon Hello I am Morpheus5828");

        //Assertions.assertEquals(mm.getMessage(), "Hello I am Morpheus5828 ");
    }

}
