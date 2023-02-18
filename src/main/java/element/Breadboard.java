package element;

import java.util.ArrayList;

public class Breadboard extends Element{
    private int columns;
    private boolean extended;

    public Breadboard(String id, String originId, ArrayList<Parameter> features, int originX, int originY) {
        super(id, originId, originX, originY, features);
        this.analyseFeatures();
        this.pins = new ArrayList<Pin>();
    }

    @Override
    public String getType() {
        return "breadboard";
    }

    @Override
    public Boolean compareConnection(Element e) {
        return true;
    }

    @Override
    protected void analyseFeatures() {
        for(Parameter p : this.parameters) {
            switch (p.getName()) {
                case "column": {
                    this.columns = Integer.parseInt(p.getValue());
                    break;
                }
                case "extended": {
                    this.extended = Boolean.parseBoolean(p.getValue());
                    break;
                }
                default: break;
            }
        }
    }

    public Boolean isOnBreadboard(int x, int y) {
        int dx = x - this.originX;
        int dy = y - this.originY;
        return ((dy > 0 && dy < 6) || (dy > 6 && dy < 11)) && (dx > 0 && dx < this.columns);
    }
}
