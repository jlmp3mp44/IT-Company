package com.solvd.it_company.lambdas;

@FunctionalInterface
public interface MonthsRefactorToDays<T, R, S> {
    R monthsToDays(T months, S days);
}
