package pass;

import circuit.Circuit;

import java.util.ArrayList;
import java.util.List;

public abstract class Pass {
    protected String id;
    protected ArrayList<String> output;
    protected ArrayList<String> preRequirements;

    public Pass() {
        this.output = new ArrayList<String>();
        this.preRequirements = new ArrayList<String>();
    }

    protected Boolean checkPreRequirements(ArrayList<String> donePasses) {
        for(String pre : this.preRequirements) {
            if(!donePasses.contains(pre)) return false;
        }
        return true;
    }

    public abstract Boolean execute(Circuit example, Circuit target, ArrayList<String> donePasses) throws Exception;

    public String getId() {
        return id;
    }

    public ArrayList<String> getOutput() {
        return output;
    }

    public ArrayList<String> getPreRequirements() {
        return preRequirements;
    }
}
