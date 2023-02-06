package element;

public class Parameter {
    private String name;
    private String value;
    private String unit;

    public Parameter() {}

    public Parameter(String name, String value, String unit) {
        this.name = name;
        this.value = value;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Boolean compareWith(Parameter p) {
        // TODO compare tow Parameters, return true if name, value and unit are all the same
        return true;
    }
}