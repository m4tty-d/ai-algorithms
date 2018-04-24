package inf.unideb.hu.search_tree;

import inf.unideb.hu.common.BaseNode;
import inf.unideb.hu.common.Solution;
import inf.unideb.hu.interfaces.OperatorInterface;
import inf.unideb.hu.interfaces.ProblemInterface;
import inf.unideb.hu.interfaces.StateInterface;

import java.util.ArrayList;
import java.util.LinkedList;

public class Optimal {

    static class Node extends BaseNode {
        boolean open;
        int cost;

        public Node(StateInterface state, Node parent, OperatorInterface creator) {
            super(state, parent, creator);
            this.open = true;
            this.cost = parent != null ? this.cost + creator.cost(parent.state) : 0;
        }
    }

    public static LinkedList<OperatorInterface> search(ProblemInterface p) {
        ArrayList<Node> nodes = new ArrayList<Node>();

        nodes.add(new Node(p.getStartState(), null, null));

        while (true) {
            Node actualNode = null;

            for (Node n : nodes) {
                if (n.open && (actualNode == null || n.cost < actualNode.cost)) {
                    actualNode = n;
                }
            }

            if (actualNode == null) {
                return null;
            }

            if (actualNode.state.isGoal()) {
                return Solution.solution(actualNode);
            }

            for (OperatorInterface o : p.getOperators()) {
                if (o.isApplicable(actualNode.state)) {
                    StateInterface newState = o.apply(actualNode.state);

                    Node previous = null;

                    for (Node n : nodes) {
                        if (n.state.equals(newState)) {
                            previous = n;
                            break;
                        }
                    }

                    if (previous != null) {
                        int newCost = actualNode.cost + o.cost(actualNode.state);

                        if (newCost < actualNode.cost) {
                            previous.parent = actualNode.parent;
                            previous.creator = actualNode.creator;
                            previous.cost = newCost;
                        }
                    } else {
                        nodes.add(new Node(newState, actualNode, o));
                    }
                }
            }

            actualNode.open = false;
        }
    }
}
