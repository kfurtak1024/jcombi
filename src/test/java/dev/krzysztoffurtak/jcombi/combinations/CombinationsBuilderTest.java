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
        final var combination =
                CombinationsBuilder.builder().of(inputSet).choose(k);

        // Then
        assertThat(combination.n()).isEqualTo(inputSet.length);
        assertThat(combination.k()).isEqualTo(k);
        assertThat(combination.count()).isEqualTo(3);
        assertThat(combination).containsExactly(
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
        final var combination =
                CombinationsBuilder.builder().of(inputSet).choose(k).withRepetition();

        // Then
        assertThat(combination.n()).isEqualTo(inputSet.length);
        assertThat(combination.k()).isEqualTo(k);
        assertThat(combination.count()).isEqualTo(6);
        assertThat(combination).containsExactly(
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
        final var combination = CombinationsBuilder.build(n, k);

        // Then
        assertThat(combination.n()).isEqualTo(n);
        assertThat(combination.k()).isEqualTo(k);
        assertThat(combination.count()).isEqualTo(3);
        assertThat(combination).containsExactly(
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
        final var combination = CombinationsBuilder.build(n, k).withRepetition();

        // Then
        assertThat(combination.n()).isEqualTo(n);
        assertThat(combination.k()).isEqualTo(k);
        assertThat(combination.count()).isEqualTo(6);
        assertThat(combination).containsExactly(
                new int[] { 0, 0 },
                new int[] { 0, 1 },
                new int[] { 0, 2 },
                new int[] { 1, 1 },
                new int[] { 1, 2 },
                new int[] { 2, 2 }
        );
    }
}
