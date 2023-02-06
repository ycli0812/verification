package verifier;

import circuit.Circuit;
import pass.Pass;

import java.util.ArrayList;

public class Verifier {
    private Circuit example, target;
    private ArrayList<String> output;
    private ArrayList<Pass> passList;
    private ArrayList<String> donePasses;

    public Verifier(String example, String target) {
        this();
        this.example.load(example);
        this.target.load(target);
    }
    public Verifier() {
        this.example = new Circuit();
        this.target = new Circuit();
        this.output = new ArrayList<String>();
        this.passList = new ArrayList<Pass>();
        this.donePasses = new ArrayList<String>();
    }

    public int updateExample(String jsonStr) {
        this.example.load(jsonStr);
        return 0;
    }

    public int updateTarget(String jsonStr) {
        this.target.load(jsonStr);
        return 0;
    }

    public Boolean executeAllPasses() {
        for(Pass p : this.passList) {
            boolean res = this.execute(p);
            if(!res) return false;
        }
        return true;
    }

    public Boolean addPass(Pass p) {
        ArrayList<String> preRequires = p.getPreRequirements();
        for(String pre : preRequires) {
            boolean included = false;
            for(Pass pAdded : this.passList) {
                if (pre.equals(pAdded.getId())) {
                    included = true;
                    break;
                }
            }
            if(!included) {
                System.out.println("Can not add Pass " + p.getId());
                return false;
            }
        }
        this.passList.add(p);
        return true;
    }

    public Boolean execute(Pass pass) {
        // TODO
        Boolean res;
        try {
            res = pass.execute(this.example, this.target, this.donePasses);
        } catch (Exception e) {
            System.out.println(e.toString());
            res = false;
        }
        return res;
    }
}
