package pass;

import circuit.Circuit;
import element.Breadboard;
import element.Element;
import element.Pin;

import java.util.ArrayList;

public class UselessElementsPass extends Pass {
    public UselessElementsPass() {
        // Call parent constructor
        super();
        // Set id
        this.id = "UselessElementsPass";
        // Set pre-requirements
//        this.preRequirements.add("CompilePass");
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
    public Boolean execute(Circuit example, Circuit target, ArrayList<String> donePasses) throws Exception {
        System.out.println("Executing " + this.id);
        Breadboard bd = this.findBreadboard(target);
        // Find breadboard
        if(bd == null) {
            this.addOutput("No breadboard found.", Pass.ERROR);
            return false;
        }
        // Check all the elements
        for (Element e : target.getElementList()) {
            for(Pin pin : e.getPins()) {
                if(!bd.isOnBreadboard(pin.getOriginX(), pin.getOriginY())) {
                    this.addOutput(String.format("Pin (%d, %d) of element %s is not on breadboard (%d, %d).", pin.getOriginX(), pin.getOriginY(), e.getOriginId(), bd.getOriginX(), bd.getOriginY()) , Pass.WARNING);
                }
            }
        }
        this.addOutput("Done.", Pass.INFO);
        return true;
    }
}
