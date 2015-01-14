package ru.ifmo.niyaz.DataStructures;

/**
 * Created by niyaz on 14.01.2015.
 */
public class QueueMin {
    StackMin first;
    StackMin second;

    public QueueMin(int capacity) {
        first = new StackMin(capacity);
        second = new StackMin(capacity);
    }

    public void add(int x) {
        first.add(x);
    }

    public int remove() {
        if (second.size() == 0) {
            while (!first.isEmpty()) {
                second.add(first.remove());
            }
        }
        return second.remove();
    }

    public int getMinimum() {
        return Math.min(first.getMinimum(), second.getMinimum());
    }

    public int size() {
        return first.size() + second.size();
    }

    public boolean isEmpty() {
        return first.isEmpty() && second.isEmpty();
    }

    public void clear() {
        first.clear();
        second.clear();
    }
}
