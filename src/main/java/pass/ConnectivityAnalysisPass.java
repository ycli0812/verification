package pass;

import circuit.Circuit;
import element.Breadboard;
import element.Element;
import element.Pin;
import element.Wire;
import info.Info;
import info.InfoType;

import java.util.*;

public class ConnectivityAnalysisPass extends Pass {

    public ConnectivityAnalysisPass() {
        super();
        this.id = "ConnectivityAnalysisPass";
        this.preRequirements.add("UselessElementsPass");
        this.preRequirements.add("ImpossibleConnectionPass");
    }

    private ArrayList<Breadboard> findBreadboards(Circuit circuit) {
        ArrayList<Breadboard> breadboards = new ArrayList<Breadboard>();
        for(Element e : circuit.getElementList()) {
            if(e.getType().equals("breadboard")) {
                breadboards.add((Breadboard) e);
            }
        }
        return breadboards;
    }

    @Override
    public Boolean execute(Circuit example, Circuit target, ArrayList<String> donePasses) throws Exception {
        if(!this.checkPreRequirements(donePasses)) {
            this.addOutput(new Info("Pre-requirements not satisfied.", InfoType.ERROR));
            return false;
        }

        Analyser a = new Analyser(target);
        a.doAnalyse();
        a.printResult();
        return true;
    }
}

class Analyser {
    private Circuit circuit;
    private ArrayList<Breadboard> breadboards;
    private ArrayList<Element> elements;
    private ArrayList<Wire> wires;
    private HashMap<Integer, ArrayList<Pin>> groupMap;

    public Analyser(Circuit circuit) {
        this.circuit = circuit;
        this.breadboards = new ArrayList<>();
        this.elements = new ArrayList<>();
        this.wires = new ArrayList<>();
        this.groupMap = new HashMap<Integer, ArrayList<Pin>>();
        this.classifyElements();
    }

    public void doAnalyse() {
        this.groupPins();
        this.analysisWires();
        this.connectPins();
    }

    public void printResult() {
        for (Map.Entry<Integer, ArrayList<Pin>> entry : this.groupMap.entrySet()) {
            ArrayList<Pin> group = entry.getValue();
            int key = entry.getKey();
            System.out.print(key + ": ");
            for(Pin p : group) {
                System.out.print("("+p.getElementId()+")"+p.getId()+" ");
            }
            System.out.println();
        }

        for(Element e : this.elements) {
            System.out.println("[" + e.getOriginId() + "]");
            for(Pin p : e.getPins()) {
                System.out.print(p.getId() + ": ");
                for(Pin pc : p.getConnections()) {
                    System.out.print("(" + pc.getElementId() + ")" + pc.getId() + " ");
                }
                System.out.println();
            }
        }
    }

    private void groupPins() {
        for(Element e : this.elements) {
            for(Pin p : e.getPins()) {
                for(Breadboard bd : this.breadboards) {
//                    if(bd.isOnBreadboard(p.getOriginX(), p.getOriginY())) {
                        int hash = this.getHashCode(p.getOriginX(), p.getOriginY());
                        if(hash == -1) continue;
                        if(this.groupMap.get(hash) == null) {
                            System.out.println("new hash code: " + hash);
                            ArrayList<Pin> newList = new ArrayList<Pin>();
                            newList.add(p);
                            this.groupMap.put(hash, newList);
                        } else {
                            this.groupMap.get(hash).add(p);
                        }
                        break;
//                    }
                }
            }
        }
    }

    private void analysisWires() {
        for(Wire w : this.wires) {
            int x1 = w.getX1();
            int x2 = w.getX2();
            int y1 = w.getY1();
            int y2 = w.getY2();
            // get groups of both ends of the wire
            int hashCode1 = this.getHashCode(x1, y1);
            ArrayList<Pin> g1 = this.groupMap.get(hashCode1);
            int hashCode2 = this.getHashCode(x2, y2);
            ArrayList<Pin> g2 = this.groupMap.get(hashCode2);
            // check if the wire is between two groups with elements on them
            if(g1 != null && g2 != null) {
                if(g1 == g2) continue; // repeated wire, the two groups have been merged
                // merge two groups and let both hashCode point to the merged group
                g1.addAll(g2);
                this.groupMap.put(hashCode2, g1);
            }
        }
    }

    private void connectPins() {
        Set<ArrayList<Pin>> processedGroups = new HashSet<>();
        for (Map.Entry<Integer, ArrayList<Pin>> entry : this.groupMap.entrySet()) {
            ArrayList<Pin> group = entry.getValue();
            if(processedGroups.contains(group)) {
                continue;
            }
            else {
                processedGroups.add(group);
            }
            int len = group.size();
            for (int i = 0; i < len; ++i) {
                for (int j = i + 1; j < len; ++j) {
                    group.get(i).connect(group.get(j));
                    group.get(j).connect(group.get(i));
                }
            }
        }
    }

    private int getHashCode(Breadboard bd, int x, int y) {
        if (bd.isOnBreadboard(x, y)) {
            String id = bd.getOriginId();
            String area = bd.area(x, y);
            int hash = 0;
            if (area == "upside" || area == "downSide") {
                hash = Objects.hash(id, area, y);
            } else {
                hash = Objects.hash(id, area, x);
            }
            return hash;
        }
        return -1;
    }

    private int getHashCode(int x, int y) {
        for(Breadboard bd : this.breadboards) {
            if (bd.isOnBreadboard(x, y)) {
                String id = bd.getOriginId();
                String area = bd.area(x, y);
                int hash = 0;
                if (area == "upside" || area == "downSide") {
                    hash = Objects.hash(id, area, y);
                } else {
                    hash = Objects.hash(id, area, x);
                }
                return hash;
            }
        }
        return -1;
    }

    private void classifyElements() {
        for(Element e : circuit.getElementList()) {
            if(Objects.equals(e.getType(), "breadboard")) {
                this.breadboards.add((Breadboard) e);
                continue;
            }
            if(Objects.equals(e.getType(), "wire")) {
                this.wires.add((Wire)e);
                continue;
            }
            this.elements.add(e);
        }
    }
}