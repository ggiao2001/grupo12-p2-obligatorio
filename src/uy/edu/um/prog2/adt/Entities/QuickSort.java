package uy.edu.um.prog2.adt.Entities;

public class QuickSort {
    public static void quickSort(int[] arr, int low, int high, boolean desc) {
        //desc es True si quiero que se ordene de forma descendiente
        if (low < high) {
            //pivote de particionado:
            //los elementos menores que el pivote se colocan a su izq
            //y los mayores a la derecha
            if(desc){
                //Ordeno de forma descendiente
                int partitionIndex = partitionDesc(arr, low, high);
                //Paso recursivo:
                quickSort(arr, low, partitionIndex - 1, desc);
                quickSort(arr, partitionIndex + 1, high, desc);
            }else {
                //Ordeno de forma ascendiente
                int partitionIndex = partitionAsc(arr, low, high);
                //Paso recursivo:
                quickSort(arr, low, partitionIndex - 1, desc);
                quickSort(arr, partitionIndex + 1, high, desc);
            }

        }
    }

    public static int partitionAsc(int[] arr, int low, int high) {
        int pivot = arr[high];
        int indiceMenor = low - 1; // Indice del elemento más chico

        for (int l = low; l < high; l++) {
            //Si el elemento actual es menor o igual al pivote
            if (arr[l] <= pivot) {
                indiceMenor++;
                //Intercambia arr[i] y arr[j]
                int temp = arr[indiceMenor];
                arr[indiceMenor] = arr[l];
                arr[l] = temp;
            }
        }

        //Intercambia arr[i+1] y arr[high] (pivote)
        int temp = arr[indiceMenor + 1];
        arr[indiceMenor + 1] = arr[high];
        arr[high] = temp;

        return indiceMenor + 1;
    }

    public static int partitionDesc(int[] arr, int low, int high) {
        int pivot = arr[high];
        int indiceMayor = low - 1; // Indice del elemento más grande

        for (int l = low; l < high; l++) {
            //Si el elemento actual es menor o igual al pivote
            if (arr[l] >= pivot) {
                indiceMayor++;
                //Intercambia arr[i] y arr[j]
                int temp = arr[indiceMayor];
                arr[indiceMayor] = arr[l];
                arr[l] = temp;
            }
        }

        //Intercambia arr[i+1] y arr[high] (pivote)
        int temp = arr[indiceMayor + 1];
        arr[indiceMayor + 1] = arr[high];
        arr[high] = temp;

        return indiceMayor + 1;
    }
}

