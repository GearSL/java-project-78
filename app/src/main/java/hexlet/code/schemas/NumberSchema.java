package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {


    public NumberSchema required() {
        addCheck(
                "required",
                Objects::nonNull
        );
        return this;
    }

    public NumberSchema positive() {
        addCheck(
                "positive",
                value -> {
                    if (value == null) {
                        return true;
                    } else if (value instanceof Integer) {
                        return (Integer) value > 0;
                    }
                    return false;
                }
        );
        return this;
    }

    public NumberSchema range(int from, int to) {
        addCheck(
                "range",
                value -> {
                    if (!(value instanceof Integer)) {
                        return true;
                    } else if (from == 0 && to == 0) {
                        return true;
                    }
                    return (Integer) value >= from && (Integer) value <= to;
                }
        );
        return this;
    }
}
