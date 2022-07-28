public class No {
    private int altura;
    private int chave;
    private No esquerda, setDireita;

    public No (int info){
        this(info, null, null);
    }

    public No(int info, No esquerda, No direita){
        this.chave = info;
        this.esquerda = esquerda;
        this.setDireita = direita;
        this.altura = 0;
    }

    public int getAltura(){
        return this.altura;
    }

    public int getChave(){
        return this.chave;
    }

    public No getEsquerda(){
        return this.esquerda;
    }

    public No getDireita(){
        return this.setDireita;
    }

    public void setEsquerda(No esquerda){
        this.esquerda = esquerda;
    }

    public void setDireita(No setDireita) {
        this.setDireita = setDireita;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

}
