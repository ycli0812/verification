package pass;

import circuit.Circuit;

import java.util.ArrayList;

public class ImpossibleConnectionPass extends Pass{
    public ImpossibleConnectionPass() {
        super();
    }

    @Override
    public Boolean execute(Circuit example, Circuit target, ArrayList<String> donePasses) throws Exception {
        return null;
    }
}
