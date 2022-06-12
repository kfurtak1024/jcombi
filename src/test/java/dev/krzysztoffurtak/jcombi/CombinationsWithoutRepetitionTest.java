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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CombinationsWithoutRepetitionTest {
    @Nested
    class GenericCombinationsTest {
        @Test
        @DisplayName("Combinations without repetition of {} choose 0")
        void verifyCombinationsOfEmptyInputSetChooseZero() {
            final var combinations = Combinatorics.combinations()
                    .of()
                    .choose(0);
            assertThat(combinations).isEmpty();
            assertThat(combinations.n()).isEqualTo(0);
            assertThat(combinations.k()).isEqualTo(0);
            assertThat(combinations.count()).isEqualTo(0);
        }

        @Test
        @DisplayName("Combinations without repetition of {A, B, C} choose 0")
        void verifyCombinationsOfInputSetWithThreeElementsChooseZero() {
            final var combinations = Combinatorics.combinations()
                    .of("A", "B", "C")
                    .choose(0);
            assertThat(combinations).isEmpty();
            assertThat(combinations.n()).isEqualTo(3);
            assertThat(combinations.k()).isEqualTo(0);
            assertThat(combinations.count()).isEqualTo(0);
        }

        @Test
        @DisplayName("Combinations without repetition of {A, B, C} choose 1")
        void verifyCombinationsOfInputSetWithThreeElementsChooseOne() {
            final var combinations = Combinatorics.combinations()
                    .of("A", "B", "C")
                    .choose(1);
            assertThat(combinations).containsExactly(
                    List.of("A"),
                    List.of("B"),
                    List.of("C")
            );
            assertThat(combinations.n()).isEqualTo(3);
            assertThat(combinations.k()).isEqualTo(1);
            assertThat(combinations.count()).isEqualTo(3);
        }

        @Test
        @DisplayName("Combinations without repetition of {A, B, C} choose 1")
        void verifyStreamOfCombinationsOfInputSetWithThreeElementsChooseOne() {
            final var combinations = Combinatorics.combinations()
                    .of("A", "B", "C")
                    .choose(1);
            assertThat(combinations.stream()).containsExactly(
                    List.of("A"),
                    List.of("B"),
                    List.of("C")
            );
            assertThat(combinations.stream().count()).isEqualTo(3);
        }

        @Test
        @DisplayName("Combinations without repetition of {A, B, C} choose 2")
        void verifyCombinationsOfInputSetWithThreeElementsChooseTwo() {
            final var combinations = Combinatorics.combinations()
                    .of("A", "B", "C")
                    .choose(2);
            assertThat(combinations).containsExactly(
                    List.of("A", "B"),
                    List.of("A", "C"),
                    List.of("B", "C")
            );
            assertThat(combinations.n()).isEqualTo(3);
            assertThat(combinations.k()).isEqualTo(2);
            assertThat(combinations.count()).isEqualTo(3);
        }

        @Test
        @DisplayName("Combinations without repetition of {A, B, C} choose 3")
        void verifyCombinationsOfInputSetWithThreeElementsChooseThree() {
            final var combinations = Combinatorics.combinations()
                    .of("A", "B", "C")
                    .choose(3);
            assertThat(combinations).containsExactly(
                    List.of("A", "B", "C")
            );
            assertThat(combinations.n()).isEqualTo(3);
            assertThat(combinations.k()).isEqualTo(3);
            assertThat(combinations.count()).isEqualTo(1);
        }

        @Test
        @DisplayName("Combinations without repetition of {A, B, C, D} choose 2")
        void verifyCombinationsOfInputSetWithFourElementsChooseTwo() {
            final var combinations = Combinatorics.combinations()
                    .of("A", "B", "C", "D")
                    .choose(2);
            assertThat(combinations).containsExactly(
                    List.of("A", "B"),
                    List.of("A", "C"),
                    List.of("A", "D"),
                    List.of("B", "C"),
                    List.of("B", "D"),
                    List.of("C", "D")
            );
            assertThat(combinations.n()).isEqualTo(4);
            assertThat(combinations.k()).isEqualTo(2);
            assertThat(combinations.count()).isEqualTo(6);
        }

        @Test
        @DisplayName("Combinations without repetition of {A, B, C, D} choose 3")
        void verifyCombinationsOfInputSetWithFourElementsChooseThree() {
            final var combinations = Combinatorics.combinations()
                    .of("A", "B", "C", "D")
                    .choose(3);
            assertThat(combinations).containsExactly(
                    List.of("A", "B", "C"),
                    List.of("A", "B", "D"),
                    List.of("A", "C", "D"),
                    List.of("B", "C", "D")
            );
            assertThat(combinations.n()).isEqualTo(4);
            assertThat(combinations.k()).isEqualTo(3);
            assertThat(combinations.count()).isEqualTo(4);
        }

        @Test
        @DisplayName("Negative value of combination length (k < 0)")
        void verifyThatExceptionIsThrownWhenCombinationLengthIsNegative() {
            final Exception exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> Combinatorics.combinations()
                            .of()
                            .choose(-1));
            assertThat(exception.getMessage()).isEqualTo("k must be greater of equal to zero");
        }

        @Test
        @DisplayName("Invalid value of combination length (k > n)")
        void verifyThatExceptionIsThrownWhenCombinationLengthIsInvalid() {
            final Exception exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> Combinatorics.combinations()
                            .of("A", "B", "C")
                            .choose(4));
            assertThat(exception.getMessage()).isEqualTo("k must be less or equal to n");
        }
    }

    @Nested
    class IntCombinationsTest {
        @Test
        @DisplayName("Combinations without repetition of 0 choose 0")
        void verifyCombinationsOfEmptyInputSetChooseZero() {
            final var combinations = Combinatorics.combinations(0, 0);
            assertThat(combinations).isEmpty();
            assertThat(combinations.n()).isEqualTo(0);
            assertThat(combinations.k()).isEqualTo(0);
            assertThat(combinations.count()).isEqualTo(0);
        }

        @Test
        @DisplayName("Combinations without repetition of 3 choose 0")
        void verifyCombinationsOfInputSetWithThreeElementsChooseZero() {
            final var combinations = Combinatorics.combinations(3, 0);
            assertThat(combinations).isEmpty();
            assertThat(combinations.n()).isEqualTo(3);
            assertThat(combinations.k()).isEqualTo(0);
            assertThat(combinations.count()).isEqualTo(0);
        }

        @Test
        @DisplayName("Combinations without repetition of 3 choose 1")
        void verifyCombinationsOfInputSetWithThreeElementsChooseOne() {
            final var combinations = Combinatorics.combinations(3, 1);
            assertThat(combinations).containsExactly(
                    new int[] { 0 },
                    new int[] { 1 },
                    new int[] { 2 }
            );
            assertThat(combinations.n()).isEqualTo(3);
            assertThat(combinations.k()).isEqualTo(1);
            assertThat(combinations.count()).isEqualTo(3);
        }

        @Test
        @DisplayName("Combinations without repetition of 3 choose 1")
        void verifyStreamOfCombinationsOfInputSetWithThreeElementsChooseOne() {
            final var combinations = Combinatorics.combinations(3, 1);
            assertThat(combinations.stream()).containsExactly(
                    new int[] { 0 },
                    new int[] { 1 },
                    new int[] { 2 }
            );
            assertThat(combinations.stream().count()).isEqualTo(3);
        }

        @Test
        @DisplayName("Combinations without repetition of 3 choose 2")
        void verifyCombinationsOfInputSetWithThreeElementsChooseTwo() {
            final var combinations = Combinatorics.combinations(3, 2);
            assertThat(combinations).containsExactly(
                    new int[] { 0, 1 },
                    new int[] { 0, 2 },
                    new int[] { 1, 2 }
            );
            assertThat(combinations.n()).isEqualTo(3);
            assertThat(combinations.k()).isEqualTo(2);
            assertThat(combinations.count()).isEqualTo(3);
        }

        @Test
        @DisplayName("Combinations without repetition of 3 choose 3")
        void verifyCombinationsOfInputSetWithThreeElementsChooseThree() {
            final var combinations = Combinatorics.combinations(3, 3);
            assertThat(combinations).containsExactly(
                    new int[] { 0, 1, 2 }
            );
            assertThat(combinations.n()).isEqualTo(3);
            assertThat(combinations.k()).isEqualTo(3);
            assertThat(combinations.count()).isEqualTo(1);
        }

        @Test
        @DisplayName("Combinations without repetition of 4 choose 2")
        void verifyCombinationsOfInputSetWithFourElementsChooseTwo() {
            final var combinations = Combinatorics.combinations(4, 2);
            assertThat(combinations).containsExactly(
                    new int[] { 0, 1 },
                    new int[] { 0, 2 },
                    new int[] { 0, 3 },
                    new int[] { 1, 2 },
                    new int[] { 1, 3 },
                    new int[] { 2, 3 }
            );
            assertThat(combinations.n()).isEqualTo(4);
            assertThat(combinations.k()).isEqualTo(2);
            assertThat(combinations.count()).isEqualTo(6);
        }

        @Test
        @DisplayName("Combinations without repetition of 4 choose 3")
        void verifyCombinationsOfInputSetWithFourElementsChooseThree() {
            final var combinations = Combinatorics.combinations(4, 3);
            assertThat(combinations).containsExactly(
                    new int[] { 0, 1, 2 },
                    new int[] { 0, 1, 3 },
                    new int[] { 0, 2, 3 },
                    new int[] { 1, 2, 3 }
            );
            assertThat(combinations.n()).isEqualTo(4);
            assertThat(combinations.k()).isEqualTo(3);
            assertThat(combinations.count()).isEqualTo(4);
        }

        @Test
        @DisplayName("Negative value of n")
        void verifyThatExceptionIsThrownWhenNIsNegative() {
            final Exception exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> Combinatorics.combinations(-1, 0));
            assertThat(exception.getMessage()).isEqualTo("n must be greater of equal to zero");
        }

        @Test
        @DisplayName("Negative value of k")
        void verifyThatExceptionIsThrownWhenKIsNegative() {
            final Exception exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> Combinatorics.combinations(0, -1));
            assertThat(exception.getMessage()).isEqualTo("k must be greater of equal to zero");
        }

        @Test
        @DisplayName("Invalid value of combination length (k > n)")
        void verifyThatExceptionIsThrownWhenCombinationLengthIsInvalid() {
            final Exception exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> Combinatorics.combinations(3, 4));
            assertThat(exception.getMessage()).isEqualTo("k must be less or equal to n");
        }
    }
}
