package com.solvd.it_company.Lambdas;

import java.util.Set;

@FunctionalInterface
public interface WordProcessor {
    public abstract  void appendInfo(String title, Set<?> employees);
}
