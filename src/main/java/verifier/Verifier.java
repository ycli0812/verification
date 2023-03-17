package verifier;

import circuit.Circuit;
import info.Info;
import info.InfoType;
import pass.Pass;

import java.util.ArrayList;

public class Verifier {
    private Circuit example, target;
    private ArrayList<Info> output;
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
        this.output = new ArrayList<Info>();
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
            this.output.add(new Info("Execute " + p.getId() + ".", InfoType.INFO));
            if(!this.execute(p)) {
                this.output.add(new Info("Verification aborted.", InfoType.ERROR));
                return false;
            } else {
                this.output.add(new Info("Pass " + p.getId() + " completed.", InfoType.INFO));
            }
        }
        this.output.add(new Info("Verification done.", InfoType.INFO));
        return true;
    }

    public Boolean addPass(Pass p) {
        ArrayList<String> preRequires = p.getPreRequirements();
        // Check if all pre-requirements are satisfied
        for(String pre : preRequires) {
            boolean included = false;
            for(Pass pAdded : this.passList) {
                if (pre.equals(pAdded.getId())) {
                    included = true;
                    break;
                }
            }
            if(!included) {
                System.out.println("Can not add Pass " + p.getId() + ", because some pre-requirements are not satisfied.");
                return false;
            }
        }
        // Check if pass has been added
        for(Pass pAdded : this.passList) {
            if(pAdded.getId().equals(p.getId())) {
                System.out.println("Can not add Pass " + p.getId() + ", this pass has already been added.");
                return false;
            }
        }
        this.passList.add(p);
        return true;
    }

    public Boolean execute(Pass pass) {
        Boolean res;
        try {
            res = pass.execute(this.example, this.target, this.donePasses);
            if(res) this.donePasses.add(pass.getId());
        } catch (Exception e) {
            res = false;
        } finally {
            this.output.addAll(pass.getOutput());
        }
//        res = pass.execute(this.example, this.target, this.donePasses);
//        if(res) this.donePasses.add(pass.getId());
        return res;
    }

    public void summaryInfo() {
        for(Info info : this.output) {
            System.out.println(info.toString());
        }

//        for(Pass p : this.passList) {
//            for(Info info : p.getOutput()) {
//                System.out.println(info.toString());
//            }
//        }
    }
}
