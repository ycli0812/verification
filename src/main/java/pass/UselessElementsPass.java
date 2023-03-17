package pass;

import circuit.Circuit;
import element.Breadboard;
import element.Element;
import element.Pin;
import info.Info;
import info.InfoType;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

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
        if(!this.checkPreRequirements(donePasses)) {
            this.addOutput(new Info("Pre-requirements not satisfied.", InfoType.ERROR));
            return false;
        }

        Breadboard bd = this.findBreadboard(target);

        // Find breadboard
        if(bd == null) {
            this.addOutput(new Info("No breadboard.", InfoType.WARNING));
            return false;
        }

        // Check all the elements
        for (int i=0; i< target.getElementList().size(); i++) {
            Element e = target.getElementList().get(i);
            for(Pin pin : e.getPins()) {
                if(!bd.isOnBreadboard(pin.getOriginX(), pin.getOriginY())) {
                    this.addOutput(new Info("Element not on breadboard.", InfoType.WARNING, e.getOriginId()));
                    target.getElementList().remove(i);
                    break;
                }
            }
        }
        return true;
    }
}