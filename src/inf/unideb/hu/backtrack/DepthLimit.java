package inf.unideb.hu.backtrack;

import inf.unideb.hu.common.BaseNode;
import inf.unideb.hu.common.Solution;
import inf.unideb.hu.interfaces.OperatorInterface;
import inf.unideb.hu.interfaces.ProblemInterface;
import inf.unideb.hu.interfaces.StateInterface;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Az alap backtrackhez képest annyi plusz, hogy meg lehet adni egy korlátot neki, az aktuális út mélysége nem haladhatja
 * meg ezt a korlátot. Új csúcs hozzáadásakor növeljük, visszalépéskor csökkentjük.
 */
public class DepthLimit {

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

    public static LinkedList<OperatorInterface> search(ProblemInterface p, int depthLimit) {
        Node actual = new Node(p.getStartState(), null, null, p);
        int depth = 1;

        while (true) {
            if (actual.state.isGoal()) {
                return Solution.solution(actual);
            }

            if (depth < depthLimit && !actual.applicableOperators.isEmpty()) {
                OperatorInterface operator = actual.applicableOperators.remove(0);
                StateInterface state = operator.apply(actual.state);
                actual = new Node(state, actual, operator, p);
                depth++;
            } else {
                if (actual.parent != null) {
                    actual = (Node) actual.parent;
                    depth--;
                } else {
                    System.out.println("No solution found!");
                    return null;
                }
            }
        }
    }
}
