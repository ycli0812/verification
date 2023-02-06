package element;

import java.util.ArrayList;

public class Breadboard extends Element{
    public Breadboard(String id, String originId, ArrayList<Parameter> features, int originX, int originY) {
        super(id, originId, originX, originY, features);
        // TODO initialize pins
    }

    @Override
    public String getType() {
        return "breadboard";
    }

    @Override
    public Boolean compareConnection(Element e) {
        return true;
    }
}
