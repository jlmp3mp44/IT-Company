package com.solvd.it_company.Threads;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.*;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private Queue<MockConnection> connections =  new ConcurrentLinkedQueue<>();
    private int poolSize;
    private final ExecutorService threadPool;
    private ConnectionPool(int poolSize){
        this.poolSize =  poolSize;
        this.threadPool = Executors.newFixedThreadPool(7);
        initializePool();
        LOGGER.info("ConnectionPool initialized with pool size: " + poolSize);
    }
    public static synchronized ConnectionPool getInstance(int poolSize) {
        if (instance == null) {
            instance = new ConnectionPool(poolSize);
        }
        return instance;
    }
    private void initializePool() {
        for (int i = 0; i < poolSize; i++) {
            connections.add(new MockConnection());
        }
        LOGGER.info("ConnectionPool initialized with " + poolSize + " connections.");
    }
    public void loadConnectionPool() {
        LOGGER.info("Loading ConnectionPool...");
        CompletableFuture<?>[] futures = new CompletableFuture[7];

        for (int i = 0; i < 5; i++) {
            futures[i] = CompletableFuture.runAsync(this::getConnectionTask, threadPool);
        }

        for (int i = 5; i < 7; i++) {
            futures[i] = CompletableFuture.runAsync(this::waitForConnectionTask, threadPool);
        }

        CompletableFuture.allOf(futures).join();
        threadPool.shutdown();
        LOGGER.info("ConnectionPool loaded successfully");
    }
    private void getConnectionTask() {
        CompletableFuture<MockConnection> connectionFuture = CompletableFuture.supplyAsync(this::getConnection, threadPool);
        connectionFuture.thenAccept(connection -> {
            LOGGER.info(Thread.currentThread().getName() + " got connection");
            releaseConnection(connection);
        });
    }
    private void waitForConnectionTask() {
        CompletableFuture<MockConnection> connectionFuture = CompletableFuture.supplyAsync(this::getConnection, threadPool);
        connectionFuture.thenAcceptAsync(connection -> {
            if (connection != null) {
                LOGGER.info(Thread.currentThread().getName() + " got connection");
                releaseConnection(connection);
            } else {
                LOGGER.info(Thread.currentThread().getName() + " waiting for connection");
            }
        }, threadPool);
    }
    public MockConnection getConnection() {
        if (connections.isEmpty()) {
            initializePool();
        }
        return connections.poll();
    }

    public void releaseConnection(MockConnection connection) {
        connections.add(connection);
    }
}
