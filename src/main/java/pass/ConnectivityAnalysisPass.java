package pass;

import circuit.Circuit;
import element.Breadboard;
import element.Element;
import info.Info;
import info.InfoType;

import java.util.ArrayList;

public class ConnectivityAnalysisPass extends Pass {
    public ConnectivityAnalysisPass() {
        super();
        this.id = "ConnectivityAnalysisPass";
        this.preRequirements.add("UselessElementsPass");
        this.preRequirements.add("ImpossibleConnectionPass");
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
        if(!this.checkPreRequirements(donePasses)) {
            this.addOutput(new Info("Pre-requirements not satisfied.", InfoType.ERROR));
            return false;
        }
        return true;
    }
}
