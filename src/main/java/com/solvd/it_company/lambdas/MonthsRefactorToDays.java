package com.solvd.it_company.lambdas;
@FunctionalInterface
public interface MonthsRefactorToDays<T extends Number, R extends Number, S extends Number> {
    R monthsToDays(T months, S days);
}
