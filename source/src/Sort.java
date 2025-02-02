public class Sort {

    // quicksort
    public static void quicksort(Job[] array, int p, int r) {
        if (r <= p) return;
        int i = partition(array, p, r);
        quicksort(array, p, i-1);
        quicksort(array, i+1, r);
    }

    public static int partition(Job array[], int p, int r){
        int i = p-1, j = r;
        Job v = array[r];

        for (;;){
            while (array[++i].getTime() > v.getTime());
            while (v.getTime() > array[--j].getTime())
                if (j == p) break;
            if (i >= j) break;
            swap(array, i, j);
        }
        swap(array, i, r);
        return i; }

    private static void swap(Job[] array, int i, int j) {
        Job temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}