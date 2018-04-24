package inf.unideb.hu.three_jars;

import inf.unideb.hu.interfaces.HeuristicInterface;
import inf.unideb.hu.interfaces.StateInterface;


public class Heuristic implements HeuristicInterface {

    /**
     * Esimates the number of operators for the solution.
     * @param s
     * @return
     */
    @Override
    public int heuristic(StateInterface s) {
        if (s.isGoal()) {
            return 0;
        }

        int[] jars = ((State) s).getJars();

        return 40000 - (jars[0] > 0 ? 1 : 0) - (jars[1] > 0 ? 1 : 0) - (jars[2] > 0 ? 1 : 0);
    }
}
