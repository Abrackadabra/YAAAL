package chelper;

import java.util.HashSet;
import java.util.Set;

public class TheNumberGame {
    static class State {
        int a, b;
        boolean turn;

        State(int a, int b, boolean turn) {
            this.a = a;
            this.b = b;
            this.turn = turn;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            State state = (State) o;

            if (a != state.a) {
                return false;
            }
            if (b != state.b) {
                return false;
            }
            if (turn != state.turn) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = a;
            result = 31 * result + b;
            result = 31 * result + (turn ? 1 : 0);
            return result;
        }
    }

    public String determineOutcome(int A, int B) {
        return go(A, B, true) ? "Manao wins" : "Manao loses";
    }

    Set<State> curStates = new HashSet<State>();

    boolean go(int a, int b, boolean turn) {
        State state = new State(a, b, turn);
        if (curStates.contains(state)) {
            return false;
        }
        curStates.add(state);

        if (a == b) {
            curStates.remove(state);
            return true;
        }
        if (turn) {
            if (go(a / 10, b, !turn) || go(reverse(a), b, !turn)) {
                curStates.remove(state);
                return true;
            }
        } else {
            if (go(a, b / 10, !turn) && go(a, reverse(b), !turn)) {
                curStates.remove(state);
                return true;
            }
        }
        curStates.remove(state);
        return false;
    }

    int reverse(int x) {
        int a = 0;
        while (x > 0) {
            a = a * 10 + x % 10;
            x /= 10;
        }
        return a;
    }
}
