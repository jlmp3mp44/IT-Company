package com.solvd.it_company.lambdas;

import java.util.Set;

@FunctionalInterface
public interface WordProcessor<T, R> {
    void appendInfo(R title, Set<? extends T> items);
}

