package com.solvd.it_company.Threads;

public class Realization {
    public static void main(String[] args) {
        ConnectionPool connectionPool = new ConnectionPool(5);
        connectionPool.loadConnectionPool();
    }
}
