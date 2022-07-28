public class No {
    private int dado;
    private No proximo;


    public No (int d){
        this.dado = d;
    }

    public No (int d, No proximo){
        this.dado = d;
        this.proximo = proximo;
    }

    public void SetProximo(No proximo){
        this.proximo = proximo;
    }

    public No proximo(){
        return proximo;
    }

    public int dado(){
        return dado;
    }
}
