package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class StringSchemaTest {
    private StringSchema stringSchema;

    @BeforeEach
    public void init() {
        Validator v = new Validator();
        stringSchema = v.string();
    }

    @Test
    public void stringSchemaWithoutConfigurationTest() {
        Assertions.assertTrue(stringSchema.isValid(""));
        Assertions.assertTrue(stringSchema.isValid(null));
    }

    @Test
    public void stringSchemaRequiredTest() {
        stringSchema.required();

        Assertions.assertFalse(stringSchema.isValid(""));
        Assertions.assertFalse(stringSchema.isValid(null));
        Assertions.assertFalse(stringSchema.isValid(5));
    }

    @Test
    public void stringSchemaContainsTest() {
        stringSchema.required();
        stringSchema.contains("wh");

        Assertions.assertFalse(stringSchema.isValid(""));
        Assertions.assertFalse(stringSchema.isValid(null));
        Assertions.assertTrue(stringSchema.isValid("what does the fox say"));
    }

    @Test
    public void stringSchemaMinLengthTest() {
        stringSchema.required();
        stringSchema.minLength(7);
        stringSchema.contains("whatthe");

        Assertions.assertFalse(stringSchema.isValid("what does the fox say"));
        Assertions.assertFalse(stringSchema.isValid("hexlet"));
        Assertions.assertTrue(stringSchema.isValid("whatthe smth"));
    }
}
