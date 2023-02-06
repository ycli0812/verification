package element;

import java.util.ArrayList;
import java.util.List;

public class Pin {
    private String id;
    private int originX;
    private int originY;
    private String elementId;
    private ArrayList<Pin> connections;

    public Pin(String id) {
        this.id = id;
    }

    public Pin(int originX, int originY, String id, String elementId) {
        this.id = id;
        this.originX = originX;
        this.originY = originY;
        this.elementId = elementId;
    }

    public void connect(Pin p) {
        connections.add(p);
    }

    public String getId() {
        return id;
    }

    public ArrayList<Pin> getConnections() {
        return connections;
    }

    public int getOriginX() {
        return originX;
    }

    public int getOriginY() {
        return originY;
    }

    public String getElementId() {
        return elementId;
    }
}
