public class QuicksortStrings {
    public static void ordenar(String[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }
    private static void quicksort(String[] arr, int inicio, int fin) {
        int i = inicio, j = fin;
        String pivote = arr[(inicio + fin) / 2];
        while (i <= j) {
            while (arr[i].compareTo(pivote) < 0) i++;
            while (arr[j].compareTo(pivote) > 0) j--;
            if (i <= j) {
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if (inicio < j) quicksort(arr, inicio, j);
        if (i < fin) quicksort(arr, i, fin);
    }
}

