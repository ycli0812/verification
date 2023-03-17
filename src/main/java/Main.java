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
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        Verifier v = new Verifier();
        v.updateTarget("{\"elementSet\":{\"breadboard0\":{\"id\":\"breadboard0\",\"x\":0,\"y\":1,\"type\":\"breadboard\",\"pins\":[],\"features\":[{\"name\":\"column\",\"value\":20},{\"name\":\"extended\",\"value\":false}]},\"resistor0\":{\"id\":\"resistor0\",\"x\":1,\"y\":5,\"type\":\"resistor\",\"pins\":[{\"name\":\"start\",\"x\":1,\"y\":5},{\"name\":\"end\",\"x\":4,\"y\":5}],\"features\":[{\"name\":\"resistance\",\"value\":1,\"unit\":\"om\"},{\"name\":\"tolerance\",\"value\":\"1%\"}]},\"resistor1\":{\"id\":\"resistor1\",\"x\":6,\"y\":5,\"type\":\"resistor\",\"pins\":[{\"name\":\"start\",\"x\":6,\"y\":5},{\"name\":\"end\",\"x\":9,\"y\":5}],\"features\":[{\"name\":\"resistance\",\"value\":1,\"unit\":\"om\"},{\"name\":\"tolerance\",\"value\":\"1%\"}]},\"resistor2\":{\"id\":\"resistor2\",\"x\":11,\"y\":5,\"type\":\"resistor\",\"pins\":[{\"name\":\"start\",\"x\":11,\"y\":5},{\"name\":\"end\",\"x\":15,\"y\":5}],\"features\":[{\"name\":\"resistance\",\"value\":1,\"unit\":\"om\"},{\"name\":\"tolerance\",\"value\":\"1%\"}]},\"wire1\":{\"id\":\"breadboard0\",\"x\":4,\"y\":6,\"type\":\"wire\",\"pins\":[],\"features\":[{\"name\":\"x1\",\"value\":4},{\"name\":\"x2\",\"value\":6},{\"name\":\"y1\",\"value\":6},{\"name\":\"y2\",\"value\":6},{\"name\":\"color\",\"value\":\"#000000\"}]},\"wire2\":{\"id\":\"breadboard0\",\"x\":9,\"y\":8,\"type\":\"wire\",\"pins\":[],\"features\":[{\"name\":\"x1\",\"value\":9},{\"name\":\"x2\",\"value\":11},{\"name\":\"y1\",\"value\":8},{\"name\":\"y2\",\"value\":9},{\"name\":\"color\",\"value\":\"#000000\"}]},\"wire3\":{\"id\":\"breadboard0\",\"x\":15,\"y\":9,\"type\":\"wire\",\"pins\":[],\"features\":[{\"name\":\"x1\",\"value\":15},{\"name\":\"x2\",\"value\":18},{\"name\":\"y1\",\"value\":9},{\"name\":\"y2\",\"value\":5},{\"name\":\"color\",\"value\":\"#000000\"}]}}}");
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