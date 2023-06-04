package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private boolean isRequired = false;
    private int size = 0;
    private boolean isShape = false;

    Map<String, BaseSchema> schemaMap;

    public MapSchema required() {
        isRequired = true;
        return this;
    }

    public MapSchema sizeof(int length) {
        this.size = length;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        schemaMap = schemas;
        isShape = true;
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        boolean checkRequired = checkRequired(value);
        boolean checkSize = checkSize(value);
        boolean shape = checkShape(value);

        checkShape(value);

        return checkRequired && checkSize && shape;
    }

    private boolean checkRequired(Object value) {
        if (isRequired && value != null) {
            return true;
        } else {
            return !isRequired;
        }
    }

    private boolean checkSize(Object value) {
        Map<String, Object> convertedValue = (Map<String, Object>) value;
        if (size > 0) {
            return convertedValue.size() == size;
        } else {
            return true;
        }
    }

    private boolean checkShape(Object value) {
        if (!isShape) {
            return true;
        }

        boolean isValid = true;
        Map<String, BaseSchema> objectMap = (Map<String, BaseSchema>) value;
        for (Map.Entry entry : objectMap.entrySet()) {
            if (!schemaMap.get(entry.getKey()).isValid(entry.getValue())) {
                isValid = false;
            }
        }

        return isValid;
    }
}
