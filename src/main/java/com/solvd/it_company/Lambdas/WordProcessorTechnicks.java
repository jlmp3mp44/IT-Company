package com.solvd.it_company.Lambdas;

import com.solvd.it_company.Device;
import com.solvd.it_company.Employee;

import java.util.Set;

@FunctionalInterface
public interface WordProcessorTechnicks<T, R> {
    void appendInfo(R title, Set<? extends T> devices);
}
