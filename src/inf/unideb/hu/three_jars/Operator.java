package inf.unideb.hu.three_jars;

import inf.unideb.hu.interfaces.OperatorInterface;
import inf.unideb.hu.interfaces.StateInterface;

public class Operator implements OperatorInterface {

    private int from;
    private int to;

    private int[] maxJarValues = {5,3,2};

    public Operator(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isApplicable(StateInterface s) {
        int[] jars = ((State) s).getJars();
        return jars[from] > 0 && jars[to] < maxJarValues[to];
    }

    @Override
    public StateInterface apply(StateInterface s) {
        int oldVals[] = ((State) s).getJars();
        int newVals[] = new int[3];
        int m = Math.min(oldVals[from], maxJarValues[to]-oldVals[to]);

        for (int k : new int[] {0,1,2} ) {
            if ( k == to )
                newVals[k] = oldVals[k] + m;
            else if ( k == from )
                newVals[k] = oldVals[k] - m;
            else
                newVals[k] = oldVals[k];
        }

        return new State(newVals[0], newVals[1], newVals[2]);
    }

    @Override
    public int cost(StateInterface s) {
        return Math.min(((State) s).getJars()[from], maxJarValues[to]-((State) s).getJars()[to]);
    }


    @Override
    public String toString() {
        return "Operator{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
