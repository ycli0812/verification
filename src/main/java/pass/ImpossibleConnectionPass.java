package pass;

import circuit.Circuit;
import element.Element;
import element.Pin;
import info.Info;
import info.InfoType;

import java.util.*;

/**
 * This pass checks if there are connections that can not be implemented in the real world in the circuit.
 *
 * <p>Here is the checking list:</p>
 *
 * <ul>
 *     <li>Multiple pins in one holes</li>
 *     <li>Still adding more...</li>
 * </ul>
 *
 * @author Lyc
 * @version 2023.02.18
 */
public class ImpossibleConnectionPass extends Pass{
    public ImpossibleConnectionPass() {
        super();
        this.id = "ImpossibleConnectionPass";
    }

    @Override
    public Boolean execute(Circuit example, Circuit target, ArrayList<String> donePasses) throws Exception {
        if(!this.checkPreRequirements(donePasses)) {
            this.addOutput(new Info("Pre-requirements not satisfied.", InfoType.ERROR));
            return false;
        }

        boolean res = true;

        HashMap<Integer, String> pinOwner = new HashMap<Integer, String>();
        for(Element e : target.getElementList()) {
            for(Pin pin : e.getPins()) {
                int hashCode = Objects.hash(pin.getOriginX(), pin.getOriginY());
                if(pinOwner.get(hashCode) == null) {
                    pinOwner.put(hashCode, e.getOriginId());
                } else {
                    this.output.add(new Info("Multiple pins in one hole.", InfoType.ERROR, e.getOriginId(), pinOwner.get(hashCode)));
                    res = false;
                }
            }
        }

        return res;
    }
}
