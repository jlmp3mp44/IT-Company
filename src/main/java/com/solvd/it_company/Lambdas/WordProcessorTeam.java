package com.solvd.it_company.Lambdas;

import com.solvd.it_company.Employee;

import java.util.Set;

@FunctionalInterface
public interface WordProcessorTeam<T, R> {
    void appendInfo(R title, Set<? extends T> employees);
}

