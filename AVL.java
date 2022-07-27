package SEMANA09.PBL;
class No {
    private int dado;           // armazena a chave
    private No pai;             // ponteiro para o nó pai
    private No esquerda;        // ponteiro para o filho da esquerda
    private No direita;         // ponteiro para o filho da direita
    private int FB;             // fator de balanço/equilibrio do nó
    public No(int dado) {
        this.dado = dado;
        this.pai = null;
        this.esquerda = null;
        this.direita = null;
        this.FB = 0;
    }
    public int getDado() {return this.dado;}
    public void setDado(int dado) {this.dado = dado;}
    public No getPai() {return this.pai;}
    public void setPai(No pai) {this.pai = pai;}
    public No getEsquerda() {return this.esquerda;}
    public void setEsquerda(No esquerda) {this.esquerda = esquerda;}
    public No getDireita() {return this.direita;}
    public void setDireita(No direita) {this.direita = direita;}
    public int getFB() {return this.FB;}
    public void setFB(int FB) {this.FB = FB;}
}
public class AVL {
    private No raiz;
    public AVL() {
        this.raiz = null;
    }
    /*imprimir a estrutura em árvore na tela*/
    private void imprimirIndentado(No ptrAtual, String indenta, boolean ultimo) {
        if (ptrAtual != null) {
            System.out.print(indenta);
            if (ultimo) {
                System.out.print("D----");
                indenta += "     ";
            } else {
                System.out.print("E----");
                indenta += "|    ";
            }
            System.out.println(ptrAtual.getDado() + "(FB = " + ptrAtual.getFB() + ")");
            imprimirIndentado(ptrAtual.getEsquerda(), indenta, false);
            imprimirIndentado(ptrAtual.getDireita(), indenta, true);
        }
    }
    public int altura(){
        return altura(this.raiz);
    }

    public int altura(No no){
        // implementar
        // incluir aqui a lógica de cálculo para a altura
        // de uma árvore

        // Se o no for nulo ou nao tiver filhos, retorna 0
        if(no == null || (no.getEsquerda() == null && no.getDireita() == null))
            return 0;
        // Busca a maior altura entre os filhos e adiciona 1 a ela
        return Math.max(altura(no.getEsquerda()), altura(no.getDireita())) + 1;
    }

    // Função que verifica se um elemento com valor N existe na árvore
    // Utiliza busca, se retornar nulo nao existe, caso contrario existe
    public boolean existe(int n) {
        return null != buscar(n);
    }

    // buscar na árvore a chave k e retornar o nó correspondente
    public No buscar(int k) {
        return buscar(this.raiz, k);
    }

    /*Busca de uma chave na estrutura de dados -- árvore binária */
    /*Se sucesso ou fracasso retorna o nó atual*/
    private No buscar(No no, int chave) {
        if (no == null || chave == no.getDado()) {
            return no;
        }
        if (chave < no.getDado()) {
            return buscar(no.getEsquerda(), chave);
        }
        return buscar(no.getDireita(), chave);
    }
    // Remover o nó (identificado pela chave) da árvore
    No remover(int chave) {
        return remover(this.raiz, chave);
    }

    private No remover(No no, int chave) {
        // verifica incialmente se nó raiz (ou no) é null
        if (no == null) {
            return no;
        } else if (chave < no.getDado()) {
            // processar a subárvore a esquerda
            No temp = no.getEsquerda();
            no.setEsquerda(remover(no.getEsquerda(), chave));
            // Apos remocao, se existir no a esquerda, atualiza o pai
            if (no.getEsquerda() != null) {
                no.getEsquerda().setPai(no);
            }
            // Se existia no a esquerda mas nao apos a remocao ou
            // se mudou a altura da arvore esquerda, atualiza FB
            // Se abs(temp.FB) era 1 passou a ser 0 ou 2 houve mudança de altura
            // Sera 0 e passou a ser -1 ou 1, a altura é a mesma
            if ((no.getEsquerda() == null && temp != null) || (Math.abs(temp.getFB()) > 0 && Math.abs(no.getEsquerda().getFB()) != 1)) {
                no.setFB(no.getFB()+1);
                // Se abs(FB) > 1, o nó está desbalanceado
                // entao reequilibra e retorna o nó correto
                // após a rotação
                if (Math.abs(no.getFB()) > 1) {
                    reequilibrar(no);
                    return no.getPai();
                }
            }
        } else if (chave > no.getDado()) {
            // processar a subárvore a direita
            No temp = no.getDireita();
            no.setDireita(remover(no.getDireita(), chave));
            // Apos remocao, se existir no a direita, atualiza o pai
            if (no.getDireita() != null) {
                no.getDireita().setPai(no);
            }
            // Se existia no a direita mas nao apos a remocao ou
            // se mudou a altura da arvore direita, atualiza FB
            // Se abs(temp.FB) era 1 passou a ser 0 ou 2 houve mudança de altura
            // Sera 0 e passou a ser -1 ou 1, a altura é a mesma
            if ((no.getDireita() == null && temp != null) || (Math.abs(temp.getFB()) > 0 && Math.abs(no.getDireita().getFB()) != 1)) {
                no.setFB(no.getFB()-1);
                // Se abs(FB) > 1, o nó está desbalanceado
                // entao reequilibra e retorna o nó correto
                // após a rotação
                if (Math.abs(no.getFB()) > 1) {
                    reequilibrar(no);
                    return no.getPai();
                }
            }
        } else {
            // A chave foi encontrada, agora delete-a
            // Caso 1: não nenhum filho
            if (no.getEsquerda() == null && no.getDireita() == null) {
                no = null;
            } // Caso 2: tem apenas um filho
            else if (no.getEsquerda() == null) {
                no = no.getDireita();
            } else if (no.getDireita() == null) {
                no = no.getEsquerda();
            } // Caso 3: Tem os dois filhos
            else {
                No menorDir = buscarMenorChave(no.getDireita());
                No temp = no.getDireita();
                no.setDado(menorDir.getDado());
                no.setDireita(remover(no.getDireita(), menorDir.getDado()));
                // Se existia no a direita mas nao apos a remocao ou
                // se mudou a altura da arvore direita, atualiza FB
                // Se abs(temp.FB) era 1 passou a ser 0 ou 2 houve mudança de altura
                // Sera 0 e passou a ser -1 ou 1, a altura é a mesma
                if ((no.getDireita()== null && temp != null) || (Math.abs(temp.getFB()) > 0 && Math.abs(no.getDireita().getFB()) != 1)) {
                    no.setFB(no.getFB()-1);
                    // Se abs(FB) > 1, o nó está desbalanceado
                    // entao reequilibra e retorna o nó correto
                    // após a rotação
                    if (Math.abs(no.getFB()) > 1) {
                        reequilibrar(no);
                        return no.getPai();
                    }
                }
            }
        }
        // implementar
        // (se necessário) a lógica de balanceamento ou
        // de reequilibrio
        return no;
    }
    // Atualizar o fator (FB) de equilibrio/balanço do nó
    private void atualizarFatorDeEquilibrio(No no) {
        if (no.getFB() < -1 || no.getFB() > 1) {
            reequilibrar(no);
            return;
        }
        if (no.getPai() != null) {
            if (no == no.getPai().getEsquerda()) {
                no.getPai().setFB(no.getPai().getFB()-1);
            }
            if (no == no.getPai().getDireita()) {
                no.getPai().setFB(no.getPai().getFB()+1);
            }
            if (no.getPai().getFB() != 0) {
                atualizarFatorDeEquilibrio(no.getPai());
            }
        }
    }
    // Reequilibrar a árvore
    void reequilibrar(No no) {
        if (no.getFB() > 0) {
            if (no.getDireita().getFB() < 0) {
                rotacaoDireita(no.getDireita());
                rotacaoEsquerda(no);
            } else {
                rotacaoEsquerda(no);
            }
        } else if (no.getFB() < 0) {
            if (no.getEsquerda().getFB() > 0) {
                rotacaoEsquerda(no.getEsquerda());
                rotacaoDireita(no);
            } else {
                rotacaoDireita(no);
            }
        }
    }

    // Travessia Pre-Ordem
    // Nó . Subárvore Esquerda . Subárvore Direita
    public void preOrdem() {
        preOrdem(this.raiz);
    }
    private void preOrdem(No no) {
        if (no != null) {
            System.out.print(no.getDado() + " ");
            preOrdem(no.getEsquerda());
            preOrdem(no.getDireita());
        }
    }
    // Travessia Em-Ordem
    // Subárvore Esquerda . Nó . Subárvore Direita
    public void emOrdem() {
        emOrdem(this.raiz);
    }
    private void emOrdem(No no) {
        if (no != null) {
            emOrdem(no.getEsquerda());
            System.out.print(no.getDado() + " ");
            emOrdem(no.getDireita());
        }
    }
    // Travessia pós-Ordem
    // Subárvore Esquerda . Nó . Subárvore Direita
    public void posOrdem() {
        posOrdem(this.raiz);
    }
    private void posOrdem(No no) {
        if (no != null) {
            posOrdem(no.getEsquerda());
            posOrdem(no.getDireita());
            System.out.print(no.getDado() + " ");
        }
    }
    // encontra o nó com a menor chave
    public No buscarMenorChave(No no) {
        while (no.getEsquerda() != null) {
            no = no.getEsquerda();
        }
        return no;
    }
    // encontra o nó com a maior chave
    public No buscarMaiorChave(No no) {
        while (no.getDireita() != null) {
            no = no.getDireita();
        }
        return no;
    }
    // encontrar/buscar o sucessor de um dado nó
    public No sucessor(No x) {
        // Se a subárvore direita não for nula,
        // o sucessor será o nó mais à esquerda na subárvore direita
        if (x.getDireita() != null) {
            return buscarMenorChave(x.getDireita());
        }
        // Senão será o menor ancestral de x cujo
        // filho da esquerda também é um ancestral de x.
        No y = x.getPai();
        while (y != null && x == y.getDireita()) {
            x = y;
            y = y.getPai();
        }
        return y;
    }
    // encontrar/buscar o antecessor de um dado nó
    public No antecessor(No x) {
        // se a subárvore esquerda não for nula, o antecessor
        // será o nó mais à direita na subárvore esquerda
        if (x.getEsquerda() != null) {
            return buscarMaiorChave(x.getEsquerda());
        }
        No y = x.getPai();
        while (y != null && x == y.getEsquerda()) {
            x = y;
            y = y.getPai();
        }
        return y;
    }
    // Rotação a esquerda do nó x
    // Testado com inserção e remoção de chaves
    void rotacaoEsquerda(No x) {
        No y = x.getDireita();
        x.setDireita(y.getEsquerda());
        if (y.getEsquerda() != null) {
            y.getEsquerda().setPai(x);
        }
        y.setPai(x.getPai());
        if (x.getPai() == null) {
            this.raiz = y;
        } else if (x == x.getPai().getEsquerda()) {
            x.getPai().setEsquerda(y);
        } else {
            x.getPai().setDireita(y);
        }
        y.setEsquerda(x);
        x.setPai(y);
        // atualiza fator de equilibrio/balanceamento
        x.setFB(x.getFB() - 1 - Math.max(0, y.getFB()));
        y.setFB(y.getFB() - 1 + Math.min(0, x.getFB()));
    }
    // Rotação a direita do nó x
    // Testado com inserção e remoção de chaves
    void rotacaoDireita(No x) {
        No y = x.getEsquerda();
        x.setEsquerda(y.getDireita());
        if (y.getDireita() != null) {
            y.getDireita().setPai(x);
        }
        y.setPai(x.getPai());
        if (x.getPai() == null) {
            this.raiz = y;
        } else if (x == x.getPai().getDireita()) {
            x.getPai().setDireita(y);
        } else {
            x.getPai().setEsquerda(y);
        }
        y.setDireita(x);
        x.setPai(y);
        // atualiza fator de equilibrio/balanceamento
        x.setFB(x.getFB() + 1 - Math.min(0, y.getFB()));
        y.setFB(y.getFB() + 1 + Math.max(0, x.getFB()));
    }
    // inserir a chave da árvore em sua posição apropriada
    public void inserir(int chave) {
        No no = new No(chave);
        No p = null;
        No f = this.raiz;
        // Encontrar a posição para inserior o novo nó/chave (ignorando se já existe algum elemento)
        while (f != null) {
            p = f;
            if (no.getDado() < f.getDado()) {
                f = f.getEsquerda();
            } else if (no.getDado() > f.getDado()) {
                f = f.getDireita();
            } else {
                // Ignora repeticoes
                return;
            }
        }
        // p é o pai de f (que é o filho de p).
        no.setPai(p);
        if (p == null) {
            // adicionei o this para ficar mais claro
            this.raiz = no;
        } else if (no.getDado() < p.getDado()) {
            p.setEsquerda(no);
        } else {
            p.setDireita(no);
        }
        // atulizar o fator de equilibrio do nó se necessário
        atualizarFatorDeEquilibrio(no);
    }
    // Imprimir a estrutura em árvore
    public void imprimir() {
        imprimirIndentado(this.raiz, "", true);
    }
    public static void main(String[] args) {

        // Testando Inserção
        System.out.println("TESTANDO INSERCAO");
        AVL avl = new AVL();
        int[] num_list = {34,45,12,67,10,89,98,35,46,73,21,40};
        for (int i : num_list) {
            System.out.println("-> Adicionando " + i);
            avl.inserir(i); 
            avl.imprimir();
            System.out.print("Pre Ordem: ");
            avl.preOrdem();
            System.out.print("\nEm Ordem: ");
            avl.emOrdem();
            System.out.print("\nPos Ordem: ");
            avl.posOrdem();
            System.out.println("\nAltura: "+avl.altura());
            System.out.println("\n");
        }

        // Testando funcoes busca, existe, sucessor, antecessor
        int n;
        n = 46;
        System.out.println("Na árvore existe o número " + n + "?\n" + (avl.existe(n) ? "Sim\n" : "Nao\n"));
        n = 31;
        System.out.println("Na árvore existe o número " + n + "?\n" + (avl.existe(n) ? "Sim\n" : "Nao\n"));

        No k,l;
        k = avl.buscar(67);
        l = avl.buscarMaiorChave(k);
        System.out.println("A maior chave a partir de " + k.getDado() + " é: " + l.getDado() );
        l = avl.sucessor(k);
        System.out.println("O sucessor de " + k.getDado() + " é: " + l.getDado());

        k = avl.buscar(34);
        l = avl.buscarMenorChave(k);
        System.out.println("A menor chave a partir de " + k.getDado() + " é: " + l.getDado() );
        l = avl.antecessor(k);
        System.out.println("O antecessor de " + k.getDado() + " é: " + l.getDado());

        System.out.println("");




        // Testando Remoção
        System.out.println("TESTANDO REMOCAO");
        avl = new AVL();
        num_list = new int[] {50,30,20,10,70,80,90,40,60,99,-70,-80,-20,-10,-90};
        for (int i : num_list) {
            if (i >= 0) {
                System.out.println("-> Adicionando " + i);
                avl.inserir(i);
            } else {
                System.out.println("-> Removendo " + Math.abs(i));
                avl.remover(Math.abs(i));
            }
            avl.imprimir();
            System.out.print("Pre Ordem: ");
            avl.preOrdem();
            System.out.print("\nEm Ordem: ");
            avl.emOrdem();
            System.out.print("\nPos Ordem: ");
            avl.posOrdem();
            System.out.println("\nAltura: "+avl.altura());
            System.out.println("\n");
        }
    }
}
