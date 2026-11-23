public interface StackInterface<T> {

    void push(T item) throws StackException;

    T pop() throws StackException;

    T peek() throws StackException;

    boolean isEmpty();
}
