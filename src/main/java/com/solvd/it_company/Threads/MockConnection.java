package com.solvd.it_company.Threads;


public  class MockConnection {
    private boolean isOpen = true;
    public void executeQuery(String query) {
        if (!isOpen) {
            throw new IllegalStateException("Connection is closed");
        }
        System.out.println("Executing query: " + query);
    }
    public void close() {
        isOpen = false;
        System.out.println("Connection closed");
    }
}
