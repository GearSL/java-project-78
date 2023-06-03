package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;

import java.util.HashMap;
import java.util.Map;

class App {
    public static void main(String[] args) {
        Validator v = new Validator();

        MapSchema schema = v.map();

// shape позволяет описывать валидацию для значений объекта Map по ключам
        Map<String, BaseSchema> schemas = new HashMap<>();

// Определяем схемы валидации для значений свойств "name" и "age"
// Имя должно быть строкой, обязательно для заполнения
        schemas.put("name", v.string().required());
// Возраст должен быть положительным числом
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

// Проверяем объекты
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        System.out.println("TRUE: " + schema.isValid(human1)); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        System.out.println("TRUE: " + schema.isValid(human2)); // true

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        System.out.println("FALSE: " + schema.isValid(human3)); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        System.out.println("FALSE: " + schema.isValid(human4)); // false
    }
}
