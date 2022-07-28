import java.util.Arrays;

public class Ordenacao {
    int[] lista;
    int[] lista_desordenada;
    int[] lista_decrescente;
    int[] lista_quase_ordenada;
    int tamanho;
    int temp_array[];
    int len;

    public Ordenacao(int tamanho){
        this.tamanho = tamanho;
    }

    public void criar_listas(){
        lista = new int[tamanho];
        lista_desordenada = new int[tamanho];
        lista_decrescente = new int[tamanho];
        lista_quase_ordenada = new int[tamanho];

        for (int i = 0;i < lista.length;i++){
            lista[i] = 1 + (int)(Math.random()*1000000);
        }

        for (int i = 0; i < lista.length; i++){
            lista_desordenada[i] = lista[i];
        }

        for (int i = 0; i < lista.length; i++){
            lista_decrescente[i] = lista[i];
        }


        for (int i = 0; i < lista_decrescente.length;i++){
            lista_decrescente[i] *= -1;
        }

        Arrays.sort(lista_decrescente);

        for (int i = 0; i < lista_decrescente.length;i++){
            lista_decrescente[i] *= -1;
        }

        for (int i = 0; i < lista.length; i++){
            lista_quase_ordenada[i] = lista[i];
        }

        Arrays.sort(lista_quase_ordenada);

        int t = (lista_quase_ordenada.length * 5) /100;
        int aux;
        for (int i = 0; i < t; i += 2){
            aux = lista_quase_ordenada[i];
            lista_quase_ordenada[i] = lista_quase_ordenada[i + 3];
            lista_quase_ordenada[i + 3] = aux;
        }
    }

    public void quickSort(int[] values) {
        // check for empty or null array
        if (values ==null || values.length==0){
            return;
        }
        int number = values.length;
        sort(values,0, number - 1);
    }

    private void sort(int[] numbers, int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = numbers[low + (high-low)/2];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (numbers[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (numbers[j] > pivot) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(numbers, i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            sort(numbers, low, j);
        if (i < high)
            sort(numbers, i, high);
    }

    private void exchange(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }


public void shellSort(int[] lista){
        int i, j, temp, tamanho = lista.length;

        int incremento = 1;
        while (incremento < tamanho){
            incremento = 3 * incremento + 1;
        }

        while (incremento > 1){
            incremento /= 3;

            for (i = incremento; i < tamanho; i++){
                temp = lista[i];
                j = i - incremento;

                while (j >= 0 && temp < lista[j]){
                    lista[j + incremento] = lista[j];
                    j -= incremento;
                }
                lista[j + incremento] = temp;
            }
        }
    }

    public void heapSort(int[] lista){
        int tamanho = lista.length;

        // Build heap
        for (int i = tamanho / 2 - 1; i >= 0; i--)
            heapify(lista, tamanho, i);

        // One by one extract (Max) an element from heap and
        // replace it with the last element in the array
        for (int i=tamanho-1; i>=0; i--) {

            //arrA[0] is a root of the heap and is the max element in heap
            int x = lista[0];
            lista[0] = lista[i];
            lista[i] = x;

            // call max heapify on the reduced heap
            heapify(lista, i, 0);
        }
    }

    private void heapify(int lista[], int tamanho, int i) {
        int maior = i; // Initialize largest as root
        int esquerda  = 2*i + 1; // left = 2*i + 1
        int direita  = 2*i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (esquerda  < tamanho && lista[esquerda ] > lista[maior])
            maior = esquerda ;

        // If right child is larger than largest so far
        if (direita  < tamanho && lista[direita ] > lista[maior])
            maior = direita ;

        // If largest is not root
        if (maior != i) {
            int swap = lista[i];
            lista[i] = lista[maior];
            lista[maior] = swap;

            // Recursive call to  heapify the sub-tree
            heapify(lista, tamanho, maior);
        }
    }

     private int getMax(int arr[], int n)
    {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    private void countSort(int arr[], int n, int exp)
    {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count,0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[ (arr[i]/exp)%10 ]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--)
        {
            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i];
            count[ (arr[i]/exp)%10 ]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    public void radixsort(int arr[], int n)
    {
        // Find the maximum number to know number of digits
        int m = getMax(arr, n);

        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    int getNextGap(int gap)
    {
        // Shrink gap by Shrink factor
        gap = (gap*10)/13;
        if (gap < 1)
            return 1;
        return gap;
    }

    void combSort(int arr[])
    {
        int n = arr.length;

        // initialize gap
        int gap = n;

        // Initialize swapped as true to make sure that
        // loop runs
        boolean swapped = true;

        // Keep running while gap is more than 1 and last
        // iteration caused a swap
        while (gap != 1 || swapped == true)
        {
            // Find next gap
            gap = getNextGap(gap);

            // Initialize swapped as false so that we can
            // check if swap happened or not
            swapped = false;

            // Compare all elements with current gap
            for (int i=0; i<n-gap; i++)
            {
                if (arr[i] > arr[i+gap])
                {
                    // Swap arr[i] and arr[i+gap]
                    int temp = arr[i];
                    arr[i] = arr[i+gap];
                    arr[i+gap] = temp;

                    // Set swapped
                    swapped = true;
                }
            }
        }
    }


    public void mergeSort(int[] array) {
        if (array == null) {
            return;
        }

        if (array.length > 1) {
            int mid = array.length / 2;

            // Split left part
            int[] left = new int[mid];
            for (int i = 0; i < mid; i++) {
                left[i] = array[i];
            }

            // Split right part
            int[] right = new int[array.length - mid];
            for (int i = mid; i < array.length; i++) {
                right[i - mid] = array[i];
            }
            mergeSort(left);
            mergeSort(right);

            int i = 0;
            int j = 0;
            int k = 0;

            // Merge left and right arrays
            while (i < left.length && j < right.length) {
                if (left[i] < right[j]) {
                    array[k] = left[i];
                    i++;
                } else {
                    array[k] = right[j];
                    j++;
                }
                k++;
            }
            // Collect remaining elements
            while (i < left.length) {
                array[k] = left[i];
                i++;
                k++;
            }
            while (j < right.length) {
                array[k] = right[j];
                j++;
                k++;
            }
        }
    }

    public void Sorting(){
        criar_listas();


        //----------------------------------------QuickSort------------------------------------------------------
        int[] lista_quicksort = new int[tamanho];

        //Conjunto quase ordenado

        for (int i = 0; i < lista.length; i++){
            lista_quicksort[i] = lista_quase_ordenada[i];
        }

        long inicio_quicksort_quase_ordenado = System.nanoTime();

        quickSort(lista_quicksort);

        long duracao_quicksort_quase_ordenado = System.nanoTime() - inicio_quicksort_quase_ordenado;
        double tempo_quicksort_quase_ordenado = (double)duracao_quicksort_quase_ordenado / 1000000000;


        //Conjunto desordenado

        for (int i = 0; i < lista.length; i++){
            lista_quicksort[i] = lista_desordenada[i];
        }

        long inicio_quicksort_desordenado = System.nanoTime();

        quickSort(lista_quicksort);

        long duracao_quicksort_desordenado = System.nanoTime() - inicio_quicksort_desordenado;
        double tempo_quicksort_desordenado = (double)duracao_quicksort_desordenado / 1000000000;


        //Conjunto em ordem decrescente

        for (int i = 0; i < lista.length; i++){
            lista_quicksort[i] = lista_decrescente[i];
        }

        long inicio_quicksort_decrescente = System.nanoTime();

        quickSort(lista_quicksort);

        long duracao_quicksort_decrescente = System.nanoTime() - inicio_quicksort_decrescente;
        double tempo_quicksort_decrescente = (double)duracao_quicksort_decrescente / 1000000000;



        //----------------------------------------ShellSort------------------------------------------------------
        int[] lista_shellsort = new int[tamanho];

        //Conjunto quase ordenado

        for (int i = 0; i < lista.length; i++){
            lista_shellsort[i] = lista_quase_ordenada[i];
        }

        long inicio_shellsort_quase_ordenado = System.nanoTime();

        shellSort(lista_shellsort);

        long duracao_shellsort_quase_ordenado = System.nanoTime() - inicio_shellsort_quase_ordenado;
        double tempo_shellsort_quase_ordenado = (double)duracao_shellsort_quase_ordenado / 1000000000;


        //Conjunto desordenado

        for (int i = 0; i < lista.length; i++){
            lista_shellsort[i] = lista_desordenada[i];
        }

        long inicio_shellsort_desordenado = System.nanoTime();

        shellSort(lista_shellsort);

        long duracao_shellsort_desordenado = System.nanoTime() - inicio_shellsort_desordenado;
        double tempo_shellsort_desordenado = (double)duracao_shellsort_desordenado / 1000000000;


        //Conjunto em ordem decrescente

        for (int i = 0; i < lista.length; i++){
            lista_shellsort[i] = lista_decrescente[i];
        }

        long inicio_shellsort_decrescente = System.nanoTime();

        shellSort(lista_shellsort);

        long duracao_shellsort_decrescente = System.nanoTime() - inicio_shellsort_decrescente;
        double tempo_shellsort_decrescente = (double)duracao_shellsort_decrescente / 1000000000;


        //----------------------------------------HeapSort------------------------------------------------------
        int[] lista_heapsort = new int[tamanho];

        //Conjunto quase ordenado

        for (int i = 0; i < lista.length; i++){
            lista_heapsort[i] = lista_quase_ordenada[i];
        }

        long inicio_heapsort_quase_ordenado = System.nanoTime();

        heapSort(lista_heapsort);

        long duracao_heapsort_quase_ordenado = System.nanoTime() - inicio_heapsort_quase_ordenado;
        double tempo_heapsort_quase_ordenado = (double)duracao_heapsort_quase_ordenado / 1000000000;


        //Conjunto desordenado

        for (int i = 0; i < lista.length; i++){
            lista_heapsort[i] = lista_desordenada[i];
        }

        long inicio_heapsort_desordenado = System.nanoTime();

        heapSort(lista_heapsort);

        long duracao_heapsort_desordenado = System.nanoTime() - inicio_heapsort_desordenado;
        double tempo_heapsort_desordenado = (double)duracao_heapsort_desordenado / 1000000000;


        //Conjunto em ordem decrescente

        for (int i = 0; i < lista.length; i++){
            lista_heapsort[i] = lista_decrescente[i];
        }

        long inicio_heapsort_decrescente = System.nanoTime();

        heapSort(lista_heapsort);

        long duracao_heapsort_decrescente = System.nanoTime() - inicio_heapsort_decrescente;
        double tempo_heapsort_decrescente = (double)duracao_heapsort_decrescente / 1000000000;


        //----------------------------------------RadixSort------------------------------------------------------
        int[] lista_radixsort = new int[tamanho];

        //Conjunto quase ordenado

        for (int i = 0; i < lista.length; i++){
            lista_radixsort[i] = lista_quase_ordenada[i];
        }

        long inicio_radix_sort_quase_ordenado = System.nanoTime();

        radixsort(lista_radixsort, lista.length);

        long duracao_radixsort_quase_ordenado = System.nanoTime() - inicio_radix_sort_quase_ordenado;
        double tempo_radixsort_quase_ordenado = (double)duracao_radixsort_quase_ordenado / 1000000000;


        //Conjunto desordenado

        for (int i = 0; i < lista.length; i++){
            lista_radixsort[i] = lista_desordenada[i];
        }

        long inicio_radixsort_desordenado = System.nanoTime();

        radixsort(lista_radixsort, lista.length);

        long duracao_radixsort_desordenado = System.nanoTime() - inicio_radixsort_desordenado;
        double tempo_radixsort_desordenado = (double)duracao_radixsort_desordenado / 1000000000;


        //Conjunto em ordem decrescente

        for (int i = 0; i < lista.length; i++){
            lista_radixsort[i] = lista_decrescente[i];
        }

        long inicio_radixsort_decrescente = System.nanoTime();

        radixsort(lista_radixsort, lista.length);

        long duracao_radixsort_decrescente = System.nanoTime() - inicio_radixsort_decrescente;
        double tempo_radixsort_decrescente = (double)duracao_radixsort_decrescente / 1000000000;


        //---------------------------------------CombSort------------------------------------------------------
        int[] lista_combsort = new int[tamanho];

        //Conjunto quase ordenado

        for (int i = 0; i < lista.length; i++){
            lista_combsort[i] = lista_quase_ordenada[i];
        }

        long inicio_combsort_quase_ordenado = System.nanoTime();

        combSort(lista_combsort);

        long duracao_combsort_quase_ordenado = System.nanoTime() - inicio_combsort_quase_ordenado;
        double tempo_combsort_quase_ordenado = (double)duracao_combsort_quase_ordenado / 1000000000;


        //Conjunto desordenado

        for (int i = 0; i < lista.length; i++){
            lista_combsort[i] = lista_desordenada[i];
        }

        long inicio_combsort_desordenado = System.nanoTime();

        combSort(lista_combsort);

        long duracao_combsort_desordenado = System.nanoTime() - inicio_combsort_desordenado;
        double tempo_combsort_desordenado = (double)duracao_combsort_desordenado / 1000000000;


        //Conjunto em ordem decrescente

        for (int i = 0; i < lista.length; i++){
            lista_combsort[i] = lista_decrescente[i];
        }

        long inicio_combsort_decrescente = System.nanoTime();

        combSort(lista_combsort);

        long duracao_combsort_decrescente = System.nanoTime() - inicio_combsort_decrescente;
        double tempo_combsort_decrescente = (double)duracao_combsort_decrescente / 1000000000;

        //----------------------------------------MergeSort------------------------------------------------------
        int[] lista_mergesort = new int[tamanho];

        //Conjunto quase ordenado

        for (int i = 0; i < lista.length; i++){
            lista_mergesort[i] = lista_quase_ordenada[i];
        }

        long inicio_mergesort_quase_ordenado = System.nanoTime();

        mergeSort(lista_mergesort);

        long duracao_mergesort_quase_ordenado = System.nanoTime() - inicio_mergesort_quase_ordenado;
        double tempo_mergesort_quase_ordenado = (double)duracao_mergesort_quase_ordenado / 1000000000;


        //Conjunto desordenado

        for (int i = 0; i < lista.length; i++){
            lista_mergesort[i] = lista_desordenada[i];
        }

        long inicio_mergesort_desordenado = System.nanoTime();

        mergeSort(lista_mergesort);

        long duracao_mergesort_desordenado = System.nanoTime() - inicio_mergesort_desordenado;
        double tempo_mergesort_desordenado = (double)duracao_mergesort_desordenado / 1000000000;


        //Conjunto em ordem decrescente

        for (int i = 0; i < lista.length; i++){
            lista_mergesort[i] = lista_decrescente[i];
        }

        long inicio_mergesort_decrescente = System.nanoTime();

        mergeSort(lista_mergesort);

        long duracao_mergesort_decrescente = System.nanoTime() - inicio_mergesort_decrescente;
        double tempo_mergesort_decrescente = (double)duracao_mergesort_decrescente / 1000000000;


        //----------------------------------------Interface------------------------------------------------------

        System.out.println("------------------------------------------------------------------");
        System.out.println("|Tamanho do Conjunto: " + tamanho + "                                     |");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|              |          Tempos obtidos pelos algoritmos        |");
        System.out.println("|--------------|-------------------------------------------------|");
        System.out.println("|  Algoritmo   | Quase ordenado | Desordenado | Ordem Decrescente|");
        System.out.println("|--------------|----------------|-------------|------------------|");
        System.out.println("|  Quicksort   |   " + String.format("%.2f", tempo_quicksort_quase_ordenado) + "         |  " +
                String.format("%.2f", tempo_quicksort_desordenado) + "       |    " + String.format("%.2f", tempo_quicksort_decrescente) + "          |");
        System.out.println("|--------------|----------------|-------------|------------------|");
        System.out.println("|  Shellsort   |   " + String.format("%.2f", tempo_shellsort_quase_ordenado) + "         |  " +
                String.format("%.2f",tempo_shellsort_desordenado) + "       |    " + String.format("%.2f",tempo_shellsort_decrescente) + "          |");
        System.out.println("|--------------|----------------|-------------|------------------|");
        System.out.println("|  Heapsort    |   " + String.format("%.2f", tempo_heapsort_quase_ordenado) + "         |  " +
                String.format("%.2f", tempo_heapsort_desordenado) + "       |    " + String.format("%.2f",tempo_heapsort_decrescente) + "          |");
        System.out.println("|--------------|----------------|-------------|------------------|");
        System.out.println("|  Radixsort   |   " + String.format("%.2f", tempo_radixsort_quase_ordenado) + "         |  " +
                String.format("%.2f",tempo_radixsort_desordenado) + "       |    " + String.format("%.2f",tempo_radixsort_decrescente) + "          |");
        System.out.println("|--------------|----------------|-------------|------------------|");
        System.out.println("|  Combsort    |   " + String.format("%.2f", tempo_combsort_quase_ordenado) + "         |  " +
                String.format("%.2f",tempo_combsort_desordenado) + "       |    " + String.format("%.2f",tempo_combsort_decrescente) + "          |");
        System.out.println("|--------------|----------------|-------------|------------------|");
        System.out.println("|  Mergesort   |   " + String.format("%.2f", tempo_mergesort_quase_ordenado) + "         |  " +
                String.format("%.2f",tempo_mergesort_desordenado) + "       |    " + String.format("%.2f",tempo_mergesort_decrescente) + "          |");
        System.out.println("|----------------------------------------------------------------|");

    }
}
