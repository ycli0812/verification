import java.util.List;

public abstract class Element {
    protected String id;
    protected String originId;
    protected List<Pin> pins;
    protected List<Parameter> parameters;
    protected int originX;
    protected int originY;

    public Element(String id, String originId, int originX, int originY, List<Parameter> features) {
        this.id = id;
        this.originId = originId;
        this.originX = originX;
        this.originY = originY;
        this.parameters = features;
    }

    public Pin getPin(String id) {
        // TODO return the pin with given id, return null if can't find it
        return null;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getOriginId() {
        return originId;
    }

    public List<Pin> getPins() {
        return pins;
    }

    public Boolean isConnectedTo(String elementId) {
        // TODO check if element with given id is connected to this one, any pin is ok
        return true;
    }

    public abstract String getType();
    public abstract Boolean compareConnection(Element e); // Should be overridden by extended classes
}

class Resistor extends Element {
    public Resistor(String id, String originId, List<Parameter> features, int originX, int originY) {
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
