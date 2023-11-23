package com.solvd.it_company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> head;
    int size;
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    static class Node<T> {
        T data;
        Node<T> next;


        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedList<T> add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
        size++;
        return this;
    }

    public T delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index in LinkedList out of Bounds");
        }
        T removedData;
        if (index == 0) {
            removedData = head.data;
            head = head.next;
        } else {
            Node<T> current = head;
            Node<T> previous = null;
            int currentIndex = 0;
            while (currentIndex < index) {
                previous = current;
                current = current.next;
                currentIndex++;
            }
            removedData = current.data;
            previous.next = current.next;
        }
        size--;
        return removedData;
    }

    public void printList() {
        Node<T> currNode = head;
        LOGGER.info("LinkedList: ");
        while (currNode != null) {
            LOGGER.info(currNode.data + " ");
            currNode = currNode.next;
        }
    }

    public LinkedList<T> insert(int index, T data) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> newNode = new Node<>(data);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            return this;
        }

        Node<T> currNode = head;
        Node<T> prev = null;
        int counter = 0;

        while (currNode != null && counter < index) {
            prev = currNode;
            currNode = currNode.next;
            counter++;
        }

        if (counter == index) {
            prev.next = newNode;
            newNode.next = currNode;
        } else {
            throw new IndexOutOfBoundsException();
        }

        return this;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }

    }
}
