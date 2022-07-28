public class Main {

    public static void main(String args[]){
        int[] lista = new int[5];
        lista[0] = 3;
        lista[1] = 1;
        lista[2] = 5;
        lista[3] = 2;
        lista[4] = 4;

        Ordenacao teste = new Ordenacao(500000);


        //teste.sort(lista);
        //teste.shellSort(lista);
        //teste.heapSort(lista);
        //teste.insertionSort(lista);
        //teste.selectionSort(lista);
        //teste.mergeSort(lista, lista.length);
        //teste.radixsort(lista, lista.length);
        //teste.combSort(lista);
        /*
        for (int x: lista){
            System.out.println(x);
        }
        */

        teste.Sorting();

    }
}
