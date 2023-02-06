import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Circuit {
    private List<Element> elementList;

    public Circuit(String jsonStr) {
        try {
            this.load(jsonStr);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void load(String jsonStr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Map elementSet field to JsonNode instance
        JsonNode elements = mapper.readValue(jsonStr, JsonNode.class).get("elementSet");
        Iterator<String> it = elements.fieldNames();
        int eCount = 0; // Counter for elements
        while(it.hasNext()) {
            // Get id, type, and features
            String id = it.next();
            JsonNode element = elements.get(id);
            String type = element.get("type").asText();
            int originX = element.get("x").asInt();
            int originY = element.get("y").asInt();
            List<Parameter> features = mapper.readerFor(new TypeReference<List<Parameter>>() {}).readValue(element.get("features"));
            // Add element instance to elementList
            // Do not instantiate Element since it is an abstract class
            switch (type) {
                case "resistor": {
                    System.out.println("find a resistor:" + features.toString());
                    elementList.add(new Resistor(String.valueOf(eCount), id, features, originX, originY));
                    break;
                }
                default: break;
            }
        }
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public Element getElement(String id) {
        // TODO return element with id given, return null if id does not exist

        return null;
    }
}

class Pin {
    private String id;
    private int originX;
    private int originY;
    private String elementId;
    private List<Pin> connections;

    public Pin(String id) {
        this.id = id;
    }

    public void connect(Pin p) {
        connections.add(p);
    }

    public String getId() {
        return id;
    }

    public List<Pin> getConnections() {
        return connections;
    }
}

class Parameter {
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