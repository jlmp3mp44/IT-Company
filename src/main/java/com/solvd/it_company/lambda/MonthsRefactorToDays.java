package com.solvd.it_company.lambdas;
@FunctionalInterface
public interface MonthsRefactorToDays<T extends Number, S extends Number, R extends Number> {
    R monthsToDays(T months, S days);
}
