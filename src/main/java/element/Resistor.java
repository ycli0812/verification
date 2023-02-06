package element;

import java.util.ArrayList;
import java.util.List;

public class Resistor extends Element {
    public Resistor(String id, String originId, ArrayList<Parameter> features, int originX, int originY) {
        super(id, originId, originX, originY, features);
        // TODO initialize pins
    }

    @Override
    public String getType() {
        return "resistor";
    }

    @Override
    public Boolean compareConnection(Element e) {
        return true;
    }
}
