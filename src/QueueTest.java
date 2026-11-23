public class QueueTest {

    public static void main(String[] args) throws QueueException {

        ArrayQueue<Integer> queue = new ArrayQueue<>(5);

        System.out.println("Is empty: " + queue.isEmpty());

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println("Front item: " + queue.peek());
        System.out.println("Removed: " + queue.dequeue());
        System.out.println("Removed: " + queue.dequeue());
        System.out.println("Is empty: " + queue.isEmpty());
    }
}