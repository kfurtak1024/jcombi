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
package dev.krzysztoffurtak.jcombi.permutations;

import dev.krzysztoffurtak.jcombi.VisitorRecorder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PermutationsWithoutRepetitionTest {
    private VisitorRecorder<Object> permutationsVisitorRecorder;

    @BeforeEach
    void setUp() {
        permutationsVisitorRecorder = new VisitorRecorder<>();
    }

    @Test
    @DisplayName("Permutations without repetition of set with 1 element")
    void verifyPermutationsOfInputSetWithOneElement() {
        // Given
        final var permutations = new PermutationsWithoutRepetition<>(1, permutationsVisitorRecorder);

        // When
        permutations.forEach(o -> {});

        // Then
        permutationsVisitorRecorder.verify(new int[][]{
                { 0 }
        });
        assertThat(permutations.n()).isEqualTo(1);
        assertThat(permutations.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("Permutations without repetition of set with 2 elements")
    void verifyPermutationsOfInputSetWithTwoElements() {
        // Given
        final var permutations = new PermutationsWithoutRepetition<>(2, permutationsVisitorRecorder);

        // When
        permutations.forEach(o -> {});

        // Then
        permutationsVisitorRecorder.verify(new int[][]{
                { 0, 1 },
                { 1, 0 }
        });
        assertThat(permutations.n()).isEqualTo(2);
        assertThat(permutations.count()).isEqualTo(2);
    }

    @Test
    @DisplayName("Stream of permutations without repetition of set with 2 elements")
    void verifyStreamOfPermutationsOfInputSetWithTwoElements() {
        // Given
        final var permutations = new PermutationsWithoutRepetition<>(2, permutationsVisitorRecorder);

        // When
        permutations.stream().forEach(o -> {});

        // Then
        permutationsVisitorRecorder.verify(new int[][]{
                { 0, 1 },
                { 1, 0 }
        });
        assertThat(permutations.n()).isEqualTo(2);
        assertThat(permutations.count()).isEqualTo(2);
    }

    @Test
    @DisplayName("Permutations without repetition of set with 3 elements")
    void verifyPermutationsOfInputSetWithThreeElements() {
        // Given
        final var permutations = new PermutationsWithoutRepetition<>(3, permutationsVisitorRecorder);

        // When
        permutations.forEach(o -> {});

        // Then
        permutationsVisitorRecorder.verify(new int[][]{
                { 0, 1, 2 },
                { 0, 2, 1 },
                { 1, 0, 2 },
                { 1, 2, 0 },
                { 2, 0, 1 },
                { 2, 1, 0 }
        });
        assertThat(permutations.n()).isEqualTo(3);
        assertThat(permutations.count()).isEqualTo(6);
    }

    @Test
    @DisplayName("Permutations without repetition of set with 4 elements")
    void verifyPermutationsOfInputSetWithFourElements() {
        // Given
        final var permutations = new PermutationsWithoutRepetition<>(4, permutationsVisitorRecorder);

        // When
        permutations.forEach(o -> {});

        // Then
        permutationsVisitorRecorder.verify(new int[][]{
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
        assertThat(permutations.n()).isEqualTo(4);
        assertThat(permutations.count()).isEqualTo(24);
    }

    @Test
    @DisplayName("Permutations without repetition of set with 5 elements")
    void verifyPermutationsOfInputSetWithFiveElements() {
        // Given
        final var permutations = new PermutationsWithoutRepetition<>(5, permutationsVisitorRecorder);

        // When
        permutations.forEach(o -> {});

        // Then
        permutationsVisitorRecorder.verify(new int[][]{
                { 0, 1, 2, 3, 4 },
                { 0, 1, 2, 4, 3 },
                { 0, 1, 3, 2, 4 },
                { 0, 1, 3, 4, 2 },
                { 0, 1, 4, 2, 3 },
                { 0, 1, 4, 3, 2 },
                { 0, 2, 1, 3, 4 },
                { 0, 2, 1, 4, 3 },
                { 0, 2, 3, 1, 4 },
                { 0, 2, 3, 4, 1 },
                { 0, 2, 4, 1, 3 },
                { 0, 2, 4, 3, 1 },
                { 0, 3, 1, 2, 4 },
                { 0, 3, 1, 4, 2 },
                { 0, 3, 2, 1, 4 },
                { 0, 3, 2, 4, 1 },
                { 0, 3, 4, 1, 2 },
                { 0, 3, 4, 2, 1 },
                { 0, 4, 1, 2, 3 },
                { 0, 4, 1, 3, 2 },
                { 0, 4, 2, 1, 3 },
                { 0, 4, 2, 3, 1 },
                { 0, 4, 3, 1, 2 },
                { 0, 4, 3, 2, 1 },
                { 1, 0, 2, 3, 4 },
                { 1, 0, 2, 4, 3 },
                { 1, 0, 3, 2, 4 },
                { 1, 0, 3, 4, 2 },
                { 1, 0, 4, 2, 3 },
                { 1, 0, 4, 3, 2 },
                { 1, 2, 0, 3, 4 },
                { 1, 2, 0, 4, 3 },
                { 1, 2, 3, 0, 4 },
                { 1, 2, 3, 4, 0 },
                { 1, 2, 4, 0, 3 },
                { 1, 2, 4, 3, 0 },
                { 1, 3, 0, 2, 4 },
                { 1, 3, 0, 4, 2 },
                { 1, 3, 2, 0, 4 },
                { 1, 3, 2, 4, 0 },
                { 1, 3, 4, 0, 2 },
                { 1, 3, 4, 2, 0 },
                { 1, 4, 0, 2, 3 },
                { 1, 4, 0, 3, 2 },
                { 1, 4, 2, 0, 3 },
                { 1, 4, 2, 3, 0 },
                { 1, 4, 3, 0, 2 },
                { 1, 4, 3, 2, 0 },
                { 2, 0, 1, 3, 4 },
                { 2, 0, 1, 4, 3 },
                { 2, 0, 3, 1, 4 },
                { 2, 0, 3, 4, 1 },
                { 2, 0, 4, 1, 3 },
                { 2, 0, 4, 3, 1 },
                { 2, 1, 0, 3, 4 },
                { 2, 1, 0, 4, 3 },
                { 2, 1, 3, 0, 4 },
                { 2, 1, 3, 4, 0 },
                { 2, 1, 4, 0, 3 },
                { 2, 1, 4, 3, 0 },
                { 2, 3, 0, 1, 4 },
                { 2, 3, 0, 4, 1 },
                { 2, 3, 1, 0, 4 },
                { 2, 3, 1, 4, 0 },
                { 2, 3, 4, 0, 1 },
                { 2, 3, 4, 1, 0 },
                { 2, 4, 0, 1, 3 },
                { 2, 4, 0, 3, 1 },
                { 2, 4, 1, 0, 3 },
                { 2, 4, 1, 3, 0 },
                { 2, 4, 3, 0, 1 },
                { 2, 4, 3, 1, 0 },
                { 3, 0, 1, 2, 4 },
                { 3, 0, 1, 4, 2 },
                { 3, 0, 2, 1, 4 },
                { 3, 0, 2, 4, 1 },
                { 3, 0, 4, 1, 2 },
                { 3, 0, 4, 2, 1 },
                { 3, 1, 0, 2, 4 },
                { 3, 1, 0, 4, 2 },
                { 3, 1, 2, 0, 4 },
                { 3, 1, 2, 4, 0 },
                { 3, 1, 4, 0, 2 },
                { 3, 1, 4, 2, 0 },
                { 3, 2, 0, 1, 4 },
                { 3, 2, 0, 4, 1 },
                { 3, 2, 1, 0, 4 },
                { 3, 2, 1, 4, 0 },
                { 3, 2, 4, 0, 1 },
                { 3, 2, 4, 1, 0 },
                { 3, 4, 0, 1, 2 },
                { 3, 4, 0, 2, 1 },
                { 3, 4, 1, 0, 2 },
                { 3, 4, 1, 2, 0 },
                { 3, 4, 2, 0, 1 },
                { 3, 4, 2, 1, 0 },
                { 4, 0, 1, 2, 3 },
                { 4, 0, 1, 3, 2 },
                { 4, 0, 2, 1, 3 },
                { 4, 0, 2, 3, 1 },
                { 4, 0, 3, 1, 2 },
                { 4, 0, 3, 2, 1 },
                { 4, 1, 0, 2, 3 },
                { 4, 1, 0, 3, 2 },
                { 4, 1, 2, 0, 3 },
                { 4, 1, 2, 3, 0 },
                { 4, 1, 3, 0, 2 },
                { 4, 1, 3, 2, 0 },
                { 4, 2, 0, 1, 3 },
                { 4, 2, 0, 3, 1 },
                { 4, 2, 1, 0, 3 },
                { 4, 2, 1, 3, 0 },
                { 4, 2, 3, 0, 1 },
                { 4, 2, 3, 1, 0 },
                { 4, 3, 0, 1, 2 },
                { 4, 3, 0, 2, 1 },
                { 4, 3, 1, 0, 2 },
                { 4, 3, 1, 2, 0 },
                { 4, 3, 2, 0, 1 },
                { 4, 3, 2, 1, 0 }
        });
        assertThat(permutations.n()).isEqualTo(5);
        assertThat(permutations.count()).isEqualTo(120);
    }

    @Test
    @DisplayName("Permutations without repetition of set with 0 elements")
    void verifyPermutationsOfInputSetWithZeroElements() {
        final var permutations = new PermutationsWithoutRepetition<>(0, permutationsVisitorRecorder);
        assertThat(permutations).isEmpty();
        assertThat(permutations.n()).isEqualTo(0);
        assertThat(permutations.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Negative value of n")
    void verifyThatExceptionIsThrownWhenNIsNegative() {
        final Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new PermutationsWithoutRepetition<>(-1, permutationsVisitorRecorder));
        assertThat(exception.getMessage()).isEqualTo("n must be greater of equal to zero");
    }

    @Test
    @DisplayName("Permutations visitor is null")
    void verifyThatExceptionIsThrownWhenPermutationsVisitorIsNull() {
        final Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new PermutationsWithoutRepetition<>(2, null));
        assertThat(exception.getMessage()).isEqualTo("permutationsVisitor cannot be null");
    }
}