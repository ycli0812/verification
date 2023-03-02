package circuit;

import java.io.IOException;
import java.sql.Array;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import element.*;

public class Circuit {
    private final ArrayList<Element> elementList;

    public Circuit() {
        this.elementList = new ArrayList<Element>();
    }

    public Circuit(String jsonStr) {
        elementList = new ArrayList<Element>();
        this.load(jsonStr);
    }

    public void load(String jsonStr) {
        ObjectMapper mapper = new ObjectMapper();
        // Map elementSet field to JsonNode instance
        JsonNode elements;
        try {
            elements = mapper.readValue(jsonStr, JsonNode.class).get("elementSet");
        } catch (IOException e) {
            System.out.println(e.toString());
            return;
        }
        Iterator<String> it = elements.fieldNames();
        int eCount = 0; // Counter for elements
        while(it.hasNext()) {
            // Get id, type, and features
            String id = it.next();
            JsonNode element = elements.get(id);
            String type = element.get("type").asText();
            int originX = element.get("x").asInt();
            int originY = element.get("y").asInt();

            // get pins
            JsonNode pinsNode = element.get("pins");
            ArrayList<Pin> pins = new ArrayList<Pin>();
            for(JsonNode pinNode : pinsNode) {
                pins.add(new Pin(pinNode.get("x").asInt(), pinNode.get("y").asInt(),pinNode.get("name").asText(), id));
            }

            // get features
            JsonNode featuresNode = element.get("features");
            ArrayList<Parameter> features = new ArrayList<Parameter>();
            for(JsonNode featNode : featuresNode) {
                String name = featNode.get("name").asText();
                String value = featNode.get("value").asText();
                String unit = featNode.get("unit") == null ? "" : featNode.get("unit").asToken().asString();
                features.add(new Parameter(name, value, unit));
            }

            // Add element instance to elementList
            // Do not instantiate Element class since it is an abstract class
            switch (type) {
                case "resistor": {
                    this.elementList.add(new Resistor(String.valueOf(eCount), id, originX, originY, features, pins));
                    break;
                }
                case "breadboard": {
                    this.elementList.add(new Breadboard(String.valueOf(eCount), id, originX, originY, features, pins));
                    break;
                }
                default: break;
            }
        }
    }

    public ArrayList<Element> getElementList() {
        return elementList;
    }

    public Element getElement(String id) {
        for(Element e : elementList) {
            if(Objects.equals(e.getId(), id)) return e;
        }
        return null;
    }
}