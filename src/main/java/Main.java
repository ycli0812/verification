import circuit.Circuit;
import com.fasterxml.jackson.core.JsonProcessingException;
import pass.UselessElementsPass;
import verifier.Verifier;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        Verifier v = new Verifier();
        v.updateTarget("{\"elementSet\":{\"resistor1\":{\"x\":13,\"y\":7,\"type\":\"resistor\",\"features\":[{\"name\":\"resistance\",\"value\":1,\"unit\":\"kom\"},{\"name\":\"tolerance\",\"value\":\"0.05%\"}]},\"resistor0\":{\"x\":1,\"y\":5,\"type\":\"resistor\",\"features\":[{\"name\":\"resistance\",\"value\":90,\"unit\":\"om\"},{\"name\":\"tolerance\",\"value\":\"2%\"}]},\"capacitor0\":{\"x\":6,\"y\":4,\"type\":\"capacitor\",\"features\":[{\"name\":\"capacity\",\"value\":1,\"unit\":\"f\"}]},\"breadboard0\":{\"x\":4,\"y\":5,\"type\":\"breadboard\",\"features\":[{\"name\":\"column\",\"value\":40},{\"name\":\"extended\",\"value\":true}]}},\"connection\":[]}");
        UselessElementsPass p = new UselessElementsPass();
        v.addPass(p);
        v.addPass(p);
        v.executeAllPasses();
        v.summaryInfo();

//        File f = new File("./test.txt");
//        FileWriter writer;
//        try {
//            writer = new FileWriter(f);
//            writer.write("content");
//            writer.close();
//        } catch (IOException e) {
//            System.out.println(e.toString());
//        }
    }
}