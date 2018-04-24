package inf.unideb.hu.non_modifiable;

import inf.unideb.hu.interfaces.OperatorInterface;
import inf.unideb.hu.interfaces.ProblemInterface;
import inf.unideb.hu.interfaces.StateInterface;

import java.util.ArrayList;
import java.util.Random;

/**
 * Adatbázis - aktuális állapot
 * Művelet - operátorok
 * Vezérlő
 *  1.az aktuális állapot célállapot e -> igen, kilépünk
 *  2.vannak e alkalmazható operátorok
 *      nem -> kilépünk
 *      igen -> véletlenszerűen választunk egyet és alkalmazzuk az aktuális állapotra, az így kapott állapot lesz az új aktuális állapot
 */
public class TrialAndError {

    public static Random random = new Random();

    public static StateInterface search(ProblemInterface p) {
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
                OperatorInterface o = applicableOperators.get(random.nextInt(applicableOperators.size()));
                actualState = o.apply(actualState);
            }
        }
    }
}
