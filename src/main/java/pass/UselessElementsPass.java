package pass;

import circuit.Circuit;

import java.util.ArrayList;

public class UselessElementsPass extends Pass {
    public UselessElementsPass() {
        // Call parent constructor
        super();
        // Set id
        this.id = "UselessElementsPass";
        // Set pre-requirements
        this.preRequirements.add("CompilePass");
    }

    @Override
    public Boolean execute(Circuit example, Circuit target, ArrayList<String> donePasses) throws Exception {
        System.out.println("Executing " + this.id);
        return true;
    }
}
