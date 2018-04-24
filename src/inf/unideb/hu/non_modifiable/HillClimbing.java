package inf.unideb.hu.non_modifiable;

import inf.unideb.hu.interfaces.HeuristicInterface;
import inf.unideb.hu.interfaces.OperatorInterface;
import inf.unideb.hu.interfaces.ProblemInterface;
import inf.unideb.hu.interfaces.StateInterface;

import java.util.ArrayList;

/**
 * Adatbázis - aktuális állapot
 * Művelet - operátorok
 * Vezérlő
 *  1.aktuális állapot célállapot e, igen -> sikeres kilépünk
 *  2.vannak e alkalmazható operátorok
 *      nem -> sikertelenül kilépünk
 *      igen -> kiválasztjuk a legkisebb heurisztikájú operátort amit az aktuális állapotra alkalmazunk, az új aktuális az így kapott állapot lesz
 */
public class HillClimbing {

    public static StateInterface search(ProblemInterface p, HeuristicInterface h) {
        StateInterface actualState = p.getStartState();

        while (true) {
            if (actualState.isGoal()) {
                return actualState;
            }

            ArrayList<OperatorInterface> applicableOperators = new ArrayList<OperatorInterface>();

            for (OperatorInterface o : p.getOperators()) {
                if (o.isApplicable(actualState)) {
                    applicableOperators.add(o);
                }
            }

            if (applicableOperators.isEmpty()) {
                return null;
            } else {
                OperatorInterface best = null;

                int min = 0;
                for (OperatorInterface o : applicableOperators) {
                    int heuristic = h.heuristic(o.apply(actualState));

                    if (best == null || heuristic < min) {
                        best = o;
                        min = heuristic;
                    }
                }

                actualState = best.apply(actualState);
            }
        }
    }
}
