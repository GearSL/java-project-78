package hexlet.code;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class AppTest {
    @Test
    public void testApp() {
        Assertions.assertEquals("String for test", App.testMethod());
    }
}
