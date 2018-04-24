package inf.unideb.hu.common;

import inf.unideb.hu.interfaces.OperatorInterface;
import java.util.LinkedList;

public class Solution {
    public static LinkedList<OperatorInterface> solution(BaseNode node) {
        LinkedList<OperatorInterface> solution = new LinkedList<OperatorInterface>();

        for (BaseNode n = node; n.parent != null; n = n.parent) {
            solution.addFirst(n.creator);
        }

        return solution;
    }
}
