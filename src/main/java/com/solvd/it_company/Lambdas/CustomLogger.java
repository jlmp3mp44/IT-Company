package com.solvd.it_company.Lambdas;

@FunctionalInterface
public interface CustomLogger<T> {
    void log(T message);
}
