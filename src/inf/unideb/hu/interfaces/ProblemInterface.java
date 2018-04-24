package inf.unideb.hu.interfaces;

import java.util.List;

public interface ProblemInterface {
    StateInterface getStartState();
    List<OperatorInterface> getOperators();
    double getCost(StateInterface state, OperatorInterface opr);
    double getHeuristic(StateInterface state);
}
