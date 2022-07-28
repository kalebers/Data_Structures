public class Merge {
    public Merge(){
    }
    // ordena os valores da lista do menor para o maior
    public void ordenar(int[] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length-1; j++) {
                if (list[j] > list[j + 1]) {
                    int aux = 0;
                    aux = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = aux;
                }
            }
        }

    }

    // cria a fila 3, adicionando os valores das listas usando o tamanho da lista 1 e 2
    public Fila getMerge(Fila fila1, Fila fila2) {
        Fila fila3 = new Fila(fila1.getTamanho() + fila2.getTamanho());
        Merge merge1 = new Merge();
        int[] list1 = new int[fila1.getTamanho()];
        int[] list2 = new int[fila2.getTamanho()];
        int[] list3 = new int[list1.length + list2.length];

        for (int i = 0; i < fila1.getTamanho(); i++) {
            if (!fila1.vazia()) {
                if (fila1.primeiro() != 0) {
                    list1[i] = fila1.primeiro();
                    fila1.remover();
                }
            }
        }
        for (int i = 0; i < fila2.getTamanho(); i++) {
             if (!fila2.vazia()) {
                 if (fila2.primeiro() != 0) {
                     list2[i] = fila2.primeiro();
                     fila2.remover();
                 }
             }
        }
        // ordena as listas.
        merge1.ordenar(list1);
        merge1.ordenar(list2);

        int i = 0, j = 0,k = 0;
        // coloca o valor da lista ordenada na fila 
        while (i < list1.length && j < list2.length){
            if(list1[i] < list2[j]){
                list3[k++] = list1[i++];
            }
            else {
                list3[k++] = list2[j++];
            }
        }
        while (i < list1.length){
            list3[k++] = list1[i++];
        }
        while (j < list2.length){
            list3[k++] = list2[j++];
        }
        // insero os valores na fila 3
        for (int x: list3){
            fila3.insere(x);
        }

        return fila3;
    }
}
