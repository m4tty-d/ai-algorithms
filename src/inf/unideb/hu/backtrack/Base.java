package inf.unideb.hu.backtrack;

import inf.unideb.hu.common.BaseNode;
import inf.unideb.hu.common.Solution;
import inf.unideb.hu.interfaces.OperatorInterface;
import inf.unideb.hu.interfaces.ProblemInterface;
import inf.unideb.hu.interfaces.StateInterface;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Adatbázis - aktuális út (a kezdőállapottól az aktuális állapotig)
 * Műveletek - operátorok, visszalépés
 * Vezérlő
 *  1.Inicializálás: hozzáadjuk a kezdőállapotot az aktuális úthoz
 *  2.Ciklus:
 *      - az aktuális állapot célállapot e, igen -> sikeresen vége
 *      - vannak e alkalmazható operátoraink
 *          igen -> kiválasztunk egyet, majd alkalmazzuk. az aktuális állapot az így kapott állapot lesz
 *          nem -> van az aktuális csúcsnak szülője?
 *              igen -> visszalépünk
 *              nem -> sikertelenül vége
 */
public class Base {

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
                actualNode = new Node(state, actualNode, operator, p);
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
