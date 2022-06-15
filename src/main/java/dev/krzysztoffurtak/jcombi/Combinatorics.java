/*
 * Copyright (c) 2022 Krzysztof Furtak
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.krzysztoffurtak.jcombi;

import dev.krzysztoffurtak.jcombi.combinations.CombinationsBuilder;
import dev.krzysztoffurtak.jcombi.combinations.CombinationsWithoutRepetition;
import dev.krzysztoffurtak.jcombi.permutations.PermutationsBuilder;
import dev.krzysztoffurtak.jcombi.permutations.PermutationsWithoutRepetition;
import dev.krzysztoffurtak.jcombi.variations.VariationsBuilder;
import dev.krzysztoffurtak.jcombi.variations.VariationsWithoutRepetition;

public final class Combinatorics {
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

    public static CombinationsWithoutRepetition<int[]> combinations(int n, int k) {
        return CombinationsBuilder.build(n, k);
    }

    public static <T> CombinationsBuilder<T> combinations() {
        return CombinationsBuilder.builder();
    }

    public static VariationsWithoutRepetition<int[]> variations(int n, int k) {
        return VariationsBuilder.build(n, k);
    }

    public static <T> VariationsBuilder<T> variations() {
        return VariationsBuilder.builder();
    }

    public static PermutationsWithoutRepetition<int[]> permutations(int n) {
        return PermutationsBuilder.build(n);
    }

    public static <T> PermutationsBuilder<T> permutations() {
        return PermutationsBuilder.builder();
    }

    /**
     * Returns {@code n!} (the factorial of {@code n}).
     *
     * <p>
     * <ul>
     * <li>{@code n!} is the product of all positive integers less than or equal to {@code n}</li>
     * <li>The value of {@code 0!} is {@code 1}</li>
     * </ul>
     * </p>
     *
     * @param n {@code n}
     * @return {@code n!}
     * @throws IllegalArgumentException if {@code n < 0}
     * @throws ArithmeticException if {@code n!} cannot be represented as long (that's the case for {@code n > 20})
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be greater or equal to zero");
        }
        if (n >= FACTORIALS.length) {
            throw new ArithmeticException("long overflow");
        }
        return FACTORIALS[n];
    }

    /**
     * Returns binomial coefficient of {@code n} and {@code k} (<i>{@code n} choose {@code k}</i>).
     *
     * <p>
     * <ul>
     * <li><i>{@code n} choose {@code k}</i> ({@code nCk}) is equal to <pre>{@code
     *     n!
     * ---------
     * k!*(n-k)!}
     * </pre></li>
     * </ul>
     * </p>
     *
     * @param n {@code n}
     * @param k {@code k}
     * @return binomial coefficient of {@code n} and {@code k}
     * @throws IllegalArgumentException if {@code n < 0} or {@code k < 0} or {@code k > n}
     */
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
