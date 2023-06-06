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
    public void numberSchemaWithoutConfigurationTest() {
        Assertions.assertTrue(schema.isValid(null));
        Assertions.assertTrue(schema.isValid(5));
        Assertions.assertTrue(schema.isValid(-5));
    }

    @Test
    public void numberSchemaPositiveTest() {
        schema.positive();

        Assertions.assertTrue(schema.isValid(null));
        Assertions.assertTrue(schema.isValid(5));
        Assertions.assertFalse(schema.isValid(-5));
    }

    @Test
    public void numberSchemaRequiredTest() {
        schema.positive();
        schema.required();

        Assertions.assertTrue(schema.isValid(10));
        Assertions.assertFalse(schema.isValid(null));
        Assertions.assertFalse(schema.isValid("5"));
        Assertions.assertFalse(schema.isValid(-10));
        //  Ноль — не положительное число
        Assertions.assertFalse(schema.isValid(0));
    }

    @Test
    public void numberSchemaRangeTest() {
        schema.positive();
        schema.required();
        schema.range(5, 10);

        Assertions.assertTrue(schema.isValid(5));
        Assertions.assertTrue(schema.isValid(10));
        Assertions.assertFalse(schema.isValid(4));
        Assertions.assertFalse(schema.isValid(11));
    }
}
