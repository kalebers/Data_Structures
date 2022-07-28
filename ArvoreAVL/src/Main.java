public class Main {
    public static void main(String[] args){
        Arvore teste1 = new Arvore();
        teste1.insere_elemento(9);
        teste1.insere_elemento(5);
        teste1.insere_elemento(10);
        teste1.insere_elemento(0);
        teste1.insere_elemento(6);
        teste1.insere_elemento(11);
        teste1.insere_elemento(-1);
        teste1.insere_elemento(1);
        teste1.insere_elemento(2);

        //System.out.println(teste1.existe_elemento(5));

        //teste1.imprime_inordem();
        //teste1.imprime_posordem();
        //teste1.imprime_preordem();

        teste1.remove_elemento(9);

        teste1.imprime_preordem();

    }
}
