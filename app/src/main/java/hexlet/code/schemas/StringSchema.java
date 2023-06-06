package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        addCheck(
                "required",
                value -> {
                    if (!(value instanceof String)) {
                        return false;
                    } else {
                        return !value.equals("");
                    }
                }
        );
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck(
                "minLength",
                value -> {
                    if (!(value instanceof String) && length == 0) {
                        return true;
                    } else {
                        return value.toString().length() >= length;
                    }
                }
        );
        return this;
    }

    public StringSchema contains(String string) {
        addCheck(
                "contains",
                value -> {
                    if (string.equals("")) {
                        return true;
                    } else if (value == null) {
                        return false;
                    }
                    return value.toString().contains(string);
                }
        );
        return this;
    }
}
