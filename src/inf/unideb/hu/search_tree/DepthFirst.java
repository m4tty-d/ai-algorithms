package inf.unideb.hu.search_tree;

import inf.unideb.hu.common.BaseNode;
import inf.unideb.hu.common.Solution;
import inf.unideb.hu.interfaces.OperatorInterface;
import inf.unideb.hu.interfaces.StateInterface;
import inf.unideb.hu.three_jars.Problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Adatbázis - a már bejárt részfát tartalmazza
 * Művelet - kiterjesztés
 * Vezérlő -
 */
public class DepthFirst {

    static class Node extends BaseNode {

        public Node(StateInterface state, Node parent, OperatorInterface creator) {
            super(state, parent, creator);
        }
    }

    public static LinkedList<OperatorInterface> search(Problem p) {
        Stack<Node> openNodes = new Stack<Node>();
        ArrayList<Node> closedNodes = new ArrayList<Node>();

        openNodes.push(new Node(p.getStartState(), null, null));

        while (true) {
            if (openNodes.isEmpty()) {
                return null;
            }

            Node actualNode = openNodes.pop();

            if (actualNode.state.isGoal()) {
                return Solution.solution(actualNode);
            }

            for (OperatorInterface o : p.getOperators()) {
                if (o.isApplicable(actualNode.state)) {
                    StateInterface newState = o.apply(actualNode.state);
                    Node newNode = new Node(newState, actualNode, o);

                    boolean duplicate = false;
                    for (Node n : openNodes) {
                        if (n.state.equals(newNode.state)) {
                            duplicate = true;
                        }
                    }

                    for (Node n : closedNodes) {
                        if (n.state.equals(newNode.state)) {
                            duplicate = true;
                        }
                    }

                    if (!duplicate) {
                        openNodes.push(newNode);
                    }

                }
            }

            closedNodes.add(actualNode);
        }
    }
}
