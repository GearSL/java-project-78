package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MapSchemaTest {
    MapSchema schema;

    @BeforeEach
    public void init() {
        Validator v = new Validator();
        schema = v.map();
    }

    @Test
    public void mapSchemaWithoutConfiguration() {
        boolean expected = true;
        Assertions.assertEquals(expected, schema.isValid(null));
    }

    @Test
    public void mapSchemaRequired() {
        boolean expectedTrue = true;
        boolean expectedFalse = false;
        schema.required();
        Assertions.assertEquals(expectedFalse, schema.isValid(null));
        Assertions.assertEquals(expectedTrue, schema.isValid(new HashMap()));
    }

    @Test
    public void mapSchemaSizeOf() {
        boolean expectedTrue = true;
        boolean expectedFalse = false;
        schema.required();
        Map<String, String> data = new HashMap<>();

        data.put("key1", "value1");
        Assertions.assertEquals(expectedTrue, schema.isValid(data));

        schema.sizeof(2);
        Assertions.assertEquals(expectedFalse, schema.isValid(data));

        data.put("key2", "value2");
        Assertions.assertEquals(expectedTrue, schema.isValid(data));
    }
}
