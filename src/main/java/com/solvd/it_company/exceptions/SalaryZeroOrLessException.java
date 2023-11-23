package com.solvd.it_company.exceptions;


public class SalaryZeroOrLessException extends Exception {
    public SalaryZeroOrLessException(String message) {
        super(message);
    }
}
