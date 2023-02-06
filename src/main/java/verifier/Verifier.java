package verifier;

import pass.Pass;

import java.util.List;

public class Verifier {
//    private Circuit example, target;
    private List<String> output;
    private List<Pass> passList;
    private List<String> donePasses;

    public Verifier(String example, String target) {}
    public Verifier() {}

    public int updateExample(String jsonStr) {
        return 0;
    }

    public int updateTarget(String jsonStr) {
        return 0;
    }

//    public int addPass(Pass p) {
//        return 0;
//    }
//
//    public int delPass(int index) {
//        return 0;
//    }
//
//    public int delPass(String passId) {
//        return 0;
//    }

    public Boolean verify() {
        return true;
    }

//    public Boolean execute(Pass pass) {
//        // TODO
//        return true;
//    }
}
