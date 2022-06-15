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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class VariationsBuilderTest {
    @Test
    @DisplayName("Verify variations builder for generic type")
    void verifyVariationsWithoutRepetitionBuilderForGenericType() {
        // Given
        final String[] inputSet = new String[] { "A", "B", "C" };
        final int k = 2;

        // When
        final var variations =
                VariationsBuilder.builder().of(inputSet).choose(k);

        // Then
        assertThat(variations.n()).isEqualTo(inputSet.length);
        assertThat(variations.k()).isEqualTo(k);
        assertThat(variations.count()).isEqualTo(6);
        assertThat(variations).containsExactly(
                List.of("A", "B"),
                List.of("A", "C"),
                List.of("B", "A"),
                List.of("B", "C"),
                List.of("C", "A"),
                List.of("C", "B")
        );
    }

    @Test
    @DisplayName("Verify variations with repetition builder for generic type")
    void verifyVariationsWithRepetitionBuilderForGenericType() {
        // Given
        final String[] inputSet = new String[] { "A", "B", "C" };
        final int k = 2;

        // When
        final var variations =
                VariationsBuilder.builder().of(inputSet).choose(k).withRepetition();

        // Then
        assertThat(variations.n()).isEqualTo(inputSet.length);
        assertThat(variations.k()).isEqualTo(k);
        assertThat(variations.count()).isEqualTo(9);
        assertThat(variations).containsExactly(
                List.of("A", "A"),
                List.of("A", "B"),
                List.of("A", "C"),
                List.of("B", "A"),
                List.of("B", "B"),
                List.of("B", "C"),
                List.of("C", "A"),
                List.of("C", "B"),
                List.of("C", "C")
        );
    }

    @Test
    @DisplayName("Verify variations builder for ints")
    void verifyVariationsWithoutRepetitionBuilderForInts() {
        // Given
        final int n = 3;
        final int k = 2;

        // When
        final var variations = VariationsBuilder.build(n, k);

        // Then
        assertThat(variations.n()).isEqualTo(n);
        assertThat(variations.k()).isEqualTo(k);
        assertThat(variations.count()).isEqualTo(6);
        assertThat(variations).containsExactly(
                new int[] { 0, 1 },
                new int[] { 0, 2 },
                new int[] { 1, 0 },
                new int[] { 1, 2 },
                new int[] { 2, 0 },
                new int[] { 2, 1 }
        );
    }

    @Test
    @DisplayName("Verify variations with repetition builder for ints")
    void verifyVariationsWithRepetitionBuilderForInts() {
        // Given
        final int n = 3;
        final int k = 2;

        // When
        final var variations = VariationsBuilder.build(n, k).withRepetition();

        // Then
        assertThat(variations.n()).isEqualTo(n);
        assertThat(variations.k()).isEqualTo(k);
        assertThat(variations.count()).isEqualTo(9);
        assertThat(variations).containsExactly(
                new int[] { 0, 0 },
                new int[] { 0, 1 },
                new int[] { 0, 2 },
                new int[] { 1, 0 },
                new int[] { 1, 1 },
                new int[] { 1, 2 },
                new int[] { 2, 0 },
                new int[] { 2, 1 },
                new int[] { 2, 2 }
        );
    }
}
