import java.util.LinkedList;

/**
 * Cau truc du lieu Queue
 */
public class MyQueue {
    LinkedList<Object> list;
    public MyQueue(){list = new LinkedList<>();}
    public void enqueue(Object p){ list.add(p);}
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public Object dequeue(){
        return list.removeFirst();
    }
    public Object front(){
        return list.getFirst();
    }
}
