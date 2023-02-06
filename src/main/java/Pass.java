import java.util.List;

public class Pass {
    protected String id;
    protected List<String> output;
    protected List<String> preRequirements;

    public Pass(String id) {}

    public Boolean execute(Circuit example, Circuit target) {
        return true;
    }
}

class UnusedElementPass extends Pass {
    UnusedElementPass(String id) {
        super(id);
    }

    @Override
    public Boolean execute(Circuit example, Circuit target) {
        output.add("Overridden by UnusedElementPass.");
        return true;
    }
}
