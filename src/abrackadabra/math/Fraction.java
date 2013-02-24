package abrackadabra.math;

public class Fraction implements Cloneable, Comparable<Fraction> {
    private long numerator = 0, denominator = 1;

    public Fraction(long numerator) {
        this.numerator = numerator;
        normalize();
    }

    public Fraction(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        normalize();
    }

    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }

    @Override
    public int hashCode() {
        int result = (int) (numerator ^ (numerator >>> 32));
        result = 31 * result + (int) (denominator ^ (denominator >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Fraction) {
            Fraction fraction = (Fraction) object;
            return numerator == fraction.getNumerator() && denominator == fraction.getDenominator();
        }
        return false;
    }

    public int signum() {
        if (numerator == 0) return 0;
        return numerator < 0 ? -1 : 1;
    }

    @Override
    public Fraction clone() {
        return new Fraction(numerator, denominator);
    }

    public int compareTo(Fraction o) {
        return subtract(o).signum();
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    private void normalize() {
        if (denominator == 0) {
            throw new IllegalArgumentException();
        }
        if ((numerator < 0 && denominator < 0) || denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        if (numerator == 0) denominator = 1;
        long gcd = AMath.gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    public Fraction add(Fraction a) {
        long gcd = AMath.gcd(denominator, a.denominator);
        long d = denominator / gcd * a.denominator;
        return new Fraction(numerator * (d / a.denominator) + a.numerator * (d / denominator), d);
    }

    public Fraction negate() {
        return new Fraction(-numerator, denominator);
    }

    public Fraction subtract(Fraction a) {
        return add(a.negate());
    }

    public Fraction multiply(Fraction a) {
        return new Fraction(numerator * a.numerator, denominator * a.denominator);
    }

    public Fraction divide(Fraction a) {
        return new Fraction(numerator * a.denominator, denominator * a.numerator);
    }
}

