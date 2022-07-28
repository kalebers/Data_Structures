public class ListaSE {
    private No primeiro = null;
    private No ultimo = null;
    private int tamanho = 0;

    public boolean vazia(){
        if (this.primeiro == null){
            return true;
        }
        else {
            return false;
        }
    }

    public void insere_primeiro(int info){
        No novo = new No(info);
        if(this.primeiro == null){
            this.primeiro = novo;
            this.ultimo = novo;
            tamanho++;
        }
        else{
            novo.SetProximo(this.primeiro);
            this.primeiro = novo;
            tamanho++;
        }
    }

    public No insere_depois(No p, int info){
        No novo = new No(info);
        No aux = this.primeiro;
        while(aux != null) {
            if (aux.dado() == p.dado()) {
                novo.SetProximo(p.proximo());
                aux.SetProximo(novo);
                tamanho++;
                return novo;
            }
            aux = aux.proximo();
        }
        return null;
    }

    public void insere_ultimo(int info){
        if (this.vazia()){
            this.insere_primeiro(info);
            tamanho++;
        }
        else {
            this.ultimo = this.insere_depois(this.ultimo, info);
            tamanho++;
        }
    }

    public void insere_ordenado(int info){
        if(this.vazia()){
            insere_primeiro(info);
            tamanho++;
        }
        else{
            if (info <= this.primeiro.dado()){
                insere_primeiro(info);
                tamanho++;
            }
            else {
                if (info >= this.ultimo.dado()){
                    insere_ultimo(info);
                    tamanho++;
                }
                else {
                    No p = this.primeiro;
                    while(p.proximo().dado() < info){
                        p = p.proximo();
                        tamanho++;
                    }
                    insere_depois(p, info);
                    tamanho++;
                }
            }
        }
    }

    public int retira_primeiro(){
        if (this.vazia()){
            return 0;
        }

        No aux = this.primeiro;
        this.primeiro = aux.proximo();
        aux.SetProximo(null);
        tamanho--;
        return aux.dado();
    }

    public int retira_ultimo(){
        if (this.vazia()){
            return 0;
        }

        No aux = this.primeiro;
        while(aux != null) {

            if (aux.proximo() == null) {
                No aux3 = this.ultimo;
                this.primeiro = null;
                tamanho--;
                return aux3.dado();
            } else {
                if (aux.proximo().proximo() == null) {
                    No aux2 = this.ultimo;
                    this.ultimo = aux;
                    this.ultimo.SetProximo(null);
                    tamanho--;
                    return aux2.dado();
                }
                aux = aux.proximo();

            }
        }
        return 0;
    }

    public int retira_depois(No no){
        if (this.vazia()){
            return 0;
        }

        No aux = this.primeiro;
        while (aux != null){
            if (aux.dado() == no.dado()){
                No aux2 = aux.proximo();
                aux.SetProximo(null);
                tamanho--;
                return aux2.dado();
            }
            aux = aux.proximo();
        }
        return 0;
    }

    public int ultimo_elemento(){
        if (vazia()){
            return 0;
        }
        else{
            return this.ultimo.dado();
        }
    }

    public void mostra_lista(){
        No p = this.primeiro;
        while(p.dado() != 0){
            System.out.println(p.dado());
            if (p.proximo() == null){
                break;
            }
            p = p.proximo();
        }
    }

    public int tamanho(){
        return tamanho;
    }


}
