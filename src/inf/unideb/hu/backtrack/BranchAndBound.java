package inf.unideb.hu.backtrack;

import inf.unideb.hu.common.BaseNode;
import inf.unideb.hu.common.Solution;
import inf.unideb.hu.interfaces.OperatorInterface;
import inf.unideb.hu.interfaces.ProblemInterface;
import inf.unideb.hu.interfaces.StateInterface;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Lényegében egyesíti a körfigyelést az úthosszkorláttal.
 * Arra kell figyelni, hogy a megoldással nem visszatérünk egyből, hanem eltároljuk egy változóban illetve ugyanitt a úthossz korlátot
 * átállítjuk mélység - 1-re.
 */
public class BranchAndBound {

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

    public static LinkedList<OperatorInterface> search(ProblemInterface p, Integer depthLimit) {
        Node actual = new Node(p.getStartState(), null, null, p);
        LinkedList<OperatorInterface> bestSolution = null;
        int depth = 1;

        while (true) {
            if ((depthLimit == null || depth < depthLimit) && !actual.applicableOperators.isEmpty()) {
                OperatorInterface operator = actual.applicableOperators.remove(0);
                StateInterface state = operator.apply(actual.state);

                boolean duplicate = false;
                for (Node n = actual; n != null; n = (Node) n.parent) {
                    if (n.state.equals(state)) {
                        duplicate = true;
                        break;
                    }
                }

                if (!duplicate) {
                    actual = new Node(state, actual, operator, p);
                    depth++;

                    if (actual.state.isGoal()) {
                        bestSolution = Solution.solution(actual);
                        depthLimit = depth - 1;
                    }
                }
            } else {
                if (actual.parent != null) {
                    actual = (Node) actual.parent;
                    depth--;
                } else {
                    return bestSolution;
                }
            }
        }
    }
}
