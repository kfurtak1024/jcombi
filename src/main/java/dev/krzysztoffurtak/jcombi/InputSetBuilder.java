package dev.krzysztoffurtak.jcombi;

import java.util.List;

public interface InputSetBuilder<T, B> {
    B of(List<T> inputSet);

    @SuppressWarnings("unchecked")
    default B of(T... elements) {
        return of(List.of(elements));
    }
}
