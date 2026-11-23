public class ArrayStack<T> implements StackInterface<T> {

    private T[] items;
    private int top;

    public ArrayStack(int capacity) {
        items = (T[]) new Object[capacity];
        top = -1;
    }

    public void push(T item) throws StackException {

        if (top == items.length - 1) {
            throw new StackException("Stack is full");
        }

        top++;
        items[top] = item;
    }

    public T pop() throws StackException {

        if (isEmpty()) {
            throw new StackException("Stack is empty");
        }

        T item = items[top];
        items[top] = null;
        top--;

        return item;
    }

    public T peek() throws StackException {

        if (isEmpty()) {
            throw new StackException("Stack is empty");
        }

        return items[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}