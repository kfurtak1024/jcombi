package dev.krzysztoffurtak.jcombi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CombinatoricsTest {
    @Nested
    @DisplayName("Test builders for combinations")
    class CombinationsTest {
        @Test
        @DisplayName("Should create combinations with use of builder")
        void testCombinations() {
            // Given
            final var n = 4;
            final var k = 2;

            // When
            final var combinations = Combinatorics.combinations(n, k);

            // Then
            assertThat(combinations).isInstanceOf(Combinations.class);
        }

        @Test
        @DisplayName("Should create combinations with use of builder")
        void testCombinationsBuilder() {
            // Given
            final var inputSet = new String[] { "A", "B", "C" };
            final var k = 2;

            // When
            final var combinations =
                    Combinatorics.<String>combinations().of(inputSet).choose(k);

            // Then
            assertThat(combinations).isInstanceOf(Combinations.class);
        }
    }

    static class BinomialCoefficientProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(2, 2, 1L),
                    Arguments.of(3, 2, 3L),
                    Arguments.of(4, 2, 6L),
                    Arguments.of(5, 2, 10L),
                    Arguments.of(6, 2, 15L),
                    Arguments.of(7, 2, 21L),
                    Arguments.of(8, 2, 28L),
                    Arguments.of(9, 2, 36L),
                    Arguments.of(10, 2, 45L),
                    Arguments.of(10, 5, 252L),
                    Arguments.of(10, 8, 45L),
                    Arguments.of(100, 10, 17310309456440L),
                    Arguments.of(300, 8, 1481062243936275L)
            );
        }
    }

    @Nested
    @DisplayName("Test Binomial Coefficient calculation")
    class BinomialTest {
        @ParameterizedTest(name = "{0} choose 0=1")
        @ValueSource(ints = {0, 1, 2, 3, 4, 5, 10, 100, 1000, 10000, 100000})
        @DisplayName("Verify special case: n choose 0")
        void verifyBinomialCoefficientForKequalTo0(int n) {
            assertThat(Combinatorics.binomial(n, 0)).isEqualTo(1);
        }

        @ParameterizedTest(name = "{0} choose 1={0}")
        @ValueSource(ints = {1, 2, 3, 4, 5, 10, 100, 1000, 10000, 100000})
        @DisplayName("Verify special case: n choose 1")
        void verifyBinomialCoefficientForKequalTo1(int n) {
            assertThat(Combinatorics.binomial(n, 1)).isEqualTo(n);
        }

        @ParameterizedTest(name = "{0} choose 1")
        @ValueSource(ints = {-1, -2, -3, -10, -100, -1000, -10000})
        @DisplayName("Should throw exception when trying to calculate nCk for negative n")
        void shouldThrowExceptionWhenTryingToCalculateBinomialCoefficientForNegativeN(int n) {
            final Exception exception = assertThrows(
                    IllegalArgumentException.class, () -> Combinatorics.binomial(n, 1));

            assertThat(exception.getMessage()).isEqualTo("n must be greater or equal to zero");
        }

        @ParameterizedTest(name = "10 choose {0}")
        @ValueSource(ints = {-1, -2, -3, -10, -100, -1000, -10000})
        @DisplayName("Should throw exception when trying to calculate nCk for negative k")
        void shouldThrowExceptionWhenTryingToCalculateBinomialCoefficientForNegativeK(int k) {
            final Exception exception = assertThrows(
                    IllegalArgumentException.class, () -> Combinatorics.binomial(10, k));

            assertThat(exception.getMessage()).isEqualTo("k must be greater or equal to zero");
        }

        @ParameterizedTest(name = "10 choose {0}")
        @ValueSource(ints = {11, 12, 13, 100, 1000, 10000})
        @DisplayName("Should throw exception when trying to calculate nCk for k greater then n")
        void shouldThrowExceptionWhenTryingToCalculateBinomialCoefficientForKgreaterThanN(int k) {
            final Exception exception = assertThrows(
                    IllegalArgumentException.class, () -> Combinatorics.binomial(10, k));

            assertThat(exception.getMessage()).isEqualTo("k must be less or equal to n");
        }

        @ParameterizedTest(name = "{0} choose {1}={2}")
        @ArgumentsSource(BinomialCoefficientProvider.class)
        @DisplayName("Verify values of nCk")
        void verifyBinomialCoefficientValues(int n, int k, long nCk) {
            assertThat(Combinatorics.binomial(n, k)).isEqualTo(nCk);
        }
    }

    static class FactorialsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(0, 1L),
                    Arguments.of(1, 1L),
                    Arguments.of(2, 2L),
                    Arguments.of(3, 6L),
                    Arguments.of(4, 24L),
                    Arguments.of(5, 120L),
                    Arguments.of(6, 720L),
                    Arguments.of(7, 5040L),
                    Arguments.of(8, 40320L),
                    Arguments.of(9, 362880L),
                    Arguments.of(10, 3628800L),
                    Arguments.of(11, 39916800L),
                    Arguments.of(12, 479001600L),
                    Arguments.of(13, 6227020800L),
                    Arguments.of(14, 87178291200L),
                    Arguments.of(15, 1307674368000L),
                    Arguments.of(16, 20922789888000L),
                    Arguments.of(17, 355687428096000L),
                    Arguments.of(18, 6402373705728000L),
                    Arguments.of(19, 121645100408832000L),
                    Arguments.of(20, 2432902008176640000L)
            );
        }
    }

    @Nested
    @DisplayName("Test Factorial calculation")
    class FactorialTest {
        @ParameterizedTest(name = "{0}!={1}")
        @ArgumentsSource(FactorialsProvider.class)
        @DisplayName("Verify values of all long-representable factorials")
        void verifyAllLongRepresentableFactorials(int n, long expectedFactorial) {
            assertThat(Combinatorics.factorial(n)).isEqualTo(expectedFactorial);
        }

        @ParameterizedTest(name = "({0})!")
        @ValueSource(ints = {-1, -2, -3, -10, -100, -1000, -10000})
        @DisplayName("Should throw exception when trying to calculate Factorial of negative number")
        void shouldThrowExceptionWhenTryingToCalculateFactorialOfNegativeNumber(int n) {
            final Exception exception = assertThrows(
                    IllegalArgumentException.class, () -> Combinatorics.factorial(n));

            assertThat(exception.getMessage()).isEqualTo("n must be greater or equal to zero");
        }

        @ParameterizedTest(name = "{0}!")
        @ValueSource(ints = {21, 22, 23, 100, 1000, 10000, 100000})
        @DisplayName("Should throw exception for factorials that cannot be represented by long")
        void shouldThrowMathArithmeticExceptionForFactorialsThatCannotBeRepresentedByLong(int n) {
            final Exception exception = assertThrows(
                    ArithmeticException.class, () -> Combinatorics.factorial(n));

            assertThat(exception.getMessage()).isEqualTo("long overflow");
        }
    }
}
