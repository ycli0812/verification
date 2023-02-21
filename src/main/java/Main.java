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
        v.updateTarget("{\"elementSet\":{\"breadboard0\":{\"id\":\"breadboard0\",\"x\":0,\"y\":0,\"type\":\"breadboard\",\"pins\":[],\"features\":[{\"name\":\"column\",\"value\":15},{\"name\":\"extended\",\"value\":true}]},\"resistor0\":{\"id\":\"resistor0\",\"x\":18,\"y\":6,\"type\":\"resistor\",\"pins\":[{\"name\":\"start\",\"x\":18,\"y\":6},{\"name\":\"end\",\"x\":27,\"y\":6}],\"features\":[{\"name\":\"resistance\",\"value\":1,\"unit\":\"om\"},{\"name\":\"tolerance\",\"value\":\"1%\"}]}}}");
        UselessElementsPass p = new UselessElementsPass();
        v.addPass(p);
//        v.addPass(p);
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