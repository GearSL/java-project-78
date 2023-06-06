package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        addCheck(
                "required",
                Objects::nonNull
        );
        return this;
    }

    public MapSchema sizeof(int length) {
        addCheck(
                "sizeOf",
                value -> {
                    Map<String, Object> convertedValue = (Map<String, Object>) value;
                    if (length > 0) {
                        return convertedValue.size() == length;
                    } else {
                        return true;
                    }
                }
        );
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addCheck(
                "shape",
                value -> {
                    boolean isValid = true;
                    Map<String, BaseSchema> objectMap = (Map<String, BaseSchema>) value;
                    for (Map.Entry entry : objectMap.entrySet()) {
                        if (!schemas.get(entry.getKey()).isValid(entry.getValue())) {
                            isValid = false;
                        }
                    }
                    return isValid;
                }
        );
        return this;
    }
}
