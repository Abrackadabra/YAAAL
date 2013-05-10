package abrackadabra.util;

public class Pair<A, B> implements Comparable<Pair<A, B>> {
    public final A first;
    public final B second;

    public Pair(A a, B b) {
        this.first = a;
        this.second = b;
    }

    @Override
    public int compareTo(Pair<A, B> o) {
        int result = ((Comparable<A>) first).compareTo(o.first);
        if (result != 0)
            return result;
        return ((Comparable<B>)second).compareTo(o.second);
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pair pair = (Pair) o;

        if (first != null ? !first.equals(pair.first) : pair.first != null) {
            return false;
        }
        if (second != null ? !second.equals(pair.second) : pair.second != null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "[" + first + ", " + second + "]";
    }
}
