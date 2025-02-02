public class MaxPQ<T> implements PQInterface<T>{
    private T[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 4;

    public MaxPQ(){
        heap = (T[]) new Object[DEFAULT_CAPACITY + 1];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void insert(T x) {

    }

    @Override
    public T max() {
        return null;
    }

    @Override
    public T getmax() {
        return null;
    }
}
