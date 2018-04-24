package inf.unideb.hu.interfaces;

public interface OperatorInterface {
    boolean isApplicable(StateInterface s);
    StateInterface apply(StateInterface s);
    int cost(StateInterface s);
}
