package inf.unideb.hu.three_jars;

import inf.unideb.hu.interfaces.StateInterface;

import java.util.Arrays;

public class State implements StateInterface {

    private int[] jars = new int[3];

    public State(int firstJar, int secondJar, int thirdJar) {
        this.jars[0] = firstJar;
        this.jars[1] = secondJar;
        this.jars[2] = thirdJar;
    }

    public int[] getJars() {
        return jars;
    }

    @Override
    public boolean isGoal() {
        return this.jars[0] == 4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Arrays.equals(jars, state.jars);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(jars);
    }

    @Override
    public String toString() {
        return "State{" +
                "jars=" + Arrays.toString(jars) +
                '}';
    }
}
