public interface QueueInterface<T> {

    void enqueue(T item) throws QueueException;

    T dequeue() throws QueueException;

    T peek() throws QueueException;

    boolean isEmpty();
}
