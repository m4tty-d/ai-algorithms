package inf.unideb.hu.backtrack;

import inf.unideb.hu.common.BaseNode;
import inf.unideb.hu.common.Solution;
import inf.unideb.hu.interfaces.OperatorInterface;
import inf.unideb.hu.interfaces.ProblemInterface;
import inf.unideb.hu.interfaces.StateInterface;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A körfigyelést úgy oldjuk meg, hogy az új állapot előállása után megnézzük, hogy az adott állapot szerepel
 * e a aktuális útban és csak akkor szúrjuk be, ha nem.
 */
public class CycleDetection {

    static class Node extends BaseNode {
        ArrayList<OperatorInterface> applicableOperators = new ArrayList<>();

        public Node(StateInterface state, Node parent, OperatorInterface creator, ProblemInterface p) {
            super(state, parent, creator);
            for (OperatorInterface o : p.getOperators()) {
                if (o.isApplicable(state)) {
                    this.applicableOperators.add(o);
                }
            }
        }
    }

    public static LinkedList<OperatorInterface> search(ProblemInterface p) {
        Node actualNode = new Node(p.getStartState(), null, null, p);

        while (true) {
            if (actualNode.state.isGoal()) {
                return Solution.solution(actualNode);
            }

            if (!actualNode.applicableOperators.isEmpty()) {
                OperatorInterface operator = actualNode.applicableOperators.remove(0);
                StateInterface state = operator.apply(actualNode.state);

                boolean duplicate = false;
                for (Node n = actualNode; n != null; n = (Node) n.parent) {
                    if (n.state.equals(state)) {
                        duplicate = true;
                    }
                }

                if (!duplicate) {
                    actualNode = new Node(state, actualNode, operator, p);
                }
            } else {
                if (actualNode.parent != null) {
                    actualNode = (Node) actualNode.parent;
                } else {
                    return null;
                }
            }
        }
    }
}
