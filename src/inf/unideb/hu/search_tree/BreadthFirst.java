package inf.unideb.hu.search_tree;

import inf.unideb.hu.common.BaseNode;
import inf.unideb.hu.common.Solution;
import inf.unideb.hu.interfaces.OperatorInterface;
import inf.unideb.hu.interfaces.ProblemInterface;
import inf.unideb.hu.interfaces.StateInterface;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Adatbázis - a már bejárt részfát tartalmazza
 * Művelet - kiterjesztés. nyílt csúcsot lehet kiterjeszteni. a kiterjesztés során az adott állapotra alkalmazzuk
 * az összes alkalmazható operátort majd az így kapott állapotokat nyílt csúcsként beszúrjuk
 * Vezérlő
 *  1.Inicializálás: nyílt csúcsként beszúrjuk a kezdőállapotot
 *  2.Ciklus:
 *      - ha nincs nyílt állapot -> sikertelenül vége
 *      - a nyílt csúcsokat tartalmazó sor elejéről kiveszünk egy csúcsot
 *      - célállapot e a aktuális állapot
 *      - kiterjesztjük a nyílt csúcsot
 *      - a kiterjesztett csúcsot zárttá tesszük
 */
public class BreadthFirst {

    static class Node extends BaseNode {
        int depth;

        public Node(StateInterface state, Node parent, OperatorInterface creator) {
            super(state, parent, creator);
            depth = parent != null ? parent.depth + 1 : 0;
        }
    }

    public static LinkedList<OperatorInterface> search(ProblemInterface p) {
        LinkedList<Node> openNodes = new LinkedList<Node>();
        ArrayList<Node> closedNodes = new ArrayList<Node>();

        openNodes.add(new Node(p.getStartState(), null, null));

        while (true) {
            if (openNodes.isEmpty()) {
                return null;
            }

            for (Node n : openNodes) {
                System.out.println();
            }

            Node actualNode = openNodes.removeFirst();

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
                        openNodes.addLast(newNode);
                    }

                }
            }

            closedNodes.add(actualNode);
        }
    }
}
