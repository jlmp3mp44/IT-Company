package com.solvd.it_company.Threads;

public class Realization {
    public static void main(String[] args) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        connectionPool.loadConnectionPool();
        executeQueries(connectionPool);
    }

    private static void executeQueries(ConnectionPool connectionPool) {
        MockConnection connection = connectionPool.getConnection();
        if (connection != null) {
            try {
                connection.executeQuery("SELECT * FROM table1");
                connection.executeQuery("UPDATE table1");
            } finally {
                connectionPool.releaseConnection(connection);
            }
        }
    }
}
