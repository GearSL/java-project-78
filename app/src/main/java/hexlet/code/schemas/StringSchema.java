package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class StringSchema extends BaseSchema {
    private boolean isValid = true;
    private boolean required = false;
    private int minLength = 0;
    List<String> substrings = new ArrayList<>();

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        substrings.add(substring);
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        checkRequired(value);
        checkMinLength(value);
        checkSubstring(value);

        return isValid;
    }

    private void checkRequired(Object value) {
        if (required) {
            if (value == null) {
                isValid = false;
            } else if (value.equals("")) {
                isValid = false;
            } else if (!(value instanceof String)) {
                isValid = false;
            }
        }
    }

    private void checkMinLength(Object value) {
        if (!(value instanceof String)) {
            return;
        }
        if (value.toString().length() < this.minLength) {
            isValid = false;
        }
    }

    private void checkSubstring(Object value) {
        if (!(value instanceof String)) {
            return;
        }
        for (String substring : substrings) {
            if (!value.toString().contains(substring)) {
                isValid = false;
                return;
            }
        }
    }
}
