package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class NumberSchemaTest {
    private NumberSchema schema;

    @BeforeEach
    public void init() {
        Validator v = new Validator();
        schema = v.number();
    }

    @Test
    public void numberSchemaWithoutConfiguration() {
        boolean expected = true;
        Assertions.assertEquals(expected, schema.isValid(null));
        Assertions.assertEquals(expected, schema.isValid(5));
        schema.positive();
        Assertions.assertEquals(expected, schema.isValid(null));

    }

    @Test
    public void numberSchemaPositive() {
        boolean expectedTrue = true;
        boolean expectedFalse = false;
        schema.positive();
        Assertions.assertEquals(expectedTrue, schema.isValid(null));
        Assertions.assertEquals(expectedTrue, schema.isValid(5));
        Assertions.assertEquals(expectedFalse, schema.isValid(-5));
    }
}
