public class Intersecao {
    private ListaSE lista_final;

    //inicia lista
    public void resultado(ListaSE lista1, ListaSE lista2){
        int aux;
        // loop de verificação
        lista_final = new ListaSE();
        while(!lista1.vazia()) {
            for (int i = 0; i < lista1.tamanho(); i++) {
                for (int j = 0; j < lista2.tamanho(); j++) {
                    aux = lista2.ultimo_elemento();

                    if (lista1.ultimo_elemento() == lista2.ultimo_elemento()) {
                        lista_final.insere_ordenado(lista2.retira_ultimo());
                        lista2.insere_primeiro(aux);
                        break;
                    }

                    else {
                        lista2.retira_ultimo();
                    }

                }

                lista1.retira_ultimo();
            }
        }

        if (!lista_final.vazia()) {
            this.lista_final.mostra_lista();
        }
        else {
            System.out.println("Nao possuem numeros em comum");
        }
    }
}
