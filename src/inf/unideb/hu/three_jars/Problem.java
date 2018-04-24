package inf.unideb.hu.three_jars;

import inf.unideb.hu.interfaces.OperatorInterface;
import inf.unideb.hu.interfaces.ProblemInterface;
import inf.unideb.hu.interfaces.StateInterface;

import java.util.ArrayList;
import java.util.List;

public class Problem implements ProblemInterface {

    private static ArrayList<OperatorInterface> operators = new ArrayList<>();

    static {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (i != j) {
                    operators.add(new Operator(i, j));
                }
            }
        }
    }

    @Override
    public StateInterface getStartState() {
        return new State(5,0,0);
    }

    @Override
    public List<OperatorInterface> getOperators() {
        return operators;
    }

    @Override
    public double getCost(StateInterface state, OperatorInterface opr) {
        return 0;
    }

    @Override
    public double getHeuristic(StateInterface state) {
        return 0;
    }
}
