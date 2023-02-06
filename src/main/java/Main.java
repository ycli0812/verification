import circuit.Circuit;
import com.fasterxml.jackson.core.JsonProcessingException;
import pass.UselessElementsPass;
import verifier.Verifier;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        Verifier v = new Verifier();
        v.updateTarget("{\"elementSet\":{\"resistor1\":{\"x\":13,\"y\":3,\"type\":\"resistor\",\"features\":[{\"name\":\"resistance\",\"value\":1,\"unit\":\"kom\"},{\"name\":\"tolerance\",\"value\":\"0.05%\"}]},\"resistor0\":{\"x\":8,\"y\":3,\"type\":\"resistor\",\"features\":[{\"name\":\"resistance\",\"value\":90,\"unit\":\"om\"},{\"name\":\"tolerance\",\"value\":\"2%\"}]},\"capacitor0\":{\"x\":6,\"y\":4,\"type\":\"capacitor\",\"features\":[{\"name\":\"capacity\",\"value\":1,\"unit\":\"f\"}]},\"breadboard0\":{\"x\":0,\"y\":3,\"type\":\"breadboard\",\"features\":[{\"name\":\"column\",\"value\":40},{\"name\":\"extended\",\"value\":true}]}},\"connection\":[]}");
//        Circuit c = new Circuit("{\"elementSet\":{\"resistor1\":{\"x\":13,\"y\":3,\"type\":\"resistor\",\"features\":[{\"name\":\"resistance\",\"value\":1,\"unit\":\"kom\"},{\"name\":\"tolerance\",\"value\":\"0.05%\"}]},\"resistor0\":{\"x\":8,\"y\":3,\"type\":\"resistor\",\"features\":[{\"name\":\"resistance\",\"value\":90,\"unit\":\"om\"},{\"name\":\"tolerance\",\"value\":\"2%\"}]},\"capacitor0\":{\"x\":6,\"y\":4,\"type\":\"capacitor\",\"features\":[{\"name\":\"capacity\",\"value\":1,\"unit\":\"f\"}]},\"breadboard0\":{\"x\":0,\"y\":3,\"type\":\"breadboard\",\"features\":[{\"name\":\"column\",\"value\":40},{\"name\":\"extended\",\"value\":true}]}},\"connection\":[]}");
//        System.out.println(c.getElement("0").getType());
        UselessElementsPass p = new UselessElementsPass();
        v.addPass(p);
        v.executeAllPasses();
    }
}