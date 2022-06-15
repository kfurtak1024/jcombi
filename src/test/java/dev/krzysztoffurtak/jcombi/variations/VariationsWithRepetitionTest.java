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

class VariationsWithRepetitionTest {
    private VisitorRecorder<Object> variationsVisitorRecorder;

    @BeforeEach
    void setUp() {
        variationsVisitorRecorder = new VisitorRecorder<>();
    }

    @Test
    @DisplayName("Variations with repetition of 1 choose 1")
    void verifyVariationsOfInputSetWithOneElementChooseOne() {
        // Given
        final var variations = new VariationsWithRepetition<>(1, 1, variationsVisitorRecorder);

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
    @DisplayName("Variations with repetition of 3 choose 1")
    void verifyVariationsOfInputSetWithThreeElementsChooseOne() {
        // Given
        final var variations = new VariationsWithRepetition<>(3, 1, variationsVisitorRecorder);

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
    @DisplayName("Stream of variations with repetition of 3 choose 1")
    void verifyStreamOfVariationsOfInputSetWithThreeElementsChooseOne() {
        // Given
        final var variations = new VariationsWithRepetition<>(3, 1, variationsVisitorRecorder);

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
    @DisplayName("Variations of 3 choose 2")
    void verifyVariationsOfInputSetWithThreeElementsChooseTwo() {
        // Given
        final var variations = new VariationsWithRepetition<>(3, 2, variationsVisitorRecorder);

        // When
        variations.forEach(o -> {});

        // Then
        variationsVisitorRecorder.verify(new int[][]{
                { 0, 0 },
                { 0, 1 },
                { 0, 2 },
                { 1, 0 },
                { 1, 1 },
                { 1, 2 },
                { 2, 0 },
                { 2, 1 },
                { 2, 2 }
        });
        assertThat(variations.n()).isEqualTo(3);
        assertThat(variations.k()).isEqualTo(2);
        assertThat(variations.count()).isEqualTo(9);
    }

    @Test
    @DisplayName("Variations of 3 choose 3")
    void verifyVariationsOfInputSetWithThreeElementsChooseThree() {
        // Given
        final var variations = new VariationsWithRepetition<>(3, 3, variationsVisitorRecorder);

        // When
        variations.forEach(o -> {});

        // Then
        variationsVisitorRecorder.verify(new int[][]{
                { 0, 0, 0 },
                { 0, 0, 1 },
                { 0, 0, 2 },
                { 0, 1, 0 },
                { 0, 1, 1 },
                { 0, 1, 2 },
                { 0, 2, 0 },
                { 0, 2, 1 },
                { 0, 2, 2 },
                { 1, 0, 0 },
                { 1, 0, 1 },
                { 1, 0, 2 },
                { 1, 1, 0 },
                { 1, 1, 1 },
                { 1, 1, 2 },
                { 1, 2, 0 },
                { 1, 2, 1 },
                { 1, 2, 2 },
                { 2, 0, 0 },
                { 2, 0, 1 },
                { 2, 0, 2 },
                { 2, 1, 0 },
                { 2, 1, 1 },
                { 2, 1, 2 },
                { 2, 2, 0 },
                { 2, 2, 1 },
                { 2, 2, 2 }
        });
        assertThat(variations.n()).isEqualTo(3);
        assertThat(variations.k()).isEqualTo(3);
        assertThat(variations.count()).isEqualTo(27);
    }

    @Test
    @DisplayName("Variations of 2 choose 4 (k > n)")
    void verifyVariationsOfInputSetWithFourElementsChooseTwo() {
        // Given
        final var variations = new VariationsWithRepetition<>(2, 4, variationsVisitorRecorder);

        // When
        variations.forEach(o -> {});

        // Then
        variationsVisitorRecorder.verify(new int[][]{
                { 0, 0, 0, 0 },
                { 0, 0, 0, 1 },
                { 0, 0, 1, 0 },
                { 0, 0, 1, 1 },
                { 0, 1, 0, 0 },
                { 0, 1, 0, 1 },
                { 0, 1, 1, 0 },
                { 0, 1, 1, 1 },
                { 1, 0, 0, 0 },
                { 1, 0, 0, 1 },
                { 1, 0, 1, 0 },
                { 1, 0, 1, 1 },
                { 1, 1, 0, 0 },
                { 1, 1, 0, 1 },
                { 1, 1, 1, 0 },
                { 1, 1, 1, 1 }
        });
        assertThat(variations.n()).isEqualTo(2);
        assertThat(variations.k()).isEqualTo(4);
        assertThat(variations.count()).isEqualTo(16);
    }

    @Test
    @DisplayName("Should allow to convert variations with repetition to variations without repetition")
    void shouldAllowToConvertVariationsWithRepetitionToVariationsWithoutRepetition() {
        // Given
        final var variations = new VariationsWithRepetition<>(2, 1, variationsVisitorRecorder);

        // When
        final var variationsWithoutRepetition = variations.withoutRepetition();

        // Then
        assertThat(variationsWithoutRepetition).isInstanceOf(VariationsWithoutRepetition.class);
    }

    @Test
    @DisplayName("Variations with repetition of 0 choose 0")
    void verifyVariationsOfEmptyInputSetChooseZero() {
        final var variations = new VariationsWithRepetition<>(0, 0, variationsVisitorRecorder);
        assertThat(variations).isEmpty();
        assertThat(variations.n()).isEqualTo(0);
        assertThat(variations.k()).isEqualTo(0);
        assertThat(variations.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Variations with repetition of 3 choose 0")
    void verifyVariationsOfInputSetWithThreeElementsChooseZero() {
        final var variations = new VariationsWithRepetition<>(3, 0, variationsVisitorRecorder);
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
                () -> new VariationsWithRepetition<>(-1, 0, variationsVisitorRecorder));
        assertThat(exception.getMessage()).isEqualTo("n must be greater of equal to zero");
    }

    @Test
    @DisplayName("Negative value of k")
    void verifyThatExceptionIsThrownWhenKIsNegative() {
        final Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new VariationsWithRepetition<>(0, -1, variationsVisitorRecorder));
        assertThat(exception.getMessage()).isEqualTo("k must be greater of equal to zero");
    }

    @Test
    @DisplayName("Variations visitor is null")
    void verifyThatExceptionIsThrownWhenVariationsVisitorIsNull() {
        final Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new VariationsWithRepetition<>(2, 1, null));
        assertThat(exception.getMessage()).isEqualTo("variationsVisitor cannot be null");
    }
}
