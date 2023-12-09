package com.solvd.it_company.Threads;

import java.sql.Connection;
import java.util.Queue;
import java.util.concurrent.*;

public class ConnectionPool {
    private Queue<MockConnection> connections =  new ConcurrentLinkedQueue<>();
    private int poolSize;
    private final ExecutorService threadPool;
    ConnectionPool(int poolSize){
        this.poolSize =  poolSize;
        this.threadPool = Executors.newFixedThreadPool(7);
        initializePool();
    }
    private void initializePool() {
        for (int i = 0; i < poolSize; i++) {
            connections.add(new MockConnection());
        }
    }
    public void loadConnectionPool() {
        CompletableFuture<?>[] futures = new CompletableFuture[7];

        for (int i = 0; i < 5; i++) {
            futures[i] = CompletableFuture.runAsync(this::getConnectionTask, threadPool);
        }

        for (int i = 5; i < 7; i++) {
            futures[i] = CompletableFuture.runAsync(this::waitForConnectionTask, threadPool);
        }

        CompletableFuture.allOf(futures).join();
        threadPool.shutdown();
    }
    private void getConnectionTask() {
        CompletableFuture<MockConnection> connectionFuture = CompletableFuture.supplyAsync(this::getConnection, threadPool);
        connectionFuture.thenAccept(connection -> {
            System.out.println(Thread.currentThread().getName() + " got connection");
            releaseConnection(connection);
        });
    }
    private void waitForConnectionTask() {
        CompletableFuture<MockConnection> connectionFuture = CompletableFuture.supplyAsync(this::getConnection, threadPool);
        connectionFuture.thenAcceptAsync(connection -> {
            if (connection != null) {
                System.out.println(Thread.currentThread().getName() + " got connection");
                releaseConnection(connection);
            } else {
                System.out.println(Thread.currentThread().getName() + " waiting for connection");
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
