package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private boolean isRequired = false;
    private int size = 0;

    public MapSchema required() {
        isRequired = true;
        return this;
    }

    public MapSchema sizeof(int length) {
        this.size = length;
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        boolean checkRequired = checkRequired(value);
        boolean checkSize = checkSize(value);

        return checkRequired && checkSize;
    }

    private boolean checkRequired(Object value) {
        if (isRequired && value != null) {
            return true;
        } else {
            return !isRequired;
        }
    }

    private boolean checkSize(Object value) {
        Map<String, String> convertedValue = (Map<String, String>) value;
        if (size > 0) {
            return convertedValue.size() == size;
        } else {
            return true;
        }
    }
}
