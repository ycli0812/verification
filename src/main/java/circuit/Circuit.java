package circuit;

import java.io.IOException;
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
            ArrayList<Parameter> features;

            JsonNode pins = element.get("pins");
            for(JsonNode pinNode : pins) {
                System.out.println(pinNode);
            }
            try {
                features = mapper.readerFor(new TypeReference<List<Parameter>>() {}).readValue(element.get("features"));
//                pins = mapper.readerFor(new TypeReference<ArrayList<Map<String, String>>>() {}).readValue(element.get("pins"));
            } catch (IOException e) {
                System.out.println(e.toString());
                continue;
            }
            // Add element instance to elementList
            // Do not instantiate Element since it is an abstract class
            switch (type) {
                case "resistor": {
                    this.elementList.add(new Resistor(String.valueOf(eCount), id, features, originX, originY));
                    break;
                }
                case "breadboard": {
                    this.elementList.add(new Breadboard(String.valueOf(eCount), id, features, originX, originY));
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