package pass;

import circuit.Circuit;

import java.util.ArrayList;
import java.util.List;

public class Pass {
    protected String id;
    protected ArrayList<String> output;
    protected ArrayList<String> preRequirements;

    public Pass(String id) {}

    public Boolean execute(Circuit example, Circuit target) {
        return true;
    }
}
