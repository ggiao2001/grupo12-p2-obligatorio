package uy.edu.um.prog2.adt.Entities;

public class QuickSortGenerics {
    public static <T extends Comparable<T>> void quickSort(T[] arr, int low, int high, boolean desc) {
        if (low < high) {
            if (desc) {
                int partitionIndex = partitionDesc(arr, low, high);
                quickSort(arr, low, partitionIndex - 1, desc);
                quickSort(arr, partitionIndex + 1, high, desc);
            } else {
                int partitionIndex = partitionAsc(arr, low, high);
                quickSort(arr, low, partitionIndex - 1, desc);
                quickSort(arr, partitionIndex + 1, high, desc);
            }
        }
    }

    public static <T extends Comparable<T>> int partitionAsc(T[] arr, int low, int high) {
        T pivot = arr[high];
        int indiceMenor = low - 1;

        for (int l = low; l < high; l++) {
            if (arr[l] == null || (arr[l] != null && arr[l].compareTo(pivot) <= 0)) {
                indiceMenor++;
                swap(arr, indiceMenor, l);
            }
        }

        swap(arr, indiceMenor + 1, high);
        return indiceMenor + 1;
    }


    public static <T extends Comparable<T>> int partitionDesc(T[] arr, int low, int high) {
        T pivot = arr[high];
        int indiceMayor = low - 1;

        for (int l = low; l < high; l++) {
            if (arr[l] != null && arr[l].compareTo(pivot) >= 0) {
                indiceMayor++;
                swap(arr, indiceMayor, l);
            }
        }

        swap(arr, indiceMayor + 1, high);
        return indiceMayor + 1;
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
