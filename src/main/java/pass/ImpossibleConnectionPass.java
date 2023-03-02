package pass;

import circuit.Circuit;
import element.Element;
import element.Pin;
import info.Info;
import info.InfoType;

import java.util.*;

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
