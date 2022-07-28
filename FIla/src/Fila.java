public class Fila {
    private int[] dado;
    private int primeiro = 0;
    private int ultimo = -1;
    private int MAX;
    private int tamanho = 0;

    // inicia fila
    public Fila(int tamanho){
        this.MAX = tamanho;
        dado = new int[tamanho];
    }

    // verifica se a fila está cheia
    public boolean cheia(){
        if (this.tamanho == this.MAX){
            return true;
        }
        else{
            return false;
        }
    }
    // verifica se a fila está vazia
    public boolean vazia(){
        if (this.tamanho == 0){
            return true;
        }
        else{
            return false;
        }
    }
    // vai adicionando valores até a fila estar cheia
    public void insere(int E){
        if (this.ultimo < this.MAX-1){
            this.ultimo++;
            this.tamanho++;
            this.dado[ultimo] = E;
        }
        else if (this.ultimo == MAX-1){
                tamanho++;
                ultimo = 0;
                this.dado[ultimo] = E;

        }
        else {
            System.out.println("Fila cheia!");
        }
    }
    // remove os valores do ultimo e do "meio", recebedo 1 e 0.
    public int remover(){
        if (vazia()){
            primeiro = 0;
            return 0;
        }
        if (!vazia() && this.primeiro == MAX- 1){
            tamanho--;
            dado[primeiro] = 0;
            primeiro = 0;
            return 1;
        }
        if(!vazia()){
            tamanho--;
            dado[primeiro] = 0;
            primeiro++;
            return 1;
        }
        else{
            return 0;
        }
    }
    // pega o tamanho da fila
    public int getTamanho(){
        return dado.length;
    }
    // se estiver vazio, o primeiro retorna 0, caso contrariom retorna o valor.
    public int primeiro(){
        if (vazia()){
            return 0;
        }
        else{
            return this.dado[primeiro];
        }
    }
    // se o ultimo estiver vazio retorna 0, se naõ, retorna o valor.
    public int ultimo(){
        if (vazia()){
            return 0;
        }
        else{
            return this.dado[ultimo];
        }
    }


    public void printFila(){
        for (int X: this.dado){
            System.out.println(X);
        }
    }


}
