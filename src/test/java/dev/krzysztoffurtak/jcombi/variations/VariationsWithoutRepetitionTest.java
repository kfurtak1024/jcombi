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
package dev.krzysztoffurtak.jcombi.variations;

import dev.krzysztoffurtak.jcombi.VisitorRecorder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VariationsWithoutRepetitionTest {
    private VisitorRecorder<Object> variationsVisitorRecorder;

    @BeforeEach
    void setUp() {
        variationsVisitorRecorder = new VisitorRecorder<>();
    }

    @Test
    @DisplayName("Variations without repetition of 1 choose 1")
    void verifyVariationsOfInputSetWithOneElementChooseOne() {
        // Given
        final var variations = new VariationsWithoutRepetition<>(1, 1, variationsVisitorRecorder);

        // When
        variations.forEach(o -> {});

        // Then
        variationsVisitorRecorder.verify(new int[][]{
                { 0 }
        });
        assertThat(variations.n()).isEqualTo(1);
        assertThat(variations.k()).isEqualTo(1);
        assertThat(variations.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("Variations without repetition of 3 choose 1")
    void verifyVariationsOfInputSetWithThreeElementsChooseOne() {
        // Given
        final var variations = new VariationsWithoutRepetition<>(3, 1, variationsVisitorRecorder);

        // When
        variations.forEach(o -> {});

        // Then
        variationsVisitorRecorder.verify(new int[][]{
                { 0 },
                { 1 },
                { 2 }
        });
        assertThat(variations.n()).isEqualTo(3);
        assertThat(variations.k()).isEqualTo(1);
        assertThat(variations.count()).isEqualTo(3);
    }

    @Test
    @DisplayName("Stream of variations without repetition of 3 choose 1")
    void verifyStreamOfVariationsOfInputSetWithThreeElementsChooseOne() {
        // Given
        final var variations = new VariationsWithoutRepetition<>(3, 1, variationsVisitorRecorder);

        // When
        variations.stream().forEach(o -> {});

        // Then
        variationsVisitorRecorder.verify(new int[][]{
                { 0 },
                { 1 },
                { 2 }
        });
        assertThat(variations.n()).isEqualTo(3);
        assertThat(variations.k()).isEqualTo(1);
        assertThat(variations.count()).isEqualTo(3);
    }

    @Test
    @DisplayName("Variations without repetition of 3 choose 2")
    void verifyVariationsOfInputSetWithThreeElementsChooseTwo() {
        // Given
        final var variations = new VariationsWithoutRepetition<>(3, 2, variationsVisitorRecorder);

        // When
        variations.forEach(o -> {});

        // Then
        variationsVisitorRecorder.verify(new int[][]{
                { 0, 1 },
                { 0, 2 },
                { 1, 0 },
                { 1, 2 },
                { 2, 0 },
                { 2, 1 }
        });
        assertThat(variations.n()).isEqualTo(3);
        assertThat(variations.k()).isEqualTo(2);
        assertThat(variations.count()).isEqualTo(6);
    }

    @Test
    @DisplayName("Variations without repetition of 3 choose 3")
    void verifyVariationsOfInputSetWithThreeElementsChooseThree() {
        // Given
        final var variations = new VariationsWithoutRepetition<>(3, 3, variationsVisitorRecorder);

        // When
        variations.forEach(o -> {});

        // Then
        variationsVisitorRecorder.verify(new int[][]{
                { 0, 1, 2 },
                { 0, 2, 1 },
                { 1, 0, 2 },
                { 1, 2, 0 },
                { 2, 0, 1 },
                { 2, 1, 0 }
        });
        assertThat(variations.n()).isEqualTo(3);
        assertThat(variations.k()).isEqualTo(3);
        assertThat(variations.count()).isEqualTo(6);
    }

    @Test
    @DisplayName("Variations without repetition of 4 choose 2")
    void verifyVariationsOfInputSetWithFourElementsChooseTwo() {
        // Given
        final var variations = new VariationsWithoutRepetition<>(4, 2, variationsVisitorRecorder);

        // When
        variations.forEach(o -> {});

        // Then
        variationsVisitorRecorder.verify(new int[][]{
                { 0, 1 },
                { 0, 2 },
                { 0, 3 },
                { 1, 0 },
                { 1, 2 },
                { 1, 3 },
                { 2, 0 },
                { 2, 1 },
                { 2, 3 },
                { 3, 0 },
                { 3, 1 },
                { 3, 2 }
        });
        assertThat(variations.n()).isEqualTo(4);
        assertThat(variations.k()).isEqualTo(2);
        assertThat(variations.count()).isEqualTo(12);
    }

    @Test
    @DisplayName("Variations without repetition of 4 choose 3")
    void verifyVariationsOfInputSetWithFourElementsChooseThree() {
        // Given
        final var variations = new VariationsWithoutRepetition<>(4, 3, variationsVisitorRecorder);

        // When
        variations.forEach(o -> {});

        // Then
        variationsVisitorRecorder.verify(new int[][]{
                { 0, 1, 2 },
                { 0, 1, 3 },
                { 0, 2, 1 },
                { 0, 2, 3 },
                { 0, 3, 1 },
                { 0, 3, 2 },
                { 1, 0, 2 },
                { 1, 0, 3 },
                { 1, 2, 0 },
                { 1, 2, 3 },
                { 1, 3, 0 },
                { 1, 3, 2 },
                { 2, 0, 1 },
                { 2, 0, 3 },
                { 2, 1, 0 },
                { 2, 1, 3 },
                { 2, 3, 0 },
                { 2, 3, 1 },
                { 3, 0, 1 },
                { 3, 0, 2 },
                { 3, 1, 0 },
                { 3, 1, 2 },
                { 3, 2, 0 },
                { 3, 2, 1 }
        });
        assertThat(variations.n()).isEqualTo(4);
        assertThat(variations.k()).isEqualTo(3);
        assertThat(variations.count()).isEqualTo(24);
    }

    @Test
    @DisplayName("Variations without repetition of 4 choose 4")
    void verifyVariationsOfInputSetWithFourElementsChooseFour() {
        // Given
        final var variations = new VariationsWithoutRepetition<>(4, 4, variationsVisitorRecorder);

        // When
        variations.forEach(o -> {});

        // Then
        variationsVisitorRecorder.verify(new int[][]{
                { 0, 1, 2, 3 },
                { 0, 1, 3, 2 },
                { 0, 2, 1, 3 },
                { 0, 2, 3, 1 },
                { 0, 3, 1, 2 },
                { 0, 3, 2, 1 },
                { 1, 0, 2, 3 },
                { 1, 0, 3, 2 },
                { 1, 2, 0, 3 },
                { 1, 2, 3, 0 },
                { 1, 3, 0, 2 },
                { 1, 3, 2, 0 },
                { 2, 0, 1, 3 },
                { 2, 0, 3, 1 },
                { 2, 1, 0, 3 },
                { 2, 1, 3, 0 },
                { 2, 3, 0, 1 },
                { 2, 3, 1, 0 },
                { 3, 0, 1, 2 },
                { 3, 0, 2, 1 },
                { 3, 1, 0, 2 },
                { 3, 1, 2, 0 },
                { 3, 2, 0, 1 },
                { 3, 2, 1, 0 }
        });
        assertThat(variations.n()).isEqualTo(4);
        assertThat(variations.k()).isEqualTo(4);
        assertThat(variations.count()).isEqualTo(24);
    }

    @Test
    @DisplayName("Should allow to convert variations without repetition to variations with repetition")
    void shouldAllowToConvertVariationsWithoutRepetitionToVariationsWithRepetition() {
        // Given
        final var variations = new VariationsWithoutRepetition<>(2, 1, variationsVisitorRecorder);

        // When
        final var variationsWithRepetition = variations.withRepetition();

        // Then
        assertThat(variationsWithRepetition).isInstanceOf(VariationsWithRepetition.class);
    }

    @Test
    @DisplayName("Variations without repetition of 0 choose 0")
    void verifyVariationsOfEmptyInputSetChooseZero() {
        final var variations = new VariationsWithoutRepetition<>(0, 0, variationsVisitorRecorder);
        assertThat(variations).isEmpty();
        assertThat(variations.n()).isEqualTo(0);
        assertThat(variations.k()).isEqualTo(0);
        assertThat(variations.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Variations without repetition of 3 choose 0")
    void verifyVariationsOfInputSetWithThreeElementsChooseZero() {
        final var variations = new VariationsWithoutRepetition<>(3, 0, variationsVisitorRecorder);
        assertThat(variations).isEmpty();
        assertThat(variations.n()).isEqualTo(3);
        assertThat(variations.k()).isEqualTo(0);
        assertThat(variations.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Negative value of n")
    void verifyThatExceptionIsThrownWhenNIsNegative() {
        final Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new VariationsWithoutRepetition<>(-1, 0, variationsVisitorRecorder));
        assertThat(exception.getMessage()).isEqualTo("n must be greater of equal to zero");
    }

    @Test
    @DisplayName("Negative value of k")
    void verifyThatExceptionIsThrownWhenKIsNegative() {
        final Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new VariationsWithoutRepetition<>(0, -1, variationsVisitorRecorder));
        assertThat(exception.getMessage()).isEqualTo("k must be greater of equal to zero");
    }

    @Test
    @DisplayName("Invalid value of combination length (k > n)")
    void verifyThatExceptionIsThrownWhenVariationLengthIsInvalid() {
        final Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new VariationsWithoutRepetition<>(3, 4, variationsVisitorRecorder));
        assertThat(exception.getMessage()).isEqualTo("k must be less or equal to n");
    }

    @Test
    @DisplayName("Variations visitor is null")
    void verifyThatExceptionIsThrownWhenVariationsVisitorIsNull() {
        final Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new VariationsWithoutRepetition<>(2, 1, null));
        assertThat(exception.getMessage()).isEqualTo("variationsVisitor cannot be null");
    }
}
