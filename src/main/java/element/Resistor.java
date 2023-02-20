package element;

import java.util.ArrayList;
import java.util.List;

public class Resistor extends Element {
    public Resistor(String id, String originId, int originX, int originY, ArrayList<Parameter> features, ArrayList<Pin> pins) {
        super(id, originId, originX, originY, features, pins);
        this.pins = new ArrayList<Pin>();
        this.pins.add(new Pin(originX, originY + 1, "left_pin", id));
        this.pins.add(new Pin(originX + 4, originY + 1, "right_pin", id));
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
