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
        boolean expect = true;
        Assertions.assertEquals(expect, stringSchema.isValid(""));
        Assertions.assertEquals(expect, stringSchema.isValid(null));
    }

    @Test
    public void stringSchemaRequiredTest() {
        boolean expect = false;
        stringSchema.required();
        Assertions.assertEquals(expect, stringSchema.isValid(""));
        Assertions.assertEquals(expect, stringSchema.isValid(null));
        Assertions.assertEquals(expect, stringSchema.isValid(5));
    }

    @Test
    public void stringSchemaEmptyContainsTest() {
        boolean expect = false;
        String testString = "what does the fox say";
        stringSchema.required();
        stringSchema.contains("wh");
        Assertions.assertEquals(expect, stringSchema.isValid(""));
        Assertions.assertEquals(expect, stringSchema.isValid(null));
    }

    @Test
    public void stringSchemaMinLength() {
        boolean expectTrue = true;
        boolean expectFalse = false;

        stringSchema.required();
        stringSchema.minLength(7);
        stringSchema.contains("whatthe");

        Assertions.assertEquals(expectFalse, stringSchema.isValid("what does the fox say"));
        Assertions.assertEquals(expectFalse, stringSchema.isValid("hexlet"));
    }
}
