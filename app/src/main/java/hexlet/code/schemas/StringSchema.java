package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private boolean isRequired = false;
    private int minLength = 0;
    String substring = "";

    public StringSchema required() {
        this.isRequired = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String value) {
        this.substring = value;
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        return checkRequired(value) && checkMinLength(value) && checkSubstring(value);
    }

    private boolean checkRequired(Object value) {
        if (isRequired && !(value instanceof String)) {
            return false;
        } else if (isRequired && value.equals("")) {
            return false;
        }
        return true;
    }

    private boolean checkMinLength(Object value) {
        if (!(value instanceof String) && minLength == 0) {
            return true;
        } else if (value.toString().length() < this.minLength) {
            return false;
        }
        return true;
    }

    private boolean checkSubstring(Object value) {
        if (substring.equals("")) {
            return true;
        } else if (value == null) {
            return false;
        }
        return value.toString().contains(substring);
    }
}
