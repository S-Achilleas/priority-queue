import java.util.Comparator;
public class PQmax<T> implements PQInterface<T> {

    private T[] heap;
    private int size;
    private Comparator<T> comparator;

    private static final int DEFAULT_CAPACITY = 4;
    private static final int AUTOGROW_SIZE = 4;



    public PQmax(Comparator<T> comparator) {
        this.heap = (T[]) new Object[DEFAULT_CAPACITY + 1];
        this.size = 0;
        this.comparator = comparator;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(T item) {
        if (size == (heap.length - 1)*0.75)
            resize();
        heap[++size] = item;
        swim(size);
    }


    @Override
    public T getmax() {
        if (size == 0)
            return null;
        T root = heap[1];
        heap[1] = heap[size];
        size--;
        sink(1);
        return root;
    }

    @Override
    public T max() {
        return (isEmpty()) ? null : heap[1];
    }


    private void swim(int i) {
        if (i == 1)
            return;

        int parent = i / 2;

        while (i != 1 && comparator.compare(heap[i], heap[parent]) > 0) {
            swap(i, parent);
            i = parent;
            parent = i / 2;
        }
    }

    private void sink(int i) {
        int left = 2 * i;
        int right = left + 1;

        if (left > size)
            return;

        while (left <= size) {
            int max = left;
            if (right <= size) {
                if (comparator.compare(heap[left], heap[right]) < 0)
                    max = right;
            }

            if (comparator.compare(heap[i], heap[max]) >= 0)
                return;
            else {
                swap(i, max);
                i = max;
                left = i * 2;
                right = left + 1;
            }
        }
    }

    private void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void resize() {
        T[] newHeap = (T[]) new Object[heap.length * 2];
        for (int i = 0; i <= size; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }
}
