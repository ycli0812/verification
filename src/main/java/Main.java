import circuit.Circuit;
import com.fasterxml.jackson.core.JsonProcessingException;
import pass.ConnectivityAnalysisPass;
import pass.ImpossibleConnectionPass;
import pass.UselessElementsPass;
import verifier.Verifier;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        Verifier v = new Verifier();
        v.updateTarget("{\"elementSet\":{\"breadboard0\":{\"id\":\"breadboard0\",\"x\":0,\"y\":0,\"type\":\"breadboard\",\"pins\":[],\"features\":[{\"name\":\"column\",\"value\":20},{\"name\":\"extended\",\"value\":true}]},\"resistor0\":{\"id\":\"resistor0\",\"x\":1,\"y\":2,\"type\":\"resistor\",\"pins\":[{\"name\":\"start\",\"x\":1,\"y\":2},{\"name\":\"end\",\"x\":10,\"y\":2}],\"features\":[{\"name\":\"resistance\",\"value\":1,\"unit\":\"om\"},{\"name\":\"tolerance\",\"value\":\"1%\"}]},\"resistor1\":{\"id\":\"resistor1\",\"x\":11,\"y\":4,\"type\":\"resistor\",\"pins\":[{\"name\":\"start\",\"x\":11,\"y\":4},{\"name\":\"end\",\"x\":11,\"y\":8}],\"features\":[{\"name\":\"resistance\",\"value\":1,\"unit\":\"om\"},{\"name\":\"tolerance\",\"value\":\"1%\"}]},\"resistor2\":{\"id\":\"resistor2\",\"x\":24,\"y\":6,\"type\":\"resistor\",\"pins\":[{\"name\":\"start\",\"x\":24,\"y\":6},{\"name\":\"end\",\"x\":32,\"y\":12}],\"features\":[{\"name\":\"resistance\",\"value\":1,\"unit\":\"om\"},{\"name\":\"tolerance\",\"value\":\"1%\"}]}}}");
        UselessElementsPass p1 = new UselessElementsPass();
        ImpossibleConnectionPass p2 = new ImpossibleConnectionPass();
        ConnectivityAnalysisPass p3 = new ConnectivityAnalysisPass();

        v.addPass(p1);
        v.addPass(p2);
        v.addPass(p3);
        v.executeAllPasses();
        v.summaryInfo();
    }
}