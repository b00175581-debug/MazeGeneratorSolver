public class ArrayQueue<T> implements QueueInterface<T> {

    private T[] items;
    private int front;
    private int back;
    private int count;

    public ArrayQueue(int capacity) {
        items = (T[]) new Object[capacity];
        front = 0;
        back = -1;
        count = 0;
    }

    public void enqueue(T item) throws QueueException {

        if (count == items.length) {
            throw new QueueException("Queue is full");
        }

        back++;
        items[back] = item;
        count++;
    }

    public T dequeue() throws QueueException {

        if (isEmpty()) {
            throw new QueueException("Queue is empty");
        }

        T item = items[front];
        items[front] = null;
        front++;
        count--;

        return item;
    }

    public T peek() throws QueueException {

        if (isEmpty()) {
            throw new QueueException("Queue is empty");
        }

        return items[front];
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
