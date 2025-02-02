public class Sort {

    // quicksort φθίνουσα σειρά
    public static void quicksort(int[] array, int p, int r) {
        if (r <= p) return;
        int i = partition(array, p, r);
        quicksort(array, p, i-1);
        quicksort(array, i+1, r);
    }

    public static int partition(int array[], int p, int r){
        int i = p-1, j = r;
        int v = array[r];

        for (;;){
            while (array[++i] > v);
            while (v > array[--j])
                if (j == p) break;
            if (i >= j) break;
            swap(array, i, j);
        }
        swap(array, i, r);
        return i; }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}