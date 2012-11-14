package abrackadabra.math;

public abstract class Fraction extends Number {
    @Override
    public abstract String toString();

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object object);

    public abstract int signum();

    public class Integer extends Fraction implements Cloneable, Comparable<Integer> {
        private int numerator = 0, denominator = 1;

        public Integer(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
            normalize();
        }

        public int getNumerator() {
            return numerator;
        }

        public int getDenominator() {
            return denominator;
        }

        public void setNumerator(int numerator) {
            this.numerator = numerator;
        }

        public void setDenominator(int denominator) {
            this.denominator = denominator;
        }

        @Override
        public int hashCode() {
            return numerator + denominator * 31;
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof Integer) {
                Integer integer = (Integer) object;
                return numerator == integer.getNumerator() && denominator == integer.getDenominator();
            }
            return false;
        }

        @Override
        public int signum() {
            if (numerator == 0) return 0;
            return numerator < 0 ? -1 : 1;
        }

        @Override
        public Integer clone() {
            return new Integer(numerator, denominator);
        }

        public int compareTo(Integer o) {
            return subtract(o).signum();
        }

        public String toString() {
            return numerator + "/" + denominator;
        }

        private void normalize() {
            if ((numerator < 0 && denominator < 0) || denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }
            if (numerator == 0) denominator = 1;
            int gcd = MathUtils.gcd(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;
        }

        public Integer add(Integer a) {
            int gcd = MathUtils.gcd(denominator, a.denominator);
            int d = denominator / gcd * a.denominator;
            return new Integer(numerator * (d / a.denominator) + a.numerator * (d / denominator), d);
        }

        public Integer subtract(Integer a) {
            int gcd = MathUtils.gcd(denominator, a.denominator);
            int d = denominator / gcd * a.denominator;
            return new Integer(numerator * (d / a.denominator) - a.numerator * (d / denominator), d);
        }

        public Integer multiply(Integer a) {
            return new Integer(numerator * a.numerator, denominator * a.denominator);
        }

        public Integer divide(Integer a) {
            return new Integer(numerator * a.denominator, denominator * a.numerator);
        }

        @Override
        public int intValue() {
            return numerator / denominator;
        }

        @Override
        public long longValue() {
            return numerator / denominator;
        }

        @Override
        public float floatValue() {
            return (float) numerator / (float) denominator;
        }

        @Override
        public double doubleValue() {
            return (double) numerator / denominator;
        }
    }

    public class Long extends Fraction implements Cloneable, Comparable<Long> {
        private long numerator = 0L, denominator = 1L;

        public Long(long numerator, long denominator) {
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

        public void setNumerator(int numerator) {
            this.numerator = numerator;
        }

        public void setDenominator(int denominator) {
            this.denominator = denominator;
        }

        @Override
        public int hashCode() {
            return (int) (numerator + denominator * 31);
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof Long) {
                Long integer = (Long) object;
                return numerator == integer.getNumerator() && denominator == integer.getDenominator();
            }
            return false;
        }

        @Override
        public int signum() {
            if (numerator == 0) return 0;
            return numerator < 0 ? -1 : 1;
        }

        @Override
        public Long clone() {
            return new Long(numerator, denominator);
        }

        public int compareTo(Long o) {
            return subtract(o).signum();
        }

        public String toString() {
            return numerator + "/" + denominator;
        }

        private void normalize() {
            if ((numerator < 0 && denominator < 0) || denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }
            if (numerator == 0) denominator = 1;
            long gcd = MathUtils.gcd(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;
        }

        public Long add(Long a) {
            long gcd = MathUtils.gcd(denominator, a.denominator);
            long d = denominator / gcd * a.denominator;
            return new Long(numerator * (d / a.denominator) + a.numerator * (d / denominator), d);
        }

        public Long subtract(Long a) {
            long gcd = MathUtils.gcd(denominator, a.denominator);
            long d = denominator / gcd * a.denominator;
            return new Long(numerator * (d / a.denominator) - a.numerator * (d / denominator), d);
        }

        public Long multiply(Long a) {
            return new Long(numerator * a.numerator, denominator * a.denominator);
        }

        public Long divide(Long a) {
            return new Long(numerator * a.denominator, denominator * a.numerator);
        }

        @Override
        public int intValue() {
            return (int) (numerator / denominator);
        }

        @Override
        public long longValue() {
            return numerator / denominator;
        }

        @Override
        public float floatValue() {
            return (float) numerator / (float) denominator;
        }

        @Override
        public double doubleValue() {
            return (double) numerator / denominator;
        }
    }

    public class BigInteger extends Fraction implements Cloneable, Comparable<BigInteger> {
        private java.math.BigInteger numerator = java.math.BigInteger.ZERO;
        private java.math.BigInteger denominator = java.math.BigInteger.ONE;

        public BigInteger(java.math.BigInteger numerator, java.math.BigInteger denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
            normalize();
        }

        public java.math.BigInteger getNumerator() {
            return numerator;
        }

        public java.math.BigInteger getDenominator() {
            return denominator;
        }

        public void setNumerator(java.math.BigInteger numerator) {
            this.numerator = numerator;
        }

        public void setDenominator(java.math.BigInteger denominator) {
            this.denominator = denominator;
        }

        @Override
        public int hashCode() {
            return numerator.hashCode() + denominator.hashCode() * 31;
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof BigInteger) {
                BigInteger integer = (BigInteger) object;
                return numerator == integer.getNumerator() && denominator == integer.getDenominator();
            }
            return false;
        }

        @Override
        public int signum() {
            return numerator.signum();
        }

        @Override
        public BigInteger clone() {
            return new BigInteger(numerator, denominator);
        }

        public int compareTo(BigInteger o) {
            return subtract(o).signum();
        }

        public String toString() {
            return numerator + "/" + denominator;
        }

        private void normalize() {
            if ((numerator.signum() < 0 && denominator.signum() < 0) || denominator.signum() < 0) {
                numerator = numerator.negate();
                denominator = denominator.negate();
            }
            if (numerator.signum() == 0) denominator = java.math.BigInteger.ONE;
            java.math.BigInteger gcd = MathUtils.gcd(numerator, denominator);
            numerator = numerator.divide(gcd);
            denominator = denominator.divide(gcd);
        }

        public BigInteger add(BigInteger a) {
            java.math.BigInteger gcd = MathUtils.gcd(denominator, a.denominator);
            java.math.BigInteger d = denominator.divide(gcd).multiply(a.denominator);
            return new BigInteger(numerator.multiply(d.divide(a.denominator)).add(a.numerator.multiply(d.divide(denominator))), d);
        }

        public BigInteger subtract(BigInteger a) {
            java.math.BigInteger gcd = MathUtils.gcd(denominator, a.denominator);
            java.math.BigInteger d = denominator.divide(gcd).multiply(a.denominator);
            return new BigInteger(numerator.multiply(d.divide(a.denominator)).subtract(a.numerator.multiply(d.divide(denominator))), d);
        }

        public BigInteger multiply(BigInteger a) {
            return new BigInteger(numerator.multiply(a.numerator), denominator.multiply(a.denominator));
        }

        public BigInteger divide(BigInteger a) {
            return new BigInteger(numerator.multiply(a.denominator), denominator.multiply(a.numerator));
        }

        @Override
        public int intValue() {
            return numerator.divide(denominator).intValue();
        }

        @Override
        public long longValue() {
            return numerator.divide(denominator).longValue();
        }

        @Override
        public float floatValue() {
            return numerator.floatValue() / denominator.floatValue();
        }

        @Override
        public double doubleValue() {
            return numerator.doubleValue() / denominator.doubleValue();
        }
    }

}
