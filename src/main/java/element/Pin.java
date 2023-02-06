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

    public void connect(Pin p) {
        connections.add(p);
    }

    public String getId() {
        return id;
    }

    public ArrayList<Pin> getConnections() {
        return connections;
    }
}
