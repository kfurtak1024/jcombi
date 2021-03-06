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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CombinationsBuilderTest {
    @Test
    @DisplayName("Verify combinations builder for generic type")
    void verifyCombinationsWithoutRepetitionBuilderForGenericType() {
        // Given
        final String[] inputSet = new String[] { "A", "B", "C" };
        final int k = 2;

        // When
        final var combinations =
                CombinationsBuilder.builder().of(inputSet).choose(k);

        // Then
        assertThat(combinations.n()).isEqualTo(inputSet.length);
        assertThat(combinations.k()).isEqualTo(k);
        assertThat(combinations.count()).isEqualTo(3);
        assertThat(combinations).containsExactly(
                List.of("A", "B"),
                List.of("A", "C"),
                List.of("B", "C")
        );
    }

    @Test
    @DisplayName("Verify combinations with repetition builder for generic type")
    void verifyCombinationsWithRepetitionBuilderForGenericType() {
        // Given
        final String[] inputSet = new String[] { "A", "B", "C" };
        final int k = 2;

        // When
        final var combinations =
                CombinationsBuilder.builder().of(inputSet).choose(k).withRepetition();

        // Then
        assertThat(combinations.n()).isEqualTo(inputSet.length);
        assertThat(combinations.k()).isEqualTo(k);
        assertThat(combinations.count()).isEqualTo(6);
        assertThat(combinations).containsExactly(
                List.of("A", "A"),
                List.of("A", "B"),
                List.of("A", "C"),
                List.of("B", "B"),
                List.of("B", "C"),
                List.of("C", "C")
        );
    }

    @Test
    @DisplayName("Verify combinations builder for ints")
    void verifyCombinationsWithoutRepetitionBuilderForInts() {
        // Given
        final int n = 3;
        final int k = 2;

        // When
        final var combinations = CombinationsBuilder.build(n, k);

        // Then
        assertThat(combinations.n()).isEqualTo(n);
        assertThat(combinations.k()).isEqualTo(k);
        assertThat(combinations.count()).isEqualTo(3);
        assertThat(combinations).containsExactly(
                new int[] { 0, 1 },
                new int[] { 0, 2 },
                new int[] { 1, 2 }
        );
    }

    @Test
    @DisplayName("Verify combinations with repetition builder for ints")
    void verifyCombinationsWithRepetitionBuilderForInts() {
        // Given
        final int n = 3;
        final int k = 2;

        // When
        final var combinations = CombinationsBuilder.build(n, k).withRepetition();

        // Then
        assertThat(combinations.n()).isEqualTo(n);
        assertThat(combinations.k()).isEqualTo(k);
        assertThat(combinations.count()).isEqualTo(6);
        assertThat(combinations).containsExactly(
                new int[] { 0, 0 },
                new int[] { 0, 1 },
                new int[] { 0, 2 },
                new int[] { 1, 1 },
                new int[] { 1, 2 },
                new int[] { 2, 2 }
        );
    }
}
