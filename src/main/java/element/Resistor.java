package element;

import java.util.ArrayList;
import java.util.List;

public class Resistor extends Element {
    public Resistor(String id, String originId, int originX, int originY, ArrayList<Parameter> features, ArrayList<Pin> pins) {
        super(id, originId, originX, originY, features, pins);
    }

    @Override
    public String getType() {
        return "resistor";
    }

    @Override
    public Boolean compareConnection(Element e) {
        return true;
    }

    @Override
    protected void analyseFeatures() {

    }
}
