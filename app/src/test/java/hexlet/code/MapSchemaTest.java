package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public final class MapSchemaTest {
    private MapSchema schema;

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

    @Test
    public void mapSchemasTest() {
        boolean expectedTrue = true;
        boolean expectedFalse = false;


        Validator v = new Validator();
        schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        Assertions.assertEquals(expectedTrue, schema.isValid(human1)); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        Assertions.assertEquals(expectedTrue, schema.isValid(human2)); // true

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        Assertions.assertEquals(expectedFalse, schema.isValid(human3)); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        Assertions.assertEquals(expectedFalse, schema.isValid(human4)); // false
    }

}
