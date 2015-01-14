package ru.ifmo.niyaz.DataStructures;

/**
 * Created by niyaz on 14.01.2015.
 */
public class StackMin {
    int[] stack;
    int[] minimum;
    int size;

    public StackMin(int capacity) {
        stack = new int[capacity];
        minimum = new int[capacity];
    }

    public void add(int x) {
        stack[size] = x;
        minimum[size] = size > 0 ? Math.min(x, minimum[size - 1]) : x;
        ++size;
    }

    public int remove() {
        return stack[--size];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int get(int index) {
        return stack[index];
    }

    public int getPrefixMinimum(int index) {
        return minimum[index];
    }

    public int getMinimum() {
        return size > 0 ? getPrefixMinimum(size - 1) : Integer.MAX_VALUE;
    }

    public void clear() {
        size = 0;
    }
}
