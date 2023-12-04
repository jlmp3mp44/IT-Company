package com.solvd.it_company.lambdas;

@FunctionalInterface
public interface CustomLogger<T> {
    void log(T message);
}
