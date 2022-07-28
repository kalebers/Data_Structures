public class Similaridade {
    private double resultado;

    public void calcular_similaridade(ListaSE lista1, ListaSE lista2){
        double somaxy = 0;
        double somax = 0;
        double somay = 0;
        while(!lista1.vazia()){
            somaxy += (lista1.ultimo_elemento() * lista2.ultimo_elemento());
            somax += Math.pow(lista1.retira_ultimo(), 2);
            somay += Math.pow(lista2.retira_ultimo(), 2);

        }

        this.resultado = somaxy/ Math.sqrt(somax * somay);

        System.out.println(this.resultado);
    }
}
