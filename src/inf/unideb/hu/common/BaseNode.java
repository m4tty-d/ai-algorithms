package inf.unideb.hu.common;

import inf.unideb.hu.interfaces.OperatorInterface;
import inf.unideb.hu.interfaces.StateInterface;

public class BaseNode {

    public StateInterface state;
    public BaseNode parent;
    public OperatorInterface creator;

    public BaseNode(StateInterface state, BaseNode parent, OperatorInterface creator) {
        this.state = state;
        this.parent = parent;
        this.creator = creator;
    }

}
