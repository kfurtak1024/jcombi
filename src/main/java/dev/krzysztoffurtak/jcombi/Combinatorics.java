package dev.krzysztoffurtak.jcombi;

public class Combinatorics {
    private static final long[] FACTORIALS = new long[] {
            1L,
            1L,
            2L,
            6L,
            24L,
            120L,
            720L,
            5040L,
            40320L,
            362880L,
            3628800L,
            39916800L,
            479001600L,
            6227020800L,
            87178291200L,
            1307674368000L,
            20922789888000L,
            355687428096000L,
            6402373705728000L,
            121645100408832000L,
            2432902008176640000L
    };

    public static Combinations<int[]> combinations(int n, int k) {
        return CombinationsBuilder.build(n, k);
    }

    public static <T> CombinationsBuilder<T> combinations() {
        return CombinationsBuilder.builder();
    }

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be greater or equal to zero");
        }
        if (n >= FACTORIALS.length) {
            throw new ArithmeticException("long overflow");
        }
        return FACTORIALS[n];
    }

    public static long binomial(int n, int k) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be greater or equal to zero");
        }
        if (k < 0) {
            throw new IllegalArgumentException("k must be greater or equal to zero");
        }
        if (k > n) {
            throw new IllegalArgumentException("k must be less or equal to n");
        }

        if (n == k) {
            return 1;
        }

        switch (k) {
            case 0:
                return 1;
            case 1:
                return n;
            default:
                long result = 1;
                for (long i = 0; i < k; i++) {
                    result *= n - i;
                    result /= i + 1;
                }
                return result;
        }
    }

    private Combinatorics() {
    }
}
