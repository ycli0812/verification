package pass;

import circuit.Circuit;
import info.Info;

import java.util.ArrayList;
import java.util.List;

public abstract class Pass {
    protected String id;
    protected ArrayList<Info> output;
    protected ArrayList<String> preRequirements;

    static final int INFO = 0;
    static final int WARNING = 1;
    static final int ERROR = 2;

    public Pass() {
        this.output = new ArrayList<Info>();
        this.preRequirements = new ArrayList<String>();
    }

    protected Boolean checkPreRequirements(ArrayList<String> donePasses) {
        for(String pre : this.preRequirements) {
            if(!donePasses.contains(pre)) return false;
        }
        return true;
    }

    public abstract Boolean execute(Circuit example, Circuit target, ArrayList<String> donePasses) throws Exception;

    public String getId() {
        return id;
    }

    public ArrayList<Info> getOutput() {
        return output;
    }

    public ArrayList<String> getPreRequirements() {
        return preRequirements;
    }

    protected void addOutput(Info info) {
        this.output.add(info);
//        String typeStr;
//        switch (type) {
//            case Pass.INFO: {
//                typeStr = "[Info]";
//                break;
//            }
//            case Pass.WARNING: {
//                typeStr = "[Warning]";
//                break;
//            }
//            case Pass.ERROR: {
//                typeStr = "[Error]";
//                break;
//            }
//            default: {
//                typeStr = "[Unknown]";
//                break;
//            }
//        }
//        this.output.add(typeStr + " " + "In " + this.id + ": " + info);
    }
}
