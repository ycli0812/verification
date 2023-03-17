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

/**
 * This pass finds and removes elements whose one or more pins are not on any breadboards.
 *
 * @author Lyc
 * @version 2023.02.06
 */
public class UselessElementsPass extends Pass {
    public UselessElementsPass() {
        // Call parent constructor
        super();
        // Set id
        this.id = "UselessElementsPass";
        // Set pre-requirements
    }

    /**
     * Find the breadboard in the given circuit.
     *
     * @param circuit Circuit to find breadboard in
     * @return Pointer to the found breadboard. Return null if no breadboards are found
     */
    private Breadboard findBreadboard(Circuit circuit) {
        // TODO: 2023/3/17 find all breadboard instead of the first one
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