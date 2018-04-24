package inf.unideb.hu.search_tree;

import inf.unideb.hu.common.BaseNode;
import inf.unideb.hu.common.Solution;
import inf.unideb.hu.interfaces.HeuristicInterface;
import inf.unideb.hu.interfaces.OperatorInterface;
import inf.unideb.hu.interfaces.ProblemInterface;
import inf.unideb.hu.interfaces.StateInterface;

import java.util.ArrayList;
import java.util.LinkedList;

public class A {

    static class Node extends BaseNode {
        boolean open;
        int totalCost;

        public Node(StateInterface state, Node parent, OperatorInterface creator, HeuristicInterface h) {
            super(state, parent, creator);
            this.open = true;
            this.totalCost = parent != null ? parent.totalCost - h.heuristic(parent.state) + creator.cost(parent.state) + h.heuristic(state) : h.heuristic(state);
        }
    }

    public static LinkedList<OperatorInterface> search(ProblemInterface p, HeuristicInterface h) {
        ArrayList<Node> nodes = new ArrayList<Node>();

        nodes.add(new Node(p.getStartState(), null, null, h));

        while (true) {
            Node actualNode = null;

            for (Node n : nodes) {
                if (n.open && (actualNode == null || n.totalCost < actualNode.totalCost)) {
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
                        int newTotalCost = actualNode.totalCost - h.heuristic(actualNode.state) + o.cost(actualNode.state) + h.heuristic(newState);

                        if (previous.totalCost > newTotalCost) {
                            if (previous.open) {
                                previous.parent = actualNode.parent;
                                previous.creator = o;
                                previous.totalCost = newTotalCost;
                            } else {
                                previous.parent = actualNode.parent;
                                previous.creator = o;
                                previous.totalCost = newTotalCost;
                                previous.open = true;
                            }
                        }

                    } else {
                        nodes.add(new Node(newState, actualNode, o, h));
                    }
                }
            }

            actualNode.open = false;
        }
    }
}
