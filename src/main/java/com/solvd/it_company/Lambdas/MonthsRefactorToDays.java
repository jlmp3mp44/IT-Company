package com.solvd.it_company.lambdas;
@FunctionalInterface
public interface MonthsRefactorToDays<T extends Integer, S,  R>{
    R monthsToDays(T months, S days);
}
