    public class Pilha {
    private int topo = -1;
    private int MAX;
    private Object[] dado;

    public Pilha(int tamanho){
        // inicia a pilha
        this.MAX = tamanho;
        this.dado = new Object[tamanho];
    }

    public Object topo(){
        // utiliza a funçao vazia para verificar se existe algum elemento no vetor 
        if (vazia())
            return 0;
        else{
            return this.dado[topo];
        }
    }

    public boolean vazia(){
        // verifica se o topo é >= 0, então exisem itens na pilha
        if(topo == -1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean cheia(){
        // verifica se o topo da pilha está no limite máximo
        if(topo == (MAX - 1)){
            return true;
        }
        else{
            return false;
        }
    }

    public void empilha(int elemento){
        if (topo < MAX){
            this.topo++;
            this.dado[topo] = elemento;
        }
        else {
            System.out.println("Pilha Cheia!");
        }
    }

    public void empilha(char caractere){
        // coloca o elemento no topo da pilha
        if (topo < MAX){
            this.topo++;
            this.dado[topo] = caractere;
        }
        else {
            System.out.println("Pilha Cheia!");
        }
    }

    public Object desempilha(){
        // retorna o ultimo elemento da pilha se não estiver vazia
        if (topo > -1){
            return this.dado[this.topo--];
        }
        else{
            return 0;
        }

    }
}
