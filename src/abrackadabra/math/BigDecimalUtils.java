package abrackadabra.math;

import java.math.BigDecimal;
import java.math.MathContext;

public class BigDecimalUtils {
    private static BigDecimal ZERO = new BigDecimal("0");
    private static BigDecimal ONE = new BigDecimal("1");
    private static BigDecimal TWO = new BigDecimal("2");

    private static BigDecimal sqrtInitialApproximation(BigDecimal n) {
        int integerDigits = Math.max(n.precision() - n.scale(), 0);
        if ((integerDigits % 2) == 0) {
            integerDigits--;
        }
        integerDigits /= 2;
        BigDecimal guess = ONE.movePointRight(integerDigits);
        return guess;
    }

    public static BigDecimal sqrt(BigDecimal n, int maxIterations, MathContext mc) {
        if (n.compareTo(ZERO) <= 0)
            throw new IllegalArgumentException();
        BigDecimal initialGuess = sqrtInitialApproximation(n);
        BigDecimal lastGuess = ZERO;
        BigDecimal guess = new BigDecimal(initialGuess.toString());
        for (int i = 0; i < maxIterations; i++) {
            lastGuess = guess;
            guess = n.divide(guess, mc);
            guess = guess.add(lastGuess);
            guess = guess.divide(TWO, mc);
            if (lastGuess.equals(guess)) break;
        }
        return guess;
    }
}
