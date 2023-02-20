package pass;

import circuit.Circuit;
import element.Breadboard;
import element.Element;
import element.Pin;
import info.Info;
import info.InfoType;

import java.util.ArrayList;

public class UselessElementsPass extends Pass {
    public UselessElementsPass() {
        // Call parent constructor
        super();
        // Set id
        this.id = "UselessElementsPass";
        // Set pre-requirements
    }

    private Breadboard findBreadboard(Circuit circuit) {
        Breadboard bd = null;
        for(Element e : circuit.getElementList()) {
            if(e.getType().equals("breadboard")) {
                bd = (Breadboard) e;
                break;
            }
        }
        return bd;
    }

    @Override
    public Boolean execute(Circuit example, Circuit target, ArrayList<String> donePasses) {
        System.out.println("Executing " + this.id);

        Breadboard bd = this.findBreadboard(target);

        // Find breadboard
        if(bd == null) {
            this.addOutput(new Info("No breadboard.", InfoType.WARNING));
            return false;
        }

        // Check all the elements
        for (Element e : target.getElementList()) {
            for(Pin pin : e.getPins()) {
                if(!bd.isOnBreadboard(pin.getOriginX(), pin.getOriginY())) {
                    this.addOutput(new Info("Element not on breadboard.", InfoType.WARNING, e.getOriginId()));
                }
            }
        }
        // TODO Remove useless elements from circuit
        this.addOutput(new Info("UselessElementsPass completed.", InfoType.INFO));
        return true;
    }
}
