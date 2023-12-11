package com.solvd.it_company.Threads;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public  class MockConnection {
    private static final Logger LOGGER = LogManager.getLogger(MockConnection.class);
    private boolean isOpen = true;
    public void executeQuery(String query) {
        if (!isOpen) {
            throw new IllegalStateException("Connection is closed");
        }
        LOGGER.info("Executing query: " + query);
    }
    public void close() {
        isOpen = false;
        LOGGER.info("Connection closed");
    }
}
