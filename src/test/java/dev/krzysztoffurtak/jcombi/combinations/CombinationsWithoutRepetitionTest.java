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
package dev.krzysztoffurtak.jcombi.combinations;

import dev.krzysztoffurtak.jcombi.VisitorRecorder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CombinationsWithoutRepetitionTest {
    private VisitorRecorder<Object> combinationsVisitorRecorder;

    @BeforeEach
    void setUp() {
        combinationsVisitorRecorder = new VisitorRecorder<>();
    }

    @Test
    @DisplayName("Combinations without repetition of 1 choose 1")
    void verifyCombinationsOfInputSetWithOneElementChooseOne() {
        // Given
        final var combinations = new CombinationsWithoutRepetition<>(1, 1, combinationsVisitorRecorder);

        // When
        combinations.forEach(o -> {});

        // Then
        combinationsVisitorRecorder.verify(new int[][]{
                { 0 }
        });
        assertThat(combinations.n()).isEqualTo(1);
        assertThat(combinations.k()).isEqualTo(1);
        assertThat(combinations.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("Combinations without repetition of 3 choose 1")
    void verifyCombinationsOfInputSetWithThreeElementsChooseOne() {
        // Given
        final var combinations = new CombinationsWithoutRepetition<>(3, 1, combinationsVisitorRecorder);

        // When
        combinations.forEach(o -> {});

        // Then
        combinationsVisitorRecorder.verify(new int[][]{
                { 0 },
                { 1 },
                { 2 }
        });
        assertThat(combinations.n()).isEqualTo(3);
        assertThat(combinations.k()).isEqualTo(1);
        assertThat(combinations.count()).isEqualTo(3);
    }

    @Test
    @DisplayName("Stream of combinations without repetition of 3 choose 1")
    void verifyStreamOfCombinationsOfInputSetWithThreeElementsChooseOne() {
        // Given
        final var combinations = new CombinationsWithoutRepetition<>(3, 1, combinationsVisitorRecorder);

        // When
        combinations.stream().forEach(o -> {});

        // Then
        combinationsVisitorRecorder.verify(new int[][]{
                { 0 },
                { 1 },
                { 2 }
        });
        assertThat(combinations.n()).isEqualTo(3);
        assertThat(combinations.k()).isEqualTo(1);
        assertThat(combinations.count()).isEqualTo(3);
    }

    @Test
    @DisplayName("Combinations without repetition of 3 choose 2")
    void verifyCombinationsOfInputSetWithThreeElementsChooseTwo() {
        // Given
        final var combinations = new CombinationsWithoutRepetition<>(3, 2, combinationsVisitorRecorder);

        // When
        combinations.forEach(o -> {});

        // Then
        combinationsVisitorRecorder.verify(new int[][]{
                { 0, 1 },
                { 0, 2 },
                { 1, 2 }
        });
        assertThat(combinations.n()).isEqualTo(3);
        assertThat(combinations.k()).isEqualTo(2);
        assertThat(combinations.count()).isEqualTo(3);
    }

    @Test
    @DisplayName("Combinations without repetition of 3 choose 3")
    void verifyCombinationsOfInputSetWithThreeElementsChooseThree() {
        // Given
        final var combinations = new CombinationsWithoutRepetition<>(3, 3, combinationsVisitorRecorder);

        // When
        combinations.forEach(o -> {});

        // Then
        combinationsVisitorRecorder.verify(new int[][]{
                { 0, 1, 2 }
        });
        assertThat(combinations.n()).isEqualTo(3);
        assertThat(combinations.k()).isEqualTo(3);
        assertThat(combinations.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("Combinations without repetition of 4 choose 2")
    void verifyCombinationsOfInputSetWithFourElementsChooseTwo() {
        // Given
        final var combinations = new CombinationsWithoutRepetition<>(4, 2, combinationsVisitorRecorder);

        // When
        combinations.forEach(o -> {});

        // Then
        combinationsVisitorRecorder.verify(new int[][]{
                { 0, 1 },
                { 0, 2 },
                { 0, 3 },
                { 1, 2 },
                { 1, 3 },
                { 2, 3 }
        });
        assertThat(combinations.n()).isEqualTo(4);
        assertThat(combinations.k()).isEqualTo(2);
        assertThat(combinations.count()).isEqualTo(6);
    }

    @Test
    @DisplayName("Combinations without repetition of 4 choose 3")
    void verifyCombinationsOfInputSetWithFourElementsChooseThree() {
        // Given
        final var combinations = new CombinationsWithoutRepetition<>(4, 3, combinationsVisitorRecorder);

        // When
        combinations.forEach(o -> {});

        // Then
        combinationsVisitorRecorder.verify(new int[][]{
                { 0, 1, 2 },
                { 0, 1, 3 },
                { 0, 2, 3 },
                { 1, 2, 3 }
        });
        assertThat(combinations.n()).isEqualTo(4);
        assertThat(combinations.k()).isEqualTo(3);
        assertThat(combinations.count()).isEqualTo(4);
    }

    @Test
    @DisplayName("Should allow to convert combinations without repetition to combinations with repetition")
    void shouldAllowToConvertCombinationsWithoutRepetitionToCombinationsWithRepetition() {
        // Given
        final var combinations = new CombinationsWithoutRepetition<>(2, 1, combinationsVisitorRecorder);

        // When
        final var combinationsWithRepetition = combinations.withRepetition();

        // Then
        assertThat(combinationsWithRepetition).isInstanceOf(CombinationsWithRepetition.class);
    }

    @Test
    @DisplayName("Combinations without repetition of 0 choose 0")
    void verifyCombinationsOfEmptyInputSetChooseZero() {
        final var combinations = new CombinationsWithoutRepetition<>(0, 0, combinationsVisitorRecorder);
        assertThat(combinations).isEmpty();
        assertThat(combinations.n()).isEqualTo(0);
        assertThat(combinations.k()).isEqualTo(0);
        assertThat(combinations.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Combinations without repetition of 3 choose 0")
    void verifyCombinationsOfInputSetWithThreeElementsChooseZero() {
        final var combinations = new CombinationsWithoutRepetition<>(3, 0, combinationsVisitorRecorder);
        assertThat(combinations).isEmpty();
        assertThat(combinations.n()).isEqualTo(3);
        assertThat(combinations.k()).isEqualTo(0);
        assertThat(combinations.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Negative value of n")
    void verifyThatExceptionIsThrownWhenNIsNegative() {
        final Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new CombinationsWithoutRepetition<>(-1, 0, combinationsVisitorRecorder));
        assertThat(exception.getMessage()).isEqualTo("n must be greater of equal to zero");
    }

    @Test
    @DisplayName("Negative value of k")
    void verifyThatExceptionIsThrownWhenKIsNegative() {
        final Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new CombinationsWithoutRepetition<>(0, -1, combinationsVisitorRecorder));
        assertThat(exception.getMessage()).isEqualTo("k must be greater of equal to zero");
    }

    @Test
    @DisplayName("Invalid value of combination length (k > n)")
    void verifyThatExceptionIsThrownWhenCombinationLengthIsInvalid() {
        final Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new CombinationsWithoutRepetition<>(3, 4, combinationsVisitorRecorder));
        assertThat(exception.getMessage()).isEqualTo("k must be less or equal to n");
    }

    @Test
    @DisplayName("Combinations visitor is null")
    void verifyThatExceptionIsThrownWhenCombinationsVisitorIsNull() {
        final Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new CombinationsWithoutRepetition<>(2, 1, null));
        assertThat(exception.getMessage()).isEqualTo("combinationsVisitor cannot be null");
    }
}
