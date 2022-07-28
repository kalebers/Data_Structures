public class Arvore {
    private No raiz = null;

    public Arvore(){
        this.raiz = null;
    }

    //retorna a altura

    private static int altura(No no){
        if (no == null){
            return -1;
        }
        else {
            return no.getAltura();
        }
    }

    public void alturaArvore(){
        altura(this.raiz);
    }



    //retorna o maior valor entre esquerda e direita

    private static int maximo(int esquerda, int direita){
        if (esquerda > direita){
            return esquerda;
        }
        else {
            return direita;
        }
    }

    //retorna o valor de balanceamento da arvore

    private int fator(No no){
        return altura(no.getEsquerda()) - altura(no.getDireita());
    }

    // Remove o elemento selecionado

    public void remove_elemento(int elemento){
         this.raiz = remove_elemento(this.raiz, elemento);
    }

    private No remove_elemento(No no, int elemento){

        if (no == null){
            return null;
        }

        No leftchild = no.getEsquerda();
        No rightchild = no.getDireita();
        int info_atual = no.getChave();

        if (elemento == info_atual){

            if (leftchild == null && rightchild == null){
                return null;
            }
            else if (leftchild == null){
                no = null;
                return rightchild;
            }
            else if (rightchild == null){
                no = null;
                return leftchild;
            }
            else {
                No menor = Minimo(rightchild);

                no.setChave(menor.getChave());

                no.setDireita(remove_elemento(no.getDireita(), menor.getChave()));
            }


        }

        else if (elemento < no.getChave()){
            no.setEsquerda(remove_elemento(leftchild, elemento));
        }
        else if (elemento > no.getChave()){
            no.setDireita(remove_elemento(rightchild, elemento));
        }


        return balanciar(no);
    }


    private No Minimo(No no){
        No aux = no;
        while (aux.getEsquerda() != null){
            aux = aux.getEsquerda();
        }
        return aux;
    }


    //insere elemento

    public void insere_elemento(int elemento){
        this.raiz = insere_elemento(elemento, raiz);
    }

    private No insere_elemento(int elemento, No no){
        if (no == null){
            no = new No(elemento, null, null);
        }

        else if (elemento < no.getChave()){
            no.setEsquerda(insere_elemento(elemento, no.getEsquerda()));
        }

        else if(elemento > no.getChave()){
            no.setDireita(insere_elemento(elemento, no.getDireita()));
        }
        no = balanciar(no);
        return no;

    }


    // balanceia a arvore

    private No balanciar(No no){
        if (fator(no) ==  2){
            if (fator(no.getEsquerda()) > 0){
                no = RotacaoDireita(no);
            }
            else {
                no = DuplaRotacaoDireita(no);
            }
        }

        else if (fator(no) == -2){
            if (fator(no.getDireita()) < 0){
                no = RotacaoEsquerda(no);
            }
            else {
                no = DuplaRotacaoEsquerda(no);
            }
        }

        no.setAltura(maximo(altura(no.getEsquerda()), altura(no.getDireita())) + 1);
        return no;
    }

    // rotacao simples a direita

    private No RotacaoDireita(No no2){
        No no1 = no2.getEsquerda();
        no2.setEsquerda(no1.getDireita());
        no1.setDireita(no2);
        no2.setAltura(maximo(altura(no2.getEsquerda()), altura(no2.getDireita())) + 1);
        no1.setAltura(maximo(altura(no1.getEsquerda()), no2.getAltura()) + 1);
        return no1;
    }

    // rotacao simples a esquerda

    private No RotacaoEsquerda(No no1){
        No no2 = no1.getDireita();
        no1.setDireita(no2.getEsquerda());
        no2.setEsquerda(no1);
        no1.setAltura(maximo(altura(no1.getEsquerda()), altura(no1.getDireita())) + 1);
        no2.setAltura(maximo(altura(no2.getDireita()), no1.getAltura()) + 1);
        return no2;
    }


    // rotacao dupla a direita

    private No DuplaRotacaoDireita(No no3){
        no3.setEsquerda(RotacaoEsquerda(no3.getEsquerda()));
        return RotacaoDireita(no3);
    }

    // rotacao dupla a esquerda

    private No DuplaRotacaoEsquerda(No no1){
        no1.setDireita(RotacaoDireita(no1.getDireita()));
        return RotacaoEsquerda(no1);
    }

    // verifica se existe o elemento

    public boolean existe_elemento(int elemento){
        return existe_elemento(raiz, elemento);
    }

    private boolean existe_elemento(No no, int elemento){
        while (no != null){
            if (elemento == no.getChave()){
                return true;
            }
            else if (elemento < no.getChave()){
                no = no.getEsquerda();
            }
            else {
                no = no.getDireita();
            }

        }
        return false;
    }


    // imprime os elementos da árvore em preordem

    public void imprime_preordem(){
        imprime_preordem(raiz);
    }

    private void imprime_preordem(No no){
        if (no != null){
            System.out.println(no.getChave() + " ");
            imprime_preordem(no.getEsquerda());
            imprime_preordem(no.getDireita());
        }
    }

    //imprime os elementos da árvore em inordem

    public void imprime_inordem(){
        imprime_inordem(raiz);
    }

    private void imprime_inordem(No no){
        if (no != null){
            imprime_inordem(no.getEsquerda());
            System.out.println(no.getChave() + " ");
            imprime_inordem(no.getDireita());
        }
    }

    //imprime os elementos da árvore em posordem

    public void imprime_posordem(){
        imprime_posordem(raiz);
    }

    private void imprime_posordem(No no){
        if (no != null){
            imprime_posordem(no.getEsquerda());
            imprime_posordem(no.getDireita());
            System.out.println(no.getChave() + " ");
        }
    }
}
