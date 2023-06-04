package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    private boolean isRequired = false;
    private boolean isPositive = false;
    private int begin;
    private int end;

    public NumberSchema required() {
        this.isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        this.isPositive = true;
        return this;
    }

    public NumberSchema range(int from, int to) {
        //Mb need check for begin and end that end will be greater than begin
        this.begin = from;
        this.end = to;
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        boolean required = checkRequired(value);
        boolean positive = checkPositive(value);
        boolean range = checkRange(value);

        return checkRequired(value) && checkPositive(value) && checkRange(value);
    }

    private boolean checkRequired(Object value) {
        if (!isRequired) {
            return true;
        } else {
            return value != null;
        }
    }

    private boolean checkPositive(Object value) {
        if (!isRequired && value == null) {
            return true;
        } else if (value instanceof Integer && isPositive) {
            return (Integer) value > 0;
        } else if (value instanceof Integer) {
            return true;
        }
        return false;
    }

    private boolean checkRange(Object value) {
        if (!(value instanceof Integer)) {
            return true;
        } else if (begin == 0 && end == 0) {
            return true;
        }
        return (Integer) value >= this.begin && (Integer) value <= this.end;
    }
}
