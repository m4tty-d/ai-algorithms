package inf.unideb.hu;

import inf.unideb.hu.backtrack.CycleDetection;
import inf.unideb.hu.non_modifiable.HillClimbing;
import inf.unideb.hu.search_tree.A;
import inf.unideb.hu.search_tree.BestFirst;
import inf.unideb.hu.search_tree.Optimal;
import inf.unideb.hu.three_jars.Heuristic;
import inf.unideb.hu.three_jars.Problem;

public class Main {

    public static void main(String[] args) {
        Problem p = new Problem();
        Heuristic h = new Heuristic();

        System.out.println(CycleDetection.search(p));
    }
}
