public class Main {

    public static void main(String args[]){

        ListaSE lista1 = new ListaSE();
        lista1.insere_primeiro(5);
        lista1.insere_primeiro(10);
        lista1.insere_primeiro(3);
        lista1.insere_primeiro(15);

        ListaSE lista2 = new ListaSE();
        lista2.insere_primeiro(8);
        lista2.insere_primeiro(27);
        lista2.insere_primeiro(22);
        lista2.insere_primeiro(12);

        Similaridade simi = new Similaridade();
        simi.calcular_similaridade(lista1,lista2);



        ListaSE lista3 = new ListaSE();
        lista3.insere_primeiro(1);
        lista3.insere_primeiro(2);


        ListaSE lista4 = new ListaSE();
        lista4.insere_primeiro(1);
        lista4.insere_primeiro(1);
        lista4.insere_primeiro(2);

        Intersecao i = new Intersecao();
        i.resultado(lista3,lista4);

    }
}
